package com.example.demo.test.export;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.example.demo.entity.BillOfSalesEntity;
import com.example.demo.entity.BillOfSalesProductEntity;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author huangjiale
 * @date 2022/3/8 16:03
 **/
public class ExcelTest {

	public static final String Dest = "D:\\demo.xlsx";

	public static void main(String[] args) throws IOException {
		main9(args);
	}

	public static void main9(String[] args) throws IOException {
		SXSSFWorkbook wb = new SXSSFWorkbook();
		Sheet sheet = wb.createSheet();
		Row row = sheet.createRow(0);

		// 颜色
		List<Font> fontList = new ArrayList<>();
		// 正常
		Font font1 = sheet.getWorkbook().createFont();
		fontList.add(font1);
		// 跨省-红
		Font font2 = sheet.getWorkbook().createFont();
		font2.setColor((short)2);
		fontList.add(font2);
		// 跨市-黄
		Font font3 = sheet.getWorkbook().createFont();
		font3.setColor((short)52);
		fontList.add(font3);


		String allText = "1、xxx;0,2、xxx;1,3、xxx;2";
		// 单元格
		Cell c = row.createCell(0);
		if (allText.length() > 0) {
			// 单元格文字，用逗号隔开，最后一位数字（0-正常，1-跨省红色，2-跨市黄色），格式：1、xxx;0,2、xxx;1,3、xxx;2
			String[] values = allText.split(",");

			// 先拼接text，放到单元格中
			StringBuffer textSb = new StringBuffer();
			for (int k = 0; k < values.length; k++) {
				String value = values[k].substring(0, values[k].length() - 1);
				textSb.append(value).append("\n");
			}
			c.setCellValue(textSb.substring(0, textSb.length()-1));

			// 改变单元格文字颜色
			RichTextString rt = c.getRichStringCellValue();
			//rt.append(textSb.substring(0, textSb.length() - 1));
			Integer beginIndex = 0;
			for (int k = 0; k < values.length; k++) {
				// 是否跨省市
				String type = values[k].substring(values[k].length() - 1);
				if ("0".equals(type)) {
					rt.applyFont(beginIndex, beginIndex + values[k].length()-1, fontList.get(0));
				} else if ("1".equals(type)) {
					rt.applyFont(beginIndex, beginIndex + values[k].length()-1, fontList.get(1));
				} else if ("2".equals(type)) {
					rt.applyFont(beginIndex, beginIndex + values[k].length()-1, fontList.get(2));
				}
				System.out.println(beginIndex+":"+(beginIndex + values[k].length()-1));
				beginIndex += values[k].length();
			}

			//rt.applyFont(0, 5, fontList.get(2));
			//rt.applyFont(font2);
			System.out.println(rt.length());
			System.out.println(rt.toString().length());
			c.setCellValue(rt);
		} else {
			c.setCellValue("");
		}

		/*// 单元格
		XSSFCell c = (XSSFCell) row.createCell(0);
		// 单元格text拼接对象
		XSSFRichTextString rt = c.getRichStringCellValue();
		// 单元格文字，用逗号隔开，最后一位数字（0-正常，1-跨省红色，2-跨市黄色），格式：1、xxx;0,2、xxx;1,3、xxx;2
		String v = "1、xxx;0,2、xxx;1,3、xxx;2";
		String[] values = v.split(",");
		for (int k = 0; k < values.length; k++) {
			// text
			String value = values[k].substring(0, values[k].length() - 1);
			// 是否跨省市
			String type = values[k].substring(values[k].length() - 1);
			if ("0".equals(type)) {
				rt.append(value + "\n", (XSSFFont) fontList.get(0));
			} else if ("1".equals(type)) {
				rt.append(value + "\n", (XSSFFont) fontList.get(1));
			} else if ("2".equals(type)) {
				rt.append(value + "\n", (XSSFFont) fontList.get(2));
			}
		}
		c.setCellValue(rt);
		for (int k = 0; k < values.length; k++) {
			System.out.println(values[k]);
		}*/

		// 写入文件
		FileOutputStream out = new FileOutputStream(Dest);
		wb.write(out);
	}

