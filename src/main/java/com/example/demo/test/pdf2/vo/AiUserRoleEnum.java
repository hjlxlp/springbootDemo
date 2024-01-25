package com.example.demo.test.pdf2.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author huangjiale
 * @date 2023/9/22 15:16
 **/
@Getter
@AllArgsConstructor
public enum AiUserRoleEnum {

	tourist(0, "游客"),
	shop(1, "商户"),
	user(2, "用户"),
	shop_user(3, "商户_用户");

	private final Integer code;
	private final String name;

}
