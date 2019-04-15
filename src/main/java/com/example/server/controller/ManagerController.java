package com.example.server.controller;

import com.example.server.common.entity.ResultInfo;
import com.example.server.entity.Conference;
import com.example.server.entity.User;
import com.example.server.service.ConferenceService;
import com.example.server.service.PaperService;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName ManagerController
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.controller
 * @Date 2019/3/7 20:10
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private UserService userService;
    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private PaperService paperService;

    @GetMapping("/simpleshow")
//    @JsonView(User.simpleValue.class)
    public ResultInfo showSimple(Authentication authentication){
//        System.out.println("manager/simpleshow");

        return new ResultInfo(HttpStatus.OK,"success",userService.showSimple(authentication.getName()));
    }

    @GetMapping("/showpapers")
    public ResultInfo showAllPaper(){
        return new ResultInfo(HttpStatus.OK,"success",paperService.findAll());
    }

    @GetMapping("/showusers")
    public ResultInfo showall(){
        return  new ResultInfo(HttpStatus.OK,"success",userService.findAllUsers());
    }

    @GetMapping("/showconferences")
    public ResultInfo showConference(){

        return new ResultInfo(HttpStatus.OK,"success",conferenceService.findAll());
    }

    @PostMapping("/mDelete/{conferenceId}")
    public ResultInfo deleteConference(Authentication authentication, @PathVariable String conferenceId){
        User user= userService.showSimple(authentication.getName());

        if(user.getRole().equals("admin") || (user.getRole().equals("user") && userService.createOrNot(user.getName(),conferenceId))){
            try {
                // new
                paperService.deleteAllByConferenceId(conferenceId);

                //new end
                conferenceService.deleteConference(conferenceId);
            }catch(Exception e){
                System.out.println(e);
                return new ResultInfo(HttpStatus.INTERNAL_SERVER_ERROR,"删除失败",false);
            }

            return new ResultInfo(HttpStatus.OK,"success",true);
        }
        else{
            return new ResultInfo(HttpStatus.INTERNAL_SERVER_ERROR,"你没有权限删除",false);
        }
    }

    @PostMapping("mDeleteUser/{username}")
    public ResultInfo deleteUser(Authentication authentication,@PathVariable String username){
        User user = userService.showSimple(authentication.getName());

        if(user.getRole().equals("admin")){

            try {
                // new
                List<Conference> conferences= userService.showMyCreateConference(username);
                for(Conference conf:conferences) {
                    paperService.deleteAllByConferenceId(conf.getId());
                }
                //new end
                userService.deleteUser(username);
            }
            catch (Exception e){
                return new ResultInfo(HttpStatus.INTERNAL_SERVER_ERROR,"删除失败",false);

            }
            return new ResultInfo(HttpStatus.OK,"删除成功",true);
        }
        else{
            return new ResultInfo(HttpStatus.INTERNAL_SERVER_ERROR,"你没有权限删除",false);
        }
    }


    @PostMapping("/modPass")
    public ResultInfo modifyPassword(Authentication authentication, HttpServletRequest httpServletRequest){
        return new ResultInfo(HttpStatus.OK,"success", userService.modifyPassword(authentication.getName(),httpServletRequest.getParameter("oldPassword"),
                httpServletRequest.getParameter("newPassword")));
    }
}
