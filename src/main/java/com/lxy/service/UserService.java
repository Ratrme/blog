package com.lxy.service;

import com.lxy.pojo.User;

public interface UserService {
    User checkUser(String username, String password);
}
