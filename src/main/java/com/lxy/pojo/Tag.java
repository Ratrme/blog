package com.lxy.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
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
    @NotBlank(message = "分类名称不能为空！")
    private String name;
    //创建时间
    private Date createTime;

    private List<Blog> blogs;
}
