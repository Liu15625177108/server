package com.example.server.service;

import com.example.server.entity.Paper;

/**
 * @ClassName PaperService
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.service
 * @Date 2019/3/7 10:57
 */
public interface PaperService {
    public boolean create(Paper paper);
    public Paper findOneByPaperId(String paperId);
}
