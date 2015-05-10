package com.ntu.igts.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.model.container.TagList;
import com.ntu.igts.service.TagService;
import com.ntu.igts.utils.InvocationUtil;
import com.ntu.igts.utils.JsonUtil;

@Service
public class TagServiceImpl implements TagService {

    @Override
    public TagList getAllTagsWithSubTags(String token) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.HEADER_X_AUTH_HEADER, token);
        String response = InvocationUtil.sendGetRequest(Constants.URL_TAG_DETAIL, header,
                        MediaType.APPLICATION_JSON);
        return JsonUtil.getPojoFromJsonString(response, TagList.class);
    }

}
