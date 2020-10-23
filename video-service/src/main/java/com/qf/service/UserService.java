package com.qf.service;

import com.qf.pojo.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public Integer update(User user);

    public Integer insertUser(User user);

    public List<User> validateEmail(String email);
}
