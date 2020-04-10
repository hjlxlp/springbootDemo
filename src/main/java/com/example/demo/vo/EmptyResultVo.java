package com.example.demo.vo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangjiale
 * @date 2020/4/3 15:18
 **/
@Data
public class EmptyResultVo<T> {

    private Boolean isSuccess = true;

    private StringBuffer errorMessage = new StringBuffer();

    private List<T> passList = new ArrayList<>();

    private List<T> errorList = new ArrayList<>();

    public EmptyResultVo(StringBuffer errorMessage, List<T> passList, List<T> errorList) {
        this.errorMessage = errorMessage;
        this.passList = passList;
        this.errorList = errorList;
        this.isSuccess = StringUtils.isEmpty(errorMessage) ? true : false;
    }

    public EmptyResultVo(StringBuffer errorMessage) {
        this.errorMessage = errorMessage;
        this.isSuccess = StringUtils.isEmpty(errorMessage) ? true : false;
    }

}
