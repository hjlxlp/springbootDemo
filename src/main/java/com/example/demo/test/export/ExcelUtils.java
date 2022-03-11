package com.example.demo.test.export;

import java.util.Iterator;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Author DouDou、
 * @create 2020-11-21 13:40
 */
public class ExcelUtils {

  /**
   * 移动多行或一行  从0开始计数
   *
   * @param startRow  开始行数
   * @param endRow    结束行数
   * @param mobileRow 移动的行数 正数向下移动  负数向上移动
   */
  public static void mobileRow(Sheet sheet, Integer startRow, Integer endRow, Integer mobileRow) {
    if (mobileRow != 0) {
      sheet.shiftRows(startRow, endRow, mobileRow, true, false);
    }
  }

  /**
   * 复制一行  -> 往下复制  ↓  样式格式会被复制
   *
   * @param sheet         sheet页
   * @param copyRow       被复制的行
   * @param copyValueFlag 是否复制内容
   */
  public static void copyRow(Workbook workbook, Sheet sheet, Integer copyRow, Boolean copyValueFlag) {
    if (copyRow + 1 > sheet.getLastRowNum()) {
      sheet.shiftRows(sheet.getLastRowNum(), sheet.getLastRowNum(), 1, true, false);
    } else {
      sheet.shiftRows(copyRow + 1, sheet.getLastRowNum(), 1, true, false);
    }
    sheet.createRow(copyRow + 1);
    if (sheet.getRow(copyRow) == null) {
      sheet.createRow(copyRow);
    }
    copyRow(workbook, sheet.getRow(copyRow), sheet.getRow(copyRow + 1), copyValueFlag);
  }

  /**
   * 合并单元格  从0开始计数
   *
   * @param startRow  开始行
   * @param endRow    结束行
   * @param startCell 开始列
   * @param endCell   结束列
   */
  public static void mergedRegion(Sheet sheet, Integer startRow, Integer endRow, Integer startCell, Integer endCell) {
    if (!startRow.equals(endRow) || !startCell.equals(endCell)) {
      changeMultipleSheetRowAndCell(sheet, startRow, endRow, startCell, endCell);
      CellRangeAddress rangeAddress = new CellRangeAddress(startRow, endRow, startCell, endCell);
      sheet.addMergedRegion(rangeAddress);
    }
  }

  /**
   * 设置单个单元格格式 边框加居中
   *
   * @param sheet sheet页
   * @param row   行数
   * @param cell  列数
   */
  public static void setCellStyle(XSSFWorkbook workbook, Sheet sheet, Integer row, Integer cell) {
    changeSheetRowAndCell(sheet, row, cell);
    sheet.getRow(row).getCell(cell).setCellStyle(getCellStyle(workbook));
  }

  /**
   * 设置单个单元格格式 居中
   *
   * @param sheet sheet页
   * @param row   行数
   * @param cell  列数
   */
  public static void setCellCenteredStyle(XSSFWorkbook workbook, Sheet sheet, Integer row, Integer cell) {
    changeSheetRowAndCell(sheet, row, cell);
    sheet.getRow(row).getCell(cell).setCellStyle(getCellCenteredStyle(workbook));
  }

  /**
   * 设置区域单元格格式 边框加居中 如果有合并单元格  -> 也要为每个单元格设置格式  -> 否则不会生效
   *
   * @param sheet     sheet页
   * @param startRow  开始行
   * @param endRow    结束行
   * @param startCell 开始列
   * @param endCell   结束列
   */
  public static void setAreaStyle(XSSFWorkbook workbook, Sheet sheet, Integer startRow, Integer endRow, Integer startCell, Integer endCell) {
    for (int row = startRow; row <= endRow; row++) {
      changeSheetRow(sheet, row);
      for (int cell = startCell; cell <= endCell; cell++) {
        changeSheetCell(sheet, row, cell);
        sheet.getRow(row).getCell(cell).setCellStyle(getCellStyle(workbook));
      }
    }
  }


  /**
   * 创建不存在的单元格
   *
   * @param sheet
   * @param row
   * @param cell
   */
  private static void changeSheetCell(Sheet sheet, Integer row, Integer cell) {
    if (sheet.getRow(row).getCell(cell) == null) {
      sheet.getRow(row).createCell(cell);
    }
  }

  /**
   * 创建不存在的行
   *
   * @param sheet
   * @param row
   */
  private static void changeSheetRow(Sheet sheet, Integer row) {
    if (sheet.getRow(row) == null) {
      sheet.createRow(row);
    }
  }

  /**
   * 创建不存在的行 或 单元格
   *
   * @param sheet
   * @param row
   * @param cell
   */
  private static void changeSheetRowAndCell(Sheet sheet, Integer row, Integer cell) {
    if (sheet.getRow(row) == null) {
      sheet.createRow(row);
    }
    if (sheet.getRow(row).getCell(cell) == null) {
      sheet.getRow(row).createCell(cell);
    }
  }

