package com.ntu.igts.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.model.Slice;
import com.ntu.igts.model.container.SliceList;
import com.ntu.igts.service.SliceService;
import com.ntu.igts.utils.InvocationUtil;
import com.ntu.igts.utils.JsonUtil;

@Service
public class SliceServiceImpl implements SliceService {

    @Override
    public Slice createSlice(String token, Slice slice) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String postBody = JsonUtil.getJsonStringFromPojo(slice);
        String response = InvocationUtil.sendPostRequest(Constants.URL_SLICE_ENTITY, header,
                        MediaType.APPLICATION_JSON, postBody, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, Slice.class);
    }

    @Override
    public Slice updateSlice(String token, Slice slice) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String putBody = JsonUtil.getJsonStringFromPojo(slice);
        String response = InvocationUtil.sendPutRequest(Constants.URL_SLICE_ENTITY, header, MediaType.APPLICATION_JSON,
                        putBody, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, Slice.class);
    }

    @Override
    public void delete(String token, String sliceId) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String path = Constants.URL_SLICE_ENTITY + "/" + sliceId;
        InvocationUtil.sendDeleteRequest(path, header, MediaType.TEXT_PLAIN);
    }

    @Override
    public Slice getDetailByid(String token, String sliceId) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String path = Constants.URL_SLICE_DETAIL + "/" + sliceId;
        String response = InvocationUtil.sendGetRequest(path, header, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, Slice.class);
    }

    @Override
    public SliceList getAll(String token) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String response = InvocationUtil.sendGetRequest(Constants.URL_SLICE_ADMIN_DETAIL, header,
                        MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, SliceList.class);
    }

}
