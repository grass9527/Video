package com.qf.service.impl;

import com.qf.dao.UserMapper;
import com.qf.pojo.User;
import com.qf.pojo.UserExample;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findAll() {
        return userMapper.selectByExample(null);
    }

    @Override
    public Integer update(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public Integer insertUser(User user) {

        return userMapper.insertSelective(user);
    }

    @Override
    public List<User> validateEmail(String email) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email);


        return userMapper.selectByExample(example);
    }
}
