package com.example.demo.test.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author huangjiale
 * @date 2022/3/7 13:30
 **/
@Data
public class TableExcelVo {

	@ExcelProperty(value = "列名", index = 0)
	private String name;

	@ExcelProperty(value = "类型", index = 1)
	private String type;

	@ExcelProperty(value = "是否可为null", index = 2)
	private String isNull;

	@ExcelProperty(value = "默认值", index = 3)
	private String defaultValue;

	@ExcelProperty(value = "备注", index = 4)
	private String remark;

}
