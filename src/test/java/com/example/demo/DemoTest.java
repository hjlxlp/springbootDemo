package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author hjl
 * @date 2019/3/25 13:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {

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

    @Test
    public void test_05() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "*";
        String accessKeySecret = "*";
        String fileName = "C:/Users/22474/Desktop/test2.jpg";
        String bucketName = "hjl-oss-test";
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 生成上传文件名
        String finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + suffixName;
        String objectName = sdf.format(new Date()) + "/" + finalFileName;
        File file = new File(fileName);
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        ossClient.putObject(bucketName, objectName, file);
        // 设置URL过期时间为1小时。
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
        ossClient.shutdown();
        System.out.println(url.toString());
    }

}
