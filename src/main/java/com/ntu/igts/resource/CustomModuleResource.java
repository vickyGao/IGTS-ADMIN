package com.ntu.igts.resource;

import javax.annotation.Resource;
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
import com.ntu.igts.model.CustomModule;
import com.ntu.igts.model.container.CustomModuleList;
import com.ntu.igts.service.CustomModuleService;
import com.ntu.igts.utils.JsonUtil;

@Component
@Path("custommodule")
public class CustomModuleResource {

    @Resource
    private CustomModuleService customModuleService;

    @POST
    @Path("entity")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String create(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, String inString) {
        CustomModule customModule = JsonUtil.getPojoFromJsonString(inString, CustomModule.class);
        CustomModule createdCustomModule = customModuleService.createCustomModule(token, customModule);
        return JsonUtil.getJsonStringFromPojo(createdCustomModule);
    }

    @PUT
    @Path("entity")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String udpate(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, String inString) {
        CustomModule customModule = JsonUtil.getPojoFromJsonString(inString, CustomModule.class);
        CustomModule updatedCustomModule = customModuleService.updateCustomModule(token, customModule);
        return JsonUtil.getJsonStringFromPojo(updatedCustomModule);
    }

    @DELETE
    @Path("entity/{custommoduleid}")
    @Produces(MediaType.TEXT_PLAIN)
    public void delete(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token,
                    @PathParam("custommoduleid") String customModuleId) {
        customModuleService.delete(token, customModuleId);
    }

    @GET
    @Path("detail/{custommoduleid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSliceById(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token,
                    @PathParam("custommoduleid") String customModuleId) {
        CustomModule returnCustomModule = customModuleService.getDetailById(token, customModuleId);
        return JsonUtil.getJsonStringFromPojo(returnCustomModule);
    }

    @GET
    @Path("entity")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomModulesForAdmin(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token) {
        CustomModuleList customModuleList = customModuleService.getAll(token);
        return JsonUtil.getJsonStringFromPojo(customModuleList);
    }
}
