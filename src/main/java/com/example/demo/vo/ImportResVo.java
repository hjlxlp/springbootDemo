package com.example.demo.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangjiale
 * @date 2023/5/26 14:05
 **/
@Data
public class ImportResVo {

	private Integer successSize = 0;

	private List<ErrorVo> errorList = new ArrayList<>();

	private class ErrorVo {
		private Integer id;
		private String errorMsg;
	}

}
