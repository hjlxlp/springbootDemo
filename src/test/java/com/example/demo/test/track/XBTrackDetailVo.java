package com.example.demo.test.track;

import lombok.Data;

import java.util.List;

/**
 * @author huangjiale
 * @date 2022/8/19 17:30
 **/
@Data
public class XBTrackDetailVo {

	private List<XBBusinessVo> business;

	private int distance;

	private String id;

	private String lng;

	private String lat;

	private String need_loc;

	private int _loc_type;

	private String location;

	private String loc_time;

	private int duration;

	private String time;

	private int start_time;

	private int end_time;

	private int _unneed_loc_type;

	private String stop_time;

	private int last_time;

	private String loc_name;

	private int loc_type;

	private String collection_type;

	private String biz_type;

	private int status;

	private String activity;

	private int acc;

	private int time_diff;

	private String power;

	private String error_msg;

	private String gps_open;

	private String device;

	private String is_self_start;

	private String miui_self_start;

	private String install_packet;

	private boolean is_miui;

	private boolean is_manage;

	private String bear;

	private String loc_time_s;

	private String wifi_open;

	private String speed;

	private String wifi_connect;

	private String altitude;

	private String phonemodel;

	private String data_open;

}
