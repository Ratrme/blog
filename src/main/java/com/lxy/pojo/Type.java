package com.lxy.pojo;

import lombok.Data;


import java.util.ArrayList;
import java.util.List;


/**
 * @Author lxy
 * @Description 类型实体类
 * @Date 2022/11/20 21:04
 */

@Data
public class Type {
    //类型id
    private Long id;
    //类型名称
    private String name;

    private List<Blog> blogs;

}
