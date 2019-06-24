package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author hjl
 * @date 2019/3/20 11:19
 */
@Data
public class TestVoTwo {

    private Long orderId;

    private String orderName;

    private Date createTime;

}
