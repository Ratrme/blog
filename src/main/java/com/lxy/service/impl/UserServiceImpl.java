package com.lxy.service.impl;

import com.lxy.mapper.UserMapper;
import com.lxy.pojo.User;
import com.lxy.service.UserService;
import com.lxy.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MD5Utils md5Utils;


    @Override
    public User checkUser(String username, String password) {
        return userMapper.findByUsernameAndPassword(username, md5Utils.getMD5Str(password));
    }

    @Override
    public User getUser() {
        return userMapper.getUser();
    }
}
