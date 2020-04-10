package com.example.demo.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购入库详情
 */
@Data
public class ExpWarehousingDetailsOpenReqDto {

    /**
     * 采购组织编码
     */
    @ChineseName("采购组织编码")
    private String purchaseDepartmentCode;

    /**
     * 采购组织名称
     */
    @ChineseName("采购组织名称")
    private String purchaseDepartmentName;

    /**
     * erp订单号
     */
    @ChineseName("erp订单号")
    private String outerOrderCode;

    /**
     * 供应商编码
     */
    @ChineseName("采购组织编码")
    private String outerSupplierCompanyCode;

    /**
     * 供应商名称
     */
    @ChineseName("供应商名称")
    private String outerSupplierCompanyName;

    /**
     * 行号
     */
    @ChineseName("行号")
    private String orderLineNumber;

    /**
     * 物料编码
     */
    @ChineseName("物料编码")
    private String outerPurchaseProductCode;

    /**
     * 物料名称
     */
    @ChineseName("物料名称")
    private String outerPurchaseProductName;

    /**
     * 收货人
     */
    @ChineseName("收货人")
    private String receiveUserName;

    /**
     * 收货时间
     */
    @ChineseName("收货时间")
    private Date receiveTime;

    /**
     * 单位
     */
    @ChineseName("单位")
    private String unit;

    /**
     * 收货数量
     */
    @ChineseName("收货数量")
    private BigDecimal receiveQuantity;

    /**
     * 仓库
     */
    @ChineseName("仓库")
    private String warehouse;

    /**
     * 备注头
     */
    private String descriptionHead;

    /**
     * 备注行1
     */
    private String descriptionLineOne;

    /**
     * 备注行2
     */
    private String descriptionLineTwo;

}
