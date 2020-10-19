package com.qf.controller;

import com.qf.pojo.Admin;
import com.qf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("login")
    public ModelAndView login(@RequestParam (required = false) String username,
                              @RequestParam (required = false) String password) {
        List<Admin> list = adminService.findAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("data","false");
        for (Admin admin : list) {
            if (admin.getUsername().equals(username)&&admin.getPassword().equals(password)) {
                mav.addObject("data","success");
            }
        }

        return mav;

    }

    @RequestMapping("test")
    public ModelAndView test() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/behind/login.jsp");

        return mav;

    }
}
