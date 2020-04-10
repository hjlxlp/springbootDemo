package com.example.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.example.demo.vo.ExcelTestVo;
import com.example.demo.vo.ExcelTest2;
import com.example.demo.service.ExcelService;
import com.example.demo.service.IUser;
import com.example.demo.util.ExcelUtils;
import com.example.demo.vo.ExportInputVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author hjl
 * @date 2019/3/14 16:45
 */
@RequestMapping("excel")
@RestController
@CrossOrigin(origins = "*")
public class ExcelController {

    private final static Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    private ExcelService excelService;
    @Autowired
    private IUser iUser;

    @PostMapping("export")
    public String export(ExportInputVo vo) {
        excelService.export(vo);
        return "1";
    }

    @PostMapping("import")
    public void excelImport(@RequestParam(value = "file") MultipartFile serviceFile) throws IOException {
        ExcelReader excelReader = null;
        InputStream in = null;
        try {
            //AnalysisEventListener<ExcelTest> listener = ExcelUtils.getListener();
            in = serviceFile.getInputStream();
            excelReader = EasyExcel.read(in, ExcelTestVo.class,
                    new DemoDataListener()).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } catch (IOException ex) {
            logger.error("import excel to db fail", ex);
        } finally {
            in.close();
            // 这里一定别忘记关闭，读的时候会创建临时文件，到时磁盘会崩
            if (excelReader != null) {
                excelReader.finish();
            }
        }
    }

    @PostMapping("importTest")
    public void importTest(@RequestParam(value = "file") MultipartFile serviceFile) throws IOException {
        ExcelReader excelReader = null;
        InputStream in = null;
        try {
            AnalysisEventListener<ExcelTest2> listener = ExcelUtils.getListener(this.batchInsert());
            in = serviceFile.getInputStream();
            excelReader = EasyExcel.read(in, ExcelTestVo.class, listener).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } catch (IOException ex) {
            logger.error("import excel to db fail", ex);
        } finally {
            in.close();
            // 这里一定别忘记关闭，读的时候会创建临时文件，到时磁盘会崩
            if (excelReader != null) {
                excelReader.finish();
            }
        }
    }

    private Consumer<List<ExcelTest2>> batchInsert() {
        System.out.println("===begin===");
        return users -> {
            iUser.saveData2(users);
        };
    }


}
