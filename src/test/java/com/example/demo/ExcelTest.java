package com.example.demo;

import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author hjl
 * @date 2019/3/14 14:51
 */
public class ExcelTest {

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
            anchor.setAnchorType(3);
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
