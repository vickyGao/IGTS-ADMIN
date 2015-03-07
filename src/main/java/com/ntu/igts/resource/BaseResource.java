package com.ntu.igts.resource;

import java.util.Date;
import java.util.UUID;

import com.ntu.igts.model.SessionContext;
import com.ntu.igts.utils.JsonUtil;

public class BaseResource {

    protected String doPost(String path, String token, String postBody) {
        System.out.println("doPost: " + postBody);
        SessionContext sessionContext = new SessionContext();
        sessionContext.setToken(UUID.randomUUID().toString());
        sessionContext.setLoginTime(new Date());
        return JsonUtil.getJsonStringWithRootFromPojo(sessionContext);
    }
}
