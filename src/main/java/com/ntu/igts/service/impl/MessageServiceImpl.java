package com.ntu.igts.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.model.Message;
import com.ntu.igts.model.container.Pagination;
import com.ntu.igts.service.MessageService;
import com.ntu.igts.utils.ConfigManagmentUtil;
import com.ntu.igts.utils.InvocationUtil;
import com.ntu.igts.utils.JsonUtil;

@Service
public class MessageServiceImpl implements MessageService {

    @SuppressWarnings("unchecked")
    @Override
    public Pagination<Message> getMessagesForCommodity(String token, int currentPage, int pageSize, String commodityId) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put(Constants.PAGE, String.valueOf(currentPage));
        if (pageSize > 0) {
            queryParams.put(Constants.SIZE, String.valueOf(pageSize));
        } else {
            queryParams.put(Constants.SIZE, ConfigManagmentUtil.getConfigProperties(Constants.DEFAULT_PAGINATION_SIZE));
        }
        queryParams.put(Constants.COMMODITY_ID, commodityId);
        String response = InvocationUtil.sendGetRequest(Constants.URL_MESSAGE_ADMIN_ENTITY, header,
                        MediaType.APPLICATION_JSON, queryParams);
        return JsonUtil.getPojoFromJsonString(response, Pagination.class);
    }

}
