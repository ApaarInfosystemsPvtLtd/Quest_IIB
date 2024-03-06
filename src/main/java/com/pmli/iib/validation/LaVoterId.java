package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LaVoterId {

    private final Logger logger = LoggerFactory.getLogger(LaVoterId.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        isCorrectLength(errorList, details);
    }

    private void isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        String laVoterId = details.getLaVoterId();
        if (laVoterId.length() > 16) {
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_AADHAR_ERROR_CODE.getValue(), "LA_DL should not be greater than 16", laVoterId));
            logger.info("{}::LA_DL should not be greater than 16::{}",ErrorCode.LA_AADHAR_ERROR_CODE.getValue(),laVoterId);
        }
    }
}
