package com.lxy.service.impl;

import com.lxy.mapper.CommentMapper;
import com.lxy.pojo.Comment;
import com.lxy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentMapper commentMapper;

    @Transactional
    @Override
    public int saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1){
            comment.setParentComment(commentMapper.getCommentByParentId(parentCommentId));
        } else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentMapper.saveComment(comment);
    }

    @Transactional
    @Override
    public int deleteByCommentId(Long id) {
        return commentMapper.deleteByCommentId(id);
    }

    @Override
    public List<Comment> getCommentsByBlogId(Long blogId) {
        return commentMapper.getCommentsByBlogId(blogId);
    }
}
