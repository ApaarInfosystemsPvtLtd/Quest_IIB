package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SumAssured {

    private final Logger logger = LoggerFactory.getLogger(SumAssured.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatoryFlag = isMandatory(errorList, details);
        if(mandatoryFlag) {
            boolean length = isCorrectLength(errorList, details);
            if (length){
                isNumeric(errorList,details);
            }
        }
    }

    private boolean isMandatory(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatory = true;
        String sumAssured = details.getSumAssured();
        if ("".equalsIgnoreCase(sumAssured.trim())) {
            mandatory = false;
            errorList.add(new QuestValidationErrorList(ErrorCode.SUM_ASSURED_ERROR_CODE.getValue(), "Sum_Assured is mandatory", sumAssured));
            logger.info("{}::Sum_Assured is mandatory::{}",ErrorCode.SUM_ASSURED_ERROR_CODE.getValue(),sumAssured);
        }
        return mandatory;
    }

    private boolean isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        boolean length = true;
        String sumAssured = details.getSumAssured();
        if (sumAssured.length() > 12) {
            length = false;
            errorList.add(new QuestValidationErrorList(ErrorCode.SUM_ASSURED_ERROR_CODE.getValue(), "Sum_Assured should be of less than length 12", sumAssured));
            logger.info("{}::Sum_Assured should be of less than length 12::{}",ErrorCode.SUM_ASSURED_ERROR_CODE.getValue(),sumAssured);
        }
        return length;
    }

    private void isNumeric(List<QuestValidationErrorList> errorList, Details details) {
        String sumAssured = details.getSumAssured();
        if (!sumAssured.matches("\\d+")) {
            errorList.add(new QuestValidationErrorList(ErrorCode.SUM_ASSURED_ERROR_CODE.getValue(), "Sum_Assured should be Numeric", sumAssured));
            logger.info("{}::Sum_Assured should be Numeric::{}",ErrorCode.SUM_ASSURED_ERROR_CODE.getValue(),sumAssured);
        }
    }

}
