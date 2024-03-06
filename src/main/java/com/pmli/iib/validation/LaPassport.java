package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class LaPassport {

    private final Logger logger = LoggerFactory.getLogger(LaPassport.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        boolean length = isCorrectLength(errorList, details);
        if(length) {
            isValid(errorList, details);
        }
    }

    private void isValid(List<QuestValidationErrorList> errorList, Details details) {
        String laPassport = details.getLaPassport();
        if (laPassport != null && !laPassport.trim().equals("")) {
            String phoneRegex = "^[A-Z][0-9]$";
            Pattern pat = Pattern.compile(phoneRegex);
            if (!pat.matcher(laPassport).matches()) {
                errorList.add(new QuestValidationErrorList(ErrorCode.LA_PASSPORT_ERROR_CODE.getValue(), "LA_Passport Validation Pattern is not proper", laPassport));
                logger.info("{}::LA_Passport Validation is not proper::{}",ErrorCode.LA_PASSPORT_ERROR_CODE.getValue(),laPassport);
            }
        }
    }

    private boolean isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        boolean length = true;
        String laPassport = details.getLaPassport();
        if (laPassport != null && !"".equalsIgnoreCase(laPassport.trim()) && laPassport.length() > 10) {
            length = false;
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_PASSPORT_ERROR_CODE.getValue(), "LA_Passport should be of length 10", laPassport));
            logger.info("{}::LA_Passport should be of length 10r::{}",ErrorCode.LA_PASSPORT_ERROR_CODE.getValue(),laPassport);
        }
        return length;
    }

}
