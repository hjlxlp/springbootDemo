package com.example.demo.test.pdf2.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fly
 */
@ApiModel("店铺详情")
@Data
public class UserShopVO {

    @ApiModelProperty(value = "自增id", example = "1222")
    private Long id;

    @ApiModelProperty(value = "用户id", example = "2")
    private Long uid;

    @ApiModelProperty(value = "店铺名称", example = "母婴店")
    private String shopName;

    @ApiModelProperty(value = "手机号", example = "13370026711")
    private String mobile;

    //@ApiModelProperty(value = "品牌数量", example = "13370026711")
    //private String brandAndNum;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户状态 0 未审核 1 审核中 2 已经审核 3 封禁 4 注销")
    private Integer userStatus;

    @ApiModelProperty(value = "省",  example = "江苏省")
    private String province;

    @ApiModelProperty(value = "市",  example = "淮安市")
    private String city;

    @ApiModelProperty(value = "区县",  example = "淮阴区")
    private String district;

    @ApiModelProperty(value = "详情地址",  example = "XX街道")
    private String addressDetail;

    @ApiModelProperty(value = "详情地址",  example = "XX街道")
    private String shopAddressDetail;

    @ApiModelProperty(value = "门店照",  example = "xxxx.png")
    private String shopPhoto;

    @ApiModelProperty(value = "账号冻结/挂失状态")
    private Integer freezeStatus;
}
