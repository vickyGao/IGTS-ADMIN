package com.ntu.igts.utils;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

public class ValidationUtil {

    public static boolean isFieldEmpty(JSONObject jsonObject, String key) {
        JSONNull jsonNull = JSONNull.getInstance();
        if (!jsonNull.equals(jsonObject)) {
            String field = jsonObject.optString(key);
            if (StringUtil.isEmpty(field)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasKey(JSONObject jsonObject, String key) {
        JSONNull jsonNull = JSONNull.getInstance();
        if (!jsonNull.equals(jsonObject)) {
            return jsonObject.containsKey(key);
        } else {
            return false;
        }
    }
}
