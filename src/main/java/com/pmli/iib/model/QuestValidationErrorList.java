package com.pmli.iib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class QuestValidationErrorList {
    private String code;
    private String reason;
    private String data;
}
