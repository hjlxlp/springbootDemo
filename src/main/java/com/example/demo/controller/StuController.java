package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("stu")
@RestController
public class StuController {
    @Autowired
    private StuService stuService;

    @GetMapping("/findStu")
    public Student findStu(String keyword, Integer scoreBegin, Integer scoreEnd){
        return stuService.findStu(keyword,scoreBegin,scoreEnd);
    }
}
