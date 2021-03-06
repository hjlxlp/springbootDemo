package com.example.demo.service;

import com.example.demo.vo.ExcelTestVo;
import com.example.demo.vo.ExcelTest2;

import java.util.List;

/**
 * 用户人员接口
 *
 * @author fraser
 * @date 2019/10/7 2:01 PM
 */
public interface IUser {

    public boolean saveData(List<ExcelTestVo> users);

    public boolean saveData2(List<ExcelTest2> users);
}
