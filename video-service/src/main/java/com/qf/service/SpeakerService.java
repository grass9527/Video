package com.qf.service;

import com.qf.pojo.Admin;
import com.qf.pojo.Speaker;

import java.util.List;

public interface SpeakerService {
    List<Speaker> findAll();

    Speaker findById(Integer id);

    int getCount();

    int addVideo(Speaker speaker);

    int update(Speaker speaker);

    int deleteById(Integer id);
}
