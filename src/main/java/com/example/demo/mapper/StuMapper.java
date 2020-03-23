package com.example.demo.mapper;

import com.example.demo.entity.Student;
import org.apache.ibatis.annotations.Param;

public interface StuMapper {
    Student findStu(@Param("keyword") String keyword,@Param("scoreBegin")Integer scoreBegin,@Param("scoreEnd")Integer scoreEnd);
}
