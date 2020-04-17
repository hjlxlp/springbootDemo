package com.example.demo.controller;


import com.example.demo.service.OssService;
import com.example.demo.util.OSSUploadUtil;
import com.example.demo.vo.oss.OssCallbackResult;
import com.example.demo.vo.oss.OssPolicyResult;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Oss相关操作接口
 * Created by macro on 2018/4/26.
 */
@RestController
@RequestMapping("/oss")
public class OssController {
    @Autowired
    private OssService ossService;

    @ApiOperation(value = "oss上传签名生成")
    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    public OssPolicyResult policy() {
        OssPolicyResult result = ossService.policy();
        return result;
    }

    @ApiOperation(value = "oss上传成功回调")
    @RequestMapping(value = "callback", method = RequestMethod.POST)
    public OssCallbackResult callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return ossCallbackResult;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty() || file.getSize() == 0) {
            return "null";
        }
        if (file.getSize() > 10 * 1024 * 1024) {
            return "too big";
        }
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        //OSS单文件上传,返回上传成功后的oss存储服务器中的url
        File f = new File("D://test.txt");
        FileUtils.copyInputStreamToFile(file.getInputStream(), f);
        String url = OSSUploadUtil.uploadFile(f, fileType);
        return url;
    }

}
