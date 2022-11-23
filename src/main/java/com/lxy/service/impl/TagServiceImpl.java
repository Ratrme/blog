package com.lxy.service.impl;

import com.lxy.mapper.TagMapper;
import com.lxy.pojo.Tag;
import com.lxy.service.TagService;
import com.lxy.utils.StingToLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Transactional
    @Override
    public int saveTag(Tag Tag) {
        return tagMapper.saveTag(Tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagMapper.getTag(id);

    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagMapper.getAllTag();
    }

    @Override
    public List<Tag> getAllTags(String ids) {
        return tagMapper.getAllTags(StingToLong.convertToLong(ids));
    }

    @Override
    public List<Tag> getAllTags(List<Long> ids) {
        return tagMapper.getAllTags(ids);
    }


    @Transactional
    @Override
    public int updateTag(Tag Tag) {
        return tagMapper.updateTag(Tag);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteTag(id);
    }
}
