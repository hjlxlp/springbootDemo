package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.*;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hjl
 * @date 2019/3/14 14:51
 */
public class MyTest {

    @Test
    public void test05() {


        String str = "[\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90596\",\n" +
                "\"purchaseCompanyName\": \"asdasd\",\n" +
                "\"categoryNameOne\": \"时光正好\",\n" +
                "\"categoryNameTwo\": \"时光正好A\",\n" +
                "\"categoryNameThree\": \"时光正好a\",\n" +
                "\"total\": 1000000\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90503\",\n" +
                "\"purchaseCompanyName\": \"万利有限公司\",\n" +
                "\"categoryNameOne\": \"测试1\",\n" +
                "\"categoryNameTwo\": \"测试1.1\",\n" +
                "\"categoryNameThree\": \"测试1.1.1\",\n" +
                "\"total\": 1000063646.32\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90503\",\n" +
                "\"purchaseCompanyName\": \"万利有限公司\",\n" +
                "\"categoryNameOne\": \"时光正好\",\n" +
                "\"categoryNameTwo\": \"时光正好A\",\n" +
                "\"categoryNameThree\": \"时光正好a\",\n" +
                "\"total\": 402010000\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90503\",\n" +
                "\"purchaseCompanyName\": \"万利有限公司\",\n" +
                "\"categoryNameOne\": \"包材\",\n" +
                "\"categoryNameTwo\": \"内包材\",\n" +
                "\"categoryNameThree\": \"塑料瓶\",\n" +
                "\"total\": 26666400\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90503\",\n" +
                "\"purchaseCompanyName\": \"万利有限公司\",\n" +
                "\"categoryNameOne\": \"辅料\",\n" +
                "\"categoryNameTwo\": \"药用级辅料\",\n" +
                "\"categoryNameThree\": \"胶囊\",\n" +
                "\"total\": 11114320\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90503\",\n" +
                "\"purchaseCompanyName\": \"万利有限公司\",\n" +
                "\"categoryNameOne\": \"哎呀呀\",\n" +
                "\"categoryNameTwo\": \"哎呀呀\",\n" +
                "\"categoryNameThree\": \"哎呀呀\",\n" +
                "\"total\": 401000\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90503\",\n" +
                "\"purchaseCompanyName\": \"万利有限公司\",\n" +
                "\"categoryNameOne\": \"不要哭了吗\",\n" +
                "\"categoryNameTwo\": \"该哭的是我吗\",\n" +
                "\"categoryNameThree\": \"明天开始吧\",\n" +
                "\"total\": 117700\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90503\",\n" +
                "\"purchaseCompanyName\": \"万利有限公司\",\n" +
                "\"categoryNameOne\": \"协同\",\n" +
                "\"categoryNameTwo\": \"协同测试\",\n" +
                "\"categoryNameThree\": \"新增协同商品\",\n" +
                "\"total\": 88300\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90503\",\n" +
                "\"purchaseCompanyName\": \"万利有限公司\",\n" +
                "\"categoryNameOne\": \"试剂及耗材\",\n" +
                "\"categoryNameTwo\": \"剧毒品/易制毒试剂\",\n" +
                "\"categoryNameThree\": \"优级纯\",\n" +
                "\"total\": 22241\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90503\",\n" +
                "\"purchaseCompanyName\": \"万利有限公司\",\n" +
                "\"categoryNameOne\": \"试剂及耗材\",\n" +
                "\"categoryNameTwo\": \"剧毒品/易制毒试剂\",\n" +
                "\"categoryNameThree\": \"分析纯\",\n" +
                "\"total\": 15816.5\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90503\",\n" +
                "\"purchaseCompanyName\": \"万利有限公司\",\n" +
                "\"categoryNameOne\": \"试剂及耗材\",\n" +
                "\"categoryNameTwo\": \"无机分类试剂\",\n" +
                "\"categoryNameThree\": \"分析纯\",\n" +
                "\"total\": 9936\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90503\",\n" +
                "\"purchaseCompanyName\": \"万利有限公司\",\n" +
                "\"categoryNameOne\": \"包材\",\n" +
                "\"categoryNameTwo\": \"印刷包材\",\n" +
                "\"categoryNameThree\": \"1431\",\n" +
                "\"total\": 8860\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90503\",\n" +
                "\"purchaseCompanyName\": \"万利有限公司\",\n" +
                "\"categoryNameOne\": \"时光正好\",\n" +
                "\"categoryNameTwo\": \"时光正好B\",\n" +
                "\"categoryNameThree\": \"时光正好b\",\n" +
                "\"total\": 6000\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90503\",\n" +
                "\"purchaseCompanyName\": \"万利有限公司\",\n" +
                "\"categoryNameOne\": \"试剂及耗材\",\n" +
                "\"categoryNameTwo\": \"基准物质\",\n" +
                "\"categoryNameThree\": \"色谱纯\",\n" +
                "\"total\": 5184\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90503\",\n" +
                "\"purchaseCompanyName\": \"万利有限公司\",\n" +
                "\"categoryNameOne\": \"时光正好\",\n" +
                "\"categoryNameTwo\": \"时光正好A\",\n" +
                "\"categoryNameThree\": \"时光正好a\",\n" +
                "\"total\": 3342.56\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90503\",\n" +
                "\"purchaseCompanyName\": \"万利有限公司\",\n" +
                "\"categoryNameOne\": \"成品制药\",\n" +
                "\"categoryNameTwo\": \"非处方药\",\n" +
                "\"categoryNameThree\": \"消炎药\",\n" +
                "\"total\": 1150\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90503\",\n" +
                "\"purchaseCompanyName\": \"万利有限公司\",\n" +
                "\"categoryNameOne\": \"明智之举\",\n" +
                "\"categoryNameTwo\": \"怀疑自己\",\n" +
                "\"categoryNameThree\": \"醇厚赐予\",\n" +
                "\"total\": 13\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90503\",\n" +
                "\"purchaseCompanyName\": \"万利有限公司\",\n" +
                "\"categoryNameOne\": \"不要哭了吗\",\n" +
                "\"categoryNameTwo\": \"该哭的是我吗\",\n" +
                "\"categoryNameThree\": \"该哭的是我吗\",\n" +
                "\"total\": 10.22\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"95743\",\n" +
                "\"purchaseCompanyName\": \"可可家里（北京）有限公司\",\n" +
                "\"categoryNameOne\": \"萌力觉醒发撒发生大的萨达撒范德\",\n" +
                "\"categoryNameTwo\": \"混沌澡泽\",\n" +
                "\"categoryNameThree\": \"78624\",\n" +
                "\"total\": 40000\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90493\",\n" +
                "\"purchaseCompanyName\": \"合肥可人信息有限公司\",\n" +
                "\"categoryNameOne\": \"试剂及耗材\",\n" +
                "\"categoryNameTwo\": \"剧毒品/易制毒试剂\",\n" +
                "\"categoryNameThree\": \"分析纯\",\n" +
                "\"total\": 148063\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90493\",\n" +
                "\"purchaseCompanyName\": \"合肥可人信息有限公司\",\n" +
                "\"categoryNameOne\": \"时光正好\",\n" +
                "\"categoryNameTwo\": \"时光正好B\",\n" +
                "\"categoryNameThree\": \"时光正好b\",\n" +
                "\"total\": 60000\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90493\",\n" +
                "\"purchaseCompanyName\": \"合肥可人信息有限公司\",\n" +
                "\"categoryNameOne\": \"试剂及耗材\",\n" +
                "\"categoryNameTwo\": \"基准物质\",\n" +
                "\"categoryNameThree\": \"色谱纯\",\n" +
                "\"total\": 360\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90493\",\n" +
                "\"purchaseCompanyName\": \"合肥可人信息有限公司\",\n" +
                "\"categoryNameOne\": \"试剂及耗材\",\n" +
                "\"categoryNameTwo\": \"无机分类试剂\",\n" +
                "\"categoryNameThree\": \"分析纯\",\n" +
                "\"total\": 237\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"90493\",\n" +
                "\"purchaseCompanyName\": \"合肥可人信息有限公司\",\n" +
                "\"categoryNameOne\": \"试剂及耗材\",\n" +
                "\"categoryNameTwo\": \"剧毒品/易制毒试剂\",\n" +
                "\"categoryNameThree\": \"优级纯\",\n" +
                "\"total\": 43\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"95744\",\n" +
                "\"purchaseCompanyName\": \"滴滴（中国）科技有限公司\",\n" +
                "\"categoryNameOne\": \"试剂及耗材\",\n" +
                "\"categoryNameTwo\": \"剧毒品/易制毒试剂\",\n" +
                "\"categoryNameThree\": \"分析纯\",\n" +
                "\"total\": 1281\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"95744\",\n" +
                "\"purchaseCompanyName\": \"滴滴（中国）科技有限公司\",\n" +
                "\"categoryNameOne\": \"试剂及耗材\",\n" +
                "\"categoryNameTwo\": \"无机分类试剂\",\n" +
                "\"categoryNameThree\": \"分析纯\",\n" +
                "\"total\": 316\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"95744\",\n" +
                "\"purchaseCompanyName\": \"滴滴（中国）科技有限公司\",\n" +
                "\"categoryNameOne\": \"试剂及耗材\",\n" +
                "\"categoryNameTwo\": \"基准物质\",\n" +
                "\"categoryNameThree\": \"色谱纯\",\n" +
                "\"total\": 36\n" +
                "},\n" +
                "{\n" +
                "\"purchaseCompanyId\": \"95744\",\n" +
                "\"purchaseCompanyName\": \"滴滴（中国）科技有限公司\",\n" +
                "\"categoryNameOne\": \"试剂及耗材\",\n" +
                "\"categoryNameTwo\": \"剧毒品/易制毒试剂\",\n" +
                "\"categoryNameThree\": \"优级纯\",\n" +
                "\"total\": 22\n" +
                "}\n" +
                "]";

        List<AmountCategoryOutputVo> list = JSONObject.parseArray(str, AmountCategoryOutputVo.class);
        final List<AmountCategoryReportDetailsOutputVo> list1 = new ArrayList<>();
        Map<String, List<AmountCategoryOutputVo>> map1 = list.stream().collect(Collectors.groupingBy(AmountCategoryOutputVo::getCategoryNameOne));
        map1.forEach((key, value) -> {
            AmountCategoryReportDetailsOutputVo vo = new AmountCategoryReportDetailsOutputVo();
            vo.setCategoryName(key);
            vo.setTotal(value.stream().map(AmountCategoryOutputVo::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add));
            list1.add(vo);
        });
        List<AmountCategoryReportDetailsOutputVo> list11 = list1.stream().sorted(Comparator.comparing(AmountCategoryReportDetailsOutputVo::getTotal).reversed()).collect(Collectors.toList());
        System.out.println(JSONObject.toJSON(list11));
    }

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
