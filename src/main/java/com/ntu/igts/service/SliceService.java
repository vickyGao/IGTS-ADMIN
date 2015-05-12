package com.ntu.igts.service;

import com.ntu.igts.model.Slice;
import com.ntu.igts.model.container.SliceList;

public interface SliceService {

    public Slice createSlice(String token, Slice slice);

    public Slice updateSlice(String token, Slice slice);

    public void delete(String token, String sliceId);

    public Slice getDetailByid(String token, String sliceId);

    public SliceList getAll(String token);
}
