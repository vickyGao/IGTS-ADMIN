package com.ntu.igts.validator;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.exception.ServiceWarningException;
import com.ntu.igts.i18n.MessageKeys;
import com.ntu.igts.utils.ValidationUtil;

@Component
public class CustomModuleValidator {

    public void validateCreate(String postBody) {
        JSONObject jsonPutBody = JSONObject.fromObject(postBody).optJSONObject(Constants.CUSTOMMODULE);
        if (!ValidationUtil.hasKey(jsonPutBody, Constants.TITLE)
                        || ValidationUtil.isFieldEmpty(jsonPutBody, Constants.TITLE)) {
            String[] param = { MessageKeys.CUSTOM_MODULE_TITLE };
            throw new ServiceWarningException("Custom module title is required", MessageKeys.FIELD_REQUIRED, param);
        }
        if (!ValidationUtil.hasKey(jsonPutBody, Constants.DISPLAY_SEQUENCE)
                        || ValidationUtil.isFieldEmpty(jsonPutBody, Constants.DISPLAY_SEQUENCE)) {
            String[] param = { MessageKeys.CUSTOM_MODULE_DISPLAY_SEQUENCE };
            throw new ServiceWarningException("Custom module display sequence is required", MessageKeys.FIELD_REQUIRED,
                            param);
        }
    }
}
