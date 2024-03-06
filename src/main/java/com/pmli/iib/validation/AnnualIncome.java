package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AnnualIncome {

    private final Logger logger = LoggerFactory.getLogger(AnnualIncome.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatory = isMandatory(errorList, details);
        if(mandatory) {
            isCorrectLength(errorList, details);
        }
    }

    private boolean isMandatory(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatory = true;
        String annualIncome = details.getAnnualIncome();
        if ("".equalsIgnoreCase(annualIncome.trim())) {
            mandatory = false;
            errorList.add(new QuestValidationErrorList(ErrorCode.ANNUAL_INCOME.getValue(), "Annual_Income is mandatory", annualIncome));
            logger.info("{}::Annual_Income is mandatory::{}",ErrorCode.ANNUAL_INCOME.getValue(),annualIncome);
        }
        return mandatory;
    }

    private void isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        String annualIncome = details.getAnnualIncome();
        if (annualIncome.length() < 4 || annualIncome.length() > 15) {
            errorList.add(new QuestValidationErrorList(ErrorCode.ANNUAL_INCOME.getValue(), "Annual_Income should be of length between 4 and 15", annualIncome));
            logger.info("{}::Annual_Income should be of length between 4 & 15::{}",ErrorCode.ANNUAL_INCOME.getValue(),annualIncome);
        }
    }
}
