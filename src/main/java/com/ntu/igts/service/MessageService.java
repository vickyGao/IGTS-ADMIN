package com.ntu.igts.service;

import com.ntu.igts.model.Message;
import com.ntu.igts.model.container.Pagination;

public interface MessageService {

    public Pagination<Message> getMessagesForCommodity(String token, int currentPage, int pageSize, String commodityId);
}
