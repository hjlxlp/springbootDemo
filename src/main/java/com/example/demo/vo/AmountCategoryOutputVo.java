package com.example.demo.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AmountCategoryOutputVo {

    /**
     * 采购商id
     */
    private String purchaseCompanyId;

    /**
     * 采购商名称
     */
    private String purchaseCompanyName;

    /**
     * 采购类别一级分类名称
     */
    private String categoryNameOne;

    /**
     * 采购类别二级分类名称
     */
    private String categoryNameTwo;

    /**
     * 采购类别三级分类名称
     */
    private String categoryNameThree;

    /**
     * 总金额
     */
    private BigDecimal total;

}
