package com.qf.controller;

import com.qf.pojo.User;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;


    //用户登录
    @RequestMapping("loginUser")
    public String login(User user) {
        System.out.println(user);
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        List<User> users = userService.findAll();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getPassword().equals(user.getPassword()) && users.get(i).getEmail().equals(user.getEmail())) {
                System.out.println("登陆成功");
              return "";
            }
        }

        return null;
    }
}
