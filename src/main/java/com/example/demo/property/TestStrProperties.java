package com.example.demo.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hjl
 * @date 2018/12/18 15:47
 */
@Data
@Component
@ConfigurationProperties(prefix = "test")
public class TestStrProperties {

    private String str;

}
