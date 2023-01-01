package com.example.demo.test.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangjiale
 * @date 2022/3/14 18:38
 **/
public class ExcelTest2 {

	public static void main(String[] args) {
		String fileName = "D:/testinit.xlsx";
		List<InitPolicyShopVo> tableList = new ArrayList<>();
		AnalysisEventListener listener = new AnalysisEventListener<InitPolicyShopVo>() {
			@Override
			public void invoke(InitPolicyShopVo o, AnalysisContext analysisContext) {
				tableList.add(o);
			}

			@Override
			public void doAfterAllAnalysed(AnalysisContext analysisContext) {
			}
		};

		EasyExcel.read(fileName, InitPolicyShopVo.class, listener).sheet().doRead();

		System.out.println(JSON.toJSONString(tableList));
		System.out.println(JSON.toJSONString(tableList.stream().map(a -> a.getPolicyName()).distinct().collect(Collectors.joining(","))));
		List<String> mobileList = tableList.stream().map(a -> a.getMobile().trim()).distinct().collect(Collectors.toList());
		System.out.println(mobileList.size());
		StringBuffer sb = new StringBuffer();
		for (String s : mobileList) {
			sb.append("'").append(s).append("',");
		}
		System.out.println(JSON.toJSONString(sb.substring(0, sb.length() - 1)));

		/*String str = "";
		List<InitPolicyShopVo> list2 = JSON.parseArray(str,InitPolicyShopVo.class);
		List<String> m2 = list2.stream().map(a->a.getMobile()).distinct().collect(Collectors.toList());
		for (String s : mobileList) {
			if(!m2.contains(s)){
				System.out.println("m:"+s);
			}
		}*/


	}

	/*public static void main(String[] args) {
		String fileName = "D:/test.xlsx";
		List<TableExcelVo2> tableList = new ArrayList<>();
		AnalysisEventListener listener = new AnalysisEventListener<TableExcelVo2>() {
			@Override
			public void invoke(TableExcelVo2 o, AnalysisContext analysisContext) {
				tableList.add(o);
			}

			@Override
			public void doAfterAllAnalysed(AnalysisContext analysisContext) {
			}
		};
		EasyExcel.read(fileName, TableExcelVo2.class, listener).sheet().doRead();

		StringBuffer splitStr = new StringBuffer();
		Integer brandSort = 1;
		Integer spuSort = 10;
		for (int i = 0; i < tableList.size(); i++) {
			TableExcelVo2 entity = tableList.get(i);
			*//*if (i > 0) {
				System.out.println(entity.getBrandSort().intValue() + ":" + tableList.get(i - 1).getBrandSort().intValue());
				if (entity.getBrandSort().intValue() != tableList.get(i - 1).getBrandSort().intValue()) {
					spuSort = 10;
				} else {
					spuSort += 10;
				}
				System.out.println(spuSort);
			}*//*
			splitStr.append("insert into act_holiday set ")
					.append("product_id = ").append(entity.getSpuId())
					.append(", group_name = '").append("area" + entity.getBrandSort()).append("'")
					.append(", show_order = ").append(entity.getSpuSort() * 10)
					.append(", holiday_type = ").append("'2022_03_18_holiday'")
					.append(";\n");
		}

		System.out.println(splitStr);

	}*/

}
