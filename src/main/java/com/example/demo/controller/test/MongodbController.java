//package com.example.demo.controller;
//
//import com.example.demo.mongodb.document.MemberReadHistory;
//import com.example.demo.service.MemberReadHistoryService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * 会员商品浏览记录管理Controller
// * Created by macro on 2018/8/3.
// */
//@RestController
//@RequestMapping("/mongodb")
//public class MongodbController {
//    @Autowired
//    private MemberReadHistoryService memberReadHistoryService;
//
//    @ApiOperation("创建浏览记录")
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public Integer create(@RequestBody MemberReadHistory memberReadHistory) {
//        int count = memberReadHistoryService.create(memberReadHistory);
//        return count;
//    }
//
//    @ApiOperation("删除浏览记录")
//    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    public Integer delete(@RequestParam("ids") List<String> ids) {
//        int count = memberReadHistoryService.delete(ids);
//        return count;
//    }
//
//    @ApiOperation("展示浏览记录")
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public List<MemberReadHistory> list(Long memberId) {
//        List<MemberReadHistory> memberReadHistoryList = memberReadHistoryService.list(memberId);
//        return memberReadHistoryList;
//    }
//}
