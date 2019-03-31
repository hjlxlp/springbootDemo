package com.example.demo.entity;

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
