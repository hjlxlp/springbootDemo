package com.example.demo.test.pdf;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
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


	private List<CellRangeAddress> cellList2;

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


	public MyMergeStrategy() {
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
		cellRangeMap.put("6:1", new CellRangeAddress(6, 6, 1, 5));
		// 第八行
		cellRangeMap.put("7:0", new CellRangeAddress(7, 7, 0, 4));
		cellRangeMap.put("7:5", new CellRangeAddress(7, 7, 5, 9));
		// 第九行
		cellRangeMap.put("8:0", new CellRangeAddress(8, 8, 0, 1));
		cellRangeMap.put("8:2", new CellRangeAddress(8, 8, 2, 3));
		cellRangeMap.put("8:4", new CellRangeAddress(8, 8, 4, 9));

		cellStyleList = new ArrayList<>();
		cellStyleList.add(0);
		cellStyleList.add(3);
		cellStyleList.add(4);

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

		if (styleCenter == null) {
			styleCenter = sheet.getWorkbook().createCellStyle();
			styleCenter.setAlignment(HorizontalAlignment.CENTER);
			styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		}
		if (cellStyleList.contains(cell.getRowIndex())) {
			cell.setCellStyle(styleCenter);
		}


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
}


