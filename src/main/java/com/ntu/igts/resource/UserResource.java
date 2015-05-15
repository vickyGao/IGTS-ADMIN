package com.ntu.igts.resource;

import javax.annotation.Resource;
import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.enums.ActiveStateEnum;
import com.ntu.igts.model.User;
import com.ntu.igts.model.container.Pagination;
import com.ntu.igts.model.container.Query;
import com.ntu.igts.service.UserService;
import com.ntu.igts.utils.JsonUtil;

@Component
@Path("user")
public class UserResource {

    @Resource
    private UserService userService;

    @PUT
    @Path("entity/{activeyn}/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateUserActiveYnState(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token,
                    @PathParam("activeyn") ActiveStateEnum activeStateEnum, @PathParam("userid") String userId) {
        User updatedUser = userService.updateActiveState(token, activeStateEnum, userId);
        return JsonUtil.getJsonStringFromPojo(updatedUser);
    }

    @DELETE
    @Path("entity/{userid}")
    @Produces(MediaType.TEXT_PLAIN)
    public void delete(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, @PathParam("userid") String userId) {
        userService.deleteUser(token, userId);
    }

    @GET
    @Path("search_term")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserByPage(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, @BeanParam Query query) {
        Pagination<User> pagination = userService.getUserByPage(token, query);
        return JsonUtil.getJsonStringFromPojo(pagination);
    }

    @GET
    @Path("detail/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserDetailById(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token,
                    @PathParam("userid") String userId) {
        User returnUser = userService.getUserDetail(token, userId);
        return JsonUtil.getJsonStringFromPojo(returnUser);
    }

    @GET
    @Path("totalcount")
    @Produces(MediaType.TEXT_PLAIN)
    public int getTotalCount(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token) {
        return userService.getTotalCount(token);
    }
}
