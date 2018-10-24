package com.example.demo.service.impl;

import com.example.demo.dao.CityDao;
import com.example.demo.entity.City;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市业务逻辑实现类
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;


    /**
     * 根据城市名称，查询城市信息
     *
     * @param id
     * @return
     */
    @Override
    public City findCityById(Integer id) {
        return cityDao.findById(id);
    }

    /**
     * 查询所有城市
     *
     * @return
     */
    @Override
    public List<City> findAllCity() {
        return cityDao.findAllCity();
    }
}
