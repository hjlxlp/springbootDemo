package com.example.demo.controller;

import com.example.demo.entity.City;
import com.example.demo.entity.TestValidation;
import com.example.demo.service.CityService;
import com.example.demo.service.CityTestService;
import com.example.demo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;


@RequestMapping("/city")
@RestController
@Controller
public class CityController {

    @Autowired
    private CityService cityService;
    @Autowired
    private CityTestService cityTestService;

    @PostMapping("testTotal")
    public City testTotal(@RequestParam("total") BigDecimal total) {
        City city = new City();
        city.setTotal(StringUtil.getTwoZeroBigDecimal(total));
        return city;
    }

    @PostMapping("testValidation")
    public City testValidation(@Valid @RequestBody TestValidation testValidation) {
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
