package com.example.demo.test.export;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.fastjson.JSON;
import io.lettuce.core.ScriptOutputType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hjl
 */
public class MyColorMergeStrategy extends AbstractMergeStrategy {

    /**
     * 水平居中单元格行数集合
     */
    private static List<Integer> cellStyleList;

    private static List<Font> fontList;

    private static List<XSSFFont> fontList2;

    public MyColorMergeStrategy() {


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
        if (fontList == null) {
            fontList = new ArrayList<>();
            // 正常
            Font font1 = sheet.getWorkbook().createFont();
            fontList.add(font1);
            // 跨省-红
            Font font2 = sheet.getWorkbook().createFont();
            font2.setColor((short) 2);
            fontList.add(font2);
            // 跨市-黄
            Font font3 = sheet.getWorkbook().createFont();
            font3.setColor((short) 52);
            fontList.add(font3);
        }

        /*XSSFWorkbook wb = new XSSFWorkbook();
        XSSFCell xssfCell = wb.createSheet().createRow(0).createCell(0);
        xssfCell.setCellValue("1、xxx;0,2、xxx;1,3、xxx;2");*/
        //cell = (XSSFCell) cell;

        // 收货地址第8列特殊处理
        //if (cell.getRowIndex() > 0 && cell.getColumnIndex() == 8) {
        if (cell.getRowIndex() == 0) {
            cell.setCellValue("1、xxx;0,2、xxx;1,3、xxx;2");
            //String allText = cell.getStringCellValue();
            String allText = "1、xxx;0,2、xxx;1,3、xxx;2";
            // 单元格
            if (allText.length() > 0) {
                // 单元格文字，用逗号隔开，最后一位数字（0-正常，1-跨省红色，2-跨市黄色），格式：1、xxx;0,2、xxx;1,3、xxx;2
                String[] values = allText.split(",");

                // 先拼接text，放到单元格中
                StringBuffer textSb = new StringBuffer();
                for (int k = 0; k < values.length; k++) {
                    String value = values[k].substring(0, values[k].length() - 1);
                    textSb.append(value).append("\n");
                }
                cell.setCellValue(textSb.substring(0, textSb.length()-1));

                // 改变单元格文字颜色
                RichTextString rt = cell.getRichStringCellValue();
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
                    //System.out.println(beginIndex+":"+(beginIndex + values[k].length()-1));
                    beginIndex += values[k].length();
                }

                //rt.applyFont(0, 5, fontList.get(2));
                //rt.applyFont(font2);
                //System.out.println(rt.length());
                //System.out.println(rt.toString().length());
                System.out.println(JSON.toJSONString(rt.toString()));
                cell.setCellValue(rt);
            } else {
                cell.setCellValue("");
            }
        }
    }


  /*  @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        if (fontList == null) {
            fontList = new ArrayList<>();
            // 正常
            Font font1 = writeSheetHolder.getSheet().getWorkbook().createFont();
            fontList.add(font1);
            // 跨省-红
            Font font2 = writeSheetHolder.getSheet().getWorkbook().createFont();
            font2.setColor((short) 2);
            fontList.add(font2);
            // 跨市-黄
            Font font3 = writeSheetHolder.getSheet().getWorkbook().createFont();
            font3.setColor((short) 52);
            fontList.add(font3);
        }


        // 收货地址第8列特殊处理
        //if (cell.getRowIndex() > 0 && cell.getColumnIndex() == 8) {
        if (cell.getRowIndex() == 0) {
            cell.setCellValue("1、xxx;0,2、xxx;1,3、xxx;2");
            //String allText = cell.getStringCellValue();
            String allText = "1、xxx;0,2、xxx;1,3、xxx;2";
            // 单元格
            if (allText.length() > 0) {
                // 单元格文字，用逗号隔开，最后一位数字（0-正常，1-跨省红色，2-跨市黄色），格式：1、xxx;0,2、xxx;1,3、xxx;2
                String[] values = allText.split(",");

                // 先拼接text，放到单元格中
                StringBuffer textSb = new StringBuffer();
                for (int k = 0; k < values.length; k++) {
                    String value = values[k].substring(0, values[k].length() - 1);
                    textSb.append(value).append("\n");
                }
                cell.setCellValue(textSb.substring(0, textSb.length()-1));

                // 改变单元格文字颜色
                RichTextString rt = cell.getRichStringCellValue();
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
                cell.setCellValue(rt);
            } else {
                cell.setCellValue("");
            }
        }
    }
*/
    /**
     * merge
     *
     * @param sheet
     * @param cell
     * @param head
     * @param relativeRowIndex
     */
    //@Override
    protected void merge2(Sheet sheet, Cell cell, Head head, Integer relativeRowIndex) {
        if (fontList2 == null) {
            fontList2 = new ArrayList<>();
            // 正常
            XSSFFont font1 = (XSSFFont) sheet.getWorkbook().createFont();
            fontList2.add(font1);
            // 跨省-红
            XSSFFont font2 = (XSSFFont) sheet.getWorkbook().createFont();
            font2.setColor((short) 2);
            fontList2.add(font2);
            // 跨市-黄
            XSSFFont font3 = (XSSFFont) sheet.getWorkbook().createFont();
            font3.setColor((short) 52);
            fontList2.add(font3);
        }


        // 收货地址第8列特殊处理
        //if (cell.getRowIndex() > 0 && cell.getColumnIndex() == 8) {
        if (cell.getRowIndex() == 0) {
            String allText = cell.getStringCellValue();
            // 单元格
            if (allText.length() > 0) {
                // 单元格文字，用逗号隔开，最后一位数字（0-正常，1-跨省红色，2-跨市黄色），格式：1、xxx;0,2、xxx;1,3、xxx;2
                String[] values = allText.split(",");

                // 先拼接text，放到单元格中
                StringBuffer textSb = new StringBuffer();
                for (int k = 0; k < values.length; k++) {
                    String value = values[k].substring(0, values[k].length() - 1);
                    textSb.append(value).append("\n");
                }
                cell.setCellValue(textSb.substring(0, textSb.length() - 1));

                // 改变单元格文字颜色
                /*RichTextString rt = cell.getRichStringCellValue();
                Integer beginIndex = 0;
                for (int k = 0; k < values.length; k++) {
                    // 是否跨省市
                    String type = values[k].substring(values[k].length() - 1);
                    if ("0".equals(type)) {
                        rt.applyFont(beginIndex, beginIndex + values[k].length() - 1, fontList.get(0));
                    } else if ("1".equals(type)) {
                        rt.applyFont(beginIndex, beginIndex + values[k].length() - 1, fontList.get(1));
                    } else if ("2".equals(type)) {
                        rt.applyFont(beginIndex, beginIndex + values[k].length() - 1, fontList.get(2));
                    }
                    beginIndex += values[k].length();
                }*/

                // 改变单元格文字颜色
                XSSFRichTextString rt = (XSSFRichTextString) cell.getRichStringCellValue();
                rt.applyFont(0, 5, fontList2.get(2));
                System.out.println(fontList2.get(2).getColor());

                cell.setCellValue(rt);
            } else {
                cell.setCellValue("");
            }
        }
    }


    /**
     * 设置值和样式，富文本 复合样式（一个单元格多个字体）
     *
     * @param cell        当前单元格
     * @param wholeStr    整个字符串
     * @param strArray    字符串分割的数组
     * @param strFontList 字符串分割后一一对应的字体
     */
    public static void setRichTextCellValue(Cell cell, String wholeStr, String[] strArray, List<Font> strFontList) {
        XSSFRichTextString hssfRichTextString = new XSSFRichTextString(wholeStr);
        int strLength = 0;
        for (int i = 0; i < strArray.length; i++) {
            hssfRichTextString.applyFont(strLength, strLength + strArray[i].length(), strFontList.get(i));
            strLength = strArray[i].length();
        }
        cell.setCellValue(hssfRichTextString);
    }


    /*List<Font> fontList = new ArrayList<>();
    Font font1 = sheet.getWorkbook().createFont();
    font1.setColor((short) 1);
    fontList.add(font1);
    Font font2 = sheet.getWorkbook().createFont();
    font1.setColor((short) 2);
    fontList.add(font2);
    setRichTextCellValue(cell, "红色字体 黑色字体", new String[]{"红色字体", " 黑色字体"}, fontList);*/

}


