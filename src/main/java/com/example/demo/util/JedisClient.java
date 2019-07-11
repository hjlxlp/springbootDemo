package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author hjl
 * @date 2019/7/11 15:29
 */
@Component
public class JedisClient {

    private Logger logger = LoggerFactory.getLogger(JedisClient.class);

    /*@Autowired
    private JedisPool jedisPool;

    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            logger.info("缓存服务器连接失败！");
        } finally {
            //返还到连接池
            jedis.close();
        }
    }

    public String get(String key) {
        String value = "";
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            logger.info("缓存服务器连接异常！");
        } finally {
            //返还到连接池
            jedis.close();
        }
        return value;
    }*/

}
