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
public class DateOfDeath {

    private final Logger logger = LoggerFactory.getLogger(DateOfDeath.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        if("2".equalsIgnoreCase(details.getQueryType())) {
            boolean manadatoryFlag = isMandatory(errorList, details);
            if (manadatoryFlag) {
                isCorrectDateFormat(errorList, details);
            }
        }
    }

    private boolean isMandatory(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatory = true;
        String dateOfDeath = details.getDateOfDeath();
        if (dateOfDeath == null || !"".equalsIgnoreCase(dateOfDeath.trim())) {
            mandatory = false;
            errorList.add(new QuestValidationErrorList(ErrorCode.DOP_DOC.getValue(), "Date_of_Death is mandatory", dateOfDeath));
            logger.info("{}::Date_of_Death is mandatory::{}",ErrorCode.DOP_DOC.getValue(),dateOfDeath);
        }
        return mandatory;
    }

    private void isCorrectDateFormat(List<QuestValidationErrorList> errorList, Details details) {
        String dateOfDeath = details.getDateOfDeath();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(dateOfDeath);
        } catch (ParseException e) {
            errorList.add(new QuestValidationErrorList(ErrorCode.DOP_DOC.getValue(), "Date_of_Death should be in YYYY-MM-DD format", dateOfDeath));
            logger.info("{}::Date_of_Death should be in YYYY-MM-DD format::{}",ErrorCode.DOP_DOC.getValue(),dateOfDeath);
        }
    }
}
