package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LaDL {

    private final Logger logger = LoggerFactory.getLogger(LaDL.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        isCorrectLength(errorList, details);
    }

    private void isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        String laDL = details.getLaDl();
        if (laDL.length() > 20) {
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_AADHAR_ERROR_CODE.getValue(), "LA_DL should not be greater than 20", laDL));
            logger.info("{}::LA_DL should not be greater than 20::{}",ErrorCode.LA_AADHAR_ERROR_CODE.getValue(),laDL);
        }
    }
}
