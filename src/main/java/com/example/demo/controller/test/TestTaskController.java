package com.example.demo.controller.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author huangjiale
 * @date 2020/4/8 15:45
 **/
@Component
public class TestTaskController {

    public static final Logger logger = LoggerFactory.getLogger(TestTaskController.class);

    //@Scheduled(cron = "0/5 * * * * ? ")
    private void logTask() {
        logger.info("===定时任务===" + System.currentTimeMillis());
    }

}
