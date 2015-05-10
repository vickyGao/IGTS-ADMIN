package com.ntu.igts.resource;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.model.SessionContext;
import com.ntu.igts.model.container.LoginForm;
import com.ntu.igts.service.AuthorizationService;
import com.ntu.igts.utils.JsonUtil;
import com.ntu.igts.validator.LoginValidator;

@Component
@Path("authorization")
public class AuthorizationResource {

    @Resource
    private AuthorizationService authorizationService;
    @Resource
    private LoginValidator loginValidator;

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(String inString) {
        loginValidator.validateLogin(inString);
        LoginForm loginForm = JsonUtil.getPojoFromJsonString(inString, LoginForm.class);
        SessionContext sessionContext = authorizationService.login(loginForm);
        return JsonUtil.getJsonStringFromPojo(sessionContext);
    }

    @GET
    @Path("logout")
    @Produces(MediaType.APPLICATION_JSON)
    public void logout(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token) {
        authorizationService.logout(token);
    }
}
