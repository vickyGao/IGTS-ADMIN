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
import com.ntu.igts.enums.ActiveStateEnum;
import com.ntu.igts.model.SensitiveWord;
import com.ntu.igts.model.container.Pagination;
import com.ntu.igts.model.container.Query;
import com.ntu.igts.service.SensitiveWordService;
import com.ntu.igts.utils.JsonUtil;

@Component
@Path("sensitiveword")
public class SensitiveWordResource {

    @Resource
    private SensitiveWordService sensitiveWordService;

    @POST
    @Path("entity")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String create(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, String inString) {
        SensitiveWord word = JsonUtil.getPojoFromJsonString(inString, SensitiveWord.class);
        SensitiveWord createdSensitiveWord = sensitiveWordService.create(token, word);
        return JsonUtil.getJsonStringFromPojo(createdSensitiveWord);
    }

    @PUT
    @Path("status/{state}/{sensitivewordid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateSensitiveWordState(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token,
                    @PathParam("state") ActiveStateEnum activeStateEnum,
                    @PathParam("sensitivewordid") String sensitiveWordId) {
        SensitiveWord updatedSensitiveWord = sensitiveWordService.updateSensitiveWordState(token, activeStateEnum,
                        sensitiveWordId);
        return JsonUtil.getJsonStringFromPojo(updatedSensitiveWord);
    }

    @DELETE
    @Path("entity/{sensitivewordid}")
    @Produces(MediaType.TEXT_PLAIN)
    public void delete(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token,
                    @PathParam("sensitivewordid") String sensitiveWordId) {
        sensitiveWordService.delete(token, sensitiveWordId);
    }

    @GET
    @Path("search_term")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBySearchTerm(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, @BeanParam Query query) {
        Pagination<SensitiveWord> pagination = sensitiveWordService.getBySearchTerm(token, query);
        return JsonUtil.getJsonStringFromPojo(pagination);
    }
}
