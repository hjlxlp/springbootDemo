package com.example.demo.service;

import com.example.demo.elasticserach.document.EsCity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author huangjiale
 * @date 2020/4/10 13:45
 **/
public interface EsService {

    /**
     * 根据id删除商品
     */
    void delete(Long id);

    /**
     * 根据id创建商品
     */
    EsCity create(Long id);

    EsCity queryById(Long id);

    /**
     * 根据关键字搜索名称或者描述
     */
    Page<EsCity> search(String keyword, Integer pageNum, Integer pageSize);


    List<EsCity> selectAll();

}
