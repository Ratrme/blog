package com.lxy.service.impl;

import com.lxy.mapper.TypeMapper;
import com.lxy.pojo.Type;
import com.lxy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeMapper.getType(id);

    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.getAllType();
    }

    @Override
    public List<Type> getFrontTypes() {
        return typeMapper.getFrontTypes();
    }


    @Transactional
    @Override
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeMapper.deleteType(id);
    }
}
