package com.ntu.igts.service;

import com.ntu.igts.enums.ActiveStateEnum;
import com.ntu.igts.model.SensitiveWord;
import com.ntu.igts.model.container.Pagination;
import com.ntu.igts.model.container.Query;

public interface SensitiveWordService {

    public SensitiveWord create(String token, SensitiveWord word);

    public SensitiveWord updateSensitiveWordState(String token, ActiveStateEnum activeStateEnum, String sensitiveWordId);

    public void delete(String token, String sensitiveWordId);

    public Pagination<SensitiveWord> getBySearchTerm(String token, Query query);
}
