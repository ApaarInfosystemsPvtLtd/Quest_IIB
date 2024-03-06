package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CauseOfDeath {

    private final Logger logger = LoggerFactory.getLogger(CauseOfDeath.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        if("2".equalsIgnoreCase(details.getQueryType())) {
            boolean mandatory = isMandatory(errorList, details);
            if (mandatory) {
                isCorrectLength(errorList, details);
            }
        }
    }

    private boolean isMandatory(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatory = true;
        String causeOfDeath = details.getCauseOfDeath();
        if ("".equalsIgnoreCase(causeOfDeath.trim())) {
            mandatory = false;
            errorList.add(new QuestValidationErrorList(ErrorCode.CAUSE_OF_DEATH.getValue(), "Cause_of_Death is mandatory", causeOfDeath));
            logger.info("{}::Cause_of_Death is mandatory::{}",ErrorCode.CAUSE_OF_DEATH.getValue(),causeOfDeath);
        }
        return mandatory;
    }

    private void isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        String causeOfDeath = details.getCauseOfDeath();
        if (causeOfDeath.length() !=2) {
            errorList.add(new QuestValidationErrorList(ErrorCode.CAUSE_OF_DEATH.getValue(), "Cause_of_Death should be of length 2", causeOfDeath));
            logger.info("{}::Cause_of_Death should be of length 2::{}",ErrorCode.CAUSE_OF_DEATH.getValue(),causeOfDeath);
        }
    }
}
