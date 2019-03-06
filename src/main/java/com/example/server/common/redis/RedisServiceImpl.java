//package com.example.server.common.redis;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.ServletComponentScan;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @ClassName RedisServiceImpl
// * @Author:Jerry.Liu;
// * @Description://TODO
// * @Package com.example.server.common.redis
// * @Date 2019/3/1 10:31
// */
//@Service
//public class RedisServiceImpl implements  RedisService {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//    @Override
//    public void setKey(String key, Object value) {
//        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
//        ops.set(key, value, 300, TimeUnit.SECONDS);
//
//    }
//
//    @Override
//    public Object getValue(String key) {
//        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
//        return ops.get(key);
//    }
//
//
//    @Override
//    public void delete(String key) {
//            redisTemplate.delete(key);
//    }
//}
