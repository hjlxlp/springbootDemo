package com.example.demo.test.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author huangjiale
 * @date 2022/12/30 9:54
 */
@Data
public class InitPolicyShopVo {

    @ExcelProperty(value = "政策名称", index = 0)
    private String policyName;

    @ExcelProperty(value = "初始数量", index = 1)
    private Integer initNum;

    @ExcelProperty(value = "用户手机号", index = 2)
    private String mobile;

}
