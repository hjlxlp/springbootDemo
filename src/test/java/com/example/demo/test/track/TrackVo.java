package com.example.demo.test.track;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author huangjiale
 * @date 2022/8/19 17:29
 **/
@Data
public class TrackVo {

	@ApiModelProperty("轨迹详情list")
	private List<TrackDetailVo> trackList;

	@ApiModelProperty("轨迹状态（0-无规矩，1-正常轨迹，2-轨迹不全，3-无需定位）")
	private Integer trackStatus;

	@ApiModelProperty("停留时长分钟")
	private Integer stopTime;

	@ApiModelProperty("停留时长分钟")
	private String stopTimeStr;

	@ApiModelProperty("失联时长分钟")
	private Integer lostTime;

	@ApiModelProperty("失联时长分钟")
	private String lostTimeStr;

	@ApiModelProperty("里程")
	private Integer dayLength;

	@ApiModelProperty("里程")
	private String dayLengthStr;

}
