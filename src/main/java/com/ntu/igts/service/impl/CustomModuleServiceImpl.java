package com.ntu.igts.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.model.CustomModule;
import com.ntu.igts.model.container.CustomModuleList;
import com.ntu.igts.service.CustomModuleService;
import com.ntu.igts.utils.InvocationUtil;
import com.ntu.igts.utils.JsonUtil;

@Service
public class CustomModuleServiceImpl implements CustomModuleService {

    @Override
    public CustomModule createCustomModule(String token, CustomModule customModule) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String postBody = JsonUtil.getJsonStringFromPojo(customModule);
        String response = InvocationUtil.sendPostRequest(Constants.URL_CUSTOM_MODULE_ENTITY, header,
                        MediaType.APPLICATION_JSON, postBody, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, CustomModule.class);
    }

    @Override
    public CustomModule updateCustomModule(String token, CustomModule customModule) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String putBody = JsonUtil.getJsonStringFromPojo(customModule);
        String response = InvocationUtil.sendPutRequest(Constants.URL_CUSTOM_MODULE_ENTITY, header,
                        MediaType.APPLICATION_JSON, putBody, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, CustomModule.class);
    }

    @Override
    public void delete(String token, String customModuleId) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String path = Constants.URL_CUSTOM_MODULE_ENTITY + "/" + customModuleId;
        InvocationUtil.sendDeleteRequest(path, header, MediaType.TEXT_PLAIN);
    }

    @Override
    public CustomModuleList getAll(String token) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String response = InvocationUtil.sendGetRequest(Constants.URL_CUSTOM_MODULE_ADMIN_ENTITY, header,
                        MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, CustomModuleList.class);
    }

    @Override
    public CustomModule getDetailById(String token, String customModuleId) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String path = Constants.URL_CUSTOM_MODULE_DETAIL + "/" + customModuleId;
        String response = InvocationUtil.sendGetRequest(path, header, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, CustomModule.class);
    }

}
