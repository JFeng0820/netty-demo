package org.example;

import org.example.redis.RedisUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;

@SpringBootApplication
@ComponentScan("org.example")
public class Application implements CommandLineRunner{

    @Resource
    private RedisUtil redisUtil;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        redisUtil.clear();
    }

}
