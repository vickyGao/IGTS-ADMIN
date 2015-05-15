package com.ntu.igts.resource;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.model.Message;
import com.ntu.igts.model.container.Pagination;
import com.ntu.igts.service.MessageService;
import com.ntu.igts.utils.JsonUtil;

@Component
@Path("message")
public class MessageResource {

    @Resource
    private MessageService messageService;

    @GET
    @Path("entity")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMessagesForCommodity(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token,
                    @QueryParam("page") int currentPage, @QueryParam("size") int pageSize,
                    @QueryParam("commodityid") String commodityId) {
        Pagination<Message> pagination = messageService.getMessagesForCommodity(token, currentPage, pageSize,
                        commodityId);
        return JsonUtil.getJsonStringFromPojo(pagination);
    }
}
