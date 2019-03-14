package com.example.demo.entity;

import lombok.Data;

import java.util.List;

/**
 * 导出字段
 */
@Data
public class ExportField {

    /**
     * 子字段
     */
    private List<ExportField> child;

    /**
     * index
     */
    private Integer index;

    /**
     * code
     */
    private String code;

    /**
     * name
     */
    private String name;

}
