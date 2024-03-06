package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CompanyNumber {

    private final Logger logger = LoggerFactory.getLogger(CompanyNumber.class);

    @Value("${quest.iib.pmliCompanyNumber}")
    private String pmliCompanyNumber;

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        isMandatory(errorList, details);
    }

    private void isMandatory(List<QuestValidationErrorList> errorList, Details details) {
        if ("".equalsIgnoreCase(details.getCompanyNumber()) || !pmliCompanyNumber.equalsIgnoreCase(details.getCompanyNumber())) {
            errorList.add(new QuestValidationErrorList(ErrorCode.COMPANY_NUMBER.getValue(), "Company_Number is mandatory & value should be " + pmliCompanyNumber + " for PNB MetLife India Insurance Company Ltd.", details.getCompanyNumber()));
            logger.info("{}::Company_Number is mandatory & value should be {} for PNB MetLife India Insurance Company Ltd.::{}",ErrorCode.COMPANY_NUMBER.getValue(),pmliCompanyNumber,details.getCompanyNumber());
        }
    }

}
