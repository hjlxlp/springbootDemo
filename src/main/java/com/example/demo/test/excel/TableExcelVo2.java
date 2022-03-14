package com.example.demo.test.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author huangjiale
 * @date 2022/3/7 13:30
 **/
@Data
public class TableExcelVo2 {

	@ExcelProperty(value = "商品id", index = 0)
	private Integer spuId;

	@ExcelProperty(value = "商品名称", index = 1)
	private String name;

	@ExcelProperty(value = "品牌排序", index = 2)
	private Integer brandSort;

	@ExcelProperty(value = "商品排序", index = 3)
	private Integer spuSort;

}
