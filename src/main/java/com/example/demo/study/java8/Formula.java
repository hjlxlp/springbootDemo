package com.example.demo.study.java8;

/**
 * @author hjl
 * @date 2019/10/29 10:13
 */
public interface Formula {

    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }

}
