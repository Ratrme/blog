package com.lxy.service;

import com.lxy.pojo.Comment;

import java.util.List;

public interface CommentService {

    int saveComment(Comment comment);


    int deleteByCommentId(Long id);

    List<Comment> getCommentsByBlogId(Long blogId);


}
