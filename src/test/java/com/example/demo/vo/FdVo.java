package com.example.demo.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author huangjiale
 * @date 2020/4/26 13:59
 **/
@Data
public class FdVo {

    private double size;

    private double price;

    private double year;

    private double pay;

    public FdVo(Integer size, Integer price, Integer year, Integer pay) {
        this.size = size;
        this.price = price;
        this.year = year;
        this.pay = pay;
    }

}
