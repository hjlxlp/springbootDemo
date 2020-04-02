package com.example.demo.controller;

import com.example.demo.service.UmsMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangjiale
 * @date 2020/4/2 9:58
 **/
@RestController
@RequestMapping("/ums")
public class UmsMemberController {

    @Autowired
    private UmsMemberService umsMemberService;

    @ApiOperation("获取验证码")
    @GetMapping(value = "/getAuthCode")
    public String getAuthCode(@RequestParam String telephone) {
        return umsMemberService.generateAuthCode(telephone);
    }

    @ApiOperation("判断验证码是否正确")
    @GetMapping(value = "/verifyAuthCode")
    public String updatePassword(@RequestParam String telephone,
                                 @RequestParam String authCode) {
        return umsMemberService.verifyAuthCode(telephone, authCode);
    }

}
