
package com.example.demo.entity.dynamics;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("employee")
public class EmployeeEntity {
    private Integer id;
    private Integer departmentId;
    private String name;
}
