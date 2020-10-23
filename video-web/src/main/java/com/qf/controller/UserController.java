package com.qf.controller;

import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.videos.utils.ImageCut;
import com.qf.videos.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.qf.videos.utils.MailUtils.getValidateCode;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;


    //用户登录
    @RequestMapping("loginUser")
    @ResponseBody
    public String login(User user, HttpSession session) {
        System.out.println(user);
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        List<User> users = userService.findAll();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getPassword().equals(user.getPassword()) && users.get(i).getEmail().equals(user.getEmail())) {
                System.out.println("登陆成功");
                session.setAttribute("user", users.get(i));
                session.setAttribute("userAccount",users.get(i).getEmail());

                return "success";
            }
        }

        return null;
    }


    @RequestMapping("insertUser")
    @ResponseBody
    public String insertUser(User user, HttpSession session) {

        if (userService.insertUser(user) != 0) {
            session.setAttribute("user", user);
            session.setAttribute("userAccount",user.getEmail());
            return "success";
        }

        return "success";

    }

    //判断邮箱是否已注册
    @RequestMapping("validateEmail")
    @ResponseBody
    // /user/validateEmail
    public String validateEmail(String email) {
        System.out.println(email);
        List<User> users = userService.validateEmail(email);
        if (!users.isEmpty()) {
            System.out.println(users.size());
            System.out.println("查到");
            return null;
        } else {
            return "success";
        }
    }

    @RequestMapping("showMyProfile")
    public String changeUser(HttpSession session, Model model) {

        System.out.println("fgdfg");
        User user = (User) session.getAttribute("user");
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        model.addAttribute("user", user);

        return "/before/change_profile.jsp";
    }

    @RequestMapping("changeProfile")
    public String changeProfile() {

        return "/before/change_profile.jsp";
    }

    @RequestMapping("updateUser")

    public String updateUser(User user, HttpSession session) {
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getNickname());
        User user1 = (User) session.getAttribute("user");

        user.setEmail(user1.getEmail());
        user.setPassword(user1.getPassword());
        user.setImgurl(user1.getImgurl());
        Integer update = userService.update(user);
        session.setAttribute("user", user);

        return "redirect:/user/showMyProfile";
    }

    @RequestMapping("passwordSafe")
    public String passwordSafe(HttpSession session) {
        Object user = session.getAttribute("user");


        return "/before/password_safe.jsp";
    }

    @RequestMapping("updatePassword")
    public String updatePassword(String newPassword, HttpSession session) {
        System.out.println("((((((9999");
        System.out.println(newPassword);
        User user = (User) session.getAttribute("user");
        user.setPassword(newPassword);
        userService.update(user);
        session.setAttribute("user", user);


        return "redirect:/user/showMyProfile";
    }

    @RequestMapping("validatePassword")
    @ResponseBody
    public String validatePassword(String password, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getPassword().equals(password)) {
            return "success";
        } else {
            return "false";
        }


    }

    @RequestMapping("forgetPassword")
    public String forgetPassword() {
        return "/before/forget_password.jsp";
    }

    //发送邮件
    @RequestMapping("sendEmail")
    @ResponseBody
    public String sendEmail(String email,HttpSession session) {
        String code = getValidateCode(6);
        boolean b = MailUtils.sendMail(email, "你好。", "验证码是：" + code);

        if (b) {
            session.setAttribute("code", code);
            return "success";

        } else {

            return "hasNoUser";
        }


    }

    @RequestMapping("validateEmailCode")
    public String validateEmailCode(String email, String code, HttpSession session, Model model) {


        String code1 = (String) session.getAttribute("code");

        if (code.equals(code1)) {
            model.addAttribute("email", email);
            return "/before/reset_password.jsp";
        }
        return null;
    }

    @RequestMapping("resetPassword")
    public String resetPassword(String email, String password, HttpSession session) {
        System.out.println(email);
        System.out.println(password);
        List<User> users = userService.validateEmail(email);
        users.get(0).setPassword(password);
        userService.update(users.get(0));
        session.setAttribute("user", users.get(0));
        return "redirect:http://localhost:8080";
    }

    @RequestMapping("changeAvatar")
    public String changeAvatar(HttpSession session) {

        return "/before/change_avatar.jsp";
    }

    @RequestMapping("upLoadImage")
    public String upload(MultipartFile image_file, String x1,String y1,String x2,String y2,HttpSession session) {
        /*上传地址*/
        String path = "E:\\javaEE1904\\WEBSoft\\apache-tomcat-7.0.103\\webapps\\upload";
        //上传的文件名
        String filename = image_file.getOriginalFilename();
        System.out.println("上传的文件名：" + filename);

        //上传文件
        File file = new File(path + filename);
        System.out.println("llllllllllllllll");
        System.out.println(x1);
        System.out.println(y1);
        System.out.println(x2);
        System.out.println(y2);
        int x1Int = (int) Double.parseDouble(x1);
        int x2Int = (int) Double.parseDouble(x2);
        int y1Int = (int) Double.parseDouble(y1);
        int y2Int = (int) Double.parseDouble(y2);


        //上传方法
        try {
            image_file.transferTo(file);
            new ImageCut().cutImage(path + filename,x1Int,y1Int,x2Int,y2Int);
            User user = (User) session.getAttribute("user");
            user.setImgurl("http://localhost:8083/upload/"+ filename);
            userService.update(user);

        } catch (IOException e) {


        }

        return "/before/change_profile.jsp";


    }
    @RequestMapping("loginOut2")
    public String loginOut2(HttpSession session){
        session.invalidate();

        return "redirect:http://localhost:8080";
    }
    @RequestMapping("loginout")
    public String loginout(HttpSession session) {
        session.invalidate();

        return "redirect:http://localhost:8080";
    }
    @RequestMapping("loginout1")
    public String loginout1(HttpSession session) {
        session.invalidate();

        return "redirect:http://localhost:8080";
    }
}
