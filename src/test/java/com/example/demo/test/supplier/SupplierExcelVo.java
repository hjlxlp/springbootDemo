package com.example.demo.test.supplier;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * @author huangjiale
 * @date 2022/2/24 13:10
 **/
@Data
public class SupplierExcelVo {

	@ExcelProperty(value = "品牌名称", index = 0)
	private String brandName;

	@ExcelProperty(value = "供应商名称", index = 1)
	private String sName;

	@ExcelProperty(value = "收款公司（供应商）", index = 2)
	private String supplierName;

	@ExcelProperty(value = "进货宝服务费比例（平台分账比例）", index = 3)
	private Integer splitRatio;

}
