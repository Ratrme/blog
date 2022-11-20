package com.lxy.pojo;

import lombok.Data;

import java.util.List;


/**
 * @Author lxy
 * @Description 用户实体类
 * @Date 2022/11/20 21:10
 */

@Data
public class User {

    //用户id
    private Long id;
    //用户昵称
    private String nickname;
    //用户名（账号）
    private String username;
    //用户密码
    private String password;
    //用户邮箱
    private String email;
    //用户头像
    private String avatar;
    //用户类型
    private Integer type;
    //用户创建时间
    private Data createTime;
    //用户修改时间
    private Data updateTime;

    private List<Blog> blogs;
}
