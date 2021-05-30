package com.example.demo.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSON;
import com.example.demo.easyexcel.DemoData;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hjl
 * @date 2019/3/14 14:51
 */
public class ExcelTest {

    @Test
    public void test02() {
        List<DemoData> list = new ArrayList<>();
        DemoData demoData;
        for (int i = 0; i < 100; i++) {
            demoData = new DemoData();
            demoData.setId(i);
            demoData.setCreateTime(new Date());
            demoData.setLength(BigDecimal.valueOf(i * 100));
            demoData.setName("name" + i);
            list.add(demoData);
        }

        LocalTime begin = LocalTime.now();

        String fileName = "test.xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(list);

        System.out.println(Duration.between(begin, LocalTime.now()).toMillis() + "ms");

    }

    @Test
    public void test01() {
        FileOutputStream fileOut = null;
        BufferedImage bufferImg = null;
        //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
        try {
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            bufferImg = ImageIO.read(new File("C:/Users/22474/Pictures/bizhi/704263.jpg"));
            ImageIO.write(bufferImg, "jpg", byteArrayOut);

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet1 = wb.createSheet("test picture");
            //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
            XSSFDrawing drawing = sheet1.createDrawingPatriarch();
            //drawing主要用于设置图片的属性
            XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 255, 255, (short) 1, 1, (short) 5, 8);
            //anchor.setAnchorType(3);
            //插入图片
            drawing.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG));
            fileOut = new FileOutputStream("C:/测试.xlsx");
            // 写入excel文件
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
