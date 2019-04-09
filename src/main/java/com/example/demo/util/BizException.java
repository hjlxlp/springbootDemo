package com.example.demo.util;


import javafx.util.Pair;

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

    public BizException(int code, String errorMsg, Throwable cause){
        super(cause);
    }

    /**
     *
     * @param cause 造成此异常的 原始异常
     * @param errorMsg 错误消息（可使用 %s 占位符）
     * @param params %s 的替换参数
     */
    public BizException(Throwable cause, String errorMsg, Object...params){
        super(String.format(errorMsg,params),cause);
    }

    public BizException(Pair<Integer, String> pair) {
        super("code:" + pair.getKey() + "\nMsg:" + pair.getValue());
        this.code = pair.getKey();
        this.errorMsg = pair.getValue();
    }

    public static BizException create(int code, String errorMsg) {
        return new BizException(code, errorMsg);
    }

    public static BizException create(Pair<Integer, String> pair) {
        return new BizException(pair);
    }


    public int getCode() {
        return code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
