package com.example.server.controller;

import com.example.server.common.entity.ResultInfo;
import com.example.server.entity.Conference;
import com.example.server.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

}
