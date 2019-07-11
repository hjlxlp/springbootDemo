package com.example.demo;

import redis.clients.jedis.Jedis;

/**
 * @author hjl
 * @date 2019/7/10 14:23
 */
public class RedisStringJava {

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        //设置 redis 字符串数据
        //jedis.set("jian", "test");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("jian"));
    }

}
