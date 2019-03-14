package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hjl
 * @date 2019/3/14 16:45
 */
@RequestMapping("city")
@RestController
public class ExcelController {

    @PostMapping("excelExport")
    public void excelExport() {

    }

}
