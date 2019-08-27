package com.example.demo.vo;

import lombok.Data;

import java.util.HashMap;

/**
 * @author hjl
 * @date 2019/7/23 13:36
 */
@Data
public class TestEquals {

    private Integer id;

    @Override
    public boolean equals(Object obj) {
        return false;
    }

}
