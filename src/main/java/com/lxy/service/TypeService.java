package com.lxy.service;

import com.lxy.pojo.Type;

import java.util.List;

public interface TypeService {

    //新增
    int saveType(Type type);

    //根据id查询
    Type getType(Long id);
    //根据名称查询
    Type getTypeByName(String name);
    //查询全部
    List<Type> getAllType();

    //修改
    int updateType(Type type);


    //删除
    void deleteType(Long id);


}
