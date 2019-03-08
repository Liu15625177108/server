package com.example.server.controller;

import com.example.server.common.entity.ResultInfo;
import com.example.server.common.sender.emailsender.SendEmialUtil;
import com.example.server.entity.Conference;
import com.example.server.entity.User;
import com.example.server.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * @ClassName UserController
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.controller
 * @Date 2019/2/22 17:41
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**注入用户service对象*/
    @Autowired
    private UserService userService;
    @Autowired
    private SendEmialUtil sendEmialUtil;

    /**
     *@Author Jerry.Liu
     *@Description://TODO
     *@Parameter
     *@Date:16:41 2018/9/10
     *@Package: com.example.remote.user
     */
//    @GetMapping("/test")
//    public boolean sayHello(@RequestParam("userName")String userName,@RequestParam("conferenceId")String conferenceId){

//    }
//    @GetMapping("/test1")
//    public boolean sayHello1(@RequestParam("userName")String userName,@RequestParam("conferenceId")String conferenceId){
//        return userService.createOrNot(userName,conferenceId);
//    }


    /**
     *@Author Jerry.Liu
     *@Description:用户个人信息查看接口
     *Parameter
     *@Date:16:41 2018/9/10
     *@Package: com.example.remote.user
     */
//    @GetMapping("/me")
//    public Object aboutme(){
//        return SecurityContextHolder.getContext().getAuthentication();
//    }

    /**
     *@Author Jerry.Liu
     *@Description:用户注册接口。
     *@Parameter [user]
     *@Date:16:41 2018/9/10
     *@Package: com.example.remote.user
     */
    @PostMapping("/signup")
    public ResultInfo signup(@Valid @RequestBody User user, BindingResult bindingResult ){
                if (bindingResult.hasErrors()){
                    List<String> list=new ArrayList<>();
                    for(ObjectError objectError:bindingResult.getAllErrors()){
                        list.add(objectError.getDefaultMessage());
                    }
            return new ResultInfo(HttpStatus.INTERNAL_SERVER_ERROR,"failure",list);
        }else {
            if(userService.signup(user)){
                return new ResultInfo(HttpStatus.OK,"success",user);
            }else {
                return new ResultInfo(HttpStatus.INTERNAL_SERVER_ERROR,"failure","用户名已经存在");
            }
        }


    }
    @GetMapping("/simpleshow")
//    @JsonView(User.simpleValue.class)
    public ResultInfo showSimple(Authentication authentication){
        return new ResultInfo(HttpStatus.OK,"success",userService.showSimple(authentication.getName()));
    }
/**
*@Author Jerry.Liu
*@Description://输入用户名以及会议id,返回报名结果。
*@Parameter
*@Date:18:09 2019/3/7
*@Package: com.example.server.controller
*/
    @PostMapping("/attend")
    public ResultInfo attendConference(Authentication authentication,@RequestParam("conferenceId")String conferenceId){
        if(userService.attendConference(authentication.getName(),conferenceId)){
            return new ResultInfo(HttpStatus.OK,"success","报名成功");
        }
        return new ResultInfo(HttpStatus.INTERNAL_SERVER_ERROR,"failure","报名失败");
    }
    /**
    *@Author Jerry.Liu
    *@Description://输入一个用户名，返回他创建的会议信息；
    *@Parameter
    *@Date:18:09 2019/3/7
    *@Package: com.example.server.controller
    */
    @GetMapping("/show/create")
    public ResultInfo show(Authentication authentication){
        return  new ResultInfo(HttpStatus.OK,"success",userService.showMyCreateConference(authentication.getName()));
    }

    /**
    *@Author Jerry.Liu
    *@Description://输入一个用户名，返回他参加的会议信息；
    *@Parameter
    *@Date:18:10 2019/3/7
    *@Package: com.example.server.controller
    */
    @GetMapping("show/attend")
    public ResultInfo showAttend(Authentication authentication){
        return  new ResultInfo(HttpStatus.OK,"success",userService.showMyAttendConference(authentication.getName()));
    }
}

