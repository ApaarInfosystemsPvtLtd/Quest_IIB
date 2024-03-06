package com.pmli.iib.validation;

import com.pmli.iib.constant.ErrorCode;
import com.pmli.iib.model.upload.Details;
import com.pmli.iib.model.QuestValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProposalNumber {

    private final Logger logger = LoggerFactory.getLogger(ProposalNumber.class);

    public void validate(List<QuestValidationErrorList> errorList, Details details) {
        isCorrectLength(errorList, details);
    }

    private void isCorrectLength(List<QuestValidationErrorList> errorList, Details details) {
        String proposalNumber = details.getProposalNumber();
        if ("".equalsIgnoreCase(proposalNumber.trim()) && proposalNumber.length() >= 15) {
            errorList.add(new QuestValidationErrorList(ErrorCode.PROPOSAL_NUMBER_ERROR_CODE.getValue(), "Proposal_Number should be of length 15", proposalNumber));
            logger.info("{}::Proposal_Number should be of length 15::{}",ErrorCode.PROPOSAL_NUMBER_ERROR_CODE.getValue(),proposalNumber);
        }
    }
}

