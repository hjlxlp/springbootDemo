package com.example.demo.controller;

import com.example.demo.entity.City;
import com.example.demo.service.CityService;
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

    @GetMapping("findById")
    public City findById(@RequestParam(value = "id", required = true) Integer id) {
        return cityService.findCityById(id);
    }

}
