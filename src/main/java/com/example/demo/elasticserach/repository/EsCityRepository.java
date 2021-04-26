//package com.example.demo.elasticserach.repository;
//
//import com.example.demo.elasticserach.document.EsCity;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
///**
// * @author huangjiale
// * @date 2020/4/10 13:38
// **/
//public interface EsCityRepository extends ElasticsearchRepository<EsCity, Long> {
//
//    Page<EsCity> queryByCityNameOrDescription(String cityName, String description, Pageable page);
//
//    Page<EsCity> queryByCityNameLike(String cityName, Pageable page);
//
//    EsCity queryById(Long id);
//
//}
