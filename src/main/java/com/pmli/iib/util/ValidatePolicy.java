package com.pmli.iib.util;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import com.pmli.iib.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ValidatePolicy {

    private final Logger logger = LoggerFactory.getLogger(ValidatePolicy.class);

    @Autowired
    PolicyNumber policyNumber;

    @Autowired
    ProposalNumber proposalNumber;

    @Autowired
    QueryType queryType;

    @Autowired
    DopDoc dopDoc;

    @Autowired
    SumAssured sumAssured;

    @Autowired
    LaFirstName laFirstName;

    @Autowired
    LaMiddleName laMiddleName;

    @Autowired
    LaLastName laLastName;

    @Autowired
    LaDob laDob;

    @Autowired
    LaGender laGender;

    @Autowired
    LaCurrentAddress laCurrentAddress;

    @Autowired
    LaPermanentAddress laPermanentAddress;

    @Autowired
    LaPinCode laPinCode;

    @Autowired
    LaPan laPan;

    @Autowired
    LaAadhar laAadhar;

    @Autowired
    LaCkyc laCkyc;

    @Autowired
    LaPassport laPassport;

    @Autowired
    LaDL laDL;

    @Autowired
    LaVoterId laVoterId;

    @Autowired
    LaPhoneNumber laPhoneNumber;

    @Autowired
    LaEmail laEmail;

    @Autowired
    DateOfDeath dateOfDeath;

    @Autowired
    CompanyNumber companyNumber;

    @Autowired
    ProductUIN productUIN;

    @Autowired
    ProductType productType;

    @Autowired
    AnnualIncome annualIncome;

    @Autowired
    CauseOfDeath causeOfDeath;

    public List<QuestValidationErrorList> validateDetail(Details details) {

        logger.info("################ Inside Validate Policy Method::{} ################", LocalDateTime.now());

            List<QuestValidationErrorList> errorList = new ArrayList<>();
            policyNumber.validate(errorList,details);
            proposalNumber.validate(errorList,details);
            queryType.validate(errorList,details);
            dopDoc.validate(errorList,details);
            sumAssured.validate(errorList,details);
            laFirstName.validate(errorList,details);
            laMiddleName.validate(errorList,details);
            laLastName.validate(errorList,details);
            laDob.validate(errorList,details);
            laGender.validate(errorList,details);
            laCurrentAddress.validate(errorList,details);
            laPermanentAddress.validate(errorList,details);
            laPinCode.validate(errorList,details);
            laPan.validate(errorList,details);
            laAadhar.validate(errorList,details);
            laCkyc.validate(errorList,details);
            laPassport.validate(errorList,details);
            laDL.validate(errorList,details);
            laVoterId.validate(errorList,details);
            laPhoneNumber.validate(errorList,details.getLaPhoneNumber1(), ErrorCode.LA_PHONE_NUMBER_1_ERROR_CODE.getValue());
            laPhoneNumber.validate(errorList,details.getLaPhoneNumber2(), ErrorCode.LA_PHONE_NUMBER_2_ERROR_CODE.getValue());
            laEmail.validate(errorList,details.getLaEmail1(),ErrorCode.LA_EMAIL_1_ERROR_CODE.getValue());
            laEmail.validate(errorList,details.getLaEmail2(),ErrorCode.LA_EMAIL_2_ERROR_CODE.getValue());
            dateOfDeath.validate(errorList,details);
            companyNumber.validate(errorList,details);
            productUIN.validate(errorList,details);
            productType.validate(errorList,details);
            annualIncome.validate(errorList,details);
            causeOfDeath.validate(errorList,details);

        logger.info("################ Outside Validate Policy Method::{} ################", LocalDateTime.now());

        return errorList;
    }
}
