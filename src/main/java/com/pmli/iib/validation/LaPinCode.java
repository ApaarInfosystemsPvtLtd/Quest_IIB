package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LaPinCode {

    private final Logger logger = LoggerFactory.getLogger(LaPinCode.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        isCorrectLength(errorList, details);
    }

    private void isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        String laPinCode = details.getLaPinCode();
        if (!"".equalsIgnoreCase(laPinCode) && laPinCode.length() > 6) {
                errorList.add(new QuestValidationErrorList(ErrorCode.LA_PIN_CODE.getValue(), "LA_Pin_Code should be of length 6", laPinCode));
                logger.info("{}::LA_Pin_Code should be of length 6::{}",ErrorCode.LA_PIN_CODE.getValue(),laPinCode);
        }
    }
}
