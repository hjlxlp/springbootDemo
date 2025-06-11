package com.example.demo.controller.qywx;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.example.demo.test.pdf.BaseResponse;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpTpUserInfo;
import me.chanjar.weixin.cp.bean.WxTpLoginInfo;
import me.chanjar.weixin.cp.bean.oa.WxCpOaApplyEventRequest;
import me.chanjar.weixin.cp.bean.oa.WxCpOaApprovalTemplateResult;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import me.chanjar.weixin.cp.util.crypto.WxCpTpCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/wx/tp")
public class WxTpController {

    private static String suite_access_token = "";

    @Autowired
    private WxCpTpConfigStorage configStorage;
    @Autowired
    private WxCpTpService wxCpTpService;
    @Autowired
    private WxCpTpCryptUtil wxCpTpCryptUtil;


    /**
     * 实际上不用自己管理
     *
     * @return
     */
    @GetMapping("/getSuiteAccessToken")
    public String getSuiteAccessToken() {
        /*WxCpTpDefaultConfigImpl config = new WxCpTpDefaultConfigImpl();
        config.setSuiteId(SUITE_ID);
        config.setSuiteSecret("o156WWwI5S6cj5MPsO7VNl0V2_F_ifr5shgwZxeLGyE");
        config.setToken(TOKEN); // 可选
        config.setAesKey(ENCODING_AES_KEY); // 可选

        WxCpTpService wxCpTpService = new WxCpTpServiceImpl();
        wxCpTpService.setWxCpTpConfigStorage(config);*/

        try {
            //wxCpTpService.setSuiteTicket("qM5dqB7G3I6n5xHr95HhRcyCeEb-yZYbaiEMNqvpxHiCy3FKl0KN_9vnHzwcH2AJ");
            String token = wxCpTpService.getSuiteAccessToken();
            System.out.println("获取到 suite_access_token:" + token);

            this.suite_access_token = token;
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return "获取失败: " + e.getMessage();
        }
    }

    @GetMapping("/getUserInfo")
    public WxCpTpUserInfo getUserInfo(@RequestParam("code") String code) throws WxErrorException {
        WxCpTpUserInfo userInfo = wxCpTpService.getUserInfo3rd(code);
        return userInfo;
    }

    @GetMapping("/getUserInfo2")
    public WxTpLoginInfo getUserInfo2(@RequestParam("auth_code") String authCode) throws WxErrorException {
        WxTpLoginInfo wxTpLoginInfo = wxCpTpService.getLoginInfo(authCode);
        return wxTpLoginInfo;
    }

    @GetMapping("/copyTemplate")
    public String copyTemplate(@RequestParam("suiteTicket") String suiteTicket) throws WxErrorException {
        WxAccessToken wxAccessToken = wxCpTpService.getCorpToken("wpWMPDIwAAKCpSrWdD_k_L6xaAi1YBLA", "edc6unkzXLnSpEegbQj05Wul6N-7-tT0un-qOLzRxU8", true);
        /*if (wxAccessToken == null || wxAccessToken.getAccessToken() == null) {
            wxCpTpService.getCorpToken("wpWMPDIwAAKCpSrWdD_k_L6xaAi1YBLA", "JZh8f20V30hX88to3Mgs8Vq4RHSPBqNqfZmhBpL_DVQ", true);
        }*/
        String res = wxCpTpService.getWxCpTpOAService().copyTemplate("1C4c4eseczeLe8mSViYbpacZsvjY2U1musB75gN9Qk", "wpWMPDIwAAKCpSrWdD_k_L6xaAi1YBLA");
        System.out.println("===copyTemplate===" + res);

        //WxAccessToken wxAccessToken = wxCpTpService.getCorpToken("wpWMPDIwAAKCpSrWdD_k_L6xaAi1YBLA", "edc6unkzXLnSpEegbQj05Wul6N-7-tT0un-qOLzRxU8", true);

        /*String url = "https://qyapi.weixin.qq.com/cgi-bin/oa/getapprovaldetail?access_token=" + wxAccessToken.getAccessToken();
        JSONObject body = new JSONObject();
        body.put("sp_no", "201909270001");
        HttpResponse response = HttpRequest.post(url)
                .header("Content-Type", "application/json")
                .body(body.toString())
                .execute();
        // 输出请求参数与响应内容
        System.out.println("请求参数：" + body.toStringPretty());
        System.out.println("响应内容：" + response.body());*/

        /*WxAccessToken wxAccessToken = wxCpTpService.getCorpToken("wpWMPDIwAAKCpSrWdD_k_L6xaAi1YBLA", "edc6unkzXLnSpEegbQj05Wul6N-7-tT0un-qOLzRxU8", true);
        String url2 = "https://qyapi.weixin.qq.com/cgi-bin/oa/approval/copytemplate?access_token=" + wxAccessToken.getAccessToken();
        JSONObject body = new JSONObject();
        body.put("open_template_id", "1C4c4eseczeLe8mSViYbpacZsvjY2U1musB75gN9Qk");
        HttpResponse response2 = HttpRequest.post(url2)
                .body(body.toString())
                .header("Content-Type", "application/json")
                .execute();
        System.out.println("复制模板响应状态：" + response2.getStatus());
        System.out.println("复制模板响应结果：" + response2.body());

        return response2.body();*/

        return res;
    }

