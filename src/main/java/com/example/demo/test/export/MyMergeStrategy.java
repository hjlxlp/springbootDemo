package com.example.demo.test.export;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hjl
 */
public class MyMergeStrategy extends AbstractMergeStrategy {

	/**
	 * 合并单元格坐标集合
	 */
	private static Map<String, CellRangeAddress> cellRangeMap;

	/**
	 * 水平居中单元格行数集合
	 */
	private static List<Integer> cellStyleList;

	/**
	 * 水平居中样式
	 */
	private static CellStyle styleCenter;

	/**
	 * 字体，9号，等线
	 */
	private static Font font9;

	public MyMergeStrategy() {

	}

	public MyMergeStrategy(Integer size) {
		/**
		 * 合并单元格坐标集合
		 */
		cellRangeMap = new HashMap<>(16);
		// 第一行
		cellRangeMap.put("0:0", new CellRangeAddress(0, 0, 0, 9));
		// 第二行
		cellRangeMap.put("1:1", new CellRangeAddress(1, 1, 1, 4));
		cellRangeMap.put("1:6", new CellRangeAddress(1, 1, 6, 9));
		// 第三行
		cellRangeMap.put("2:1", new CellRangeAddress(2, 2, 1, 4));
		cellRangeMap.put("2:6", new CellRangeAddress(2, 2, 6, 9));
		// 第四行
		// 第五行
		// 第六行
		// 第七行
		cellRangeMap.put(5 + size + ":1", new CellRangeAddress(5 + size, 5 + size, 1, 5));
		// 第八行
		cellRangeMap.put(6 + size + ":0", new CellRangeAddress(6 + size, 6 + size, 0, 4));
		cellRangeMap.put(6 + size + ":5", new CellRangeAddress(6 + size, 6 + size, 5, 9));
		// 第九行
		cellRangeMap.put(7 + size + ":0", new CellRangeAddress(7 + size, 7 + size, 0, 1));
		cellRangeMap.put(7 + size + ":2", new CellRangeAddress(7 + size, 7 + size, 2, 3));
		cellRangeMap.put(7 + size + ":4", new CellRangeAddress(7 + size, 7 + size, 4, 9));

		cellStyleList = new ArrayList<>();
		cellStyleList.add(0);
		for (int i = 0; i <= size; i++) {
			cellStyleList.add(3 + i);
		}


		//cellStyleList.add(4);

		/*cellList2 = new ArrayList<>();
		// 第一行
		cellList2.add(new CellRangeAddress(0, 0, 0, 9));
		// 第二行
		cellList2.add(new CellRangeAddress(1, 1, 1, 4));
		cellList2.add(new CellRangeAddress(1, 1, 6, 9));
		// 第三行
		cellList2.add(new CellRangeAddress(2, 2, 1, 4));
		cellList2.add(new CellRangeAddress(2, 2, 6, 9));
		// 第四行
		// 第五行
		// 第六行
		// 第七行
		cellList2.add(new CellRangeAddress(6, 6, 1, 5));
		// 第八行
		cellList2.add(new CellRangeAddress(7, 7, 0, 4));
		cellList2.add(new CellRangeAddress(7, 7, 5, 9));
		// 第九行
		cellList2.add(new CellRangeAddress(8, 8, 0, 1));
		cellList2.add(new CellRangeAddress(8, 8, 2, 3));
		cellList2.add(new CellRangeAddress(8, 8, 4, 9));*/
	}

	/**
	 * 设置值和样式，富文本 复合样式（一个单元格多个字体）
	 * @param cell 当前单元格
	 * @param wholeStr 整个字符串
	 * @param strArray 字符串分割的数组
	 * @param strFontList 字符串分割后一一对应的字体
	 */
	public static void setRichTextCellValue(Cell cell, String wholeStr, String[] strArray, List<Font> strFontList){
		HSSFRichTextString hssfRichTextString = new HSSFRichTextString(wholeStr);
		int strLength = 0;
		for(int i = 0; i < strArray.length; i++){
			hssfRichTextString.applyFont(strLength, strLength + strArray[i].length(), strFontList.get(i));
			strLength = strArray[i].length();
		}
		cell.setCellValue(hssfRichTextString);
	}

	public static void main(String[] args) {




		String v = "1、xxx;0,2、xxx;1,3、xxx;2";
		String[] values = v.split(",");
		for (int k = 0; k < values.length; k++) {
			// text
			String value = values[k].substring(0, values[k].length()-1);
			// 是否跨省市
			String type = values[k].substring(values[k].length()-1);
			System.out.println(value);
			System.out.println(type);
		}
	}

