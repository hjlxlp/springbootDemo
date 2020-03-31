package com.example.demo.controller;

import com.example.demo.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangjiale
 * @date 2020/3/25 10:26
 **/
@RequestMapping("/redis")
@RestController
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/get")
    @ApiOperation("根据key获取value")
    public String get(@RequestParam("key") String key) {
        return redisUtil.get(key);
    }

    @PostMapping("/del")
    @ApiOperation("根据key删除key")
    public void del(@RequestParam("key") String key) {
        redisUtil.del(key);
    }

    @PostMapping("/set")
    @ApiOperation("根据set赋值value")
    public boolean set(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("time") long time) {
        return redisUtil.set(key, value, time);
    }

}
