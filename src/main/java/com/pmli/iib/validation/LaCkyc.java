package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LaCkyc {

    private final Logger logger = LoggerFactory.getLogger(LaCkyc.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        isCorrectLength(errorList, details);
    }

    private void isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        String laCkyc = details.getLaCkyc();
        if (!"".equalsIgnoreCase(laCkyc) && laCkyc.length() != 14) {
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_CYKC_ID_ERROR_CODE.getValue(), "LA_CYKC should be of length 14", laCkyc));
            logger.info("{}::LA_CYKC should be of length 14::{}",ErrorCode.LA_CYKC_ID_ERROR_CODE.getValue(),laCkyc);
        }
    }
}
