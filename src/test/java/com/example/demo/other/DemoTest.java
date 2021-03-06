package com.example.demo.other;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author hjl
 * @date 2018/12/18 15:40
 */
@EnableCaching
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration    //一级注释，用于声明一个ApplicationContext集成测试加载
public class DemoTest {

    @Value("${test.str}")
    private String str;

    @Test
    public void test01() {
        List<String> ordIdList = Arrays.asList(str.split(","));
        Integer n = 90503;
        System.out.println(ordIdList.contains(String.valueOf(n)));
    }

    @Test
    public void test02() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://m.zmyouke.com/uke-api/uke-apollo/dekayExcel/12228";
        HttpEntity<byte[]> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<byte[]> res = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);
        OutputStream outputStream = new FileOutputStream(new File("C:/test.xlsx"), false);
        outputStream.write(res.getBody());
        outputStream.flush();
        outputStream.close();
        System.out.println(res.getBody());
        /*int length = 0;
        byte[] buf = new byte[1024];
        while (inputStream.read(buf, 0, length) != -1) {
            outputStream.write(buf,0,length);
        }*/
    }

    @Test
    public void test03() {
        RedisTemplate redisTemplate = new RedisTemplate();
        /*redisTemplate.opsForValue().set("jian","hhd");
        Boolean b = redisTemplate.hasKey("jian");
        System.out.println(b);*/

        System.out.println(redisTemplate.opsForValue().get("test"));
        //redisTemplate.opsForValue().increment("test",1);
        //System.out.println(redisTemplate.opsForValue().get("test"));
    }

    @Test
    public void test05() {
        System.out.println(JSON.toJSONString(null, SerializerFeature.WriteMapNullValue));
    }

}
