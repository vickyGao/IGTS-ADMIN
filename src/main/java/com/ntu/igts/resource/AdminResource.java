package com.ntu.igts.resource;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.model.Admin;
import com.ntu.igts.service.AdminService;
import com.ntu.igts.utils.JsonUtil;
import com.ntu.igts.utils.StringUtil;

@Component
@Path("admin")
public class AdminResource {

    @Resource
    private AdminService adminService;

    @GET
    @Path("detail/token")
    public String getAdminDetailByToken(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token) {
        Admin admin = adminService.getAdminByToken(token);
        admin.setAdminPassword(StringUtil.EMPTY);
        return JsonUtil.getJsonStringFromPojo(admin);
    }
}
