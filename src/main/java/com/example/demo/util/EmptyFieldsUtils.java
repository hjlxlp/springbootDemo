package com.example.demo.util;

import com.example.demo.vo.ChineseFieldName;
import com.example.demo.vo.EmptyResultVo;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huangjiale
 * @date 2020/4/3 15:13
 **/
public class EmptyFieldsUtils {

    /**
     * 获取对象空字段，搭配注解ChineseFieldName使用
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> EmptyResultVo<T> getNullMessage(T t) {
        if (t == null) {
            return new EmptyResultVo<>(new StringBuffer("对象为空"));
        }
        StringBuffer message = new StringBuffer();
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                ChineseFieldName chineseFieldName = field.getAnnotation(ChineseFieldName.class);
                if (chineseFieldName != null) {
                    Object value = field.get(t);
                    if (value == null || value == "") {
                        message.append(chineseFieldName.value() + "不能为空。");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new EmptyResultVo<>(message);
    }

    /**
     * 获取list空字段，搭配注解ChineseFieldName使用
     *
     * @param list
     * @return
     */
    public static <T> EmptyResultVo<T> getNullMessage(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new EmptyResultVo<>(new StringBuffer("list对象为空"));
        }
        StringBuffer message = new StringBuffer();
        StringBuffer msg;
        List<T> passList = new ArrayList<>();
        List<T> errorList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            try {
                Field[] fields = t.getClass().getDeclaredFields();
                Boolean flag = true;
                msg = new StringBuffer("第" + (i + 1) + "行：");
                for (Field field : fields) {
                    field.setAccessible(true);
                    ChineseFieldName chineseFieldName = field.getAnnotation(ChineseFieldName.class);
                    if (chineseFieldName != null) {
                        Object value = field.get(t);
                        if (value == null || value == "") {
                            msg.append(chineseFieldName.value() + "不能为空。");
                            flag = false;
                        }
                    }
                }
                if (flag) {
                    passList.add(t);
                } else {
                    message.append(msg.append("\n"));
                    errorList.add(t);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new EmptyResultVo<>(message, passList, errorList);
    }

}
