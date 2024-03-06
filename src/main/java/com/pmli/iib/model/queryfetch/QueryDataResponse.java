package com.pmli.iib.model.queryfetch;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@ToString
public class QueryDataResponse {

    private String inputProposalPolicyNo;
    private String questDBNo;
    private String inputMatchingParameter;
    private String questDoPDoC;
    private String questSumAssured;
    private String questPolicyStatus;
    private String questDateOfExit;
    private String questDateOfDeath;
    private String questCauseOfDeath;
    private String questRecordLastUpdated;
    private String questEntityCautionStatus;
    private String questIntermediaryCautionStatus;
    private String questCompanyNumber;
    private String isNegativeMatch;
    private String productType;
    private String linkedNonLinked;
    private String medicalNonMedical;
    private String whetherStandardLife;
    private String reasonForDecline;
    private String reasonForPostpone;
    private String reasonForRepudiation;

}
