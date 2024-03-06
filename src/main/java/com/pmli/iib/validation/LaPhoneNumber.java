package com.pmli.iib.validation;

import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LaPhoneNumber {

    private final Logger logger = LoggerFactory.getLogger(LaPhoneNumber.class);

    public void validate(List<QuestValidationErrorList> errorList, String laPhoneNumber, String laPhoneNumber1ErrorCode) {
        boolean length = isCorrectLength(errorList, laPhoneNumber, laPhoneNumber1ErrorCode);
        if(length){
            isPattenMatch(errorList, laPhoneNumber, laPhoneNumber1ErrorCode);
        }
    }

    private boolean isCorrectLength(List<QuestValidationErrorList> errorList, String laPhoneNumber, String laPhoneNumber1ErrorCode) {
        boolean length = laPhoneNumber.length() != 0;
        if (laPhoneNumber.length() > 15) {
            length = false;
            errorList.add(new QuestValidationErrorList(laPhoneNumber1ErrorCode, "LA_Phone_Number should be Less than 15", laPhoneNumber));
            logger.info("{}::LA_Phone_Number should be Less than 15::{}",laPhoneNumber1ErrorCode,laPhoneNumber);
        }
        return length;
    }

    private void isPattenMatch(List<QuestValidationErrorList> errorList, String laPhoneNumber, String laPhoneNumber1ErrorCode) {
        if (!laPhoneNumber.matches("\\d+")) {
            errorList.add(new QuestValidationErrorList(laPhoneNumber1ErrorCode, "LA_Phone_Number should be Number Only", laPhoneNumber));
            logger.info("{}::LA_Phone_Number should be Number Only::{}",laPhoneNumber1ErrorCode,laPhoneNumber);
        }
    }

}
