package com.pmli.iib.util;

import com.pmli.iib.model.QuestValidationErrorList;
import java.util.List;

public interface Validator {
    void validate(List<QuestValidationErrorList> paramList, String paramString);
}
