package com.example.demo.service;


import com.example.demo.entity.City;

/**
 * 城市业务逻辑接口类
 */
public interface CityTestService {

    /**
     * 根据城市名称，查询城市信息
     *
     * @param id
     * @return
     */
    City findCityByIdTest(Integer id);

}
