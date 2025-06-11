package com.example.demo.controller.qywx;

import me.chanjar.weixin.cp.bean.WxCpTpPermanentCodeInfo;
import me.chanjar.weixin.cp.bean.message.WxCpTpXmlMessage;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import me.chanjar.weixin.cp.util.crypto.WxCpTpCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wx/tp/callback")
public class WxTpCallbackController {

    public static String authCorpId = "";
    public static String permanentCode = "";

    @Autowired
    private WxCpTpConfigStorage configStorage;
    @Autowired
    private WxCpTpService wxCpTpService;
    @Autowired
    private WxCpTpCryptUtil wxCpTpCryptUtil;


    /**
     * 企业微信验证URL（GET）
     */
    @GetMapping
    public String callbackGet(@RequestParam("msg_signature") String msgSignature,
                              @RequestParam("timestamp") String timestamp,
                              @RequestParam("nonce") String nonce,
                              @RequestParam("echostr") String echostr) {
        try {
            /*WxCpTpDefaultConfigImpl config = new WxCpTpDefaultConfigImpl();
            config.setSuiteId(SUITE_ID);
            config.setToken(TOKEN);
            config.setAesKey(ENCODING_AES_KEY);
            config.setSuiteSecret(SUITE_SECRET);
            WxCpTpCryptUtil wxCpTpCryptUtil = new WxCpTpCryptUtil(config);*/
            String message = wxCpTpCryptUtil.decryptContent(msgSignature, timestamp, nonce, echostr);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 企业微信指令回调处理（POST）
     */
    @PostMapping
    public String callbackPost(@RequestParam("msg_signature") String msgSignature,
                               @RequestParam("timestamp") String timestamp,
                               @RequestParam("nonce") String nonce,
                               @RequestBody String requestBody) {
        try {
            /*WxCpTpDefaultConfigImpl config = new WxCpTpDefaultConfigImpl();
            config.setSuiteId(SUITE_ID);
            config.setToken(TOKEN);
            config.setAesKey(ENCODING_AES_KEY);
            config.setSuiteSecret(SUITE_SECRET);

            WxCpTpCryptUtil wxCpTpCryptUtil = new WxCpTpCryptUtil(config);*/
            String decryptedXml = wxCpTpCryptUtil.decryptXml(msgSignature, timestamp, nonce, requestBody);

            // 解析 XML 获取 InfoType
            WxCpTpXmlMessage message = WxCpTpXmlMessage.fromXml(decryptedXml);
            if ("suite_ticket".equals(message.getInfoType())) {
                String suiteTicket = message.getSuiteTicket();
                System.out.println("接收到 suite_ticket: " + suiteTicket);

                // TODO: 建议保存 suite_ticket 到缓存或数据库
                /*WxCpTpService wxCpTpService = new WxCpTpServiceImpl();
                wxCpTpService.setWxCpTpConfigStorage(config);*/
                wxCpTpService.setSuiteTicket(suiteTicket);

            } else if ("create_auth".equals(message.getInfoType())) {
                String suiteId = message.getSuiteId();

                // 获取企业微信推送的临时授权码
                String authCode = message.getAuthCode();
                System.out.println("接收到 auth_code: " + authCode);

                // 调用接口获取永久授权码和企业信息
                WxCpTpPermanentCodeInfo permanentCodeInfo = wxCpTpService.getPermanentCodeInfo(authCode);

                String authCorpId = permanentCodeInfo.getAuthCorpInfo().getCorpId();
                String permanentCode = permanentCodeInfo.getPermanentCode();
                this.authCorpId = authCorpId;
                this.permanentCode = permanentCode;

                System.out.println("授权企业 corpId: " + authCorpId);
                System.out.println("获取到 permanent_code: " + permanentCode);

                // TODO: 建议保存 authCorpId + permanentCode 到数据库，以后换取 access_token 使用

            }

            // 可添加更多InfoType分支，如: create_auth, cancel_auth等

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

}
