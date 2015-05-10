package com.ntu.igts.service;

import com.ntu.igts.model.User;

public interface UserService {

    public User getUserByToken(String token);
}
