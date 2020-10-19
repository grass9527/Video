package com.qf.controller;

import com.qf.pojo.Admin;
import com.qf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping("login")
    public String login(@RequestParam (required = false) String username,
                              @RequestParam (required = false) String password) {
        List<Admin> list = adminService.findAll();

        for (Admin admin : list) {
            if (admin.getUsername().equals(username)&&admin.getPassword().equals(password)) {
                return "success";
            }
        }

        return "false";

    }

    @RequestMapping("test")
    @ResponseBody
    public ModelAndView test() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/behind/login.jsp");

        return mav;

    }
}
