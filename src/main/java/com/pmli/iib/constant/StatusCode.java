package com.pmli.iib.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {
    SUCCESS_MESSAGE                     ("SUCCESS"),
    FAIL_MESSAGE                        ("FAIL");
    private String value;
}
