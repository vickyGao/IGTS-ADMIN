package com.ntu.igts.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.enums.ActiveStateEnum;
import com.ntu.igts.model.User;
import com.ntu.igts.model.container.Pagination;
import com.ntu.igts.model.container.Query;
import com.ntu.igts.service.UserService;
import com.ntu.igts.utils.ConfigManagmentUtil;
import com.ntu.igts.utils.InvocationUtil;
import com.ntu.igts.utils.JsonUtil;
import com.ntu.igts.utils.StringUtil;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User updateActiveState(String token, ActiveStateEnum activeStateEnum, String userId) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String path = Constants.URL_USER_ENTITY + "/" + activeStateEnum.name() + "/" + userId;
        String response = InvocationUtil.sendPutRequest(path, header, MediaType.APPLICATION_JSON, StringUtil.EMPTY,
                        MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, User.class);
    }

    @Override
    public void deleteUser(String token, String userId) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String path = Constants.URL_USER_ENTITY + "/" + userId;
        InvocationUtil.sendDeleteRequest(path, header, MediaType.TEXT_PLAIN);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pagination<User> getUserByPage(String token, Query query) {
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
        String response = InvocationUtil.sendGetRequest(Constants.URL_USER_SEARCH_TERM, header,
                        MediaType.APPLICATION_JSON, queryParams);
        return JsonUtil.getPojoFromJsonString(response, Pagination.class);
    }

    @Override
    public User getUserDetail(String token, String userId) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String path = Constants.URL_USER_ADMIN_DETAIL + "/" + userId;
        String response = InvocationUtil.sendGetRequest(path, header, MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, User.class);
    }

    @Override
    public int getTotalCount(String token) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String response = InvocationUtil.sendGetRequest(Constants.URL_USER_TOTALCOUNT, header, MediaType.TEXT_PLAIN);
        return Integer.valueOf(response);
    }

}
