package com.example.demo;

import com.example.demo.mapper.CityMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hjl
 * @date 2019/3/25 13:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo2Test {

    @Autowired
    private CityMapper cityMapper;

    @Test
    public void test_01() {
        cityMapper.findAllCity();
        System.out.println(1);
    }


}
