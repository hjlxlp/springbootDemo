//package com.example.demo.controller;
//
//import com.example.demo.elasticserach.document.EsCity;
//import com.example.demo.service.EsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * @author huangjiale
// * @date 2020/4/10 13:54
// **/
//@RequestMapping("es")
//@RestController
//public class EsController {
//
//    @Autowired
//    private EsService esService;
//
//
//    /**
//     * 根据id删除商品
//     */
//    @GetMapping("delete")
//    public void delete(Long id) {
//        esService.delete(id);
//    }
//
//    /**
//     * 根据id创建商品
//     */
//    @GetMapping("create")
//    public EsCity create(Long id) {
//        return esService.create(id);
//    }
//
//    @GetMapping("queryById")
//    public EsCity queryById(Long id) {
//        return esService.queryById(id);
//    }
//
//    /**
//     * 根据关键字搜索名称或者描述
//     */
//    @GetMapping("search")
//    public Page<EsCity> search(@RequestParam String keyword, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
//        return esService.search(keyword, pageNum, pageSize);
//    }
//
//    @GetMapping("selectAll")
//    public List<EsCity> selectAll() {
//        return esService.selectAll();
//    }
//
//}
