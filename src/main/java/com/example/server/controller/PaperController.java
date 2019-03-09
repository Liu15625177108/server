package com.example.server.controller;

import com.example.server.common.entity.ResultInfo;
import com.example.server.entity.Paper;
import com.example.server.service.PaperService;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName PaperController
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.controller
 * @Date 2019/3/7 18:20
 */
@RestController
@RequestMapping("/paper")
public class PaperController {
    @Autowired
    private PaperService paperService;


    @Autowired
    private UserService userService;
    /**
    *@Author Jerry.Liu
    *@Description://查看某个会议的所以paper,需要验证该用户是否为此会议的创建者，如果是则查找返回数据，否则返回null
    *@Parameter
    *@Date:9:48 2019/3/8
    *@Package: com.example.server.controller
    */
    @GetMapping("/conference/{conferenceId}")
    public ResultInfo findAllByConferenceId(@PathVariable String conferenceId, Authentication authentication){
        if(userService.createOrNot(authentication.getName(),conferenceId)){
            return  new ResultInfo(HttpStatus.OK,"success",paperService.findAllByConferenceId(conferenceId));
        }
        return  new ResultInfo(HttpStatus.INTERNAL_SERVER_ERROR,"failure",null);
    }

    /**
    *@Author Jerry.Liu
    *@Description://返回用户所以的paper信息；
    *@Parameter
    *@Date:9:50 2019/3/8
    *@Package: com.example.server.controller
    */
    @GetMapping("/user")
    public ResultInfo findAllbyUserName(Authentication authentication){
        return  new ResultInfo(HttpStatus.OK,"success",paperService.findAllByUserName(authentication.getName()));
    }



    @PostMapping("/addComment")
    public  ResultInfo addComment(Authentication authentication,@RequestParam("paperId")String paperId,@RequestParam("comment")String comment){
        Paper paper=paperService.findOneByPaperId(paperId);
        if(userService.createOrNot(authentication.getName(),paper.getConferenceId())){
            paperService.addComment(paper,comment);
            return new ResultInfo(HttpStatus.OK,"success",true);
        }
        return new ResultInfo(HttpStatus.INTERNAL_SERVER_ERROR,"failure",false);
    }
}
