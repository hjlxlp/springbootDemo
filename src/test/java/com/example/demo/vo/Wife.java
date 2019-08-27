package com.example.demo.vo;

import lombok.*;

/**
 * @author hjl
 * @date 2019/8/27 16:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wife {

    private Integer id;

    private String name;

    private String code;

    private Integer husbandId;

}
