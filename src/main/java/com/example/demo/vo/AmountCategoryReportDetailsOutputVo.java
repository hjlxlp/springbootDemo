package com.example.demo.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AmountCategoryReportDetailsOutputVo {

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 总金额
     */
    private BigDecimal total;

}
