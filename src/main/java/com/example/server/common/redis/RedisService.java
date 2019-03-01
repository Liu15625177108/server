package com.example.server.common.redis;

/**
 * @ClassName RedisService
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.common.redis
 * @Date 2019/3/1 10:31
 */
public interface RedisService {


    /**
     * 设置key-value
     * @param key
     * @param value
     */
    void setKey(String key, Object value);

    /**
     * 获取key
     * @param key
     * @return
     */
    Object getValue(String key);

    /**
     * 删除key
     * @param key
     */
    void delete(String key);
}
