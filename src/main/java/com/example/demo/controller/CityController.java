package com.example.demo.controller;

import com.example.demo.entity.City;
import com.example.demo.entity.TestValidation;
import com.example.demo.mapper.CityMapper;
import com.example.demo.service.CityService;
import com.example.demo.service.CityTestService;
import com.example.demo.util.BaseResultModel;
import com.example.demo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import javax.validation.Valid;


@RequestMapping("/city")
@RestController
@Controller
public class CityController {

    @Autowired
    private CityService cityService;
    @Autowired
    private CityTestService cityTestService;
    @Autowired
    private CityMapper cityMapper;


    @PostMapping("test10000")
    public BaseResultModel test10000() {
        List<City> list = new ArrayList<>();
        Date date = new Date();
        for (int i = 0; i < 10000; i++) {
            City city = new City();
            city.setCityName(i + "");
            city.setProvinceId(new Long(i));
            city.setDescription(i + "d");
            city.setCreateTime(date);
            list.add(city);
        }
        LocalTime begin = LocalTime.now();
        cityMapper.insert10000(list);
        System.out.println(Duration.between(begin, LocalTime.now()).toMillis());
        return new BaseResultModel();
    }

    @PostMapping("testError")
    public BaseResultModel<String> testError() {
        return cityService.testError();
    }

    @PostMapping("testDevtools")
    public String testDevtools() {
        return "2000";
    }

    @PostMapping("testTotal")
    public City testTotal(@RequestParam("total") BigDecimal total) {
        City city = new City();
        city.setTotal(StringUtil.getTwoZeroBigDecimal(total));
        return city;
    }

    @PostMapping("testValidation")
    public City testValidation(@RequestBody TestValidation testValidation) {
        City city = new City();
        city.setCityName("测试");
        return city;
    }

    @Cacheable(key = "#id", value = "city")
    @GetMapping("findById")
    public City findById(@RequestParam(value = "id", required = true) Integer id) {
        return cityService.findCityById(id);
    }

    @GetMapping("findByIdTest")
    public City findByIdTest(@RequestParam(value = "id", required = true) Integer id) {
        return cityTestService.findCityByIdTest(id);
    }

    @GetMapping("transactional")
    public void transactional() {
        cityService.transactional();
    }

    @GetMapping("transactional2")
    public void transactional2() {
        cityService.transactional2();
    }

    @GetMapping("printStackTrace")
    public void printStackTrace() {
        cityService.printStackTrace();
    }


    @PostMapping("insert")
    public void insert(@RequestBody City city) {
        cityService.insert(city);
    }

}
