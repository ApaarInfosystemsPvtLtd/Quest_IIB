package com.pmli.iib.model.log;

import com.pmli.iib.model.QuestValidationErrorList;
import com.pmli.iib.model.upload.Query;
import com.pmli.iib.model.upload.UploadFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QueryUploadDetails {

    private Query request;
    private String xmlData;
    private String encodedZIP;
    private List<QuestValidationErrorList> errors;
    private UploadFile queryUploadResponse;
    private String status;
}
