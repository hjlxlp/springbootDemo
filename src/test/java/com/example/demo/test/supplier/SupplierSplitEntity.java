package com.example.demo.test.supplier;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author huangjiale
 * @date 2021/5/28 13:26
 **/
@Data
public class SupplierSplitEntity {

    private Integer id;

    /**
     * 供应商id
     */
    private Integer supplierId;

    /**
     * skuId
     */
    private Integer skuId;

    /**
     * spuId
     */
    private Long spuId;

    /**
     * 品牌id
     */
    private Integer brandId;

    /**
     * 分账比例
     */
    private Integer splitRatio;

    /**
     * 分账类型（0-按sku，1-按品牌）
     */
    private Integer splitType;

    private Integer deleted;
    private Date createTime;
    private Date updateTime;

}
