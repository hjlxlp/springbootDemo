package com.example.demo.test.xm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Data
public class XmVo {

    private Boolean isBuy;

    private Integer count;

    private BigDecimal price;

    private BigDecimal total;

    private Date createTime;

}
