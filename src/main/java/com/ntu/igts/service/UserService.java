package com.ntu.igts.service;

import com.ntu.igts.enums.ActiveStateEnum;
import com.ntu.igts.model.User;
import com.ntu.igts.model.container.Pagination;
import com.ntu.igts.model.container.Query;

public interface UserService {

    public User updateActiveState(String token, ActiveStateEnum activeStateEnum, String userId);

    public void deleteUser(String token, String userId);

    public Pagination<User> getUserByPage(String token, Query query);

    public User getUserDetail(String token, String userId);

    public int getTotalCount(String token);
}
