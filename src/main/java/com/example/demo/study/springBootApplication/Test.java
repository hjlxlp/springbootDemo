package com.example.demo.study.springBootApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * @author hjl
 * @date 2018/12/27 15:05
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ListServiceConfig.class);
        Map<String, ListService> beansOfType = context.getBeansOfType(ListService.class);

        ListService listService = context.getBean(ListService.class);
        System.out.println(context.getEnvironment().getProperty("os.name")
                + "系统下的列表命令为:" + listService.showListCmd());
    }

}
