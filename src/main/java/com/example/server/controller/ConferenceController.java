package com.example.server.controller;

import com.example.server.common.entity.ResultInfo;
import com.example.server.entity.Conference;
import com.example.server.entity.Paper;
import com.example.server.entity.User;
import com.example.server.service.ConferenceService;
import com.example.server.service.PaperService;
import com.example.server.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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

    @Autowired
    UserService userService;

    @Autowired
    PaperService paperService;


    @PostMapping("/create")
    public ResultInfo createConference(@RequestBody Conference conference) {

            if (conferenceService.createConference(conference)) {
                return new ResultInfo(HttpStatus.OK, "success", conference);
            } else {
                return new ResultInfo(HttpStatus.INTERNAL_SERVER_ERROR, "failure", "会议名已经存在");
            }
    }

    @PostMapping("/modify/{conferenceId}")
    public ResultInfo modifyCOnference(@RequestBody Conference conference,@PathVariable String conferenceId){

        List<Paper> paperList = new ArrayList<>();
        paperList = paperService.findAllByConferenceId(conferenceId);
        String conferenceName = conference.getName();
        for(Paper paper:paperList){
            paper.setConferenceName(conferenceName);
        }

        conferenceService.modifyConference(conference,conferenceId);
        return new ResultInfo(HttpStatus.OK,"success","修改成功");

    }

    @GetMapping("/all")
//    @JsonView(Conference.simpleView.class)
    public ResultInfo showAll(){
        return  new ResultInfo(HttpStatus.OK,"success",conferenceService.findAll());
    }

    /**
    *@Author Jerry.Liu
    *@Description://输入一个会议id，返回参加会议的users
    *@Parameter
    *@Date:18:12 2019/3/7
    *@Package: com.example.server.controller
    */
    @GetMapping("/users")
    public ResultInfo conferenceAttendUser(@RequestParam("conferenceId")String conferenceId){
        return  new ResultInfo(HttpStatus.OK,"success",conferenceService.attendConferenceUsers(conferenceId));
    }

    @GetMapping("/details")
    public ResultInfo conferenceDetails(@RequestParam("conferenceId") String conferenceId){
        return  new ResultInfo(HttpStatus.OK,"success",conferenceService.findOneByConferenceId(conferenceId));
    }

    @GetMapping("delete/{conferenceId}")
    public ResultInfo deleteConference(Authentication authentication,@PathVariable String conferenceId){
        User user= userService.showSimple(authentication.getName());

        if(user.getRole().equals("manager") || (user.getRole().equals("user") && userService.createOrNot(user.getName(),conferenceId))){
            try {
                conferenceService.deleteConference(conferenceId);
            }catch(Exception e){
                return new ResultInfo(HttpStatus.INTERNAL_SERVER_ERROR,"删除失败",false);
            }

            return new ResultInfo(HttpStatus.OK,"success",true);
        }
        else{
            return new ResultInfo(HttpStatus.INTERNAL_SERVER_ERROR,"你没有权限删除",false);
        }




    }


}
