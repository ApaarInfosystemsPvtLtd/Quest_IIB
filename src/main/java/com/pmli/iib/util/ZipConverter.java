package com.pmli.iib.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class ZipConverter {

    private final Logger logger = LoggerFactory.getLogger(ZipConverter.class);

    public String encodedFileContent(String xmlRequest) {
        logger.info("################ Inside encodedFileContent Method::{} ################", LocalDateTime.now());
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        try (ZipOutputStream zos = new ZipOutputStream(boas)) {
            zos.putNextEntry(new ZipEntry("file.xml"));
            zos.write(xmlRequest.getBytes());
            zos.closeEntry();
        }catch (IOException ioe){
            logger.error("Exception Occurred while Encoding File Content::{}", Arrays.asList(ioe.getStackTrace()));
            ioe.printStackTrace();
        }
        byte [] encodedByteArray = boas.toByteArray();
        String encodedString = Base64.getEncoder().encodeToString(encodedByteArray);
        logger.info("################ Outside encodedFileContent Method::{} ################", LocalDateTime.now());
        return encodedString;
    }
}

