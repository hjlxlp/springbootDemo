package com.example.demo.test.pdf2.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AiReportItemVO {

    @ApiModelProperty(value = "排序")
    private Integer orderByNum;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "辨识维度")
    private String key;

    @ApiModelProperty(value = "检测结果")
    private String field;

    @ApiModelProperty(value = "正常值")
    private String value;

    @ApiModelProperty(value = "正常值")
    private String faceMsg;

    @ApiModelProperty(value = "正常值")
    private String tongueMsg;

    @ApiModelProperty(value = "是否标红")
    private Boolean isRed;

    @ApiModelProperty(value = "图片")
    private String photo;

    @ApiModelProperty(value = "图片2")
    private String photoTwo;

    @ApiModelProperty(value = "姓名")
    private String player_url;

    @ApiModelProperty(value = "地址")
    private String audio_url;

    @ApiModelProperty(value = "描述")
    private String desc;

    @ApiModelProperty(value = "姓名")
    private Integer code;

    @ApiModelProperty(value = "姓名")
    private List<String> tongueNatureAttrList;

    private List<AiReportItemVO> tongueColorAttrList;

    private List<AiReportItemVO> tongueMossAttrList;

    private AiReportItemVO newReportInfo;

    private AiReportItemVO oldReportInfo;

    private Date createTime;
}
