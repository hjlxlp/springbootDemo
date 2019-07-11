package com.example.demo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("hello")
@RestController
public class HelloController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("index")
    public String index() {
        return "亲爱的小婷婷，生日快乐";
    }

    @GetMapping("set/{key}/{value}")
    @ApiOperation(value = "设置缓存")
    public String set(@PathVariable("key") String key, @PathVariable("value") String value) {
        redisTemplate.opsForValue().set(key, value);
        return key + "," + value;
    }

    @GetMapping("get/{key}")
    @ApiOperation(value = "根据key获取缓存")
    public String get(@PathVariable("key") String key) {
        //User user = new User(1, "hjl", 20);
        //redisTemplate.opsForValue().set(key, user);
        return "key=" + key
                + ",value=" + redisTemplate.opsForValue().get(key)
                + ",b=" + redisTemplate.hasKey(key);
    }


}
