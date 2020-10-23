package com.qf.controller;

import com.qf.pojo.Course;
import com.qf.pojo.Subject;
import com.qf.service.CourseService;
import com.qf.service.SubjectService;
import com.qf.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("course")
public class CourseController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private VideoService videoService;
    @RequestMapping(value="/course/{id}")
    public String course(@PathVariable(name = "id") Integer id, Model model){

        Subject subject = subjectService.findId(id);
        System.out.println(id);
        //--------------------------------
        Course videoById = courseService.findVideoById(7);
        System.out.println("--------------");

        System.out.println(videoById.getVideoList().size());

        //--------------------------
        List<Subject> subjectList = subjectService.findAll();

        model.addAttribute("subjectList",subjectList);

        List<Course> courses = courseService.findSubjectId(id);
        ArrayList<Course> courses1 = new ArrayList<>();
        for (Course cours : courses) {
            Course course = courseService.findVideoById(cours.getId());
            courses1.add(course);
        }


        System.out.println(courses1);
        for (Course course : courses1) {
            System.out.println(course.getCourseTitle());
            System.out.println(course.getVideoList().size());
        }

        subject.setCourseList(courses1);


        model.addAttribute("subject",subject);


        return "/before/course.jsp";
    }
}
