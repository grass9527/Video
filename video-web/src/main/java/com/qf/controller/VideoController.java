package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.Admin;
import com.qf.pojo.Course;
import com.qf.pojo.QueryVo;
import com.qf.pojo.Video;
import com.qf.service.AdminService;
import com.qf.service.CourseService;
import com.qf.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private CourseService courseService;

    @RequestMapping("findByPage")
    public String findByPage(@RequestParam(defaultValue = "1",required = false) Integer pageNum,
                             @RequestParam(defaultValue = "2",required = false) Integer pageSize,
                             Model model,
                             QueryVo queryVo){

        model.addAttribute("queryVo",queryVo);


        PageHelper.startPage(pageNum,pageSize);

        List<Video> videos = videoService.findAllVideo(queryVo);

        PageInfo<Video> bookPageInfo = new PageInfo<>(videos);
        model.addAttribute("bookPageInfo",bookPageInfo);

        List<Course> courseList = courseService.findAll();
        model.addAttribute("courseList",courseList);

        return "/behind/videoList.jsp";
    }

    @RequestMapping("delAll")
    public String delAll(Integer [] ids){

        System.out.println("-----------");

        for(Integer id:ids){
            System.out.println(id);
        }

        return "redirect:/book/findByPage";
    }


}
