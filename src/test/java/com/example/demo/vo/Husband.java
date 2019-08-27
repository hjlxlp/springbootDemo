package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hjl
 * @date 2019/8/27 16:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Husband {

    private Integer id;

    private String name;

    private String code;

    private Integer wifeId;

}
