package com.example.demo.util;

import java.math.BigDecimal;

/**
 * @author hjl
 * @date 2019/11/18 16:14
 */
public class StringUtil {

    private final static String XHX = "_";
    private final static String DIAN = ".";


    /**
     * BigDecimal去除小数点后面0，并且小数2位内则保留2位，返回String
     *
     * @param b
     * @return
     */
    public static String getTwoZeroString(BigDecimal b) {
        if (b == null) {
            return null;
        }
        return getTwoZeroBigDecimal(b).toPlainString();
    }

    /**
     * BigDecimal去除小数点后面0，并且小数2位内则保留2位，返回BigDecimal
     *
     * @param b
     * @return
     */
    public static BigDecimal getTwoZeroBigDecimal(BigDecimal b) {
        if (b == null) {
            return null;
        }
        BigDecimal bd = b.stripTrailingZeros();
        if (bd.toPlainString().indexOf(DIAN) < 0
                || bd.toPlainString().indexOf(DIAN) == bd.toPlainString().length() - 2) {
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return bd;
    }

    /**
     * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。
     * 例如：HelloWorld->hello_world
     *
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线大写方式命名的字符串
     */
    public static String underScoreName(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toLowerCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append(XHX);
                }
                // 其他字符直接转成小写
                result.append(s.toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。
     * 例如：HELLO_WORLD->HelloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains(XHX)) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split(XHX);
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
            /*if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }*/
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(camelName("mpo_order_d"));
        System.out.println(underScoreName(camelName("mpo_order_d")));
    }

}
