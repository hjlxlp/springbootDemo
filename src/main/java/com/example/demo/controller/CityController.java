package com.example.demo.controller;

import com.example.demo.entity.City;
import com.example.demo.entity.TestValidation;
import com.example.demo.service.CityService;
import com.example.demo.service.CityTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("city")
@RestController
public class CityController {

    @Autowired
    private CityService cityService;
    @Autowired
    private CityTestService cityTestService;

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

}
