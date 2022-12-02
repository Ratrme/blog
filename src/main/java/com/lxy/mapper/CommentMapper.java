package com.lxy.mapper;

import com.lxy.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    List<Comment> listComments(@Param("blogId") Long blogId);

    int saveComment(Comment comment);

    Comment getCommentByParentId(@Param("parentId") Long parentId);

    int deleteComments(@Param("id") Long id);
}
