package com.example.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huangjiale
 * @date 2020/3/31 10:41
 **/
@Data
public class ExcelTestVo {

    @ExcelProperty(value = "id", index = 0)
    private Integer id;

    @ExcelProperty(value = "名称", index = 1)
    private String name;

    @ExcelProperty(value = "长度", index = 2)
    private BigDecimal length;

    @ExcelProperty(value = "createTime", index = 3)
    private String createTime;

}
