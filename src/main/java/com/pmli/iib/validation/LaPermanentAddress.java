package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LaPermanentAddress {

    private final Logger logger = LoggerFactory.getLogger(LaPermanentAddress.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        boolean regexPattern = isRegexMatched(errorList,details);
        if(regexPattern){
            isCorrectLength(errorList,details);
        }
    }

    private void isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        String laPermanentAddress = details.getLaPermanentAddress();
        if (laPermanentAddress.length() > 500) {
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_PERMANENT_ADDRESS_LINE_ERROR_CODE.getValue(), "LA_Permanent_Address Should not be more than 500", laPermanentAddress));
            logger.info("{}::LA_Permanent_Address Should not be more than 500::{}",ErrorCode.LA_PERMANENT_ADDRESS_LINE_ERROR_CODE.getValue(),laPermanentAddress);
        }

    }

    private boolean isRegexMatched(List<QuestValidationErrorList> errorList, Details details) {
        boolean length = true;
        String laPermanentAddress = details.getLaPermanentAddress();
        if (!laPermanentAddress.matches("^[a-zA-Z0-9]*$")) {
            length = false;
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_PERMANENT_ADDRESS_LINE_ERROR_CODE.getValue(), "LA_Permanent_Address should not contains Numeric values of Special Characters", laPermanentAddress));
            logger.info("{}::LA_Permanent_Address should not contains Numeric values of Special Characters::{}",ErrorCode.LA_PERMANENT_ADDRESS_LINE_ERROR_CODE.getValue(),laPermanentAddress);
        }
        return length;
    }
}
