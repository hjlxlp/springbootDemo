package com.example.demo.config;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @author huangjiale
 * @date 2020/4/1 13:31
 **/
public class CodeGenerator {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String driver = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String pwd = "root";

        //项目路径 （注意后面拼接 module 名称）
        String projectPath = System.getProperty("user.dir");
        String entityPackage = "com.example.demo.entity";
        String mapperPackage = "com.example.demo.mapper";
        String xmlPath = System.getProperty("user.dir") + "/src/main/resources/mapper";
        String servicePackage = "com.example.demo.service";
        String serviceImplPackage = "com.example.demo.service.impl";
        String controllerPackage = "com.example.demo.controller";
        String[] targetTables = {"excel_test"};

        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setDateType(DateType.ONLY_DATE);
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("huangjiale");
        gc.setOpen(false);
        gc.setFileOverride(false);
        //xml
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driver);
        dsc.setUsername(user);
        dsc.setPassword(pwd);
        mpg.setDataSource(dsc);

        //包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com");
        pc.setModuleName("springboodDemo");
        pc.setEntity(entityPackage);
        pc.setMapper(mapperPackage);
        pc.setService(servicePackage);
        pc.setServiceImpl(serviceImplPackage);
        pc.setController(controllerPackage);
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(targetTables);
        mpg.setStrategy(strategy);

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

    }

}
