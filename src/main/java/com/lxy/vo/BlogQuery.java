package com.lxy.vo;

import lombok.Data;

@Data
public class BlogQuery {
    private String title;           //文章标题
    private Long typeId;        //文章分类
    private boolean recommend;  //文章：推荐状态
    private boolean published; //文章：是否发布
}
