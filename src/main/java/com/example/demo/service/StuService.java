package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.util.BaseResultModel;

public interface StuService {
    Student findStu(String keyword,Integer scoreBegin,Integer scoreEnd);

    BaseResultModel<String> testInsert();

}
