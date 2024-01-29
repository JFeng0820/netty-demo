package org.example.service;

import org.example.domain.MsgAgreement;
import org.example.redis.Publisher;
import org.example.redis.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("extServerService")
public class ExtServerService {

    @Resource
    private Publisher publisher;
    @Resource
    private RedisUtil redisUtil;

    public void push(MsgAgreement msgAgreement){
        publisher.pushMessage("itstack-demo-netty-push-msgAgreement", msgAgreement);
    }

    public RedisUtil getRedisUtil() {
        return redisUtil;
    }
}
