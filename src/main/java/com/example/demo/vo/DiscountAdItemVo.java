package com.example.demo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


@Data
@ApiModel("后台-折扣类详细信息")
@Accessors(chain = true)
public class DiscountAdItemVo {
	@ApiModelProperty(value = "用户uid", example = "1")
	private Long uid;

	@ApiModelProperty(value = "用户姓名", example = "1")
	private String username;

	@ApiModelProperty(value = "用户店铺地址-省", example = "1")
	private String province;

	@ApiModelProperty(value = "用户店铺地址-市", example = "1")
	private String city;

	@ApiModelProperty(value = "用户店铺地址-区/县", example = "1")
	private String county;

	@ApiModelProperty(value = "分类折扣信息", example = "1")
	private List<PriceDiscountItemVo> priceDiscountItemInfo;

	private Integer excelNo;

}
