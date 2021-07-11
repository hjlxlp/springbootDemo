package com.example.demo.service;


import com.example.demo.entity.City;
import com.example.demo.util.BaseResultModel;

import java.util.List;

/**
 * 城市业务逻辑接口类
 */
public interface CityService {

    void insertList();

    long deleteOne();

    long deleteTwo();

    /**
     * 根据城市名称，查询城市信息
     *
     * @param id
     * @return
     */
    City findCityById(Integer id);

    List<City> findCity(City city);

    /**
     * 查询所有城市
     *
     * @return
     */
    List<City> findAllCity();

    void transactional();

    void transactional2();

    void printStackTrace();

    void testInsert();

    BaseResultModel<String> testError();

    /**
     * 新增一个城市
     * @param c
     */
    void insert(City c);

    /**
     * 分页查询城市
     * @return
     */
    List<City> findByidFenYe(int a,int b);

}
