package com.example.demo.entity.dynamics;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author hjl
 */
@Data
@TableName("dynamics_value")
public class DynamicsValueEntity {
    private Integer id;
    private Integer templateId;
    private Integer dataId;
    private String dataValue;
}
