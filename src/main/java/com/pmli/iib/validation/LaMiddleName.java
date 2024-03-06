package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LaMiddleName {

    private final Logger logger = LoggerFactory.getLogger(LaMiddleName.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        isCorrectLength(errorList, details);
    }

    private void isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        String laMiddleName = details.getLaMiddleName();
        if (laMiddleName.length() > 25) {
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_MIDDLE_NAME_ERROR_CODE.getValue(), "LA_Middle_Name should Should not be more than 25", laMiddleName));
            logger.info("{}::LA_Middle_Name should Should not be more than 25::{}",ErrorCode.LA_MIDDLE_NAME_ERROR_CODE.getValue(),laMiddleName);
        }
    }
}
