package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LaLastName {

    private final Logger logger = LoggerFactory.getLogger(LaLastName.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        boolean length = isCorrectLength(errorList, details);
        if (length) {
            isNumeric(errorList, details);
        }
    }

    private boolean isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        boolean length = true;
        String laLastName = details.getLaLastName();
        if (laLastName.length() > 75) {
            length = false;
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_LAST_NAME_ERROR_CODE.getValue(), "LA_Last_Name should Should not be more than 75", laLastName));
            logger.info("{}::LA_Last_Name should Should not be more than 75::{}",ErrorCode.LA_LAST_NAME_ERROR_CODE.getValue(),laLastName);
        }
        return length;
    }

    private void isNumeric(List<QuestValidationErrorList> errorList, Details details) {
        String laLastName = details.getLaLastName();
        if (!laLastName.matches("^[a-zA-Z\\s]*$")) {
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_LAST_NAME_ERROR_CODE.getValue(), "LA_Last_Name should not contains Numeric values or Special Characters", laLastName));
            logger.info("{}::LA_Last_Name should not contains Numeric values or Special Characters::{}",ErrorCode.LA_LAST_NAME_ERROR_CODE.getValue(),laLastName);
        }
    }

}
