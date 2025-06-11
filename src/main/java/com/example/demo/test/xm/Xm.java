package com.example.demo.test.xm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Xm {

    public static void main(String[] args) {
        List<XmVo> allList = new ArrayList<>();

        BigDecimal totalBuyAmount = BigDecimal.ZERO;
        int totalBuyCount = 0;

        BigDecimal totalSellAmount = BigDecimal.ZERO;
        int totalSellCount = 0;

        for (XmVo vo : allList) {
            if (vo.getIsBuy()) {
                totalBuyAmount = totalBuyAmount.add(vo.getTotal());
                totalBuyCount += vo.getCount();
            } else {
                totalSellAmount = totalSellAmount.add(vo.getTotal());
                totalSellCount += vo.getCount();
            }
        }

        // 买入平均价
        BigDecimal avgBuyPrice = totalBuyAmount.divide(BigDecimal.valueOf(totalBuyCount), 4, RoundingMode.HALF_UP);
        // 卖出部分的总成本
        BigDecimal sellCost = avgBuyPrice.multiply(BigDecimal.valueOf(totalSellCount));
        // 已到手收益
        BigDecimal profit = totalSellAmount.subtract(sellCost);

        BigDecimal res = profit.setScale(2, RoundingMode.HALF_UP);
        System.out.println(res);

    }
}
