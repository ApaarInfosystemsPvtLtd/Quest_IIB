package com.pmli.iib.service;

import com.pmli.iib.model.log.GetQueryDetails;
import com.pmli.iib.model.log.QueryUploadDetails;
import com.pmli.iib.model.queryfetch.QueryDataResponse;
import com.pmli.iib.model.response.Response;
import com.pmli.iib.model.upload.Query;
import com.pmli.iib.model.upload.UploadFile;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface QuestIIBService {

    Response queryDetails(Query query) throws JAXBException;
    UploadFile getQueryDataResponse(Query query, QueryUploadDetails queryUploadDetails) throws JAXBException;
    List<QueryDataResponse> getQueryDetails(String transactionReferenceNo, GetQueryDetails getQueryDetails);

}
