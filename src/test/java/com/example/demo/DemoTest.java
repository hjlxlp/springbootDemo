package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

/**
 * @author hjl
 * @date 2018/12/18 15:40
 */
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

}