  /**
   * 创建多个不存在的行 或 单元格
   *
   * @param sheet     sheet页
   * @param startRow  起始行
   * @param endRow    结束行
   * @param startCell 开始列
   * @param endCell   结束列
   */
  private static void changeMultipleSheetRowAndCell(Sheet sheet, Integer startRow, Integer endRow, Integer startCell, Integer endCell) {
    if (sheet.getRow(startRow) == null) {
      sheet.createRow(startRow);
    }
    if (sheet.getRow(endRow) == null) {
      sheet.createRow(endRow);
    }
    if (sheet.getRow(startRow).getCell(startCell) == null) {
      sheet.getRow(startRow).createCell(startCell);
    }
    if (sheet.getRow(startRow).getCell(endCell) == null){
      sheet.getRow(startRow).createCell(endCell);
    }
    if(sheet.getRow(endRow).getCell(startCell) == null){
      sheet.getRow(endRow).createCell(startCell);
    }
    if(sheet.getRow(endRow).getCell(endCell) == null){
      sheet.getRow(endRow).createCell(endCell);
    }
  }

  /**
   * 复制行
   *
   * @param fromRow
   * @param toRow
   * @param copyValueFlag
   */
  private static void copyRow(Workbook workbook, Row fromRow, Row toRow, boolean copyValueFlag) {
    toRow.setHeight(fromRow.getHeight());
    for (Iterator cellIt = fromRow.cellIterator(); cellIt.hasNext(); ) {
      Cell tmpCell = (Cell) cellIt.next();
      Cell newCell = toRow.createCell(tmpCell.getColumnIndex());
      copyCell(workbook, tmpCell, newCell, copyValueFlag);
    }
    Sheet worksheet = fromRow.getSheet();
    for (int i = 0; i < worksheet.getNumMergedRegions(); i++) {
      CellRangeAddress cellRangeAddress = worksheet.getMergedRegion(i);
      if (cellRangeAddress.getFirstRow() == fromRow.getRowNum()) {
        CellRangeAddress newCellRangeAddress = new CellRangeAddress(toRow.getRowNum(), (toRow.getRowNum() +
            (cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow())), cellRangeAddress
            .getFirstColumn(), cellRangeAddress.getLastColumn());
        worksheet.addMergedRegionUnsafe(newCellRangeAddress);
      }
    }
  }

  /**
   * 复制单元格
   *
   * @param srcCell
   * @param distCell
   * @param copyValueFlag true则连同cell的内容一起复制
   */
  private static void copyCell(Workbook wb, Cell srcCell, Cell distCell, boolean copyValueFlag) {
    CellStyle newStyle = wb.createCellStyle();
    CellStyle srcStyle = srcCell.getCellStyle();
    newStyle.cloneStyleFrom(srcStyle);
    newStyle.setFont(wb.getFontAt(srcStyle.getFontIndex()));
    distCell.setCellStyle(newStyle);
    if (srcCell.getCellComment() != null) {
      distCell.setCellComment(srcCell.getCellComment());
    }
    // 不同数据类型处理
    CellType srcCellType = srcCell.getCellTypeEnum();
    distCell.setCellType(srcCellType);
    if (copyValueFlag) {
      if (srcCellType == CellType.NUMERIC) {
        if (DateUtil.isCellDateFormatted(srcCell)) {
          distCell.setCellValue(srcCell.getDateCellValue());
        } else {
          distCell.setCellValue(srcCell.getNumericCellValue());
        }
      } else if (srcCellType == CellType.STRING) {
        distCell.setCellValue(srcCell.getRichStringCellValue());
      } else if (srcCellType == CellType.BLANK) {

      } else if (srcCellType == CellType.BOOLEAN) {
        distCell.setCellValue(srcCell.getBooleanCellValue());
      } else if (srcCellType == CellType.ERROR) {
        distCell.setCellErrorValue(srcCell.getErrorCellValue());
      } else if (srcCellType == CellType.FORMULA) {
        distCell.setCellFormula(srcCell.getCellFormula());
      } else {
      }
    }
  }

  /**
   * 上、下、左、右边框 实线 水平、垂直居中
   *
   * @param workbook
   * @return XSSFCellStyle
   */
  private static XSSFCellStyle getCellStyle(XSSFWorkbook workbook) {
    XSSFCellStyle style = workbook.createCellStyle();
    style.setBorderTop(BorderStyle.THIN);
    style.setBorderBottom(BorderStyle.THIN);
    style.setBorderLeft(BorderStyle.THIN);
    style.setBorderRight(BorderStyle.THIN);
    style.setAlignment(HorizontalAlignment.CENTER);
    style.setVerticalAlignment(VerticalAlignment.CENTER);
    return style;
  }

  /**
   * 水平、垂直居中
   *
   * @param workbook
   * @return XSSFCellStyle
   */
  private static XSSFCellStyle getCellCenteredStyle(XSSFWorkbook workbook) {
    XSSFCellStyle style = workbook.createCellStyle();
    style.setAlignment(HorizontalAlignment.CENTER);
    style.setVerticalAlignment(VerticalAlignment.CENTER);
    return style;
  }


}

