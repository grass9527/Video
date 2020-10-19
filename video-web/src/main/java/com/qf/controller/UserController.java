package com.qf.controller;

import com.qf.pojo.User;
import com.qf.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    private UserService userService;


    //用户登录
    @RequestMapping("loginUser")
    public String login(User user) {
        List<User> users = userService.findAll();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getNickname().equals(user.getNickname()) && users.get(i).getEmail().equals(user.getEmail())) {
                System.out.println("登陆成功");
              return "";
            }
        }

        return null;
    }
}
