package com.example.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.example.demo.entity.ExcelTest;
import com.example.demo.entity.ExportInputVo;
import com.example.demo.service.ExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

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

    @PostMapping("export")
    public String export(ExportInputVo vo) {
        excelService.export(vo);
        return "1";
    }

}
