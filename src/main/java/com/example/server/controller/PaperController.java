package com.example.server.controller;

import com.example.server.common.entity.ResultInfo;
import com.example.server.entity.Conference;
import com.example.server.entity.Paper;
import com.example.server.entity.User;
import com.example.server.service.ConferenceService;
import com.example.server.service.PaperService;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private ConferenceService conferenceService;


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


    //返回所有论文信息
    @GetMapping("/all")
    public ResultInfo findAll(Authentication authentication){
//        List<Object> list = new ArrayList<>();
//        List<Paper> paperList = new ArrayList<>();
//        paperList = paperService.findAll();
//
//        list.add(paperList);

        return new ResultInfo(HttpStatus.OK,"success,",paperService.findAll());
    }

    @GetMapping("/details")
    public ResultInfo conferenceDetails(@RequestParam("paperId") String paperId){
//        List<Object> list = new ArrayList<Object>();
//        Paper paper = paperService.findOneByPaperId(paperId);
//        String conferenceName = conferenceService.findOneByConferenceId(paper.getConferenceId()).getName();
//        list.add(paper);
//        list.add(conferenceName);

        return  new ResultInfo(HttpStatus.OK,"success",paperService.findOneByPaperId(paperId));
    }



    @PostMapping("/addComment")
    public  ResultInfo addComment(Authentication authentication,@RequestParam("paperId")String paperId,@RequestParam("comment")String comment){
        Paper paper=paperService.findOneByPaperId(paperId);
        if(userService.createOrNot(authentication.getName(),paper.getConferenceId())){
            paperService.addComment(paper,comment);
            return new ResultInfo(HttpStatus.OK,"success",true);
        }
        return new ResultInfo(HttpStatus.INTERNAL_SERVER_ERROR,"failure",false);
    };

    @GetMapping("confenerce")
    public ResultInfo findConferenceName(Authentication authentication,@RequestParam("paperId") String paperId){

        Paper paper = paperService.findOneByPaperId(paperId);
        String conferenceId = paper.getConferenceId();
        Conference conference =conferenceService.findOneByConferenceId(conferenceId);
        String conferenceName = conference.getName();
        return new ResultInfo(HttpStatus.OK,"success",conferenceName);
    }

    @GetMapping("/delete/{paperId}")
    public ResultInfo deletePaper(Authentication authentication,@PathVariable("paperId")String paperId){
        User user = userService.showSimple(authentication.getName());
        Paper paper = paperService.findOneByPaperId(paperId);

        if(user.getRole().equals("manager") || (user.getRole().equals("user") &&paper.getUserName().equals(user.getName()))){
            try {
                paperService.deletePaper(paperId);
                return new ResultInfo(HttpStatus.OK, "success", true);
            }catch (Exception e){
                return new ResultInfo(HttpStatus.INTERNAL_SERVER_ERROR,"删除失败",false);
            }
        }

        return new ResultInfo(HttpStatus.INTERNAL_SERVER_ERROR,"你没有权限删除",true);

    }


}
