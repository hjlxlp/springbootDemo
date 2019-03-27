package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.ChineseName;
import com.example.demo.entity.City;
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

    @Test
    public void test_02() {
        String str = "{\n" +
                "    \"address\": \"CN|上海|上海|None|CHINANET|0|0\",\n" +
                "    \"content\": {\n" +
                "        \"address\": \"上海市\",\n" +
                "        \"address_detail\": {\n" +
                "            \"city\": \"上海市\",\n" +
                "            \"city_code\": 289,\n" +
                "            \"district\": \"\",\n" +
                "            \"province\": \"上海市\",\n" +
                "            \"street\": \"\",\n" +
                "            \"street_number\": \"\"\n" +
                "        },\n" +
                "        \"point\": {\n" +
                "            \"x\": \"121.48789949\",\n" +
                "            \"y\": \"31.24916171\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"status\": 0\n" +
                "}";

        JSONObject js = JSONObject.parseObject(str);
        //System.out.println(js.getString("address"));
        System.out.println(getJsonValue(js, "city_code"));
    }

    private String getJsonValue(JSONObject js, String key) {
        //1.先转为String
        String jstr = js.toString();
        System.out.println(jstr);
        //2.得到key的下标
        int index = jstr.indexOf(key);
        //3.得到value+后面的字符串
        String str = jstr.substring(index + key.length() + 2);
        if (str.startsWith("{")) {
            //4.如果以{开头，说明是value是个对象，取第一个}结尾的字符串就是value
            str = str.substring(0, str.indexOf("}") + 1);
        } else if (str.startsWith("\"")) {
            //5.如果以"开头，说明value是个String类型，取第一个"结尾的字符串就是value
            int end = str.indexOf("\"", 1);
            str = str.substring(1, end);
        } else if (str.indexOf("\":") > 0) {
            //6.如果是数字，value没有双引号，如果后面有":字符串则说明后面还有key和value，
            //找到第一个,之前的字符串  要么},结尾  要么,结尾
            int end = str.indexOf(",");
            str = str.substring(0, end);
            if (str.endsWith("}")) {
                str = str.substring(0, str.length() - 1);
            }
        } else {
            //7.最后一个值，肯定以}结尾
            str = str.substring(0, str.indexOf("}"));
        }
        return str;
    }

    @Test
    public void test_03() {
        // <<二进制往左移
        int res = 4 << 2;
        System.out.println(res);
    }

    @Test
    public void test_04() {
        City city = new City();
        city.setCityName("长沙");
        city.setId((long) 1);
        String js = JSONObject.toJSONString(city);
        JSONObject j = JSONObject.parseObject(js);
        city = j.toJavaObject(City.class);
        System.out.println(city.getCityName());
    }

}
