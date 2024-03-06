package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PolicyNumber {

    private final Logger logger = LoggerFactory.getLogger(PolicyNumber.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatoryFlag = isMandatory(errorList, details);
        if (mandatoryFlag) {
            isCorrectLength(errorList, details);
        }
    }

    private boolean isMandatory(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatoryFlag = true;
        String policyNumber = details.getPolicyNumber();
        if (policyNumber == null || "".equalsIgnoreCase(policyNumber.trim())) {
            mandatoryFlag = false;
            errorList.add(new QuestValidationErrorList(ErrorCode.POLICY_NUMBER_ERROR_CODE.getValue(), "Policy_Number is mandatory", policyNumber));
            logger.info("{}::Policy_Number is mandatory::{}", ErrorCode.POLICY_NUMBER_ERROR_CODE.getValue(), policyNumber);
        }
        return mandatoryFlag;
    }

    private void isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        String policyNumber = details.getPolicyNumber();
        if (policyNumber.length() >= 15) {
            errorList.add(new QuestValidationErrorList(ErrorCode.POLICY_NUMBER_ERROR_CODE.getValue(), "Policy_Number should be of length 15", policyNumber));
            logger.info("{}::Policy_Number should be of length 15::{}", ErrorCode.POLICY_NUMBER_ERROR_CODE.getValue(), policyNumber);
        }
    }
}