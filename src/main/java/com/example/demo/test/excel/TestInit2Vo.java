package com.example.demo.test.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author huangjiale
 * @date 2022/12/30 9:54
 */
@Data
public class TestInit2Vo {

    @ExcelProperty(index = 0)
    private String s1;

    @ExcelProperty(index = 1)
    private String s2;

    @ExcelProperty(index = 2)
    private String s3;

}
