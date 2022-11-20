package com.lxy.pojo;

import lombok.Data;


/**
 * @Author lxy
 * @Description 友链实体类
 * @Date 2022/11/20 22:29
 */

@Data
public class Link {
    private Long id;
    private String name;
    private String avatar;
    private String url;
    private boolean canShow;
}
