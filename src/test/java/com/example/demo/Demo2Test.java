package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.City;
import com.example.demo.mapper.CityMapper;
import com.example.demo.service.CityService;
import com.example.demo.service.StuService;
import com.example.demo.util.BaseResultModel;
import com.example.demo.util.BeanMapperUtil;
import okhttp3.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

/**
 * @author hjl
 * @date 2019/3/25 13:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo2Test {

    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private CityService cityService;
    @Autowired
    private StuService stuService;

    @Test
    public void test_01() {
        cityMapper.findAllCity();
    }

    @Test
    public void test_02() {
        City city = new City();
        city.setCityName("1");
        List<City> list = cityMapper.findByGroup(city);
        list.add(city);
        System.out.println(JSONObject.toJSON(list));
    }

    @Test
    public void test_03() {
        /*cityService.insertList();
        long ms1 = cityService.deleteOne();
        System.out.println(ms1);*/
        cityService.insertList();
        long ms2 = cityService.deleteTwo();
        System.out.println(ms2);
    }

    @Test
    public void test_04() {
        BaseResultModel<String> resultModel = stuService.testInsert();
        System.out.println(JSON.toJSONString(resultModel));
    }


}
