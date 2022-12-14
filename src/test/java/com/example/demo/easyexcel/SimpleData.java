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
public class SimpleData {

    @ExcelProperty(index = 0)
    private String date;

    @ExcelProperty(index = 1)
    private String brand;

    @ExcelProperty(index = 2)
    private String name;

}
