package com.example.demo.service.impl;

import com.example.demo.entity.City;
import com.example.demo.entity.Student;
import com.example.demo.mapper.CityMapper;
import com.example.demo.mapper.StuMapper;
import com.example.demo.service.CityService;
import com.example.demo.service.StuService;
import com.example.demo.util.BaseResultModel;
import com.example.demo.util.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private CityService cityService;

    public Student findStu(String keyword, Integer scoreBegin, Integer scoreEnd) {
        return stuMapper.findStu(keyword, scoreBegin, scoreEnd);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResultModel<String> testInsert() {
        BaseResultModel<String> resultModel = new BaseResultModel<>();
        try {
            City c = new City();
            c.setCityName("c2");
            cityMapper.insertCity(c);
            //System.out.println(new ArrayList<>().get(1));
            //throw new BizException(0, "0");
            resultModel.setError(20,"20");
            resultModel.setError(30,"30");
            return resultModel;
        } catch (Exception e) {
            e.printStackTrace();
            resultModel.setError(10, "10");
        } finally {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            cityService.testInsert();
            return resultModel;
        }
    }

}
