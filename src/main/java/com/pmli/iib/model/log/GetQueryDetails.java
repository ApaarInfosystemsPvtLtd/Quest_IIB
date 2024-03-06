package com.pmli.iib.model.log;

import com.pmli.iib.model.queryfetch.QueryDataResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetQueryDetails {

    private String  transactionReferenceNo;
    private String encodedResponse;
    private List<QueryDataResponse> queryDataResponse;
    private String status;
}
