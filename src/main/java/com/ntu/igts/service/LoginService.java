package com.ntu.igts.service;

import com.ntu.igts.model.SessionContext;
import com.ntu.igts.model.container.LoginForm;

public interface LoginService {

    public SessionContext login(LoginForm loginForm);
}
