package com.ntu.igts.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.model.Tag;
import com.ntu.igts.model.container.TagList;
import com.ntu.igts.service.TagService;
import com.ntu.igts.utils.InvocationUtil;
import com.ntu.igts.utils.JsonUtil;

@Service
public class TagServiceImpl implements TagService {

    @Override
    public Tag createTag(String token, Tag tag) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String postBody = JsonUtil.getJsonStringFromPojo(tag);
        String response = InvocationUtil.sendPostRequest(Constants.URL_TAG_ENTITY, header, MediaType.APPLICATION_JSON,
                        postBody, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, Tag.class);
    }

    @Override
    public Tag updateTag(String token, Tag tag) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String putBody = JsonUtil.getJsonStringFromPojo(tag);
        String response = InvocationUtil.sendPutRequest(Constants.URL_TAG_ENTITY, header, MediaType.APPLICATION_JSON,
                        putBody, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, Tag.class);
    }

    @Override
    public void deleteTag(String token, String tagId) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String path = Constants.URL_TAG_ENTITY + "/" + tagId;
        InvocationUtil.sendDeleteRequest(path, header, MediaType.TEXT_PLAIN);
    }

    @Override
    public TagList getAllTagsWithSubTags(String token) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String response = InvocationUtil.sendGetRequest(Constants.URL_TAG_DETAIL, header, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, TagList.class);
    }

    @Override
    public Tag getTagById(String token, String tagId) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String path = Constants.URL_TAG_ENTITY + "/" + tagId;
        String response = InvocationUtil.sendGetRequest(path, header, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, Tag.class);
    }

    @Override
    public TagList getAllTopLevelTags(String token) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String response = InvocationUtil.sendGetRequest(Constants.URL_TAG_ENTITY, header, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, TagList.class);
    }

    @Override
    public int getTotalCount(String token) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String response = InvocationUtil.sendGetRequest(Constants.URL_TAG_TOTALCOUNT, header, MediaType.TEXT_PLAIN);
        return Integer.valueOf(response);
    }
}
