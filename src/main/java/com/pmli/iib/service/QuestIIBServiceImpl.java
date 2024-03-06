package com.pmli.iib.service;

import com.pmli.iib.config.IibClient;
import com.pmli.iib.constant.StatusCode;
import com.pmli.iib.model.QuestValidationErrorList;
import com.pmli.iib.model.log.GetQueryDetails;
import com.pmli.iib.model.log.QueryUploadDetails;
import com.pmli.iib.model.log.QuestIibState;
import com.pmli.iib.model.queryfetch.QueryDataResponse;
import com.pmli.iib.model.response.InputValidationError;
import com.pmli.iib.model.response.Response;
import com.pmli.iib.model.upload.Query;
import com.pmli.iib.model.upload.UploadFile;
import com.pmli.iib.repo.QuestStateRepo;
import com.pmli.iib.util.ValidatePolicy;
import com.pmli.iib.util.XmlConverter;
import com.pmli.iib.util.ZipConverter;
import com.pmli.iib.wsdlbean.GetQueryDataResponse;
import com.pmli.iib.wsdlbean.QueryUploadResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.xml.bind.JAXBException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class QuestIIBServiceImpl implements QuestIIBService {

    private final Logger logger = LoggerFactory.getLogger(QuestIIBServiceImpl.class);

    @Autowired
    IibClient iibClient;

    @Autowired
    XmlConverter xmlConverter;

    @Autowired
    ZipConverter zipConverter;

    @Autowired
    ValidatePolicy validatePolicy;

    @Autowired
    QuestStateRepo questStateRepo;

    @Override
    public Response queryDetails(Query query) throws JAXBException {
        logger.info("Query Details Request Payload::{}",query);

        Response response = new Response();

        QuestIibState questIibState = new QuestIibState();
        questIibState.setPolicyNumber(query.getDetails().getPolicyNumber());

        QueryUploadDetails queryUploadDetails = new QueryUploadDetails();
        queryUploadDetails.setRequest(query);
        GetQueryDetails getQueryDetails = new GetQueryDetails();

        List<QuestValidationErrorList> errorList = this.validatePolicy.validateDetail(query.getDetails());
        if (!hasError(errorList)) {
            logger.info("Validations Are Perfect::");
            UploadFile uploadFile = getQueryDataResponse(query,queryUploadDetails);
            logger.info("GetQueryDataResponse::UploadFile::{}",uploadFile);
            if(uploadFile.getFileHeader()!=null && "Success".equalsIgnoreCase(uploadFile.getFileHeader().getStatus())){

                queryUploadDetails.setQueryUploadResponse(uploadFile);
                queryUploadDetails.setStatus(StatusCode.SUCCESS_MESSAGE.getValue());

                String transactionReferenceNo = uploadFile.getFileHeader().getTransactionID();
                logger.info("transactionReferenceNo::{}",transactionReferenceNo);
                List<QueryDataResponse> queryDataResponseList = getQueryDetails(transactionReferenceNo,getQueryDetails);
                logger.info("GetQueryDetails::QueryDataResponse::{}",queryDataResponseList);
                getQueryDetails.setTransactionReferenceNo(transactionReferenceNo);

                if(!queryDataResponseList.isEmpty()){
                    getQueryDetails.setQueryDataResponse(queryDataResponseList);
                    getQueryDetails.setStatus(StatusCode.SUCCESS_MESSAGE.getValue());
                    questIibState.setGetQueryDetails(getQueryDetails);
                    questIibState.setStatus(StatusCode.SUCCESS_MESSAGE.getValue());
                    response.setQueryDataResponse(queryDataResponseList);
                    response.setTransactionId(transactionReferenceNo);
                    response.setStatus(StatusCode.SUCCESS_MESSAGE.getValue());
                }else {
                    getQueryDetails.setStatus(StatusCode.FAIL_MESSAGE.getValue());
                    questIibState.setGetQueryDetails(getQueryDetails);
                    questIibState.setStatus(StatusCode.FAIL_MESSAGE.getValue());
                    response.setMessage("Couldn't find any policy in QUEST IIB for TransactionRefNo "+transactionReferenceNo);
                    response.setTransactionId(transactionReferenceNo);
                    response.setStatus(StatusCode.FAIL_MESSAGE.getValue());
                }
            }else{

                queryUploadDetails.setQueryUploadResponse(uploadFile);
                queryUploadDetails.setStatus(StatusCode.FAIL_MESSAGE.getValue());
                questIibState.setStatus(StatusCode.FAIL_MESSAGE.getValue());
                response.setMessage(uploadFile.getFileHeader().getRemarks());
                response.setStatus(StatusCode.FAIL_MESSAGE.getValue());
            }
        }else {
            logger.info("Contains some Validations::");
            questIibState.setStatus(StatusCode.FAIL_MESSAGE.getValue());
            queryUploadDetails.setErrors(errorList);
            queryUploadDetails.setStatus(StatusCode.FAIL_MESSAGE.getValue());
            InputValidationError inputValidationError = new InputValidationError();
            inputValidationError.setErrors(errorList);
            response.setInputValidationError(inputValidationError);
            response.setMessage("Please check input and try again.");
            response.setStatus(StatusCode.FAIL_MESSAGE.getValue());
        }
        questIibState.setQueryUploadDetails(queryUploadDetails);
        questStateRepo.save(questIibState);

        logger.info("Query Details Response Payload::{}",response);
        return response;
    }

    @Override
    public UploadFile getQueryDataResponse(Query query, QueryUploadDetails queryUploadDetails) throws JAXBException {
        logger.info("################ Inside getQueryDataResponse Method::{} ################", LocalDateTime.now());
        String xmlRequestString = xmlConverter.queryDataUploadMarshal(query);
        String encodedString = zipConverter.encodedFileContent(xmlRequestString);
        queryUploadDetails.setXmlData(xmlRequestString);
        queryUploadDetails.setEncodedZIP(encodedString);
        QueryUploadResponse queryUploadResponse = iibClient.queryDataUploadResponse(encodedString);
        logger.info("################ Outside getQueryDataResponse Method::{} ################", LocalDateTime.now());
        return xmlConverter.queryDataResponseUnmarshal(queryUploadResponse);
    }

    @Override
    public List<QueryDataResponse> getQueryDetails(String transactionReferenceNo, GetQueryDetails getQueryDetails) {
        logger.info("################ Inside getQueryDetails Method::{} ################", LocalDateTime.now());
        List<QueryDataResponse> queryDataResponseList = new ArrayList<>();
        try {
            GetQueryDataResponse getQueryDataResponse = iibClient.getQueryDataResponse(transactionReferenceNo);
            byte[] responseGetQueryDataResult = getQueryDataResponse.getGetQueryDataResult();

            if (responseGetQueryDataResult != null) {
                String queryDataResultString = new String(responseGetQueryDataResult);
                getQueryDetails.setEncodedResponse(queryDataResultString);
                List<String> convertedList = Stream.of(queryDataResultString.split("\r\n"))
                        .filter(s -> !"".equalsIgnoreCase(s.trim()))
                        .collect(Collectors.toList());
                queryDataResponseList = responseParser(convertedList);
            }
        }catch (Exception e){
            logger.error("Error Occurred getQueryDetails::{}", Arrays.asList(e.getStackTrace()));
        }
        logger.info("################ Outside getQueryDetails Method::{} ################", LocalDateTime.now());
        return queryDataResponseList;
    }

    private boolean hasError(List<QuestValidationErrorList> validate) {
        boolean hasErrorFlag = false;
            if (!validate.isEmpty()) {
                hasErrorFlag = true;
            }
        return hasErrorFlag;
    }

    private List<QueryDataResponse> responseParser(List<String> convertedList){
        logger.info("################ Inside responseParser Method::{} ################", LocalDateTime.now());
        List<QueryDataResponse> queryDataResponseList = new ArrayList<>();

        // Delete First element CSV File Headers.
        if(!convertedList.isEmpty()) {
            convertedList.remove(0);

            for (String s : convertedList) {
                String[] messageArray = s.split(",", -1);

                QueryDataResponse queryDataResponse = new QueryDataResponse();
                queryDataResponse.setInputProposalPolicyNo(messageArray[0]);
                queryDataResponse.setQuestDBNo(messageArray[1]);
                queryDataResponse.setInputMatchingParameter(messageArray[2]);
                queryDataResponse.setQuestDoPDoC(messageArray[3]);
                queryDataResponse.setQuestSumAssured(messageArray[4]);
                queryDataResponse.setQuestPolicyStatus(messageArray[5]);
                queryDataResponse.setQuestDateOfExit(messageArray[6]);
                queryDataResponse.setQuestDateOfDeath(messageArray[7]);
                queryDataResponse.setQuestCauseOfDeath(messageArray[8]);
                queryDataResponse.setQuestRecordLastUpdated(messageArray[9]);
                queryDataResponse.setQuestEntityCautionStatus(messageArray[10]);
                queryDataResponse.setQuestIntermediaryCautionStatus(messageArray[11]);
                queryDataResponse.setQuestCompanyNumber(messageArray[12]);
                queryDataResponse.setIsNegativeMatch(messageArray[13]);
                queryDataResponse.setProductType(messageArray[14]);
                queryDataResponse.setLinkedNonLinked(messageArray[15]);
                queryDataResponse.setMedicalNonMedical(messageArray[16]);
                queryDataResponse.setWhetherStandardLife(messageArray[17]);
                queryDataResponse.setReasonForDecline(messageArray[18]);
                queryDataResponse.setReasonForPostpone(messageArray[19]);
                if (messageArray.length > 20) {
                    queryDataResponse.setReasonForRepudiation(messageArray[20]);
                } else {
                    queryDataResponse.setReasonForRepudiation("");
                }
                queryDataResponseList.add(queryDataResponse);
            }
        }
        logger.info("################ Outside responseParser Method::{} ################", LocalDateTime.now());
        return queryDataResponseList;
    }
}


