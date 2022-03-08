package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author huangjiale
 * @date 2022/3/7 14:10
 **/
@Data
@TableName("bill_of_sales")
public class BillOfSalesProductEntity {

	@TableId(type = IdType.AUTO)
	private Long id;

	@ApiModelProperty(value = "出货单id")
	private Long billOfSalesId;

	@ApiModelProperty(value = "商品名称")
	private String productName;

	@ApiModelProperty(value = "商品编号")
	private String barCode;

	@ApiModelProperty(value = "规格")
	private String specs;

	@ApiModelProperty(value = "批文号")
	private String approvalNo;

	@ApiModelProperty(value = "批次")
	private String batchNo;

	@ApiModelProperty(value = "有效期")
	private String termOfValidity;

	@ApiModelProperty(value = "单位")
	private String unit;

	@ApiModelProperty(value = "数量")
	private String number;

	@ApiModelProperty(value = "单价")
	private String unitPrice;

	@ApiModelProperty(value = "总价")
	private String amount;

	@ApiModelProperty(value = "是否删除")
	@TableLogic
	private Boolean deleted;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "更新时间")
	private Date updateTime;

}
