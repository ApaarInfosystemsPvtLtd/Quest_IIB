package com.pmli.iib.validation;

import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class LaEmail {

    private static final Logger logger = LoggerFactory.getLogger(LaEmail.class);

    public void validate(List<QuestValidationErrorList> errorList, String laEmail , String laEmailError) {
        boolean length = isCorrectLength(errorList, laEmail, laEmailError);
        if(length) {
            isValidEmail(errorList, laEmail, laEmailError);
        }
    }

    private void isValidEmail(List<QuestValidationErrorList> errorList, String laEmail , String laEmailError) {
        if (laEmail != null && !laEmail.trim().equals("")) {
            String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(emailRegex);
            if (!pattern.matcher(laEmail).matches()) {
                errorList.add(new QuestValidationErrorList(laEmailError, "LA_Email pattern is invalid", laEmail));
                logger.info("{}::LA_Email pattern is invalid::{}",laEmailError,laEmail);
            }
        }
    }

    private boolean isCorrectLength(List<QuestValidationErrorList> errorList, String laEmail , String laEmailError) {
        boolean length = true;
            if (laEmail.length() > 50) {
                length = false;
                errorList.add(new QuestValidationErrorList(laEmailError, "LA_Email should be of length 50", laEmail));
                logger.info("{}::LA_Email should be of length 50::{}",laEmailError,laEmail);
            }
            return length;
    }

}
