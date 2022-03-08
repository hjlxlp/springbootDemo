package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author huangjiale
 * @date 2022/3/7 14:10
 **/
@Data
@TableName("bill_of_sales")
public class BillOfSalesEntity {

	@TableId(type = IdType.AUTO)
	private Long id;

	@ApiModelProperty(value = "供应商名称")
	private String supplierName;

	@ApiModelProperty(value = "供应商id")
	private String supplierId;

	@ApiModelProperty(value = "出货单名称")
	private String billOfSalesName;

	@ApiModelProperty(value = "店铺id")
	private String shopId;

	@ApiModelProperty(value = "店铺名称")
	private String shopName;

	@ApiModelProperty(value = "生成时间")
	private String generateTime;

	@ApiModelProperty(value = "收货人信息")
	private String consignee;

	@ApiModelProperty(value = "订单编号")
	private String childOrderId;

	@ApiModelProperty(value = "积分抵扣")
	private String scoreDeduct;

	@ApiModelProperty(value = "总数")
	private String totalNumber;

	@ApiModelProperty(value = "总金额")
	private String totalAmount;

	@ApiModelProperty(value = "大写的总数")
	private String bigTotalAmount;

	@ApiModelProperty(value = "备注")
	private String remarks;

	@ApiModelProperty(value = "业务员")
	private String salesNan;

	@ApiModelProperty(value = "联系电话")
	private String mobile;

	@ApiModelProperty(value = "Excel地址")
	private String excelUrl;

	@ApiModelProperty(value = "Pdf地址")
	private String pdfUrl;

	@ApiModelProperty(value = "是否删除")
	@TableLogic
	private Boolean deleted;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "更新时间")
	private Date updateTime;

	@ApiModelProperty(value = "商品信息")
	private List<BillOfSalesProductEntity> productList;

}