	public static void main8(String[] args) throws IOException {
		SXSSFWorkbook wb = new SXSSFWorkbook();
		Sheet sheet = wb.createSheet();
		Row row = sheet.createRow(0);

		// 颜色
		List<Font> fontList = new ArrayList<>();
		// 正常
		Font font1 = sheet.getWorkbook().createFont();
		fontList.add(font1);
		// 跨省-红
		Font font2 = sheet.getWorkbook().createFont();
		font2.setColor((short)2);
		fontList.add(font2);
		// 跨市-黄
		Font font3 = sheet.getWorkbook().createFont();
		font3.setColor((short)52);
		fontList.add(font3);

		/*CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setColor((short) k);
		style.setFont(font);*/

		/*// 单元格
		XSSFCell c = (XSSFCell) row.createCell(0);
		// 单元格text拼接对象
		XSSFRichTextString rt = c.getRichStringCellValue();
		// 单元格文字，用逗号隔开，最后一位数字（0-正常，1-跨省红色，2-跨市黄色），格式：1、xxx;0,2、xxx;1,3、xxx;2
		String v = "1、xxx;0,2、xxx;1,3、xxx;2";
		String[] values = v.split(",");
		for (int k = 0; k < values.length; k++) {
			// text
			String value = values[k].substring(0, values[k].length() - 1);
			// 是否跨省市
			String type = values[k].substring(values[k].length() - 1);
			if ("0".equals(type)) {
				rt.append(value + "\n", (XSSFFont) fontList.get(0));
			} else if ("1".equals(type)) {
				rt.append(value + "\n", (XSSFFont) fontList.get(1));
			} else if ("2".equals(type)) {
				rt.append(value + "\n", (XSSFFont) fontList.get(2));
			}
		}
		c.setCellValue(rt);
		for (int k = 0; k < values.length; k++) {
			System.out.println(values[k]);
		}*/


		// 单元格
		Cell c =  row.createCell(0);
		c.setCellValue("1、xxx;0,\n2、xxx;1,3、xxx;2");
		// 单元格text拼接对象
		RichTextString rt = c.getRichStringCellValue();
		// 单元格文字，用逗号隔开，最后一位数字（0-正常，1-跨省红色，2-跨市黄色），格式：1、xxx;0,2、xxx;1,3、xxx;2
		String v = "1、xxx;0,2、xxx;1,3、xxx;2";
		String[] values = v.split(",");
		/*for (int k = 0; k < values.length; k++) {
			// text
			String value = values[k].substring(0, values[k].length() - 1);
			// 是否跨省市
			String type = values[k].substring(values[k].length() - 1);
			if ("0".equals(type)) {
				rt.append(value + "\n", (XSSFFont) fontList.get(0));
			} else if ("1".equals(type)) {
				rt.append(value + "\n", (XSSFFont) fontList.get(1));
			} else if ("2".equals(type)) {
				rt.append(value + "\n", (XSSFFont) fontList.get(2));
			}
		}*/
		System.out.println("========"+rt.toString().length());
		rt.applyFont(0,5,fontList.get(2));
		c.setCellValue(rt);

		//c.setCellValue(rt);
		//c.setCellStyle(style);

		// 写入文件
		FileOutputStream out = new FileOutputStream(Dest);
		wb.write(out);

	}


	public static void main7(String[] args) throws IOException {
		Workbook wb = new XSSFWorkbook();

		Sheet sheet = wb.createSheet();
		int k = 1;
		for (int i = 0; i < 40; i++) {
			Row row = sheet.createRow(i);
			for (int j = 0; j < 4; j++) {
				CellStyle style = wb.createCellStyle();
				//style.setFillForegroundColor((short)(k));
				//style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

				Font font = wb.createFont();
				font.setColor((short)k);
				style.setFont(font);

				Cell cell = row.createCell(j);
				cell.setCellValue(k);
				cell.setCellStyle(style);
				k++;
			}
		}
		// 写入文件
		FileOutputStream out = new FileOutputStream(Dest);
		wb.write(out);

	}

