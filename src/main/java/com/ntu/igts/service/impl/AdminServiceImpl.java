package com.ntu.igts.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.model.Admin;
import com.ntu.igts.model.container.Pagination;
import com.ntu.igts.model.container.Query;
import com.ntu.igts.service.AdminService;
import com.ntu.igts.utils.ConfigManagmentUtil;
import com.ntu.igts.utils.InvocationUtil;
import com.ntu.igts.utils.JsonUtil;
import com.ntu.igts.utils.StringUtil;

@Service
public class AdminServiceImpl implements AdminService {

    @Override
    public Admin getAdminByToken(String token) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String response = InvocationUtil.sendGetRequest(Constants.URL_ADMIN_GET_BY_TOKEN, header,
                        MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, Admin.class);
    }

    @Override
    public Admin GetDetailById(String token, String adminId) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String path = Constants.URL_ADMIN_DETAIL + "/" + adminId;
        String response = InvocationUtil.sendGetRequest(path, header, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, Admin.class);
    }

    @Override
    public Admin updateAdmin(String token, Admin admin) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String putBody = JsonUtil.getJsonStringFromPojo(admin);
        String response = InvocationUtil.sendPutRequest(Constants.URL_ADMIN_ENTITY, header, MediaType.APPLICATION_JSON,
                        putBody, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, Admin.class);
    }

    @Override
    public Admin createAdmin(String token, Admin admin) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String postBody = JsonUtil.getJsonStringFromPojo(admin);
        String response = InvocationUtil.sendPostRequest(Constants.URL_ADMIN_ENTITY, header,
                        MediaType.APPLICATION_JSON, postBody, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, Admin.class);
    }

    @Override
    public void deleteAdmin(String token, String adminId) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String path = Constants.URL_ADMIN_ENTITY + "/" + adminId;
        InvocationUtil.sendDeleteRequest(path, header, MediaType.TEXT_PLAIN);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pagination<Admin> getPaginatedAdmins(String token, Query query) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        Map<String, String> queryParams = new HashMap<String, String>();
        if (!StringUtil.isEmpty(query.getSearchTerm())) {
            queryParams.put(Constants.SEARCH_TERM, query.getSearchTerm());
        }
        if (query.getOrderBy() != null) {
            queryParams.put(Constants.ORDERBY, query.getOrderBy().name());
        }
        if (query.getSortBy() != null) {
            queryParams.put(Constants.SORTBY, query.getSortBy().name());
        }
        if (query.getPage() > 0) {
            queryParams.put(Constants.PAGE, String.valueOf(query.getPage()));
        }
        if (query.getSize() > 0) {
            queryParams.put(Constants.SIZE, String.valueOf(query.getSize()));
        } else {
            queryParams.put(Constants.SIZE, ConfigManagmentUtil.getConfigProperties(Constants.DEFAULT_PAGINATION_SIZE));
        }
        String response = InvocationUtil.sendGetRequest(Constants.URL_ADMIN_SEARCH_TERM, header,
                        MediaType.APPLICATION_JSON, queryParams);
        return JsonUtil.getPojoFromJsonString(response, Pagination.class);
    }

    @Override
    public int getTotalCount(String token) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String response = InvocationUtil.sendGetRequest(Constants.URL_ADMIN_TOTALCOUNT, header, MediaType.TEXT_PLAIN);
        return Integer.valueOf(response);
    }

}
