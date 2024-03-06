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
public class LaGender {

    private final Logger logger = LoggerFactory.getLogger(LaGender.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatoryFlag = isMandatory(errorList, details);
        if(mandatoryFlag) {
            isCorrectLength(errorList, details);
        }
    }

    private boolean isMandatory(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatoryFlag = true;
        String laGender = details.getLaGender();
        if ("".equalsIgnoreCase(laGender.trim())) {
            mandatoryFlag = false;
            errorList.add(new QuestValidationErrorList(ErrorCode.POLICY_NUMBER_ERROR_CODE.getValue(), "LA_Gender is mandatory", laGender));
            logger.info("{}::LA_Gender is mandatory::{}",ErrorCode.POLICY_NUMBER_ERROR_CODE.getValue(),laGender);
        }
        return mandatoryFlag;
    }

    private void isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        String laGender = details.getLaGender();
        String[] genderList = {"M", "F", "T", "I"};
        if (laGender.length() != 1 && !Arrays.asList(genderList).contains(laGender)) {
            errorList.add(new QuestValidationErrorList(ErrorCode.POLICY_NUMBER_ERROR_CODE.getValue(), "LA_Gender should Contain 1 value Or data is other than 'M','F','T','I'", laGender));
            logger.info("{}::LA_Gender should Contain 1 value Or data is other than 'M','F','T','I'::{}",ErrorCode.POLICY_NUMBER_ERROR_CODE.getValue(),laGender);
        }
    }
}
