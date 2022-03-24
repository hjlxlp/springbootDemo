package com.example.demo.test.export;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huangjiale
 * @date 2022/3/24 15:59
 */
public class ExcelColorTest {

    public static void main(String[] args) {
        main1(args);
    }

    public static void main1(String[] args) {
        LocalDateTime begin = LocalDateTime.now();
        try {
            List<List<String>> list = new ArrayList<>();
            List<String> l = new ArrayList<>();
            //l.add("1、xxx;0,2、xxx;1,3、xxx;2");
            l.add("");
            list.add(l);
            File newFile = new File("D://excelColor.xlsx");
            EasyExcel.write(newFile)
                    .registerWriteHandler(new MyColorMergeStrategy())
                    .sheet()
                    .doWrite(list);
            //newFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Duration.between(begin, LocalDateTime.now()).toMillis());
    }

}
