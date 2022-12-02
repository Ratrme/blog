package com.lxy.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * @Author lxy
 * @Description 评论实体类
 * @Date 2022/11/20 21:05
 */

@Data
public class Comment {

    //评论id
    private Long id;
    //评论者昵称
    private String nickname;
    //评论者邮箱
    private String email;
    //评论内容
    private String content;
    //评论者头像
    private String avatar;
    //评论时间
    private Date createTime;

    private Blog blog;

    private List<Comment> replyComments;

    private Comment parentComment;

    private boolean adminComment;

}
