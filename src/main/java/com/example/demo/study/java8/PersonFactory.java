package com.example.demo.study.java8;

/**
 * @author hjl
 * @date 2019/10/29 11:07
 */
public interface PersonFactory<p extends Person> {

    p create(String firstName, String lastName);

}
