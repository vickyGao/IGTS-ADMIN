package com.ntu.igts.service;

import com.ntu.igts.model.CustomModule;
import com.ntu.igts.model.container.CustomModuleList;

public interface CustomModuleService {

    public CustomModule createCustomModule(String token, CustomModule customModule);

    public CustomModule updateCustomModule(String token, CustomModule customModule);

    public void delete(String token, String customModuleId);

    public CustomModuleList getAll(String token);

    public CustomModule getDetailById(String token, String customModuleId);
}
