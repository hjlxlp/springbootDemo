package com.example.demo;

import com.example.demo.entity.ChineseName;
import com.example.demo.entity.TestVo;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hjl
 * @date 2019/3/14 14:51
 */
public class MyTest {

    @Test
    public void test01() {
        List<Object> list = new ArrayList<>();
        TestVo vo1 = new TestVo();
        vo1.setOrderCode("1");
        vo1.setOrderName("1");
        list.add(vo1);
        TestVo vo2 = new TestVo();
        vo2.setOrderId(2);
        vo2.setOrderName("2");
        list.add(vo2);
        TestVo vo3 = new TestVo();
        vo3.setOrderId(3);
        vo3.setOrderType("3");
        list.add(vo3);
        StringBuffer message = isNull(list);
        System.out.println(message);

    }

    private StringBuffer isNull(List<Object> list) {
        StringBuffer message = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            try {
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    ChineseName chineseName = field.getAnnotation(ChineseName.class);
                    if (chineseName != null) {
                        Object value = field.get(obj);
                        if (value == null) {
                            message.append("第" + (i + 1) + "行的" + chineseName.value() + "不能为空。\n");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return message;
    }

}
