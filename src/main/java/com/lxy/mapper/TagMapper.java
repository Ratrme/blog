package com.lxy.mapper;

import com.lxy.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {

    int saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> getAllTag();

    List<Tag> getAllTags(@Param("ids") List<Long> ids);

    List<Tag> getFrontTags(Integer size);

    int updateTag(Tag tag);

    int deleteTag(Long id);
    
}
