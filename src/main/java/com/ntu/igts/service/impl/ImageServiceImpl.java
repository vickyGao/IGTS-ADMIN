package com.ntu.igts.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.model.Image;
import com.ntu.igts.model.container.ImageList;
import com.ntu.igts.service.ImageService;
import com.ntu.igts.utils.InvocationUtil;
import com.ntu.igts.utils.JsonUtil;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public Image createImage(String token, Image image) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String postBody = JsonUtil.getJsonStringFromPojo(image);
        String response = InvocationUtil.sendPostRequest(Constants.URL_IMAGE_ADMIN_ENTITY, header,
                        MediaType.APPLICATION_JSON, postBody, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, Image.class);
    }

    @Override
    public Image updateImage(String token, Image image) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String putBody = JsonUtil.getJsonStringFromPojo(image);
        String response = InvocationUtil.sendPutRequest(Constants.URL_IMAGE_ADMIN_ENTITY, header,
                        MediaType.APPLICATION_JSON, putBody, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, Image.class);
    }

    @Override
    public void deleteImage(String token, String imageId) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String path = Constants.URL_IMAGE_ADMIN_ENTITY + "/" + imageId;
        InvocationUtil.sendDeleteRequest(path, header, MediaType.TEXT_PLAIN);
    }

    @Override
    public ImageList getImagesByToken(String token) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String response = InvocationUtil.sendGetRequest(Constants.URL_IMAGE_GET_ENTITY_BY_TOKEN, header,
                        MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, ImageList.class);
    }

    @Override
    public String getStoragePath(String token) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String response = InvocationUtil.sendGetRequest(Constants.URL_IMAGE_ADMIN_LOCATION, header,
                        MediaType.TEXT_PLAIN);
        return response;
    }

    @Override
    public Image getImageByFileName(String token, String fileName, String suffix) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put(Constants.FILENAME, fileName);
        queryParams.put(Constants.SUFFIX, suffix);
        String response = InvocationUtil.sendGetRequest(Constants.URL_IMAGE_ADMIN_ENTITY, header,
                        MediaType.APPLICATION_JSON, queryParams);
        return JsonUtil.getPojoFromJsonString(response, Image.class);
    }

    @Override
    public Image getImageById(String token, String imageId) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String path = Constants.URL_IMAGE_ADMIN_ENTITY + "/" + imageId;
        String response = InvocationUtil.sendGetRequest(path, header, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, Image.class);
    }

}
