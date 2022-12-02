package com.lxy.service;

import com.lxy.pojo.Tag;

import java.util.List;

public interface TagService {

    int saveTag(Tag tag);

    //根据id查询
    Tag getTag(Long id);
    //根据名称查询
    Tag getTagByName(String name);
    //查询全部
    List<Tag> getAllTags();

    List<Tag> getAllTags(String ids);

    List<Tag> getAllTags(List<Long> ids);

    List<Tag> getFrontTags();

    //修改
    int updateTag(Tag type);


    //删除
    void deleteTag(Long id);
    
}
