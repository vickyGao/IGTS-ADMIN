package com.ntu.igts.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.model.SessionContext;
import com.ntu.igts.model.container.LoginForm;
import com.ntu.igts.service.AuthorizationService;
import com.ntu.igts.utils.InvocationUtil;
import com.ntu.igts.utils.JsonUtil;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Override
    public SessionContext login(LoginForm loginForm) {
        String postBody = JsonUtil.getJsonStringFromPojo(loginForm);
        String response = InvocationUtil.sendPostRequest(Constants.URL_LOGIN, null, MediaType.APPLICATION_JSON,
                        postBody, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, SessionContext.class);
    }

    @Override
    public void logout(String token) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        InvocationUtil.sendGetRequest(Constants.URL_LOGOUT, header, MediaType.APPLICATION_JSON);
    }

}
