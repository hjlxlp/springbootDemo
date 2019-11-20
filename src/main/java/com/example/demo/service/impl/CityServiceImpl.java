package com.example.demo.service.impl;

import com.example.demo.entity.City;
import com.example.demo.mapper.CityMapper;
import com.example.demo.service.CityService;
import com.example.demo.util.SpringUtil;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 城市业务逻辑实现类
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

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
    public void Transactional() {
        delete1();
        delete2();
        cityMapper.deleteCityById(3);
        cityMapper.insertCity(new City((long) 4, (long) 2, "2", "2"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void Transactional2() {
        /*CityServiceImpl proxy = (CityServiceImpl) AopContext.currentProxy();
        proxy.delete1();
        proxy.delete2();*/
        getService().delete1();
        getService().delete2();
        cityMapper.deleteCityById(3);
        cityMapper.insertCity(new City((long) 4, (long) 2, "2", "2"));
    }

    /**
     * 解决事务失效
     */
    private CityServiceImpl getService() {
        return SpringUtil.getBean(this.getClass());
    }

}
