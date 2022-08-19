package com.example.demo.test.track;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author huangjiale
 * @date 2022/8/19 17:30
 **/
@Data
public class TrackDetailVo {

	@ApiModelProperty("业务list")
	private List<BusinessVo> businessList;

	@ApiModelProperty("唯一主键")
	private String id;

	@ApiModelProperty("距离，米")
	private Integer distance;

	@ApiModelProperty("定位偏差距离")
	private Integer acc;

	@ApiModelProperty("开始时间")
	private String time;

	@ApiModelProperty("结束时间")
	private String locTime;

	@ApiModelProperty("开始时间戳")
	private Integer startTime;

	@ApiModelProperty("结束时间戳")
	private Integer endTime;

	@ApiModelProperty("类型，0-无轨迹，1-移动，2-停留，3-上班打卡，4-下班打卡，5-拜访")
	private Integer type;

	@ApiModelProperty("定位地址")
	private String location;

	@ApiModelProperty("经度")
	private String lng;

	@ApiModelProperty("纬度")
	private String lat;

	@ApiModelProperty("停留时长")
	private Integer stopTime;

	@ApiModelProperty("停留时间差")
	private String stopTimeStr;

	@ApiModelProperty("错误原因")
	private String errorMsg;

	@ApiModelProperty("和上一个展示点的时间差")
	private String lastTimeStr;

	@ApiModelProperty("和上一个展示点的距离差")
	private String lastDistanceStr;

}
