package com.ntu.igts.service;

import com.ntu.igts.model.Tag;
import com.ntu.igts.model.container.TagList;

public interface TagService {

    public Tag createTag(String token, Tag tag);

    public Tag updateTag(String token, Tag tag);

    public void deleteTag(String token, String tagId);

    public TagList getAllTagsWithSubTags(String token);

    public Tag getTagById(String token, String tagId);

    public TagList getAllTopLevelTags(String token);
}
