package com.example.demo.test.supplier;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author huangjiale
 * @date 2022/2/24 13:10
 **/
@Data
public class SkuExcelVo {

	@ExcelProperty(index = 0)
	private Integer skuId;

	@ExcelProperty(index = 1)
	private Long spuId;

	@ExcelProperty(index = 2)
	private Integer brandId;

}
