package com.ntu.igts.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.model.Hot;
import com.ntu.igts.model.container.HotList;
import com.ntu.igts.service.HotService;
import com.ntu.igts.utils.InvocationUtil;
import com.ntu.igts.utils.JsonUtil;

@Service
public class HotServiceImpl implements HotService {

    @Override
    public Hot createHotCommodity(String token, Hot hot) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String postBody = JsonUtil.getJsonStringFromPojo(hot);
        String response = InvocationUtil.sendPostRequest(Constants.URL_HOT_ENTITY, header, MediaType.APPLICATION_JSON,
                        postBody, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, Hot.class);
    }

    @Override
    public Hot updateHotCommodity(String token, Hot hot) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String putBody = JsonUtil.getJsonStringFromPojo(hot);
        String response = InvocationUtil.sendPostRequest(Constants.URL_HOT_ENTITY, header, MediaType.APPLICATION_JSON,
                        putBody, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, Hot.class);
    }

    @Override
    public void delete(String token, String hotId) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        InvocationUtil.sendDeleteRequest(Constants.URL_HOT_ENTITY, header, MediaType.TEXT_PLAIN);
    }

    @Override
    public Hot getDetailById(String token, String hotId) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String path = Constants.URL_HOT_ADMIN_DETAIL + "/" + hotId;
        String response = InvocationUtil.sendGetRequest(path, header, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, Hot.class);
    }

    @Override
    public HotList getHotCommodities(String token) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String response = InvocationUtil.sendGetRequest(Constants.URL_HOT_DETAIL, header, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, HotList.class);
    }

}
