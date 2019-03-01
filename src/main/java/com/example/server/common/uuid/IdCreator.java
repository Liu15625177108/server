package com.example.server.common.uuid;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @ClassName IdCreator
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.common.uuid
 * @Date 2019/2/22 17:39
 */
@Component
public class IdCreator  {
    public String createId(){
        return UUID.randomUUID().toString();
    }
}