	/**
	 * merge
	 *
	 * @param sheet
	 * @param cell
	 * @param head
	 * @param relativeRowIndex
	 */
	@Override
	protected void merge(Sheet sheet, Cell cell, Head head, Integer relativeRowIndex) {

		/**
		 *  ****加个判断:if (cell.getRowIndex() == 1 && cell.getColumnIndex() == 0) {}****
		 * 保证每个cell被合并一次，如果不加上面的判断，因为是一个cell一个cell操作的，
		 * 例如合并A2:A3,当cell为A2时，合并A2,A3，但是当cell为A3时，又是合并A2,A3，
		 * 但此时A2,A3已经是合并的单元格了
		 */
		/*for (CellRangeAddress item : cellList) {
			sheet.addMergedRegionUnsafe(item);
		}*/

		CellRangeAddress cellAddresses = cellRangeMap.get(cell.getRowIndex() + ":" + cell.getColumnIndex());
		if (cellAddresses != null) {
			sheet.addMergedRegionUnsafe(cellAddresses);
		}
		sheet.setColumnWidth(0, 11 * 256);

		// 字体
		Font font = sheet.getWorkbook().createFont();
		font.setFontName("等线");
		font.setFontHeightInPoints((short) 9);

		// 水平居中样式
		CellStyle styleCenter = sheet.getWorkbook().createCellStyle();
		// 垂直居中
		styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCenter.setBorderBottom(BorderStyle.THIN);
		styleCenter.setBorderLeft(BorderStyle.THIN);
		styleCenter.setBorderTop(BorderStyle.THIN);
		styleCenter.setBorderRight(BorderStyle.THIN);

		if (cellStyleList.contains(cell.getRowIndex())) {
			// 水平居中
			styleCenter.setAlignment(HorizontalAlignment.CENTER);
			font.setBold(true);
		} else {
			// 水平居左
			styleCenter.setAlignment(HorizontalAlignment.LEFT);
			font.setBold(false);
		}

		// 换行
		styleCenter.setWrapText(true);


		styleCenter.setFont(font);
		//cell.setCellStyle(styleCenter);


		List<Font> fontList = new ArrayList<>();
		Font font1 = sheet.getWorkbook().createFont();
		font1.setColor((short)1);
		fontList.add(font1);
		Font font2 = sheet.getWorkbook().createFont();
		font1.setColor((short)2);
		fontList.add(font2);
		setRichTextCellValue(cell, "红色字体 黑色字体", new String[]{"红色字体", " 黑色字体"}, fontList);

		//sheet.addMergedRegionUnsafe(new CellRangeAddress(0, 0, 0, 9));
		//System.out.println(cell.getRowIndex() + ":" + cell.getColumnIndex());
        /*if (CollectionUtils.isNotEmpty(cellRangeAddresss)) {
            if (cell.getRowIndex() == 1 && cell.getColumnIndex() == 0) {
                for (CellRangeAddress item : cellRangeAddresss) {
                    sheet.addMergedRegionUnsafe(item);
                }
            }
        }*/
	}


	/*public static void main(String[] args) {
		WriteCellDemoData writeCellDemoData = new WriteCellDemoData();

		// 设置单个单元格的样式 当然样式 很多的话 也可以用注解等方式。
		WriteCellData<String> writeCellStyle = new WriteCellData<>("单元格样式");
		writeCellStyle.setType(CellDataTypeEnum.STRING);
		writeCellDemoData.setWriteCellStyle(writeCellStyle);
		WriteCellStyle writeCellStyleData = new WriteCellStyle();
		writeCellStyle.setWriteCellStyle(writeCellStyleData);
		// 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.
		writeCellStyleData.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
		// 背景绿色
		writeCellStyleData.setFillForegroundColor(IndexedColors.GREEN.getIndex());

		// 设置单个单元格多种样式
		WriteCellData<String> richTest = new WriteCellData<>();
		richTest.setType(CellDataTypeEnum.RICH_TEXT_STRING);
		writeCellDemoData.setRichText(richTest);
		RichTextStringData richTextStringData = new RichTextStringData();
		richTest.setRichTextStringDataValue(richTextStringData);
		richTextStringData.setTextString("红色绿色默认");
		// 前2个字红色
		WriteFont writeFont = new WriteFont();
		writeFont.setColor(IndexedColors.RED.getIndex());
		richTextStringData.applyFont(0, 2, writeFont);
		// 接下来2个字绿色
		writeFont = new WriteFont();
		writeFont.setColor(IndexedColors.GREEN.getIndex());
		richTextStringData.applyFont(2, 4, writeFont);

		List<WriteCellDemoData> data = new ArrayList<>();
		data.add(writeCellDemoData);
		EasyExcel.write("D:/test2.xlsx", WriteCellDemoData.class).inMemory(true).sheet("模板").doWrite(data);
	}*/

}


