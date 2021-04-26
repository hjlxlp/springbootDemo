//package com.example.demo.service.impl;
//
//import com.example.demo.elasticserach.document.EsCity;
//import com.example.demo.elasticserach.repository.EsCityRepository;
//import com.example.demo.entity.City;
//import com.example.demo.mapper.CityMapper;
//import com.example.demo.service.EsService;
//import com.example.demo.util.BeanMapperUtil;
//import com.google.common.collect.Lists;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author huangjiale
// * @date 2020/4/10 13:47
// **/
//@Service
//public class EsServiceImpl implements EsService {
//
//    @Autowired
//    private EsCityRepository esCityRepository;
//    @Autowired
//    private CityMapper cityMapper;
//
//    @Override
//    public void delete(Long id) {
//        esCityRepository.deleteById(id);
//    }
//
//    @Override
//    public EsCity create(Long id) {
//        City city = cityMapper.findById(Integer.valueOf(id.toString()));
//        EsCity esCity = BeanMapperUtil.objConvert(city, EsCity.class);
//        esCityRepository.save(esCity);
//        return esCity;
//    }
//
//    @Override
//    public Page<EsCity> search(String keyword, Integer pageNum, Integer pageSize) {
//        Pageable pageable = PageRequest.of(pageNum, pageSize);
//        return esCityRepository.queryByCityNameOrDescription(keyword, keyword, pageable);
//    }
//
//    @Override
//    public EsCity queryById(Long id) {
//        return esCityRepository.queryById(id);
//    }
//
//    @Override
//    public List<EsCity> selectAll() {
//        Iterable<EsCity> cityIterable = esCityRepository.findAll();
//        List<EsCity> list = Lists.newArrayList(cityIterable);
//        return list;
//    }
//
//}
