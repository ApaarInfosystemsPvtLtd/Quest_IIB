package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LaFirstName {

    private final Logger logger = LoggerFactory.getLogger(LaFirstName.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatoryFlag = isMandatory(errorList, details);
        if(mandatoryFlag) {
            boolean length = isCorrectLength(errorList, details);
            if (length){
                isNumeric(errorList,details);
            }
        }
    }

    private boolean isMandatory(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatory = true;
        String laFirstName = details.getLaFirstName();
        if ("".equalsIgnoreCase(laFirstName.trim())) {
            mandatory = false;
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_FIRST_NAME_ERROR_CODE.getValue(), "LA_First_Name is mandatory", laFirstName));
            logger.info("{}::LA_First_Name is mandatory::{}",ErrorCode.LA_FIRST_NAME_ERROR_CODE.getValue(),laFirstName);
        }
        return mandatory;
    }

    private boolean isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        boolean length = true;
        String laFirstName = details.getLaFirstName();
        if (laFirstName.length() > 75) {
            length = false;
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_FIRST_NAME_ERROR_CODE.getValue(), "LA_First_Name should Should not be more than 75", laFirstName));
            logger.info("{}::LA_First_Name should Should not be more than 75::{}",ErrorCode.LA_FIRST_NAME_ERROR_CODE.getValue(),laFirstName);
        }
        return length;
    }

    private void isNumeric(List<QuestValidationErrorList> errorList, Details details) {
        String laFirstName = details.getLaFirstName();
        if (!laFirstName.matches("^[a-zA-Z\\s]*$")) {
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_FIRST_NAME_ERROR_CODE.getValue(), "LA_First_Name should not contains Numeric values or Special Characters", laFirstName));
            logger.info("{}::LA_First_Name should not contains Numeric values or Special Characters::{}",ErrorCode.LA_FIRST_NAME_ERROR_CODE.getValue(),laFirstName);
        }

    }
}
