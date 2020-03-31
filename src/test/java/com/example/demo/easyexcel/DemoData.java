package com.example.demo.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 基础数据类.这里的排序和excel里面的排序一致
 *
 * @author Jiaju Zhuang
 **/
@Data
public class DemoData {
    @ExcelProperty(value = "id", index = 0)
    private Integer id;

    @ExcelProperty(value = "名称", index = 1)
    private String name;

    @ExcelProperty(value = "长度", index = 2)
    private BigDecimal length;

    @ExcelProperty(value = "createTime", index = 3)
    private Date createTime;
}
