package com.example.demo.study.java8;

/**
 * @author hjl
 * @date 2019/10/29 10:52
 */
@FunctionalInterface
public interface Converter<F, T> {

    T convert(F f);

}
