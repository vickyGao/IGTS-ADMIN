package com.ntu.igts.service.impl;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.model.SessionContext;
import com.ntu.igts.model.container.LoginForm;
import com.ntu.igts.service.LoginService;
import com.ntu.igts.utils.InvocationUtil;
import com.ntu.igts.utils.JsonUtil;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public SessionContext login(LoginForm loginForm) {
        String postBody = JsonUtil.getJsonStringFromPojo(loginForm);
        String response = InvocationUtil.sendPostRequest(Constants.URL_LOGIN, null, MediaType.APPLICATION_JSON,
                        postBody, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, SessionContext.class);
    }

}
