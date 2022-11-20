package com.lxy.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author lxy
 * @Description 博客信息实体类
 * @Date 2022/11/20 20:45
 */

@Data
public class Blog {
    //博客id
    private Long id;
    //博客标题
    private String title;
    //博客内容
    private String content;
    //博客首图
    private String firstPicture;
    //博客摘要
    private String description;
    //博客原创转载标签
    private String flag;
    //查看数
    private Integer views;
    //是否开启赞赏
    private boolean appreciation;
    //是否开启出处声明
    private boolean shareStatement;
    //是否开启评论
    private boolean commentable;
    //保存或发布
    private boolean published;
    //是否开启推荐
    private boolean recommend;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;

    private Type type;

    private List<Tag> tags;

    private String tagIds;

    private User user;

    private List<Comment> comments;




}
