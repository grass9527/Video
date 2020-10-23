package com.qf.controller;

import com.qf.pojo.Speaker;
import com.qf.pojo.Subject;
import com.qf.service.SpeakerService;
import com.qf.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("speaker")
public class SpeakerController {

    @Autowired
    private SpeakerService speakerService;


    @RequestMapping("showSpeakerList")
    public String showSpeakerList(Model model) {

        List<Speaker> speakerList = speakerService.findAll();

        model.addAttribute("speakerList",speakerList);

        return "/behind/speakerList.jsp";
    }

    @RequestMapping("addSpeaker")
    public String addSpeaker(Model model,Speaker speaker) {
        int count = speakerService.getCount()+1;
        speaker.setId(count);
        model.addAttribute("count",count);
        model.addAttribute("speaker",speaker);

        return "/behind/addSpeaker.jsp";
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Speaker speaker) {

        if (speaker.getId()==speakerService.getCount()+1) {
            speakerService.addVideo(speaker);
        } else {
            speakerService.update(speaker);
        }

        return "redirect:/speaker/showSpeakerList";
    }

    @RequestMapping("edit")
    public String edit(  @RequestParam( required = false) Integer id,
                           Model model) {

        Speaker speaker = speakerService.findById(id);
        model.addAttribute("speaker",speaker);

        return "/behind/addSpeaker.jsp";
    }

    @ResponseBody
    @RequestMapping("deleteById")
    public String deleteById(  @RequestParam( required = false) Integer id) {

        speakerService.deleteById(id);
        return "success";
    }
}
