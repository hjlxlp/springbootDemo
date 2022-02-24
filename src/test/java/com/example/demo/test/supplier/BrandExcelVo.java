package com.example.demo.test.supplier;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author huangjiale
 * @date 2022/2/24 13:10
 **/
@Data
public class BrandExcelVo {

	@ExcelProperty(index = 0)
	private Integer brandId;

	@ExcelProperty(index = 1)
	private Integer parentId;

	@ExcelProperty(index = 2)
	private String brandName;

}
