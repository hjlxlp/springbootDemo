package com.example.demo.controller.dynamics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangjiale
 * @date 2025/3/12 13:40
 **/
@RequestMapping("/dynamics")
@RestController
public class DynamicsController {

	@GetMapping("test")
	public String insert() {
		return "hello world";
	}

}
