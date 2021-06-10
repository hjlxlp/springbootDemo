package com.example.demo.service.impl;

import com.example.demo.entity.City;
import com.example.demo.mapper.CityMapper;
import com.example.demo.service.CityService;
import com.example.demo.util.BaseResultModel;
import com.example.demo.util.BizException;
import com.example.demo.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 城市业务逻辑实现类
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public void insertList() {
        City city;
        for (int i = 0; i < 1; i++) {
            city = new City();
            city.setId(new Long(i));
            city.setCityName(i + "n");
            cityMapper.insertCity(city);
        }
    }

    @Override
    public long deleteOne() {
        LocalTime begin = LocalTime.now();
        for (int i = 1; i <= 1; i++) {
            cityMapper.deleteCityById(i);
        }
        return Duration.between(begin, LocalTime.now()).toMillis();
    }

    @Override
    public long deleteTwo() {
        LocalTime begin = LocalTime.now();
        try {
            for (int i = 1; i <= 1; i++) {
                Method deleteMethod = cityMapper.getClass().getDeclaredMethod("deleteCityById", Integer.class);
                deleteMethod.setAccessible(true);
                deleteMethod.invoke(cityMapper, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Duration.between(begin, LocalTime.now()).toMillis();
    }

    /**
     * 根据城市名称，查询城市信息
     *
     * @param id
     * @return
     */
    @Override
    public City findCityById(Integer id) {
        return cityMapper.findById(id);
    }

    @Override
    public List<City> findCity(City city) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("1");
        list.add("b");
        city.setNameList(list);
        return cityMapper.findCity(city);
    }

    /**
     * 查询所有城市
     *
     * @return
     */
    @Override
    public List<City> findAllCity() {
        return cityMapper.findAllCity();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void delete1() {
        try {
            cityMapper.deleteCityById(1);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void delete2() {
        try {
            cityMapper.deleteCityById(2);
            System.out.println(new ArrayList<>().get(1));
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Override
    public void transactional() {
        delete1();
        delete2();
        cityMapper.deleteCityById(3);
        //cityMapper.insertCity(new City((long) 4, (long) 2, "2", "2"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transactional2() {
        /*CityServiceImpl proxy = (CityServiceImpl) AopContext.currentProxy();
        proxy.delete1();
        proxy.delete2();*/
        getService().delete1();
        getService().delete2();
        cityMapper.deleteCityById(3);
        //cityMapper.insertCity(new City((long) 4, (long) 2, "2", "2"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void printStackTrace() {
        cityMapper.deleteCityById(1);
        try {
            cityMapper.deleteCityById(2);
            System.out.println(new ArrayList<>().get(1));
        } catch (Exception e) {
            cityMapper.deleteCityById(3);
            e.printStackTrace();
            cityMapper.deleteCityById(4);
        }
        cityMapper.deleteCityById(5);
    }

    /**
     * 解决事务失效
     */
    private CityServiceImpl getService() {
        return SpringUtil.getBean(this.getClass());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void testInsert() {
        City c = new City();
        c.setCityName("c1");
        cityMapper.insertCity(c);
    }

    @Override
    public void insert(City c) {
        cityMapper.insertCity(c);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResultModel<String> testError() {
        BaseResultModel<String> t = new BaseResultModel<>();
        //t = testErrorDetails();
        //return t;
        try {
            t = testErrorDetails();
        } catch (Exception e) {
            //e.printStackTrace();
            //System.out.println("=============");
            throw new BizException(1000, "下单异常：" + e.getMessage());
        }
        return t;
    }

    private BaseResultModel<String> testErrorDetails() {
        BaseResultModel resultModel = new BaseResultModel();
        //System.out.println(5 / 0);
        resultModel.setData("test");
        cityMapper.insertCity(new City());
        if (1 == 1) {
            throw new BizException(1, "xxx错误");
        }
        return resultModel;
    }

}
