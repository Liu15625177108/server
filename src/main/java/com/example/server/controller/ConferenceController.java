package com.example.server.controller;

import com.example.server.common.entity.ResultInfo;
import com.example.server.entity.Conference;
import com.example.server.entity.User;
import com.example.server.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName ConferenceController
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.controller
 * @Date 2019/3/5 20:56
 */
@RequestMapping("/conference")
@RestController
public class ConferenceController {
    @Autowired
    private ConferenceService conferenceService;


    @PostMapping("/create")
    public ResultInfo createConference(@RequestBody Conference conference) {

            if (conferenceService.createConference(conference)) {
                return new ResultInfo(HttpStatus.OK, "success", conference);
            } else {
                return new ResultInfo(HttpStatus.INTERNAL_SERVER_ERROR, "failure", "会议名已经存在");
            }
        }
    @GetMapping("/all")
    public List<Conference> showAll(){
        return  conferenceService.findAll();
    }

    /**
    *@Author Jerry.Liu
    *@Description://输入一个会议id，返回参加会议的users
    *@Parameter
    *@Date:18:12 2019/3/7
    *@Package: com.example.server.controller
    */
    @GetMapping("/users")
    public Set<User> conferenceAttendUser(@RequestParam("conferenceId")String conferenceId){
        return  conferenceService.attendConferenceUsers(conferenceId);
    }

    @GetMapping("/details")
    public Conference conferenceDetails(@RequestParam("conferenceId") String conferenceId){
        return  conferenceService.findOneByConferenceId(conferenceId);
    }


}
