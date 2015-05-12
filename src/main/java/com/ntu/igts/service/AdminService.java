package com.ntu.igts.service;

import com.ntu.igts.model.Admin;

public interface AdminService {

    public Admin getAdminByToken(String token);

    public Admin GetDetailById(String token, String adminId);

    public Admin updateAdmin(String token, Admin admin);
}
