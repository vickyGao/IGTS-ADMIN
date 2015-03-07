package com.ntu.igts.utils;

import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.exception.ServiceErrorException;
import com.ntu.igts.exception.ServiceWarningException;
import com.ntu.igts.exception.UnAuthorizedException;
import com.ntu.igts.i18n.MessageKeys;

public class InvocationUtil {

    public static String getUrlRoot() {
        StringBuilder sbUrl = new StringBuilder(Constants.HTTP_PROTOCOL);
        sbUrl.append(ConfigManagmentUtil.getConfigProperties(Constants.MGMT_HOST_PROPS_KEY));
        sbUrl.append(":");
        sbUrl.append(ConfigManagmentUtil.getConfigProperties(Constants.MGMT_PORT_PROPS_KEY));
        sbUrl.append(ConfigManagmentUtil.getConfigProperties(Constants.MGMT_BASE_URI_PROPS_KEY));
        return sbUrl.toString();
    }

    public static String sendPostRequest(String readPath, Map<String, String> header, String reponseType,
                    Object postBody, String bodyType) {
        Response response = BaseInvocationUtil.sendPostRequest(getUrlRoot(), readPath, header, reponseType, postBody,
                        bodyType);
        return handleResponse(response);
    }

    public static String getResponseOfPostRequest(String readPath, Map<String, String> header, String reponseType,
                    Object postBody, String bodyType) {
        Response response = BaseInvocationUtil.sendPostRequest(getUrlRoot(), readPath, header, reponseType, postBody,
                        bodyType);
        return handleResponse(response);
    }

    public static String sendPutRequest(String readPath, Map<String, String> header, String reponseType,
                    Object postBody, String bodyType) {
        Response response = BaseInvocationUtil.sendPutRequest(getUrlRoot(), readPath, header, reponseType, postBody,
                        bodyType);
        return handleResponse(response);
    }

    public static String sendDeleteRequest(String readPath, Map<String, String> header, String reponseType) {
        Response response = BaseInvocationUtil.sendDeleteRequest(getUrlRoot(), readPath, header, reponseType);
        return handleResponse(response);
    }

    public static String sendGetRequest(String readPath, Map<String, String> header, String reponseType) {
        return sendGetRequest(readPath, header, reponseType, null);
    }

    public static String sendGetRequest(String readPath, Map<String, String> header, String reponseType,
                    Map<String, String> queryParams) {
        Response response = BaseInvocationUtil.sendGetRequest(getUrlRoot(), readPath, header, reponseType, queryParams);
        return handleResponse(response);
    }

    private static String handleResponse(Response response) {
        int originRespCode = response.getStatus();
        String result = null;
        if (originRespCode >= Status.OK.getStatusCode() && originRespCode <= Status.PARTIAL_CONTENT.getStatusCode()) {
            result = response.readEntity(String.class);
            return result != null ? result : StringUtil.EMPTY;
        } else if (originRespCode == Status.UNAUTHORIZED.getStatusCode()) {
            throw new UnAuthorizedException("Error 401 Unauthorized", MessageKeys.UNAUTHORIZED);
        } else if (originRespCode == Status.PRECONDITION_FAILED.getStatusCode()) {
            result = response.readEntity(String.class);
            throw new ServiceWarningException(getExceptionMessage(result));
        } else {
            result = response.readEntity(String.class);
            throw new ServiceErrorException(getExceptionMessage(result));
        }
    }

    private static String getExceptionMessage(String result) {
        JSONNull jsonNull = JSONNull.getInstance();
        if (!jsonNull.equals(result)) {
            JSONObject jsonObject = JSONObject.fromObject(result);
            if (!jsonNull.equals(jsonObject)) {
                return jsonObject.optString("message");
            }
        }
        return StringUtil.EMPTY;
    }
}
