package com.example.demo.service.impl;

import com.example.demo.entity.City;
import com.example.demo.mapperTest.CityTestMapper;
import com.example.demo.service.CityTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 城市业务逻辑实现类
 */
@Service
public class CityServiceTestImpl implements CityTestService {

    @Autowired
    private CityTestMapper cityTestMapper;

    /**
     * 根据城市名称，查询城市信息
     *
     * @param id
     * @return
     */
    @Override
    public City findCityByIdTest(Integer id) {
        return cityTestMapper.findById(id);
    }


}
