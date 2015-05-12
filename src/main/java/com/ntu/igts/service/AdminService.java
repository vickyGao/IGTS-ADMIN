package com.ntu.igts.service;

import com.ntu.igts.model.Admin;
import com.ntu.igts.model.container.Pagination;
import com.ntu.igts.model.container.Query;

public interface AdminService {

    public Admin getAdminByToken(String token);

    public Admin GetDetailById(String token, String adminId);

    public Admin updateAdmin(String token, Admin admin);

    public Admin createAdmin(String token, Admin admin);

    public void deleteAdmin(String token, String adminId);

    public Pagination<Admin> getPaginatedAdmins(String token, Query query);
}
