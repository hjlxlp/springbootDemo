package com.example.demo.test.java8.vo;

import lombok.Data;

/**
 * @author huangjiale
 * @date 2021/11/30 10:18
 **/
@Data
public class Cow {

	/**
	 * 产地
	 */
	private String origin;

	/**
	 * 颜色
	 */
	private String color;

	/**
	 * 口味
	 */
	private String taste;

	/**
	 * 重量
	 */
	private Integer weight;

	/**
	 * 价格
	 */
	private Integer price;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 内脏重量
	 */
	private Integer gut;

	/**
	 * 骨头重量
	 */
	private Integer bones;


}
