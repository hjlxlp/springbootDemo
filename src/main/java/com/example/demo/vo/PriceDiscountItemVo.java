package com.example.demo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@ApiModel("商品一户一价折扣信息vo")
@Accessors(chain = true)
public class PriceDiscountItemVo {
	@ApiModelProperty(value = "折扣分类ID", required = true, example = "1")
	private Integer discountCategoryId;

	@ApiModelProperty(value = "折扣分类名称", required = true)
	private String name;

	@ApiModelProperty(value = "折扣值，乘100传入", required = true, example = "100")
	private Integer discount;

	@ApiModelProperty(value = "用户uid", example = "1")
	private Long uid;
}
