package com.example.demo.test.pdf;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.*;

/**
 * @author huangjiale
 * @date 2022/3/9 16:38
 **/
public class MyStrategy implements CellWriteHandler {

	private static CellStyle styleCenter;

	@Override
	public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
		if (styleCenter == null) {
			styleCenter = writeSheetHolder.getSheet().getWorkbook().createCellStyle();
			styleCenter.setAlignment(HorizontalAlignment.RIGHT);
			styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		}
		
	}


}
