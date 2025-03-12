package com.example.demo.controller.dynamics;

import com.example.demo.entity.dynamics.DepartmentEntity;
import com.example.demo.mapper.dynamics.DepartmentMapper;
import com.example.demo.mapper.dynamics.DynamicsTemplateMapper;
import com.example.demo.mapper.dynamics.DynamicsValueMapper;
import com.example.demo.mapper.dynamics.EmployeeMapper;
import com.example.demo.vo.dynamics.QueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huangjiale
 * @date 2025/3/12 13:40
 **/
@RequestMapping("/dynamics")
@RestController
public class DynamicsController {

	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private DynamicsTemplateMapper dynamicsTemplateMapper;
	@Autowired
	private DynamicsValueMapper dynamicsValueMapper;

	@PostMapping("/selectDepartmentList")
	public List<DepartmentEntity> selectDepartmentList(@RequestBody QueryDto dto) {
		return departmentMapper.selectList(null);
	}

	@PostMapping("/selectEmployeeDetail")
	public Object selectEmployeeDetail(@RequestBody QueryDto dto) {
		return employeeMapper.queryById(1);
	}


}
