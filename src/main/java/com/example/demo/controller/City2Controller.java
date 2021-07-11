package com.example.demo.controller;

import com.example.demo.entity.City;
import com.example.demo.mapper.CityMapper;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/city2")
@RestController
public class City2Controller {
    @Autowired
    private CityService cityService;

    @Autowired
    private CityMapper cityMapper;

    @PostMapping("insert")
    public void insert(@RequestBody City city) {
        cityService.insert(city);
    }
    @PostMapping("findByidFenye")
    public List<City> findByidFenye(int a,int b){
        return cityService.findByidFenYe(a,b);
    }
}
