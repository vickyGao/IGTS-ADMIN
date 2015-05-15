package com.ntu.igts.resource;

import javax.annotation.Resource;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.exception.ServiceWarningException;
import com.ntu.igts.i18n.MessageKeys;
import com.ntu.igts.model.Admin;
import com.ntu.igts.model.container.Pagination;
import com.ntu.igts.model.container.Query;
import com.ntu.igts.service.AdminService;
import com.ntu.igts.utils.ConfigManagmentUtil;
import com.ntu.igts.utils.JsonUtil;
import com.ntu.igts.utils.StringUtil;
import com.ntu.igts.validator.AdminValidator;

@Component
@Path("admin")
public class AdminResource {

    @Resource
    private AdminService adminService;
    @Resource
    private AdminValidator adminValidator;

    @POST
    @Path("entity")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createAdmin(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, String inString) {
        adminValidator.validateCreate(inString);
        Admin admin = JsonUtil.getPojoFromJsonString(inString, Admin.class);
        admin.setAdminPassword(ConfigManagmentUtil.getConfigProperties(Constants.DEFAULT_PASSWORD));
        Admin createdAdmin = adminService.createAdmin(token, admin);
        return JsonUtil.getJsonStringFromPojo(createdAdmin);
    }

    @GET
    @Path("detail/token")
    public String getAdminDetailByToken(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token) {
        Admin admin = adminService.getAdminByToken(token);
        admin.setAdminPassword(StringUtil.EMPTY);
        return JsonUtil.getJsonStringFromPojo(admin);
    }

    @PUT
    @Path("entity")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String update(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, String inString) {
        adminValidator.validateUpdate(inString);
        Admin admin = JsonUtil.getPojoFromJsonString(inString, Admin.class);
        if (admin.getNewPwd1().equals(admin.getNewPwd2())) {
            admin.setNewPassword(admin.getNewPwd1());
        } else {
            throw new ServiceWarningException("Input not consistent", MessageKeys.INPUT_NOT_CONSISTENT);
        }
        Admin updatedAdmin = adminService.updateAdmin(token, admin);
        return JsonUtil.getJsonStringFromPojo(updatedAdmin);
    }

    @DELETE
    @Path("entity/{adminid}")
    @Produces(MediaType.TEXT_PLAIN)
    public void delete(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, @PathParam("adminid") String adminId) {
        adminService.deleteAdmin(token, adminId);
    }

    @GET
    @Path("detail/{adminid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAdminById(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token,
                    @PathParam("adminid") String adminId) {
        Admin returnAdmin = adminService.GetDetailById(token, adminId);
        return JsonUtil.getJsonStringFromPojo(returnAdmin);
    }

    @GET
    @Path("search_term")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPaginatedAdmins(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, @BeanParam Query query) {
        Pagination<Admin> pagination = adminService.getPaginatedAdmins(token, query);
        return JsonUtil.getJsonStringFromPojo(pagination);
    }

    @GET
    @Path("totalcount")
    @Produces(MediaType.TEXT_PLAIN)
    public int getTotalCount(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token) {
        return adminService.getTotalCount(token);
    }
}
