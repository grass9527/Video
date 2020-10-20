package com.qf.service.impl;

import com.qf.dao.AdminMapper;
import com.qf.dao.VideoMapper;
import com.qf.pojo.Admin;
import com.qf.pojo.QueryVo;
import com.qf.pojo.Video;
import com.qf.service.AdminService;
import com.qf.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> findAll() {
        return videoMapper.selectByExample(null);
    }

    @Override
    public Video findById(Integer id) {
        return videoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Video> findAllVideo(QueryVo queryVo) {
        return null;
    }
}
