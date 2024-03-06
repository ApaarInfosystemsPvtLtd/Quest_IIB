package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductType {

    private final Logger logger = LoggerFactory.getLogger(ProductType.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatory = isMandatory(errorList, details);
        if (mandatory) {
            isCorrectLength(errorList, details);
        }

    }

    private boolean isMandatory(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatory = true;
        String productType = details.getProductType();
        if ("".equalsIgnoreCase(productType.trim())) {
            mandatory = false;
            errorList.add(new QuestValidationErrorList(ErrorCode.PRODUCT_TYPE.getValue(), "Product_type is mandatory", productType));
            logger.info("{}::Product_type is mandatory::{}",ErrorCode.PRODUCT_TYPE.getValue(),productType);
        }
        return mandatory;
    }

    private void isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        String productType = details.getProductType();
        if (productType.length() != 2) {
            errorList.add(new QuestValidationErrorList(ErrorCode.PRODUCT_TYPE.getValue(), "Product_type should be of length 2", productType));
            logger.info("{}::Product_type should be of length 2::{}",ErrorCode.PRODUCT_TYPE.getValue(),productType);

        }
    }
}
