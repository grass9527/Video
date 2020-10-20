package com.qf.service;

import com.qf.pojo.Admin;
import com.qf.pojo.QueryVo;
import com.qf.pojo.Video;

import java.util.List;

public interface VideoService {
    List<Video> findAll();

    Video findById(Integer id);


    List<Video> findAllVideo(QueryVo queryVo);
}
