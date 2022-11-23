package com.lxy.pojo;

import lombok.Data;
import lombok.NonNull;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
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
    @NotBlank(message = "分类名称不能为空！")
    private String name;
    //创建时间
    private Date createTime;

    private List<Blog> blogs;

}
