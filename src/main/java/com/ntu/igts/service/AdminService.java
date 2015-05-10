package com.ntu.igts.service;

import com.ntu.igts.model.Admin;

public interface AdminService {

    public Admin getAdminByToken(String token);
}
