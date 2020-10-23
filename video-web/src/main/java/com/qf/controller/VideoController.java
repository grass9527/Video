package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.*;
import com.qf.service.*;
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

    @Autowired
    private SpeakerService speakerService;

    @Autowired
    private SubjectService subjectService;

    @RequestMapping("list")
    public String findByPage(@RequestParam(defaultValue = "1", required = false) Integer pageNum,
                             @RequestParam(defaultValue = "6", required = false) Integer pageSize,
                             Model model,
                             QueryVo queryVo) {
        model.addAttribute("queryVo", queryVo);
        PageHelper.startPage(pageNum, pageSize);

        List<Video> videos = videoService.findAllVideo(queryVo);
        for (Video video : videos) {
            Speaker speaker = new Speaker();
            speaker.setSpeakerName(video.getSpeakName());
            video.setSpeaker(speaker);
        }
        PageInfo<Video> page = new PageInfo<>(videos);
        model.addAttribute("page", page);

        List<Course> courseList = courseService.findAll();
        List<Speaker> speakerList = speakerService.findAll();
        model.addAttribute("courseList", courseList);
        model.addAttribute("speakerList", speakerList);

        return "/behind/videoList.jsp";
    }

    @RequestMapping("delAll")
    public String delAll(Integer[] ids) {

        for (Integer id : ids) {
            videoService.deleteById(id);
        }
        return "redirect:/video/list";
    }

    @ResponseBody
    @RequestMapping("deleteById")
    public String deleteById(Integer id) {
        videoService.deleteById(id);
        return "success";
    }

    @RequestMapping("update")
    public String update(  @RequestParam( required = false) Integer id,
                           Model model) {

        Video video = videoService.findById(id);
        model.addAttribute("video",video);
        List<Course> courseList = courseService.findAll();
        List<Speaker> speakerList = speakerService.findAll();
        model.addAttribute("courseList", courseList);
        model.addAttribute("speakerList", speakerList);
        return "/behind/addVideo.jsp";
    }

    @RequestMapping("addVideo")
    public String addVideo(Video video,Model model) {
        int count = videoService.getCount()+1;
        video.setId(count);

        model.addAttribute("video",video);
        List<Speaker> speakerList = speakerService.findAll();
        List<Course> courseList = courseService.findAll();
        model.addAttribute("courseList", courseList);
        model.addAttribute("speakerList", speakerList);
        return "/behind/addVideo.jsp";
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Video video) {
        if (video.getId()==videoService.getCount()+1) {
            videoService.addVideo(video);
        } else {
            videoService.update(video);
        }

        return "redirect:/video/list";
    }

    @RequestMapping("showVideo")
    public String showVideo(Integer videoId,String subjectName, Model model) {
        List<Subject> subjectList = subjectService.findAll();

        model.addAttribute("subjectList",subjectList);

        //---------------------------------------
        Video video = videoService.find(videoId);

        model.addAttribute("video", video);
        model.addAttribute("subjectName",subjectName);

        Course course = courseService.findVideoById(video.getCourseId());

        model.addAttribute("course", course);
        return "/before/section.jsp";

    }

}
