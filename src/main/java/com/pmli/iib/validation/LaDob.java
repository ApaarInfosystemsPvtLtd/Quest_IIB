package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class LaDob {

    private final Logger logger = LoggerFactory.getLogger(LaDob.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatoryFlag = isMandatory(errorList, details);
        if(mandatoryFlag) {
            isCorrectLength(errorList, details);
        }
    }

    private boolean isMandatory(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatory = true;
        String laDoB = details.getLaDob();
        if ("".equalsIgnoreCase(laDoB.trim())) {
            mandatory = false;
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_DOB_ERROR_CODE.getValue(), "LA_First_Name is mandatory", laDoB));
            logger.info("{}::LA_First_Name is mandatory::{}",ErrorCode.LA_DOB_ERROR_CODE.getValue(),laDoB);
        }
        return mandatory;
    }

    private void isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        String laDoB = details.getLaDob();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(laDoB);
        } catch (ParseException e) {
            errorList.add(new QuestValidationErrorList(ErrorCode.LA_DOB_ERROR_CODE.getValue(), "LA_DoB should be in YYYY-MM-DD format", laDoB));
            logger.info("{}::LA_DoB should be in YYYY-MM-DD format::{}",ErrorCode.LA_DOB_ERROR_CODE.getValue(),laDoB);
        }
    }
}
