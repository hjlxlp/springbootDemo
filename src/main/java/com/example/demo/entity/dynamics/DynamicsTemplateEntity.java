package com.example.demo.entity.dynamics;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author hjl
 */
@Data
@TableName("dynamics_template")
public class DynamicsTemplateEntity {
	private Integer id;
	private String companyCode;
	private String tableName;
	private String fieldName;
	private String fieldDesc;
}
