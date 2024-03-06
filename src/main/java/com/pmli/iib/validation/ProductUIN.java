package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductUIN {

    private final Logger logger = LoggerFactory.getLogger(ProductUIN.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatory = isMandatory(errorList, details);
        if(mandatory) {
            isCorrectLength(errorList, details);
        }
    }

    private boolean isMandatory(List<QuestValidationErrorList> errorList, Details details) {
        boolean mandatory = true;
        String productUin = details.getProductUIN();
        if ("".equalsIgnoreCase(productUin.trim())) {
            mandatory = false;
            errorList.add(new QuestValidationErrorList(ErrorCode.PRODUCT_UIN.getValue(), "Product_UIN is mandatory", productUin));
            logger.info("{}::Product_UIN is mandatory::{}",ErrorCode.PRODUCT_UIN.getValue(),productUin);
        }
        return mandatory;
    }

    private void isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        String productUin = details.getProductUIN();
        if (productUin.length() != 10) {
            errorList.add(new QuestValidationErrorList(ErrorCode.PRODUCT_UIN.getValue(), "Product_UIN should be of length 10", productUin));
            logger.info("{}::Product_UIN should be of length 10::{}",ErrorCode.PRODUCT_UIN.getValue(),productUin);
        }
    }
}
