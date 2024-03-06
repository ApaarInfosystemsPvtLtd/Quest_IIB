package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class QueryType {

    private final Logger logger = LoggerFactory.getLogger(QueryType.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        isMandatory(errorList, details);
        isCorrectLength(errorList, details);
    }

    private void isMandatory(List<QuestValidationErrorList> errorList, Details details) {
        String queryType = details.getQueryType();
        if ("".equalsIgnoreCase(queryType.trim())) {
            errorList.add(new QuestValidationErrorList(ErrorCode.POLICY_NUMBER_ERROR_CODE.getValue(), "QueryType is mandatory", queryType));
            logger.info("{}::QueryType is mandatory::{}",ErrorCode.POLICY_NUMBER_ERROR_CODE.getValue(),queryType);
        }
    }

    private void isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        String queryType = details.getQueryType();
        String [] queryTypeList = {"1","2"};
        if (!Arrays.asList(queryTypeList).contains(queryType)) {
            errorList.add(new QuestValidationErrorList(ErrorCode.POLICY_NUMBER_ERROR_CODE.getValue(), "QueryType value should be 1 or 2. QueryType provided is", queryType));
            logger.info("{}::QueryType value should be 1 or 2. QueryType provided is::{}",ErrorCode.POLICY_NUMBER_ERROR_CODE.getValue(),queryType);
        }
    }
}
