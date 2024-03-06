package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LaAadhar {

    private final Logger logger = LoggerFactory.getLogger(LaAadhar.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        isCorrectLength(errorList, details);
    }

    private void isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        String laAadhar = details.getLaAadhar();
        if (!"".equalsIgnoreCase(laAadhar) && laAadhar.length() != 12) {
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_AADHAR_ERROR_CODE.getValue(), "LA_AADHAR should be of length 12 ", laAadhar));
            logger.info("{}::LA_AADHAR should be of length 12::{}",ErrorCode.LA_AADHAR_ERROR_CODE.getValue(),laAadhar);
        }
    }
}
