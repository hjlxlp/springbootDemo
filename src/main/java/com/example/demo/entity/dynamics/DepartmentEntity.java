package com.example.demo.entity.dynamics;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author hjl
 */
@Data
@TableName("department")
public class DepartmentEntity {
	private Integer id;
	private String name;
}
