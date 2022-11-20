package com.lxy.pojo;

import lombok.Data;

import java.util.List;


/**
 * @Author lxy
 * @Description 标签实体类
 * @Date 2022/11/20 21:04
 */

@Data
public class Tag {

    //标签id
    private Long id;
    //标签名称
    private String name;

    private List<Blog> blogs;
}