	public static void main2(String[] args) {
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		Sheet sheet = workbook.createSheet();

		// 字体
		Font font = workbook.createFont();
		font.setColor((short)1);

		int rownum = 0;
		int column = 0;
		Row row = sheet.createRow(rownum);
		sheet.setColumnWidth(rownum, 6000);// 设置列宽
		for (IndexedColors colors : IndexedColors.values()) {
			// 为了方便查看,做个换行处理
			if (column > 5) {
				rownum++;
				column = 0;
				row = sheet.createRow(rownum);
				sheet.setColumnWidth(rownum, 6000);
			}
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFillForegroundColor(colors.getIndex());
			cellStyle.setFont(font);

			Cell cell = row.createCell(column);
			cell.setCellValue(colors.toString());
			cell.setCellStyle(cellStyle);
			column++;
		}
		// 写文件
		try (FileOutputStream outputStream = new FileOutputStream(new File("D:\\POI.xlsx"))) {
			workbook.write(outputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main5(String[] args) {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFRow row = wb.createSheet().createRow(0);

		// XSSF实现
		XSSFFont font1 = wb.createFont();
		font1.setBold(true);
		font1.setColor(new XSSFColor(new java.awt.Color(255, 0, 0)));

		XSSFFont font2 = wb.createFont();
		font2.setItalic(true);
		font2.setUnderline(XSSFFont.U_DOUBLE);
		font2.setColor(new XSSFColor(new java.awt.Color(0, 255, 0)));

		XSSFFont font3 = wb.createFont();
		font3.setColor(new XSSFColor(new java.awt.Color(0, 0, 255)));

		XSSFRichTextString rt = new XSSFRichTextString("The quick brown fox");
		rt.applyFont(0, 10, font1);
		rt.applyFont(10, 19, font2);
		System.out.println("=================="+rt.toString().length());
		rt.append(" Jumped over the lazy dog", font3);

		XSSFCell cell = row.createCell(1);
		cell.setCellValue(rt);

		// 写文件
		try (FileOutputStream outputStream = new FileOutputStream(new File("D:\\POI.xlsx"))) {
			wb.write(outputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main3(String[] args) {
		BillOfSalesEntity entity = new BillOfSalesEntity();
		List<BillOfSalesProductEntity> productEntityList = new ArrayList<>();
		BillOfSalesProductEntity product = new BillOfSalesProductEntity();
		product.setProductName("纽曼思藻油DHA软胶囊（成人型）国产中国");
		productEntityList.add(product);
		BillOfSalesProductEntity product2 = new BillOfSalesProductEntity();
		product2.setProductName("纽曼思藻油DHA软胶囊（成人型）国产中国");
		productEntityList.add(product2);
		entity.setProductList(productEntityList);
		createExcel(entity);
	}

	public static void createExcel(BillOfSalesEntity entity) {
		LocalDateTime begin = LocalDateTime.now();
		try {
			WriteCellStyle writeCellStyle = new WriteCellStyle();
			File newFile = new File("D://demo.xlsx");
			EasyExcel.write(newFile)
					//.head(head2())
					.registerWriteHandler(new MyMergeStrategy(entity.getProductList().size()))
					//.registerWriteHandler(new MyStrategy())
					.sheet()
					.doWrite(dataList3(entity));
			//newFile.delete();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Duration.between(begin, LocalDateTime.now()).toMillis());
	}

	private static List<List<String>> dataList3(BillOfSalesEntity entity) {
		List<List<String>> list = new ArrayList<>();

		// 第一行
		List<String> data = new ArrayList<>();
		data.add(entity.getBillOfSalesName());
		list.add(data);

		// 第二行
		data = new ArrayList<>();
		data.add("购货单位：");
		data.add(entity.getShopName());
		data.add("");
		data.add("");
		data.add("");
		data.add("生成时间：");
		data.add(entity.getGenerateTime());
		data.add("");
		data.add("");
		data.add("");
		list.add(data);

		// 第三行
		data = new ArrayList<>();
		data.add("收货人：");
		data.add(entity.getConsignee());
		data.add("");
		data.add("");
		data.add("");
		data.add("订单编号：");
		data.add(entity.getChildOrderId());
		data.add("");
		data.add("");
		data.add("");
		list.add(data);

		// 第四行
		data = new ArrayList<>();
		data.add("产品名称");
		data.add("商品条码");
		data.add("规格");
		data.add("批文号");
		data.add("批号");
		data.add("有效期至");
		data.add("单位");
		data.add("数量");
		data.add("单价");
		data.add("金额");
		list.add(data);

		// 第五行
		for (BillOfSalesProductEntity productEntity : entity.getProductList()) {
			data = new ArrayList<>();
			data.add(productEntity.getProductName());
			data.add(productEntity.getBarCode());
			data.add(productEntity.getSpecs());
			data.add(productEntity.getApprovalNo());
			data.add(productEntity.getBatchNo());
			data.add(productEntity.getTermOfValidity());
			data.add(productEntity.getUnit());
			data.add(productEntity.getNumber());
			data.add(productEntity.getUnitPrice());
			data.add(productEntity.getAmount());
			list.add(data);
		}

		// 第六行
		data = new ArrayList<>();
		data.add("积分抵扣");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add(entity.getScoreDeduct());
		list.add(data);

		// 第七行
		data = new ArrayList<>();
		data.add("金额（大写）");
		data.add(entity.getBigTotalAmount());
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("合计数量");
		data.add(entity.getTotalNumber());
		data.add("合计金额");
		data.add(entity.getTotalAmount());
		list.add(data);

		// 第八行
		data = new ArrayList<>();
		data.add("备注：" + (entity.getRemarks1() == null ? "" : entity.getRemarks1()));
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add(entity.getRemarks2());
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		list.add(data);

		// 第九行
		data = new ArrayList<>();
		data.add("业务员：" + (entity.getManagerName() == null ? "" : entity.getManagerName()));
		data.add("");
		data.add("电话：" + (entity.getMobile() == null ? "" : entity.getMobile()));
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		list.add(data);

		return list;
	}

	/**
	 * 创建数据
	 *
	 * @return
	 */
	private static List<List<String>> dataList2() {
		List<List<String>> list = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			List<String> data = new ArrayList<>();
			for (int j = 0; j < 10; j++) {
				data.add("创建数据");
			}
			list.add(data);
		}
		return list;
	}

	private static List<List<String>> head() {
		List<List<String>> list = ListUtils.newArrayList();
		List<String> head0 = ListUtils.newArrayList();
		head0.add("字符串" + System.currentTimeMillis());
		List<String> head1 = ListUtils.newArrayList();
		head1.add("数字" + System.currentTimeMillis());
		List<String> head2 = ListUtils.newArrayList();
		head2.add("日期" + System.currentTimeMillis());
		list.add(head0);
		list.add(head1);
		list.add(head2);
		return list;
	}

	private static List<List<String>> dataList() {
		List<List<String>> list = ListUtils.newArrayList();
		for (int i = 0; i < 10; i++) {
			List<String> data = ListUtils.newArrayList();
			data.add("字符串" + i);
			data.add(new Date().toString());
			data.add("0.56");
			list.add(data);
		}
		return list;
	}


	/**
	 * 创建表头
	 *
	 * @return
	 */
	private static List<List<String>> head2() {
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> head0 = new ArrayList<String>();
		head0.add("字符串");
		List<String> head1 = new ArrayList<String>();
		head1.add("数字");
		List<String> head2 = new ArrayList<String>();
		head2.add("日期");
		list.add(head0);
		list.add(head1);
		list.add(head2);
		return list;
	}


}
