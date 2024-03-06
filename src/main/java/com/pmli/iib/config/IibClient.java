package com.pmli.iib.config;

import com.pmli.iib.wsdlbean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.transport.WebServiceConnection;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpUrlConnection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@Component
public class IibClient{

    private static final Logger logger = LoggerFactory.getLogger(IibClient.class);

    @Autowired
    WebServiceTemplate iibTemplate;

    @Value("${quest.iib.soap.username}")
    private String username;

    @Value("${quest.iib.soap.password}")
    private String password;

    @Value("${quest.apiGateway.X-IBM-Client-Secret}")
    private String clientSecret;

    @Value("${quest.apiGateway.X-IBM-Client-Id}")
    private String clientId;

    @Value("${quest.soap.getQueryDatActionUri}")
    private String getQueryActionUri;

    @Value("${quest.soap.queryDataUploadActionUri}")
    private String queryDataUploadActionUri;

    public GetQueryDataResponse getQueryDataResponse(String transactionReferenceNo) {
        GetQueryDataResponse getQueryDataResponse = new GetQueryDataResponse();
        try {

            GetQueryData getQueryData = new GetQueryData();
            getQueryData.setTransactionRefereceNo(transactionReferenceNo);

            getQueryDataResponse = (GetQueryDataResponse) iibTemplate.marshalSendAndReceive(getQueryData,
                    message -> {
                        try {
                            // get the header from the SOAP message
                            SoapHeader soapHeader = ((SoapMessage) message).getSoapHeader();

                            TransportContext transportContext = TransportContextHolder.getTransportContext();
                            WebServiceConnection connection = transportContext.getConnection();

                            if (connection instanceof HttpUrlConnection) {
                                HttpUrlConnection conn = (HttpUrlConnection) connection;
                                conn.addRequestHeader("X-IBM-Client-Secret", clientSecret);
                                conn.addRequestHeader("X-IBM-Client-Id", clientId);

                            }
                            // create the header element
                            ObjectFactory factory = new ObjectFactory();
                            SecuredWebServiceHeader securedWebServiceHeader =
                                    factory.createSecuredWebServiceHeader();
                            securedWebServiceHeader.setUsername(username);
                            securedWebServiceHeader.setPassword(password);

                            JAXBElement<SecuredWebServiceHeader> headers =
                                    factory.createSecuredWebServiceHeaderSecuredWebServiceHeader(securedWebServiceHeader);

                            // create a marshaller
                            JAXBContext context = JAXBContext.newInstance(SecuredWebServiceHeader.class);
                            Marshaller marshaller = context.createMarshaller();

                            // marshal the headers into the specified result
                            marshaller.marshal(headers, soapHeader.getResult());

                            ((SoapMessage) message).setSoapAction(getQueryActionUri);
                        } catch (Exception e) {
                            logger.error("error during marshalling of the SOAP headers", e);
                        }
                    });
            logger.info("IibClient::setTransactionReferenceNo::TransactionReferenceNo::{}::response::{}",
                    transactionReferenceNo, getQueryDataResponse);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("IibClient::setTransactionReferenceNo::TransactionReferenceNo::{}::exceptionMessage::{}",
                    transactionReferenceNo, e.getMessage());
        }
        return getQueryDataResponse;
    }

    public QueryUploadResponse queryDataUploadResponse(String queryUploadRequestByteArray) {
        QueryUploadResponse queryUploadResponse = new QueryUploadResponse();
        try {

            QueryUpload queryUpload = new QueryUpload();
            queryUpload.setFileContent(queryUploadRequestByteArray);

            queryUploadResponse = (QueryUploadResponse) iibTemplate.marshalSendAndReceive(queryUpload,
                    message -> {
                        try {
                            // get the header from the SOAP message
                            SoapHeader soapHeader = ((SoapMessage) message).getSoapHeader();

                            TransportContext transportContext = TransportContextHolder.getTransportContext();
                            WebServiceConnection connection = transportContext.getConnection();

                            if (connection instanceof HttpUrlConnection) {
                                HttpUrlConnection conn = (HttpUrlConnection) connection;
                                conn.addRequestHeader("X-IBM-Client-Secret", clientSecret);
                                conn.addRequestHeader("X-IBM-Client-Id", clientId);

                            }
                            // create the header element
                            ObjectFactory factory = new ObjectFactory();
                            SecuredWebServiceHeader securedWebServiceHeader =
                                    factory.createSecuredWebServiceHeader();
                            securedWebServiceHeader.setUsername(username);
                            securedWebServiceHeader.setPassword(password);

                            StringWriter stringWriter = new StringWriter();

                            JAXBElement<SecuredWebServiceHeader> headers =
                                    factory.createSecuredWebServiceHeaderSecuredWebServiceHeader(securedWebServiceHeader);

                            // create a marshaller
                            JAXBContext context = JAXBContext.newInstance(SecuredWebServiceHeader.class);
                            Marshaller marshaller = context.createMarshaller();
                            marshaller.marshal(headers, stringWriter);

                            // marshal the headers into the specified result
                           marshaller.marshal(headers, soapHeader.getResult());

                            ((SoapMessage) message).setSoapAction(queryDataUploadActionUri);
                        } catch (Exception e) {
                            logger.error("error during marshalling of the SOAP headers", e);
                        }
                    });
            logger.info("IibClient::setFileContent::FileContent::{}::response::{}",
                    queryUploadRequestByteArray, queryUploadResponse);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("IibClient::setFileContent::FileContent::{}::exceptionMessage::{}",
                    queryUploadRequestByteArray, e.getMessage());
        }
        return queryUploadResponse;
    }
}
