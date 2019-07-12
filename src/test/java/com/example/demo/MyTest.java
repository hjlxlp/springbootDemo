package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.*;
import com.example.demo.util.BaseResultModel;
import com.example.demo.util.BeanMapperUtil;
import com.example.demo.vo.DocumentType;
import com.example.demo.vo.ExpWarehousingDetailsOpenReqDto;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hjl
 * @date 2019/3/14 14:51
 */
public class MyTest {

    public String test(DocumentType type) {
        return type.toString();
    }

    @Test
    public void test_10() {
        System.out.println(test(DocumentType.FH));
        System.out.println(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));
        System.out.println(String.format("%0" + 3 + "d", 12));

        String serialNumber = "";
        //根据企业id获取企业缩写
        String abbreviationStr = "COM";
        //单据类型
        String documentTypeStr = "DD";
        //日期
        String dateStr = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now());
        //序号
        serialNumber = abbreviationStr + documentTypeStr + dateStr;
        String num = "12";
        String numStr = String.format("%0" + 3 + "d", Integer.parseInt(num));
        serialNumber = serialNumber + numStr;
        System.out.println(serialNumber);
    }

    @Test
    public void test_09() {
        String str = "[\n" +
                "{\n" +
                "\"descriptionLineTwo\": \"\",\n" +
                "\"receiveQuantity\": \"6000\",\n" +
                "\"descriptionHead\": \"\",\n" +
                "\"receiveUserName\": \"陈雯婷\",\n" +
                "\"outerPurchaseProductName\": \"培美小盒（巴基斯坦版）\",\n" +
                "\"outerOrderCode\": \"05-CGRK-201906-0167\",\n" +
                "\"purchaseDepartmentName\": \"上海凯茂生物医药有限公司\",\n" +
                "\"warehouse\": \"05.002;凯茂-包材库\",\n" +
                "\"outerSupplierCompanyName\": \"上海浦东自立彩印厂有限公司\",\n" +
                "\"unit\": \"数量组（只）\",\n" +
                "\"outerPurchaseProductCode\": \"04.13.447\",\n" +
                "\"purchaseDepartmentCode\": \"050000\",\n" +
                "\"receiveTime\": \"2019-07-02 00:00:00\",\n" +
                "\"orderLineNumber\": \"1\",\n" +
                "\"outerSupplierCompanyCode\": \"31.0342\",\n" +
                "\"descriptionLineOne\": \"\"\n" +
                "},\n" +
                "{\n" +
                "\"descriptionLineTwo\": \"\",\n" +
                "\"receiveQuantity\": \"5000\",\n" +
                "\"descriptionHead\": \"\",\n" +
                "\"receiveUserName\": \"陈雯婷\",\n" +
                "\"outerPurchaseProductName\": \"培美说明书（巴基斯坦版）\",\n" +
                "\"outerOrderCode\": \"05-CGRK-201906-0167\",\n" +
                "\"purchaseDepartmentName\": \"上海凯茂生物医药有限公司\",\n" +
                "\"warehouse\": \"05.002;凯茂-包材库\",\n" +
                "\"outerSupplierCompanyName\": \"上海浦东自立彩印厂有限公司\",\n" +
                "\"unit\": \"数量组（张）\",\n" +
                "\"outerPurchaseProductCode\": \"04.12.234\",\n" +
                "\"purchaseDepartmentCode\": \"050000\",\n" +
                "\"receiveTime\": \"2019-07-02 00:00:00\",\n" +
                "\"orderLineNumber\": \"2\",\n" +
                "\"outerSupplierCompanyCode\": \"31.0342\",\n" +
                "\"descriptionLineOne\": \"\"\n" +
                "}\n" +
                "]";
        List<ExpWarehousingDetailsOpenReqDto> reqDtoList = JSONObject.parseArray(str, ExpWarehousingDetailsOpenReqDto.class);
        System.out.println(reqDtoList);
        List<Object> list = new ArrayList<>();
        reqDtoList.forEach(a -> {
            list.add(a);
        });
        StringBuffer sb = isNull(list);
        System.out.println(sb);
    }

    private StringBuffer isNull(List<Object> list) {
        StringBuffer message = new StringBuffer();
        if (CollectionUtils.isEmpty(list)) {
            message.append("list数据为空");
            return message;
        }
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            try {
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    ChineseName chineseName = field.getAnnotation(ChineseName.class);
                    if (chineseName != null) {
                        Object value = field.get(obj);
                        if (value == null || value == "") {
                            message.append("第" + (i + 1) + "行的" + chineseName.value() + "不能为空。");
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
    public void test_08() {
        String str = "110110";
        String[] num = new String[str.length()];
        for (int i = 0; i < str.length(); i++) {
            num[i] = str.substring(i, 1);
        }
    }

    @Test
    public void test07() {
        TestVoOne testVoOne = new TestVoOne();
        testVoOne.setOrderId(1);
        testVoOne.setCreateTime(LocalDateTime.now());
        TestVoTwo testVoTwo = BeanMapperUtil.objConvert(testVoOne, TestVoTwo.class);
        System.out.println(testVoTwo);
    }

    @Test
    public void test06() {
        BaseResultModel<TestVo> resultModel = new BaseResultModel<>();
        resultModel.setError(10010, "参数为空");
        BaseResultModel<City> model = new BaseResultModel<>();
        model.setError(resultModel.getCode(), resultModel.getErrorMsg());
        System.out.println(model.getErrorMsg());
    }


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
        List<TestVo> list = new ArrayList<>();
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

        List<Object> objectList = new ArrayList<>();
        objectList.addAll(list);
        //StringBuffer message = isNull(objectList);
        //System.out.println(message);

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
        /*City city = new City();
        city.setCityName(null);
        String js = JSONObject.toJSONString(city);
        JSONObject j = JSONObject.parseObject(js);
        City c = j.toJavaObject(City.class);
        System.out.println(JSONObject.toJSONString(c));
        c.setCityName("null");
        System.out.println(JSONObject.toJSONString(c));*/
    }

    @Test
    public void test_05() {
        List<City> list = new ArrayList<>();
        City city1 = new City();
        city1.setCityName("null");
        City city = new City();
        list.add(city);
        list.add(city1);
        List<String> brandNames = list.stream().map(City::getCityName).filter(a -> a != null).filter(Objects::nonNull).distinct().collect(Collectors.toList());
        //System.out.println(brandNames.get(0) == null);
        //System.out.println(brandNames.get(1) == null);
        String s = null;
        System.out.println(s == null);
        List<String> ls = new ArrayList<>();
        list.get(0).setCityName(String.join("/", s == null ? null : s));
        //Optional.ofNullable(brandNames).ifPresent(b -> list.get(0).setCityName(String.join("/", s)));
        //Optional.ofNullable(brandNames).ifPresent(b -> list.get(1).setCityName(String.join("/", s)));
        //list.get(0).setCityName(String.join("/", brandNames));
        System.out.println(JSONObject.toJSONString(list));
        List<String> l = new ArrayList<>();
    }

}
