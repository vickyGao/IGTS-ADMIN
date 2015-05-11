package com.ntu.igts.validator;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.exception.ServiceWarningException;
import com.ntu.igts.i18n.MessageKeys;
import com.ntu.igts.utils.ValidationUtil;

@Component
public class TagValidator {

    public void validateCreate(String postBody) {
        JSONObject jsonPostBody = JSONObject.fromObject(postBody).optJSONObject(Constants.TAG);
        if (!ValidationUtil.hasKey(jsonPostBody, Constants.NAME)
                        || ValidationUtil.isFieldEmpty(jsonPostBody, Constants.NAME)) {
            String[] param = { MessageKeys.TAG_NAME };
            throw new ServiceWarningException("Tag name is required.", MessageKeys.FIELD_REQUIRED, param);
        } else if (!ValidationUtil.hasKey(jsonPostBody, Constants.STANDARD_NAME)
                        || ValidationUtil.isFieldEmpty(jsonPostBody, Constants.STANDARD_NAME)) {
            String[] param = { MessageKeys.TAG_STANDARD_NAME };
            throw new ServiceWarningException("Tag standard name is required.", MessageKeys.FIELD_REQUIRED, param);
        }
    }

    public void validateUpdate(String putBody) {
        JSONObject jsonPutBody = JSONObject.fromObject(putBody).optJSONObject(Constants.TAG);
        if (!ValidationUtil.hasKey(jsonPutBody, Constants.FIELD_ID)
                        || ValidationUtil.isFieldEmpty(jsonPutBody, Constants.FIELD_ID)) {
            String[] param = { MessageKeys.FIELD_ID };
            throw new ServiceWarningException("ID is required.", MessageKeys.FIELD_REQUIRED, param);
        } else if (!ValidationUtil.hasKey(jsonPutBody, Constants.NAME)
                        || ValidationUtil.isFieldEmpty(jsonPutBody, Constants.NAME)) {
            String[] param = { MessageKeys.TAG_NAME };
            throw new ServiceWarningException("Tag name is required.", MessageKeys.FIELD_REQUIRED, param);
        }
    }

}
