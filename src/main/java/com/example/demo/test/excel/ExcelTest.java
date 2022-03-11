package com.example.demo.test.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.base.CaseFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangjiale
 * @date 2022/3/7 14:02
 **/
public class ExcelTest {

	public static void main(String[] args) {
		String tableName = "test_excel";
		String fileName = "D:/test.xlsx";
		List<TableExcelVo> tableList = new ArrayList<>();
		AnalysisEventListener listener = new AnalysisEventListener<TableExcelVo>() {
			@Override
			public void invoke(TableExcelVo o, AnalysisContext analysisContext) {
				tableList.add(o);
			}

			@Override
			public void doAfterAllAnalysed(AnalysisContext analysisContext) {
			}
		};
		EasyExcel.read(fileName, TableExcelVo.class, listener).sheet().doRead();

		testSql(tableList, tableName);
		testEntity(tableList);


		String str = "";
		testService(str);

	}

	/**
	 * 生成sql
	 */
	private static void testSql(List<TableExcelVo> tableList, String tableName) {
		// 组装sql
		StringBuffer splitStr = new StringBuffer();
		// begin
		splitStr.append("CREATE TABLE IF NOT EXISTS `" + tableName + "` (\n" +
				"  `id` bigint NOT NULL AUTO_INCREMENT,\n");
		for (TableExcelVo vo : tableList) {
			splitStr.append("  `" + vo.getName() + "`  " + vo.getType());
			if ("否".equals(vo.getIsNull())) {
				splitStr.append(" NOT NULL ");
				if (vo.getType().contains("varchar")) {
					splitStr.append(" DEFAULT '' ");
				}
				if (vo.getType().contains("int")) {
					splitStr.append(" DEFAULT '0' ");
				}
			}
			if (vo.getRemark() != null) {
				splitStr.append(" COMMENT '" + vo.getRemark() + "',\n");
			}
		}
		// end
		splitStr.append("  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
				"  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n" +
				"  `deleted` tinyint(1) NOT NULL DEFAULT '0',\n" +
				"  PRIMARY KEY (`id`)\n" +
				") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='';");

		System.out.println("==========sql==========");
		System.out.println(splitStr.toString());
	}

	/**
	 * 生成entity
	 *
	 * @param tableList
	 */
	private static void testEntity(List<TableExcelVo> tableList) {
		// 组装entity
		StringBuffer splitStr = new StringBuffer();
		// begin
		splitStr.append("@TableId(type = IdType.AUTO)\n private Long id;");
		for (TableExcelVo vo : tableList) {
			splitStr.append("/**\n * ").append(vo.getRemark()).append("\n */\n")
					.append("@ApiModelProperty(value = \"").append(vo.getRemark()).append("\")\n")
					.append("private");

			if (vo.getType().contains("bigint")) {
				splitStr.append(" Long ");
			} else if (vo.getType().contains("int")) {
				splitStr.append(" Integer ");
			} else if (vo.getType().contains("varchar")) {
				splitStr.append(" String ");
			} else if (vo.getType().contains("datetime")) {
				splitStr.append(" Date ");
			}

			splitStr.append(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, vo.getName())).append(";\n\n");
		}
		// end
		splitStr.append("@ApiModelProperty(value = \"是否删除\")\n" +
				"@TableLogic\n" +
				"private Boolean deleted;\n\n" +
				"@ApiModelProperty(value = \"创建时间\")\n" +
				"private Date createTime;\n\n" +
				"@ApiModelProperty(value = \"更新时间\")\n" +
				"private Date updateTime;");

		System.out.println("==========entity==========");
		System.out.println(splitStr.toString());
	}

	private static void testService(String str) {

	}

}
