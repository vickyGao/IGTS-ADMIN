package com.ntu.igts.validator;

import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.exception.ServiceWarningException;
import com.ntu.igts.i18n.MessageKeys;
import com.ntu.igts.utils.ValidationUtil;

@Component
public class AdminValidator {

    public void validateUpdate(String putBody) {
        JSONObject jsonPutBody = JSONObject.fromObject(putBody).optJSONObject(Constants.ADMIN);
        if (!ValidationUtil.hasKey(jsonPutBody, Constants.ADMIN_PASSWORD)
                        || ValidationUtil.isFieldEmpty(jsonPutBody, Constants.ADMIN_PASSWORD)) {
            String[] param = { MessageKeys.ADMIN_PASSWORD };
            throw new ServiceWarningException("Admin password is required", MessageKeys.FIELD_REQUIRED, param);
        } else if ((!ValidationUtil.hasKey(jsonPutBody, Constants.NEWPWD1) || ValidationUtil.isFieldEmpty(jsonPutBody,
                        Constants.NEWPWD1))
                        && (!ValidationUtil.hasKey(jsonPutBody, Constants.NEWPWD2) || ValidationUtil.isFieldEmpty(
                                        jsonPutBody, Constants.NEWPWD2))) {
            String[] param = { MessageKeys.ADMIN_NEW_PASSWORD };
            throw new ServiceWarningException("New password is required", MessageKeys.FIELD_REQUIRED, param);
        }
    }
}
