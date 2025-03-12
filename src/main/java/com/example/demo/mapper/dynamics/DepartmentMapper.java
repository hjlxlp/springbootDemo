package com.example.demo.mapper.dynamics;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.dynamics.DepartmentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author hjl
 */
@Mapper
@Repository
public interface DepartmentMapper extends BaseMapper<DepartmentEntity> {

}
