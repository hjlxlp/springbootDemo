package com.example.demo.controller;

import com.example.demo.entity.ExportInputVo;
import com.example.demo.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hjl
 * @date 2019/3/14 16:45
 */
@RequestMapping("excel")
@RestController
@CrossOrigin(origins = "*")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @PostMapping("export")
    public String export(ExportInputVo vo) {
        excelService.export(vo);
        return "1";
    }

}
