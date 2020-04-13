package com.example.demo.vo.oss;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author huangjiale
 * @date 2020/4/13 13:28
 **/
@Data
public class OSSConfig {
    private String endpoint="http://oss-cn-hangzhou.aliyuncs.com";        //连接区域地址
    private String accessKeyId="*";    //连接keyId
    private String accessKeySecret="*";    //连接秘钥
    private String bucketName="hjl-oss-test";    //需要存储的bucketName
    private String picLocation="mall/images/";    //图片保存路径

}
