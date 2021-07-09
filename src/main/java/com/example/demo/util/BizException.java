package com.example.demo.util;

/**
 * 业务异常，用这个来封装，键值对在common定义
 */
public class BizException extends RuntimeException {

	private int code;
	private String errorMsg;

	public BizException(int code, String errorMsg) {
		super("code:" + code + "\nMsg:" + errorMsg);
		this.code = code;
		this.errorMsg = errorMsg;
	}

	public BizException(int code, String errorMsg, Throwable cause) {
		super(cause);
	}

	/**
	 * @param cause    造成此异常的 原始异常
	 * @param errorMsg 错误消息（可使用 %s 占位符）
	 * @param params   %s 的替换参数
	 */
	public BizException(Throwable cause, String errorMsg, Object... params) {
		super(String.format(errorMsg, params), cause);
	}

	public BizException(Integer key, String value) {
		super("code:" + key + "\nMsg:" + value);
		this.code = key;
		this.errorMsg = value;
	}

	public static BizException create(int code, String errorMsg) {
		return new BizException(code, errorMsg);
	}

	public int getCode() {
		return code;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
