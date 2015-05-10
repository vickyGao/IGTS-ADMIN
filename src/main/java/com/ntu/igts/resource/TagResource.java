package com.ntu.igts.resource;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.model.container.TagList;
import com.ntu.igts.service.TagService;
import com.ntu.igts.utils.JsonUtil;

@Component
@Path("tag")
public class TagResource {

    @Resource
    private TagService tagService;

    @GET
    @Path("detail")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllTagsWithSubTags(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token) {
        TagList tagsWitSubTags = tagService.getAllTagsWithSubTags(token);
        return JsonUtil.getJsonStringFromPojo(tagsWitSubTags);
    }
}
