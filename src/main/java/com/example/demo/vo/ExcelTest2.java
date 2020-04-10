package com.example.demo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author huangjiale
 * @date 2020/3/31 10:41
 **/
@Data
public class ExcelTest2 {

    @ExcelProperty(value = "id", index = 0)
    private Integer id;

    @ExcelProperty(value = "名称", index = 1)
    private String name;

    @ExcelProperty(value = "长度", index = 2)
    private BigDecimal length;

    @ExcelProperty(value = "createTime", index = 3)
    private String createTime;

}
