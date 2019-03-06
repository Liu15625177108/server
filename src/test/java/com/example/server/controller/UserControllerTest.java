package com.example.server.controller;

import com.example.server.common.logger.MyLogger;
import com.example.server.entity.Conference;
import com.example.server.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private MyLogger myLogger;
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void aboutme() throws Exception {

    }

    @Test
    public void signup() throws Exception {
        User user = new User("jerry", "123456", "15625177108", "2447152234@qq.com");
        String result = mvc.perform(post("/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        myLogger.getLogger().info(result);

    }
    @Test
    public void create() throws Exception {
        Conference conference =new Conference("舞会","xxx-xx-xxx","descroption1232312",new Date(),"jerryLiu",
                "erwin","24423132@qq.com","123123212131");
        String result = mvc.perform(post("/conference/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(conference)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        myLogger.getLogger().info(result);

    }

    @Test
    public void attend() throws Exception{

    }


}