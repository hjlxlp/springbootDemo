package com.example.demo.util;

import java.io.Serializable;
import java.util.Optional;

/**
 * Created by Administrator on 2016/7/19.
 * 所有api的基类
 */
public class BaseResultModel<T> implements Serializable {

	private static final int DefaultErrorCode = 500;

	public BaseResultModel() {

	}

	public BaseResultModel(T data) {
		this.data = data;
	}


	private T data = null;

	/**
	 * 状态码,0表示成功,其他表示错误,
	 * 默认为0,如果需要设置异常code,请使用setError 一起设置
	 */
	private int code = 0;

	private String msg = "";

	private String errorMsg = "";


	public boolean getSuccess() {
		return this.code == 0;
	}

	public BaseResultModel<T> setError(int code, String errorMsg) {
		this.code = code;
		this.errorMsg = errorMsg;
		return this;
	}

	public BaseResultModel<T> setError(BizException e) {
		return setError(e.getCode(), e.getErrorMsg());
	}

	public BaseResultModel<T> setError(Integer key, String value) {
		return setError(key, value);
	}

	public BaseResultModel<T> setMsg(String msg) {
		this.msg = msg;
		return this;

	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return this.code;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public T getData() {
		return data;
	}

	public BaseResultModel<T> setData(T data) {
		this.data = data;
		return this;
	}


	public static BaseResultModel of(Integer key, String value) {
		return new BaseResultModel().setError(key, value);
	}

	public static BaseResultModel of(Exception e) {
		//msg
		StringBuilder msg = new StringBuilder();
		msg.append("异常类型:");
		msg.append(e.getClass().getName());
		msg.append(",");
		msg.append("异常信息:");
		msg.append(e.getMessage());
		msg.append(",");
		msg.append("异常堆栈追踪:");
		Optional.ofNullable(e.getStackTrace()).ifPresent(
				(stacks) -> {
					for (StackTraceElement s : stacks) {
						msg.append(s);
					}
				}
		);
		//code
		int code;
		if (e instanceof BizException) {
			code = ((BizException) e).getCode();
		} else {
			code = DefaultErrorCode;
		}
		//construct
		return new BaseResultModel().setError(code, msg.toString());
	}

	public static <T> BaseResultModel of(T data) {
		return new BaseResultModel<>().setData(data);
	}

	public static void main(String[] args) {
		System.out.println(NullPointerException.class.getName());
	}

}
