package com.example.demo.vo;

import lombok.Data;

/**
 * @author hjl
 * @date 2019/3/20 11:19
 */
@Data
public class TestVo {

    @ChineseFieldName(value = "订单id")
    private Integer orderId;

    @ChineseFieldName(value = "订单编号")
    private String orderCode;

    @ChineseFieldName(value = "订单名称")
    private String orderName;

    private String orderType;

}
