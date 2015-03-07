package com.ntu.igts.resource;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.ntu.igts.model.SessionContext;
import com.ntu.igts.model.container.LoginForm;
import com.ntu.igts.service.LoginService;
import com.ntu.igts.utils.JsonUtil;
import com.ntu.igts.validator.LoginValidator;

@Component
@Path("login")
public class Login extends BaseResource {

    @Resource
    private LoginService loginService;
    @Resource
    private LoginValidator loginValidator;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(String inString) {
        loginValidator.validateLogin(inString);
        LoginForm loginForm = JsonUtil.getPojoFromJsonString(inString, LoginForm.class);
        SessionContext sessionContext = loginService.login(loginForm);
        return JsonUtil.getJsonStringFromPojo(sessionContext);
    }
}
