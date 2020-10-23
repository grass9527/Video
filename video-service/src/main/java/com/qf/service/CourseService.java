package com.qf.service;

import com.qf.pojo.Course;
import com.qf.pojo.Video;

import java.util.List;

public interface CourseService {
    List<Course> findAll();

    Course findById(Integer id);

    List<Course> findSubjectId(Integer id);

    Course findVideoById(Integer id);


}
