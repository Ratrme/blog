package com.lxy.service.impl;

import com.lxy.mapper.UserMapper;
import com.lxy.pojo.User;
import com.lxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User checkUser(String username, String password) {
        return userMapper.findByUsernameAndPassword(username, password);
    }
}
