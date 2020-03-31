package com.example.demo.util;

import com.example.demo.entity.ExcelInputVo;
import com.example.demo.entity.ExportField;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author hjl
 * @date 2018/11/12 18:25
 */
public class ExcelUtil {

    /**
     * 单元格格式类型（1-文本，2-日期，3-数字，4-货币，5-科学计数法）
     */
    public static final String STYLE_TYPE_1 = "1";
    public static final String STYLE_TYPE_2 = "2";
    public static final String STYLE_TYPE_3 = "3";
    public static final String STYLE_TYPE_4 = "4";
    public static final String STYLE_TYPE_5 = "5";

    /**
     * 生成excel
     *
     * @param inputVo       excel自定义参数
     * @param exportAllList 所有字段
     * @param mapList       需要导出的数据
     * @return
     */
    public static XSSFWorkbook createWorkbook(ExcelInputVo inputVo, List<ExportField> exportAllList, List<Map<String, String>> mapList) {
        //需要用的导入字段
        List<ExportField> exportList = new ArrayList<>();
        for (ExportField export : exportAllList) {
            for (ExportField child : export.getChild()) {
                exportList.add(child);
            }
        }
        //创建excel和生成excel头部
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(inputVo.getSheetName());
        //4种格式
        XSSFCellStyle style1 = getStyle(wb, STYLE_TYPE_1);
        XSSFCellStyle style2 = getStyle(wb, STYLE_TYPE_2);
        XSSFCellStyle style3 = getStyle(wb, STYLE_TYPE_3);
        XSSFCellStyle style4 = getStyle(wb, STYLE_TYPE_4);
        XSSFCellStyle style5 = getStyle(wb, STYLE_TYPE_5);
        //第一行标题
        XSSFRow row0 = sheet.createRow(0);
        XSSFCell cell0 = row0.createCell(0);
        cell0.setCellValue(inputVo.getTitle());
        cell0.setCellStyle(style1);
        CellRangeAddress cra = new CellRangeAddress(0, 0, 0, exportList.size() - 1);
        sheet.addMergedRegion(cra);
        setRegionBorder(1, cra, sheet, wb);
        //第二行
        XSSFRow row1 = sheet.createRow(1);
        Integer colIndex = -1;
        for (int i = 0; i < exportAllList.size(); i++) {
            ExportField export = exportAllList.get(i);
            XSSFCell cell = row1.createCell(colIndex + 1);
            cell.setCellValue(export.getName());
            cell.setCellStyle(style1);
            CellRangeAddress cra2 = new CellRangeAddress(1, 1, colIndex + 1, export.getChild().size() + colIndex);
            sheet.addMergedRegion(cra2);
            setRegionBorder(1, cra2, sheet, wb);
            colIndex = colIndex + export.getChild().size();
        }
        //第三行
        XSSFRow row2 = sheet.createRow(2);
        for (int i = 0; i < exportList.size(); i++) {
            ExportField export = exportList.get(i);
            XSSFCell cell = row2.createCell(i);
            cell.setCellValue(export.getName());
            cell.setCellStyle(style1);
        }
        //生成excel数据
        if (mapList != null && mapList.size() > 0) {
            for (int i = 0; i < mapList.size(); i++) {
                XSSFRow row = sheet.createRow(i + 3);
                Map<String, String> map = mapList.get(i);
                for (int j = 0; j < exportList.size(); j++) {
                    ExportField export = exportList.get(j);
                    XSSFCell cell = row.createCell(j);
                    cell.setCellValue(map.get(export.getCode()));
                    //格式
                    String index = export.getIndex().toString();
                    String last = index.substring(index.length() - 1);
                    //文本
                    if (STYLE_TYPE_1.equals(last)) {
                        cell.setCellStyle(style1);
                    }
                    //日期
                    if (STYLE_TYPE_2.equals(last)) {
                        cell.setCellStyle(style2);
                    }
                    //数字
                    if (STYLE_TYPE_3.equals(last)) {
                        cell.setCellStyle(style3);
                    }
                    //货币
                    if (STYLE_TYPE_4.equals(last)) {
                        cell.setCellStyle(style4);
                    }
                    //科学计数法
                    if (STYLE_TYPE_5.equals(last)) {
                        cell.setCellStyle(style5);
                    }
                }
            }
        }
        return wb;
    }

    /**
     * 处理合并单元格的边框问题
     *
     * @param border
     * @param region
     * @param sheet
     * @param wb
     */
    public static void setRegionBorder(int border, CellRangeAddress region, XSSFSheet sheet, XSSFWorkbook wb) {
        /*RegionUtil.setBorderBottom(border, region, sheet, wb);
        RegionUtil.setBorderLeft(border, region, sheet, wb);
        RegionUtil.setBorderRight(border, region, sheet, wb);
        RegionUtil.setBorderTop(border, region, sheet, wb);*/
    }

    /**
     * 获取单元格格式
     *
     * @param wb
     * @param type
     * @return
     */
    public static XSSFCellStyle getStyle(XSSFWorkbook wb, String type) {
        XSSFCellStyle cellStyle = wb.createCellStyle();
        /*cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);*/
        XSSFDataFormat format = wb.createDataFormat();
        //文本
        if (STYLE_TYPE_1.equals(type)) {
            cellStyle.setDataFormat(format.getFormat("@"));
        }
        //日期
        if (STYLE_TYPE_2.equals(type)) {
            cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
        }
        //数字
        if (STYLE_TYPE_3.equals(type)) {
            cellStyle.setDataFormat(format.getFormat("0.000"));
        }
        //货币
        if (STYLE_TYPE_4.equals(type)) {
            cellStyle.setDataFormat(format.getFormat("0.00"));
        }
        //科学计数法
        if (STYLE_TYPE_5.equals(type)) {
            cellStyle.setDataFormat(format.getFormat("#,##0.00"));
        }
        return cellStyle;
    }

}
