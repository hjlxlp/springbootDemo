package com.example.demo.study.springBootApplication;

/**
 * @author hjl
 * @date 2018/12/27 15:02
 */
public class WindowsListService implements ListService {

    @Override
    public String showListCmd() {
        return "dir";
    }
}
