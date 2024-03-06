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
public class LaPan {

    private final Logger logger = LoggerFactory.getLogger(LaPan.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        boolean length = isCorrectLength(errorList, details);
        if(length) {
            isValid(errorList, details);
        }
    }

    private void isValid(List<QuestValidationErrorList> errorList, Details details) {
        String laPan = details.getLaPan();
        String panRegex = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
        Pattern pat = Pattern.compile(panRegex);
        if (!pat.matcher(laPan).matches()) {
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_PAN_ERROR_CODE.getValue(), "LA_PAN is changed with blankData due to validation issue", laPan));
            logger.info("{}::LA_PAN is changed with blankData due to validation issue::{}",ErrorCode.LA_PAN_ERROR_CODE.getValue(),laPan);
        }
    }

    private boolean isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        boolean length = true;
        String laPan = details.getLaPan();
        if ("".equalsIgnoreCase(laPan)) {
            length =false;
        }else  if (laPan.length() > 10) {
            length = false;
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_PAN_ERROR_CODE.getValue(), "LA_PAN is of invalid length should be less tha 10", laPan));
            logger.info("{}::LA_PAN is of invalid length should be less tha 10::{}", ErrorCode.LA_PAN_ERROR_CODE.getValue(), laPan);
        }
        return length;
    }
}

