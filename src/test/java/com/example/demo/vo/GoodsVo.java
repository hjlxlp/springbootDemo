package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author huangjiale
 * @date 2020/4/30 11:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo {

    private Integer id;

    private String name;

    private static String jingTai = "jingTai";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsVo goodsVo = (GoodsVo) o;
        return Objects.equals(id, goodsVo.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
