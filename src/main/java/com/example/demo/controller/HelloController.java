package com.example.demo.controller;

import com.example.demo.entity.DocumentType;
import com.example.demo.util.JedisClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@RequestMapping("hello")
@RestController
public class HelloController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JedisClient jedisClient;

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
        /*User user = new User(1, "hjl", 20);
        redisTemplate.opsForValue().set(key, user);*/
        return "key=" + key
                + ",value=" + redisTemplate.opsForValue().get(key)
                + ",b=" + redisTemplate.hasKey(key);
    }

    @GetMapping("/setV")
    public String setV(String key, String value) throws Exception {
        jedisClient.set(key, value);
        return "success";
    }

    @GetMapping("/getV")
    public String getV(String key) throws Exception {
        return jedisClient.get(key);
    }

    @GetMapping("/test")
    public String test(String key) throws Exception {
        Jedis jedis = jedisClient.getJedis();
        System.out.println(jedis.get(key));
        jedis.incr(key);
        System.out.println(jedis.get(key));
        jedisClient.returnResource(jedis);
        return jedis.get(key);
    }

    /**
     * 计数器，根据key获取序号
     *
     * @param key
     * @return
     */
    private String getNumber(String key) {
        if (redisTemplate.opsForValue().get(key) == null) {
            redisTemplate.opsForValue().set(key, 1);
            redisTemplate.expire(key, 30, TimeUnit.SECONDS);
        } else {
            redisTemplate.opsForValue().increment(key, 1);
        }
        return redisTemplate.opsForValue().get(key).toString();
        /*Jedis jedis = jedisClient.getJedis();
        if (jedis.get(key) == null) {
            jedis.set(key, "1");
            jedis.expire(key, 30);
        } else {
            jedis.incr(key);
        }
        jedisClient.returnResource(jedis);
        return jedis.get(key);*/
    }

    /**
     * 生成流水号
     * 企业缩写3位大写字母+单据类型2位大写字母+yyyyMMdd+3位序号
     *
     * @param companyId
     * @param documentType
     * @return
     */
    @GetMapping("/createSerialNumber")
    public String createSerialNumber(Integer companyId, DocumentType documentType) {
        //todo
        String serialNumber = "";
        //根据企业id获取企业缩写
        String abbreviationStr = "COM" + companyId;
        //单据类型
        String documentTypeStr = documentType.toString();
        //日期
        String dateStr = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now());
        //序号
        serialNumber = abbreviationStr + documentTypeStr + dateStr;
        Integer num = Integer.parseInt(getNumber(serialNumber));
        String numStr = String.format("%0" + 3 + "d", num);
        serialNumber = serialNumber + numStr;
        return serialNumber;
    }

}
