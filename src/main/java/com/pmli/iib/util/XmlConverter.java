package com.pmli.iib.util;

import com.pmli.iib.model.upload.Query;
import com.pmli.iib.model.upload.UploadFile;
import com.pmli.iib.wsdlbean.QueryUploadResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class XmlConverter {

    private final Logger logger = LoggerFactory.getLogger(XmlConverter.class);

    public String queryDataUploadMarshal(Query query){
        logger.info("################ Inside queryDataUploadMarshal Method::{} ################", LocalDateTime.now());
        StringWriter stringWriter = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Query.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
            jaxbMarshaller.marshal(query, stringWriter);
        } catch (NullPointerException | JAXBException ex) {
            logger.error("Exception occurred while creating jaxb context::{}", Arrays.asList(ex.getStackTrace()));
            ex.printStackTrace();
        }
        logger.info("QueryDataUploadMarshal XMLData::{}",stringWriter);
        logger.info("################ Outside queryDataUploadMarshal Method::{} ################", LocalDateTime.now());
        return stringWriter.toString();
    }

    public UploadFile queryDataResponseUnmarshal (QueryUploadResponse queryUploadResponse) throws JAXBException {
        logger.info("################ Inside queryDataResponseUnmarshal Method::{} ################", LocalDateTime.now());
        String queryDataResultString = new String(queryUploadResponse.getQueryUploadResult());
        JAXBContext jaxbContext = JAXBContext.newInstance(UploadFile.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(queryDataResultString.trim());
        logger.info("################ Outside queryDataResponseUnmarshal Method::{} ################", LocalDateTime.now());
        return (UploadFile) unmarshaller.unmarshal(reader);
    }

}

