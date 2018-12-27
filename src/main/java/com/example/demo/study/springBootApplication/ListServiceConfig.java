package com.example.demo.study.springBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author hjl
 * @date 2018/12/27 15:04
 */
@Configuration
public class ListServiceConfig {

    @Bean
    @Conditional(OnWindowsCondition.class)
    public ListService windowsListService() {
        return new WindowsListService();
    }

    @Bean
    @Conditional(OnUnixCondition.class)
    public ListService unixListService() {
        return new UnixListService();
    }

}
