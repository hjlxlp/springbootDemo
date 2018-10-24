package com.example.demo.service;


import com.example.demo.entity.City;

import java.util.List;

/**
 * 城市业务逻辑接口类
 */
public interface CityService {

    /**
     * 根据城市名称，查询城市信息
     *
     * @param id
     * @return
     */
    City findCityById(Integer id);

    /**
     * 查询所有城市
     *
     * @return
     */
    List<City> findAllCity();

}
