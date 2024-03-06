package com.pmli.iib.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    POLICY_NUMBER_ERROR_CODE                    ("1"),
    PROPOSAL_NUMBER_ERROR_CODE                  ("2"),
    QUERY_TYPE                                  ("3"),   //NEW
    DOP_DOC                                     ("4"),   //NEW
    SUM_ASSURED_ERROR_CODE                      ("5"),
    LA_FIRST_NAME_ERROR_CODE                    ("6"),
    LA_MIDDLE_NAME_ERROR_CODE                   ("7"),
    LA_LAST_NAME_ERROR_CODE                     ("8"),
    LA_DOB_ERROR_CODE                           ("9"),
    LA_GENDER_ERROR_CODE                        ("10"),
    LA_CURRENT_ADDRESS_LINE_ERROR_CODE          ("11"),
    LA_PERMANENT_ADDRESS_LINE_ERROR_CODE        ("12"),
    LA_PIN_CODE                                 ("13"),   //NEW
    LA_PAN_ERROR_CODE                           ("14"),
    LA_AADHAR_ERROR_CODE                        ("15"),
    LA_CYKC_ID_ERROR_CODE                       ("16"),
    LA_DL                                       ("17"),  //NEW
    LA_PASSPORT_ERROR_CODE                      ("18"),
    LA_VOTER_ID_ERROR_CODE                      ("19"),
    LA_PHONE_NUMBER_1_ERROR_CODE                ("20"),
    LA_PHONE_NUMBER_2_ERROR_CODE                ("21"),
    LA_EMAIL_1_ERROR_CODE                       ("22"),
    LA_EMAIL_2_ERROR_CODE                       ("23"),
    LA_DATE_OF_DEATH_ERROR_CODE                 ("24"),
    COMPANY_NUMBER                              ("25"),  //NEW
    PRODUCT_TYPE                                ("26"),  //NEW
    PRODUCT_UIN                                 ("27"),  //NEW
    ANNUAL_INCOME                               ("28"),  //NEW
    CAUSE_OF_DEATH                              ("29");  //NEW

    private String value;
}
