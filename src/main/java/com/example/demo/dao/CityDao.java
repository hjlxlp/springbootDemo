package com.example.demo.dao;

import com.example.demo.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 城市 DAO 接口类
 *
 */
public interface CityDao {

    /**
     * 根据城市名称，查询城市信息
     *
     * @param id
     * @return
     */
    City findById(@Param("id") Integer id);

    /**
     * 查询所有城市
     *
     * @return
     */
    List<City> findAllCity();

}