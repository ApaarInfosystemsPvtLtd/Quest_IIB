package com.pmli.iib.model.log;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document("QuestIibState")
public class QuestIibState {

    private String policyNumber;
    private QueryUploadDetails queryUploadDetails;
    private GetQueryDetails getQueryDetails;
    private String status;
    private LocalDateTime createdDate = LocalDateTime.now();

}
