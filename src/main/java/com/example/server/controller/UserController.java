package com.example.server.controller;

import com.example.server.common.entity.ResultInfo;
import com.example.server.entity.User;
import com.example.server.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     *@Author Jerry.Liu
     *@Description://TODO
     *@Parameter
     *@Date:16:41 2018/9/10
     *@Package: com.example.remote.user
     */
    @GetMapping("/sayhello")
    public Map sayHello(){
        Map map =new HashMap<String ,String>();
        map.put("aa","1234");
        return map;
    }


    /**
     *@Author Jerry.Liu
     *@Description:用户个人信息查看接口
     *Parameter
     *@Date:16:41 2018/9/10
     *@Package: com.example.remote.user
     */
    @GetMapping("/me")
    public Object aboutme(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

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
                return new ResultInfo(HttpStatus.INTERNAL_SERVER_ERROR,"failure",null);
            }
        }


    }
    @GetMapping("/simpleshow")
    @JsonView(User.simpleValue.class)
    public User showSimple(@RequestParam(required = true,name = "username",defaultValue = "erwin")String userName){
        return userService.showSimple(userName);
    }
}

