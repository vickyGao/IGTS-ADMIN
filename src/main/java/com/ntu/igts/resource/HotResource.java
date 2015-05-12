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
import com.ntu.igts.model.Hot;
import com.ntu.igts.model.container.HotList;
import com.ntu.igts.service.HotService;
import com.ntu.igts.utils.JsonUtil;

@Component
@Path("hot")
public class HotResource {

    @Resource
    private HotService hotService;

    @POST
    @Path("entity")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String create(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, String inString) {
        Hot hot = JsonUtil.getPojoFromJsonString(inString, Hot.class);
        Hot createdHot = hotService.createHotCommodity(token, hot);
        return JsonUtil.getJsonStringFromPojo(createdHot);
    }

    @PUT
    @Path("entity")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String update(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, String inString) {
        Hot hot = JsonUtil.getPojoFromJsonString(inString, Hot.class);
        Hot updatedHot = hotService.updateHotCommodity(token, hot);
        return JsonUtil.getJsonStringFromPojo(updatedHot);
    }

    @DELETE
    @Path("entity/{hotid}")
    @Produces(MediaType.TEXT_PLAIN)
    public void delete(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, @PathParam("hotid") String hotId) {
        hotService.delete(token, hotId);
    }

    @GET
    @Path("entity/{hotid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHotById(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, @PathParam("hotid") String hotId) {
        Hot returnHot = hotService.getDetailById(token, hotId);
        return JsonUtil.getJsonStringFromPojo(returnHot);
    }

    @GET
    @Path("detail")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHotCommodities(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token) {
        HotList hotList = hotService.getHotCommodities(token);
        return JsonUtil.getJsonStringFromPojo(hotList);
    }
}
