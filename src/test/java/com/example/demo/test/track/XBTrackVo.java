package com.example.demo.test.track;

import lombok.Data;

import java.util.List;

/**
 * @author huangjiale
 * @date 2022/8/19 17:29
 **/
@Data
public class XBTrackVo {

	private boolean ok;

	private int servertime;

	private String leave_str;

	private String vacation_str;

	private int day_status;

	private String day_desc;

	private List<XBTrackDetailVo> data;

	private String day_length;

	private boolean today_is_end;

	//private Fence fence;

	private String stop_time;

	private String lost_time;

	private int is_use_baidu_yinyan;

}
