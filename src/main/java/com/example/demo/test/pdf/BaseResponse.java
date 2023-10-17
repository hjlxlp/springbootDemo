package com.example.demo.test.pdf;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

import java.io.Serializable;

/**
 * 响应类
 *
 * @author vijay
 */
@ApiModel("公共响应参数")
@ToString
public class BaseResponse<T> implements Serializable {
    public static <T> BaseResponse<T> fail(Throwable throwable) {
        return new BaseResponse<>(EXCEPTION_CODE, throwable.getMessage());
    }

    public static <T> BaseResponse<T> fail(String message) {
        return new BaseResponse<>(UNKNOWN_FAILED_CODE, message);
    }

    public static <T> BaseResponse<T> fail(Integer responseCode, String message) {
        if (responseCode.equals(SUCCESS_CODE)) {
            responseCode = UNKNOWN_FAILED_CODE;
        }
        return new BaseResponse<>(responseCode, message);
    }

    public static <T> BaseResponse<T> success(T body) {
        return new BaseResponse<>(body);
    }


    private static final long serialVersionUID = -5300633602365536773L;
    /** 成功状态码 */
    public static final Integer SUCCESS_CODE = 200;
    public static final Integer EXCEPTION_CODE = 1;
    public static final Integer UNKNOWN_FAILED_CODE = 2;

    /** 响应状态码 */
    @ApiModelProperty(value = "响应状态码，200表示成功", required = true, example = "0")
    private Integer responseCode;
    /** 响应信息 */
    @ApiModelProperty(value = "响应信息，成功时一般为空字符串", required = true, example = "请求成功")
    private String responseMsg;

    /** 业务对象 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T body;

    public BaseResponse() {
    }

    /**
     * 使用业务对象 <code>T body</code> 构造的实例，默认为成功
     *
     * @param body 业务对象
     */
    public BaseResponse(T body) {
        this(SUCCESS_CODE, "", body);
    }

    public BaseResponse(Integer responseCode, String responseMsg) {
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
    }

    public BaseResponse(Integer responseCode, String responseMsg, T body) {
        this(responseCode, responseMsg);
        this.body = body;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public BaseResponse<T> setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public BaseResponse<T> setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
        return this;
    }

    public T getBody() {
        return body;
    }

    /**
     * 设置body的值。支持链式访问。
     *
     * @param body 消息体
     * @return 返回一个当前的实例对象
     */
    public BaseResponse<T> setBody(T body) {
        this.body = body;
        return this;
    }

    /**
     * 判断该响应是否成功。
     *
     * @return true 成功响应
     * false 失败响应
     */
    public boolean success() {
        return SUCCESS_CODE.equals(getResponseCode());
    }
}
