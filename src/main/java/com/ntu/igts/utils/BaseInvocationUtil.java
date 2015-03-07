package com.ntu.igts.utils;

import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class BaseInvocationUtil {

    public static WebTarget getWebTarget(String rootPath) {
        Client client = ClientBuilder.newClient();
        return client.target(rootPath);
    }

    public static Response sendGetRequest(String rootPath, String readPath, Map<String, String> header,
                    String reponseType) {
        return sendGetRequest(rootPath, readPath, header, reponseType, null);
    }

    public static Response sendPostRequest(String rootPath, String readPath, Map<String, String> header,
                    String reponseType, Object postBody, String bodyType) {
        WebTarget webTarget = getWebTarget(rootPath);
        Builder requestBuild = webTarget.path(readPath).request(reponseType);
        if (header != null) {
            for (Entry<String, String> entry : header.entrySet()) {
                requestBuild.header(entry.getKey(), entry.getValue());
            }
        }
        return requestBuild.post(Entity.entity(postBody, bodyType));
    }

    public static Response sendPutRequest(String rootPath, String readPath, Map<String, String> header,
                    String reponseType, Object postBody, String bodyType) {
        WebTarget webTarget = getWebTarget(rootPath);
        Builder requestBuild = webTarget.path(readPath).request(reponseType);
        for (Entry<String, String> entry : header.entrySet()) {
            requestBuild.header(entry.getKey(), entry.getValue());
        }
        return requestBuild.put(Entity.entity(postBody, bodyType));
    }

    public static Response sendDeleteRequest(String rootPath, String readPath, Map<String, String> header,
                    String reponseType) {
        WebTarget webTarget = getWebTarget(rootPath);
        Builder requestBuild = webTarget.path(readPath).request(reponseType);
        for (Entry<String, String> entry : header.entrySet()) {
            requestBuild.header(entry.getKey(), entry.getValue());
        }
        return requestBuild.delete();
    }

    public static Response sendGetRequest(String rootPath, String readPath, Map<String, String> header,
                    String reponseType, Map<String, String> queryParams) {
        WebTarget webTarget = getWebTarget(rootPath).path(readPath);

        if (null != queryParams) {
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                webTarget = webTarget.queryParam(entry.getKey(), entry.getValue());
            }
        }

        Builder requestBuild = webTarget.request(reponseType);
        for (Entry<String, String> entry : header.entrySet()) {
            requestBuild.header(entry.getKey(), entry.getValue());
        }
        return requestBuild.get();
    }
}
