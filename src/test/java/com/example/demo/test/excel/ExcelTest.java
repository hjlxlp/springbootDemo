package com.example.demo.test.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangjiale
 * @date 2022/3/7 14:02
 **/
public class ExcelTest {

	@Test
	public void testMysql() {

		String fileName1 = "D:/test2.xlsx";
		List<TableExcelVo> tableList = new ArrayList<>();
		AnalysisEventListener listener1 = new AnalysisEventListener<TableExcelVo>() {
			@Override
			public void invoke(TableExcelVo o, AnalysisContext analysisContext) {
				tableList.add(o);
			}

			@Override
			public void doAfterAllAnalysed(AnalysisContext analysisContext) {
			}
		};
		EasyExcel.read(fileName1, TableExcelVo.class, listener1).sheet().doRead();


		// 组装sql
		StringBuffer splitStr = new StringBuffer();
		// begin
		splitStr.append("CREATE TABLE IF NOT EXISTS `bill_of_sales` (\n" +
				"  `id` bigint NOT NULL AUTO_INCREMENT,\n");
		//`activity_id` bigint NOT NULL DEFAULT '0' COMMENT '活动id',
		//`prize_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '奖品名称',
		for (TableExcelVo vo : tableList) {
			splitStr.append("  `" + vo.getName() + "`  " + vo.getType()
					+ " NOT NULL DEFAULT '' COMMENT '" + vo.getRemark() + "',\n");
		}
		// end
		splitStr.append("  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
				"  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n" +
				"  `deleted` tinyint(1) NOT NULL DEFAULT '0',\n" +
				"  PRIMARY KEY (`id`)\n" +
				") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='出货单表';");
		System.out.println(splitStr.toString());

	}

	@Test
	public void testVo() {

		String fileName1 = "D:/test2.xlsx";
		List<TableExcelVo> tableList = new ArrayList<>();
		AnalysisEventListener listener1 = new AnalysisEventListener<TableExcelVo>() {
			@Override
			public void invoke(TableExcelVo o, AnalysisContext analysisContext) {
				tableList.add(o);
			}

			@Override
			public void doAfterAllAnalysed(AnalysisContext analysisContext) {
			}
		};
		EasyExcel.read(fileName1, TableExcelVo.class, listener1).sheet().doRead();


		// 组装sql
		StringBuffer splitStr = new StringBuffer();
		for (TableExcelVo vo : tableList) {
			splitStr.append("@ApiModelProperty(value = \"").append(vo.getRemark()).append("\")\n")
				.append("private String ").append(vo.getName()).append(";\n\n");
		}
		System.out.println(splitStr.toString());

	}

}
