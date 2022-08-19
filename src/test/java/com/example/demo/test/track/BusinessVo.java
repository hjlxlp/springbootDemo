package com.example.demo.test.track;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huangjiale
 * @date 2022/8/19 17:31
 **/
@Data
public class BusinessVo {

	@ApiModelProperty("业务唯一主键")
	private Integer businessId;

	@ApiModelProperty("名称")
	private String name;

	@ApiModelProperty("时间戳")
	private Integer time;

	@ApiModelProperty("时间")
	private String timeStr;

	@ApiModelProperty("类型，3-上班打卡，4-下班打卡，5-拜访")
	private Integer type;

}
