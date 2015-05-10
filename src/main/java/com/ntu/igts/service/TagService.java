package com.ntu.igts.service;

import java.util.List;

import com.ntu.igts.model.container.TagList;

public interface TagService {

    public TagList getAllTagsWithSubTags(String token);
}
