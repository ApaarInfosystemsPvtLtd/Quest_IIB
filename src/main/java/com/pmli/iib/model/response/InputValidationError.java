package com.pmli.iib.model.response;

import com.pmli.iib.model.QuestValidationErrorList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class InputValidationError {

    private  List<QuestValidationErrorList> errors;
}
