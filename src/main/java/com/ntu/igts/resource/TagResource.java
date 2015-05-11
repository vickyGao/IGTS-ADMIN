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
import com.ntu.igts.model.Tag;
import com.ntu.igts.model.container.TagList;
import com.ntu.igts.service.TagService;
import com.ntu.igts.utils.JsonUtil;
import com.ntu.igts.validator.TagValidator;

@Component
@Path("tag")
public class TagResource {

    @Resource
    private TagService tagService;
    @Resource
    private TagValidator tagValidator;

    @POST
    @Path("entity")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String create(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, String inString) {
        tagValidator.validateCreate(inString);
        Tag tag = JsonUtil.getPojoFromJsonString(inString, Tag.class);
        Tag createdTag = tagService.createTag(token, tag);
        return JsonUtil.getJsonStringFromPojo(createdTag);
    }

    @PUT
    @Path("entity")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String update(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, String inString) {
        tagValidator.validateUpdate(inString);
        Tag tag = JsonUtil.getPojoFromJsonString(inString, Tag.class);
        Tag updatedTag = tagService.updateTag(token, tag);
        return JsonUtil.getJsonStringFromPojo(updatedTag);
    }

    @DELETE
    @Path("entity/{tagid}")
    @Produces(MediaType.TEXT_PLAIN)
    public void delete(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, @PathParam("tagid") String tagId) {
        tagService.deleteTag(token, tagId);
    }

    @GET
    @Path("detail")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllTagsWithSubTags(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token) {
        TagList tagsWitSubTags = tagService.getAllTagsWithSubTags(token);
        return JsonUtil.getJsonStringFromPojo(tagsWitSubTags);
    }

    @GET
    @Path("entity")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllTopLevelTags(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token) {
        TagList allTopLevelTags = tagService.getAllTopLevelTags(token);
        return JsonUtil.getJsonStringFromPojo(allTopLevelTags);
    }

    @GET
    @Path("entity/{tagid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTagById(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, @PathParam("tagid") String tagId) {
        Tag returnTag = tagService.getTagById(token, tagId);
        return JsonUtil.getJsonStringFromPojo(returnTag);
    }
}
