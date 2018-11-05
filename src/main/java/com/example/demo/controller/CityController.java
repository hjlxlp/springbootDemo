package com.example.demo.controller;

import com.example.demo.entity.City;
import com.example.demo.service.CityService;
import com.example.demo.service.CityTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("city")
@RestController
public class CityController {

    @Autowired
    private CityService cityService;
    @Autowired
    private CityTestService cityTestService;

    @GetMapping("findById")
    public City findById(@RequestParam(value = "id", required = true) Integer id) {
        return cityService.findCityById(id);
    }

    @GetMapping("findByIdTest")
    public City findByIdTest(@RequestParam(value = "id", required = true) Integer id) {
        return cityTestService.findCityByIdTest(id);
    }

}
