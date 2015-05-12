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
import com.ntu.igts.model.Slice;
import com.ntu.igts.model.container.SliceList;
import com.ntu.igts.service.SliceService;
import com.ntu.igts.utils.JsonUtil;

@Component
@Path("slice")
public class SliceResource {

    @Resource
    private SliceService sliceService;

    @POST
    @Path("entity")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String create(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, String inString) {
        Slice slice = JsonUtil.getPojoFromJsonString(inString, Slice.class);
        Slice createdSlice = sliceService.createSlice(token, slice);
        return JsonUtil.getJsonStringFromPojo(createdSlice);
    }

    @PUT
    @Path("entity")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String udpate(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, String inString) {
        Slice slice = JsonUtil.getPojoFromJsonString(inString, Slice.class);
        Slice updatedSlice = sliceService.updateSlice(token, slice);
        return JsonUtil.getJsonStringFromPojo(updatedSlice);
    }

    @DELETE
    @Path("entity/{sliceid}")
    @Produces(MediaType.TEXT_PLAIN)
    public void delete(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, @PathParam("sliceid") String sliceId) {
        sliceService.delete(token, sliceId);
    }

    @GET
    @Path("detail/{sliceid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSliceById(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token,
                    @PathParam("sliceid") String sliceId) {
        Slice returnSlice = sliceService.getDetailByid(token, sliceId);
        return JsonUtil.getJsonStringFromPojo(returnSlice);
    }

    @GET
    @Path("detail")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSlices(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token) {
        SliceList sliceList = sliceService.getAll(token);
        return JsonUtil.getJsonStringFromPojo(sliceList);
    }
}
