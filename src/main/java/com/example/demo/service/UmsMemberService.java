package com.example.demo.service;

/**
 * @author huangjiale
 * @date 2020/4/2 9:50
 **/
public interface UmsMemberService {

    /**
     * 生成验证码
     */
    String generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    String verifyAuthCode(String telephone, String authCode);

}
