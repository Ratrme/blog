package com.lxy.mapper;

import com.lxy.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    int saveComment(Comment comment);


    int deleteByCommentId(Long id);

    List<Comment> getCommentsByBlogId(Long blogId);

    Comment getCommentByParentId(Long parentId);
}
