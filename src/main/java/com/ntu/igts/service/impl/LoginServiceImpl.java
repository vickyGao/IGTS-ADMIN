package com.ntu.igts.service.impl;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.ntu.igts.model.SessionContext;
import com.ntu.igts.model.container.LoginForm;
import com.ntu.igts.service.LoginService;
import com.ntu.igts.utils.InvocationUtil;
import com.ntu.igts.utils.JsonUtil;

@Component
public class LoginServiceImpl implements LoginService {

    @Override
    public SessionContext login(LoginForm loginForm) {
        String postBody = JsonUtil.getJsonStringWithRootFromPojo(loginForm);
        String response = InvocationUtil.sendPostRequest("login/user", null, MediaType.APPLICATION_JSON, postBody,
                        MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonStringWithRoot(response, SessionContext.class);
    }

}
