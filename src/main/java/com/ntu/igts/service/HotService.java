package com.ntu.igts.service;

import com.ntu.igts.model.Hot;
import com.ntu.igts.model.container.HotList;

public interface HotService {

    public Hot createHotCommodity(String token, Hot hot);

    public Hot updateHotCommodity(String token, Hot hot);

    public void delete(String token, String hotId);

    public Hot getDetailById(String token, String hotId);

    public HotList getHotCommodities(String token);
}
