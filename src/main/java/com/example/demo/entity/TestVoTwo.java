package com.example.demo.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hjl
 * @date 2019/3/20 11:19
 */
@Data
public class TestVoTwo {

    private Integer orderId;

    private String orderName;

    private LocalDateTime createTime;

}
