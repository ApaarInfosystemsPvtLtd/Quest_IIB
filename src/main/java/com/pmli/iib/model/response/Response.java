package com.pmli.iib.model.response;

import com.pmli.iib.model.queryfetch.QueryDataResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Response {
    private List<QueryDataResponse> queryDataResponse;
    private InputValidationError inputValidationError;
    private String message;
    private String status;
    private String transactionId;
}
