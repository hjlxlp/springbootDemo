package com.example.demo.mapper;

import com.example.demo.entity.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 城市 DAO 接口类
 */
public interface CityMapper {

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

    /**
     * 测试
     *
     * @param city
     * @return
     */
    List<City> findByGroup(@Param("vo") City city);

    Integer insertCity(City city);

    Integer deleteCityById(@Param("id") Integer id);

}
