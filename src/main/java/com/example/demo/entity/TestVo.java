package com.example.demo.entity;

import lombok.Data;

/**
 * @author hjl
 * @date 2019/3/20 11:19
 */
@Data
public class TestVo {

    @ChineseName(value = "订单id")
    private Integer orderId;

    @ChineseName(value = "订单编号")
    private String orderCode;

    @ChineseName(value = "订单名称")
    private String orderName;

    private String orderType;

}
