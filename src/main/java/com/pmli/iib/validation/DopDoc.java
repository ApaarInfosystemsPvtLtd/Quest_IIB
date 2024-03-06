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
public class DopDoc {

    private final Logger logger = LoggerFactory.getLogger(DopDoc.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        boolean manadatoryFlag = isMandatory(errorList, details);
        if(manadatoryFlag) {
            isCorrectDateFormat(errorList, details);
        }
    }

    private boolean isMandatory(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatory = true;
        String doPDoC = details.getDoPDoC();
        if ("".equalsIgnoreCase(doPDoC.trim())) {
            mandatory = false;
            errorList.add(new QuestValidationErrorList(ErrorCode.DOP_DOC.getValue(), "DopDoc is mandatory", doPDoC));
            logger.info("{}::DopDoc is mandatory::{}",ErrorCode.DOP_DOC.getValue(),doPDoC);
        }
        return mandatory;
    }

    private void isCorrectDateFormat(List<QuestValidationErrorList> errorList, Details details) {
        String doPDoC = details.getDoPDoC();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(doPDoC);
        } catch (ParseException e) {
            errorList.add(new QuestValidationErrorList(ErrorCode.DOP_DOC.getValue(), "DopDoc should be in YYYY-MM-DD format", doPDoC));
            logger.info("{}::DopDoc should be in YYYY-MM-DD format::{}",ErrorCode.DOP_DOC.getValue(),doPDoC);
        }
    }

}
