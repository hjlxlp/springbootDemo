package com.example.demo.controller.qywx;

import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpTpDefaultConfigImpl;
import me.chanjar.weixin.cp.tp.service.WxCpTpOAService;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import me.chanjar.weixin.cp.tp.service.impl.WxCpTpOAServiceImpl;
import me.chanjar.weixin.cp.tp.service.impl.WxCpTpServiceImpl;
import me.chanjar.weixin.cp.util.crypto.WxCpTpCryptUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;

@Configuration
public class WxTpCallbackConfig {

    //@Value("${wecom.tp.suiteId}")
    private String suiteId = "ww92b1fe94b0b5f52a";

    //@Value("${wecom.tp.token}")
    private String token = "oiO3SxUtYYpuFhrfMYHxu";

    //@Value("${wecom.tp.encodingAESKey}")
    private String encodingAESKey = "ibkMst7zzHEy6RTEsEIO5fdwglSRMUH1rWFkJr1TjhR";

    //@Value("${wecom.tp.token}")
    private String suiteSecret = "o156WWwI5S6cj5MPsO7VNl0V2_F_ifr5shgwZxeLGyE";

    //@Value("${wecom.tp.corpid}")
    private String corpid = "ww8d0f5f9b18409cb2";

    //@Value("${wecom.tp.providerSecret}")
    private String providerSecret = "v52XX5euVsWKb31CehRwZd3jfkWXHwNRUG294xJSDA7H6G2sIwkoq6pANy1VcR4K";

    @Bean
    public WxCpTpConfigStorage wxCpTpConfigStorage() {
        WxCpTpDefaultConfigImpl config = new WxCpTpDefaultConfigImpl();
        config.setSuiteId(suiteId);
        config.setToken(token);
        config.setAesKey(encodingAESKey);
        config.setSuiteSecret(suiteSecret);
        config.setCorpId(corpid);

        // 反射注入 providerSecret
        try {
            Field field = WxCpTpDefaultConfigImpl.class.getDeclaredField("providerSecret");
            field.setAccessible(true);
            field.set(config, providerSecret);
        } catch (Exception e) {
            throw new RuntimeException("注入 providerSecret 失败", e);
        }

        return config;
    }

    @Bean
    public WxCpTpService wxCpTpService(WxCpTpConfigStorage storage) {
        WxCpTpServiceImpl service = new WxCpTpServiceImpl();
        service.setWxCpTpConfigStorage(storage);
        return service;
    }

    @Bean
    public WxCpTpCryptUtil wxCpTpCryptUtil(WxCpTpConfigStorage storage) {
        WxCpTpCryptUtil wxCpTpCryptUtil = new WxCpTpCryptUtil(storage);
        return wxCpTpCryptUtil;
    }

}
