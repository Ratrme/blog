package com.lxy.service;

import com.lxy.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listComments(Long blogId);


    int saveComment(Comment comment);

    int deleteComments(Long id);


}
