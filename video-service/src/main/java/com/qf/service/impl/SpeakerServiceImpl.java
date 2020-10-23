package com.qf.service.impl;

import com.qf.dao.AdminMapper;
import com.qf.dao.SpeakerMapper;
import com.qf.pojo.Admin;
import com.qf.pojo.Speaker;
import com.qf.service.AdminService;
import com.qf.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakerServiceImpl implements SpeakerService {

    @Autowired
    private SpeakerMapper speakerMapper;

    @Override
    public List<Speaker> findAll() {
        return speakerMapper.findAll();
    }

    @Override
    public Speaker findById(Integer id) {
        return speakerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int getCount() {
        return speakerMapper.countByExample(null);
    }

    @Override
    public int addVideo(Speaker speaker) {
        return speakerMapper.insert(speaker);
    }

    @Override
    public int update(Speaker speaker) {
        return speakerMapper.updateByPrimaryKeyWithBLOBs(speaker);
    }

    @Override
    public int deleteById(Integer id) {
        return speakerMapper.deleteByPrimaryKey(id);
    }
}