    @GetMapping("/getTemplateDetail")
    public WxCpOaApprovalTemplateResult getTemplateDetail() throws WxErrorException {
        //WxAccessToken wxAccessToken = wxCpTpService.getCorpToken("wpWMPDIwAAKCpSrWdD_k_L6xaAi1YBLA", "yB1mU0NJYPftvBVbrWBEA6lLb_BjmUFRv15YVcXRSSs", false);
        //WxCpOaApprovalTemplateResult res = wxCpTpService.getWxCpTpOAService().getTemplateDetail("3WN5uZgqrk5k1Ha6m522wtuiBoVhNwVdPSHGKo4E", "wpWMPDIwAAKCpSrWdD_k_L6xaAi1YBLA");

        WxAccessToken wxAccessToken = wxCpTpService.getCorpToken("wpWMPDIwAAKCpSrWdD_k_L6xaAi1YBLA", "edc6unkzXLnSpEegbQj05Wul6N-7-tT0un-qOLzRxU8", true);
        WxCpOaApprovalTemplateResult result = wxCpTpService.getWxCpTpOAService().getTemplateDetail("open_C4c4fH8nvASnR17v8zrMfiyhHkBBVtnScgxteFJUJ", "wpWMPDIwAAKCpSrWdD_k_L6xaAi1YBLA");

        return result;
    }

    @PostMapping("/submitTemplate")
    public String submitTemplate(@RequestBody WxCpOaApplyEventRequest request) throws WxErrorException {
        //request = JSON.parseObject("{\"creator_userid\":\"woWMPDIwAA8j9dgD9EmyqFcPAY2yyZ8g\",\"template_id\":\"open_C4c4fH8nvASnR17v8zrMfiyhHkBBVtnScgxteFJUJ\",\"use_template_approver\":1,\"apply_data\":{\"contents\":[{\"control\":\"Text\",\"id\":\"item-1747128730050\",\"title\":[{\"text\":\"名称\",\"lang\":\"zh_CN\"}],\"value\":{\"text\":\"测试文本111\"}},{\"control\":\"File\",\"id\":\"item-1747128740331\",\"title\":[{\"text\":\"附件\",\"lang\":\"zh_CN\"}],\"value\":{\"text\":\"\"},\"files\":[]}]}}", WxCpOaApplyEventRequest.class);
        WxAccessToken wxAccessToken = wxCpTpService.getCorpToken("wpWMPDIwAAKCpSrWdD_k_L6xaAi1YBLA", "edc6unkzXLnSpEegbQj05Wul6N-7-tT0un-qOLzRxU8", true);
        String res = wxCpTpService.getWxCpTpOAService().apply(request, "wpWMPDIwAAKCpSrWdD_k_L6xaAi1YBLA");
        return res;
    }

    @PostMapping("/uploadFile")
    @ApiOperation("上传文件")
    public BaseResponse<WxMediaUploadResult> uploadFile(@RequestParam("file") MultipartFile file) throws WxErrorException {
        WxAccessToken wxAccessToken = wxCpTpService.getCorpToken("wpWMPDIwAAKCpSrWdD_k_L6xaAi1YBLA", "edc6unkzXLnSpEegbQj05Wul6N-7-tT0un-qOLzRxU8", true);

        WxMediaUploadResult result = null;
        File tempFile = null;
        try {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                return BaseResponse.fail("文件名不能为空");
            }
            // 文件扩展名
            File tempDir = new File(System.getProperty("java.io.tmpdir"));
            tempFile = new File(tempDir, originalFilename);
            file.transferTo(tempFile);

            WxMediaUploadResult res = wxCpTpService.getWxCpTpMediaService().upload("file", tempFile, "wpWMPDIwAAKCpSrWdD_k_L6xaAi1YBLA");
            return BaseResponse.success(res);
        } catch (Exception e) {
            return BaseResponse.fail("上传文件错误");
        } finally {
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
        }

    }

    @GetMapping("/downloadFile")
    @ApiOperation("下载文件")
    public File downloadFile(@RequestParam("mediaId") String mediaId) throws WxErrorException {
        // 请替换为你自己的 access_token 和 media_id
        WxAccessToken wxAccessToken = wxCpTpService.getCorpToken("wpWMPDIwAAKCpSrWdD_k_L6xaAi1YBLA", "edc6unkzXLnSpEegbQj05Wul6N-7-tT0un-qOLzRxU8", true);
        String url = "https://qyapi.weixin.qq.com/cgi-bin/media/get"
                + "?access_token=" + wxAccessToken.getAccessToken()
                + "&media_id=" + mediaId;

        // 下载文件并保存
        HttpResponse response = HttpRequest.get(url)
                .execute();

        if (response.isOk()) {
            File file = FileUtil.writeBytes(response.bodyBytes(), new File("downloaded_media.jpg"));
            System.out.println("文件已保存到：" + file.getAbsolutePath());
            return file;
        } else {
            System.err.println("下载失败，状态码：" + response.getStatus());
            System.err.println("响应内容：" + response.body());
            return null;
        }
    }

}
