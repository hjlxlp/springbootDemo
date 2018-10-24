package com.example.demo.controller;

import com.example.demo.entity.City;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * 城市 Controller 实现 Restful HTTP 服务
 */
@Controller
@RequestMapping("free")
public class FreemarkerController {

    @Autowired
    private CityService cityService;

    @GetMapping("/one/{id}")
    public String findOneCity(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("city", cityService.findCityById(id));
        return "city";
    }

    @GetMapping("/all")
    public String findAllCity(Model model) {
        List<City> cityList = cityService.findAllCity();
        model.addAttribute("cityList", cityList);
        return "cityList";
    }
    
}
