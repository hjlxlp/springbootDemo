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
    private Integer status;

    @ExcelProperty(index = 1)
    private Integer num;

    @ExcelProperty(index = 2)
    private Integer amount;

    @ExcelProperty(index = 3)
    private Long policy_id;

    @ExcelProperty(index = 4)
    private Long policy_sku_id;

    @ExcelProperty(index = 5)
    private Long policy_shop_id;

    @ExcelProperty(index = 6)
    private Long policy_sku_shop_id;

    @ExcelProperty(index = 7)
    private Long user_id;

}
