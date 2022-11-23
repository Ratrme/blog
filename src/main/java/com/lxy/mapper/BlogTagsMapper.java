package com.lxy.mapper;

import com.lxy.pojo.BlogTags;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogTagsMapper {

    int insertBlogTag(@Param("blogId") Long blogId, @Param("tagsId") Long tagsId);

    int delete(@Param("blogId") Long blogId);

    List<Long> selectByBlogId(@Param("blogId") long blogId);
}
