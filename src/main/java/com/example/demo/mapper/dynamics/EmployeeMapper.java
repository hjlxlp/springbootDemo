package com.example.demo.mapper.dynamics;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.dynamics.EmployeeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author hjl
 */
@Mapper
@Repository
public interface EmployeeMapper extends BaseMapper<EmployeeEntity> {

	EmployeeEntity queryById(@Param("id") Integer id);

}

