package com.example.demo.entity;

import com.example.demo.util.JsonSerializerUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 城市实体类
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class City implements Serializable{

    @JsonSerialize(using = JsonSerializerUtils.class)
    private BigDecimal total;

    /**
     * 城市编号
     */
    private Long id;

    /**
     * 省份编号
     */
    private Long provinceId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

}
