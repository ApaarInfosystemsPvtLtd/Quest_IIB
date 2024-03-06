package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LaCurrentAddress {

    private final Logger logger = LoggerFactory.getLogger(LaCurrentAddress.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        boolean regexPattern = isRegexMatched(errorList,details);
        if(regexPattern){
            isCorrectLength(errorList,details);
        }
    }

    private void isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        String laCurrentAddress = details.getLaCurrentAddress();
        if (laCurrentAddress.length() > 500) {
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_FIRST_NAME_ERROR_CODE.getValue(), "LA_Current_Address Should not be more than 500", laCurrentAddress));
            logger.info("{}::LA_Current_Address Should not be more than 500::{}",ErrorCode.LA_FIRST_NAME_ERROR_CODE.getValue(),laCurrentAddress);
        }

    }

    private boolean isRegexMatched(List<QuestValidationErrorList> errorList, Details details) {
        boolean length = true;
        String laCurrentAddress = details.getLaCurrentAddress();
        if (!laCurrentAddress.matches("^[a-zA-Z0-9]*$")) {
            length = false;
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_FIRST_NAME_ERROR_CODE.getValue(), "LA_Current_Address should not contains Numeric values of Special Characters", laCurrentAddress));
            logger.info("{}::LA_Current_Address should not contains Numeric values of Special Characters::{}",ErrorCode.LA_FIRST_NAME_ERROR_CODE.getValue(),laCurrentAddress);
        }
        return length;
    }
}
