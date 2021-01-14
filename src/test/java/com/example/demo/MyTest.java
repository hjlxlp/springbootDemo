package com.example.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.entity.City;
import com.example.demo.entity.User;
import com.example.demo.util.BaseResultModel;
import com.example.demo.util.BeanMapperUtil;
import com.example.demo.vo.*;
import okhttp3.*;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hjl
 * @date 2019/3/14 14:51
 */
public class MyTest {

    @Test
    public void test() {

    }

    @Test
    public void test_fd() {
        List<Integer> sizeList = Arrays.asList(120);
        List<Integer> priceList = Arrays.asList(14000);
        List<Integer> yearList = Arrays.asList(30);
        List<Integer> payList = Arrays.asList(800000);
        List<FdVo> fdList = new ArrayList<>();
        for (Integer size : sizeList) {
            for (Integer price : priceList) {
                for (Integer year : yearList) {
                    for (Integer pay : payList) {
                        fdList.add(new FdVo(size, price, year, pay));
                    }
                }
            }
        }
        DecimalFormat df = new DecimalFormat("#");
        double lilv = 0.05635 / 12;
        for (FdVo vo : fdList) {
            double zongjia = vo.getSize() * vo.getPrice();
            double daikuan = zongjia - vo.getPay();
            double pow = Math.pow(1 + lilv, vo.getYear() * 12);
            double yuegong = daikuan * lilv * pow / (pow - 1);
            double lixi = yuegong * vo.getYear() * 12 - daikuan;
            System.out.println(df.format(vo.getSize()) + "平米，"
                    + "单价" + vo.getPrice() / 10000 + "万，"
                    + "总价" + df.format(zongjia / 10000) + "万，"
                    + "首付" + df.format(vo.getPay() / 10000) + "万，"
                    + "年限" + df.format(vo.getYear()) + "年，"
                    + "贷款" + df.format(daikuan / 10000) + "万，"
                    + "利息" + df.format(lixi / 10000) + "万，"
                    + "月供" + df.format(yuegong) + "元，"
                    + "总花" + df.format((zongjia + lixi) / 10000) + "万。");
        }

    }

    @Test
    public void test_m() {
        //8,17
        Integer b = 10;
        Integer k = 85;
        Integer m;
        Integer e;
        for (int i = 0; i < 13; i++) {
            m = b + i > 12 ? b + i - 12 : b + i;
            if (Arrays.asList(2, 5, 8, 11).contains(m)) {
                e = 6;
            } else {
                e = 15;
            }
            System.out.println(m + ": " + k + "+" + e + "=" + (k = k + e));
        }
    }

    @Test
    public void test17() {

        String str3 = "2020-05-27 15:35:42.185  INFO 22154 --- [0880-thread-198] c.o.s.d.i.pdf.ShowDeliveryServiceImpl    : ===订单发邮件 begin==={\"dataModel\":{\"homeUrl\":\"http://www.onelinkplus.com/\",\"orderDList\":\"<tr><td>1</td><td>甲酸<br/>分析纯_250kg/桶<br/>版本号：-</td><td>7500.00</td><td>重量组（千克）</td><td>2020-06-12</td></tr><tr><td>2</td><td>盐酸羟胺<br/>分析纯_25kg/桶<br/>版本号：-</td><td>800.00</td><td>重量组（千克）</td><td>2020-06-03</td></tr>\",\"orderCode\":\"WBJQDD200527005\",\"purchaseUserName\":\"王家辉\",\"purchaseCompanyName\":\"徐州万邦金桥制药有限公司\",\"supplierCompanyName\":\"成都市科隆化学品有限公司\",\"writer\":\"立即登录一链网<a href=\\\"http://sc-supplier.onelinkplus.com/#/orderDetail?orderId=1436\\\">查看订单</a>\"},\"templateCode\":\"order_email_supplier_delivery\",\"receiverEmail\":\"xiefeng588@163.com\"}\n" +
                "2020-05-27 15:35:42.231  INFO 22154 --- [0880-thread-197] c.o.s.d.i.pdf.ShowDeliveryServiceImpl    : ===订单发邮件 begin==={\"dataModel\":{\"homeUrl\":\"http://www.onelinkplus.com/\",\"orderDList\":\"<tr><td>1</td><td>乙酸锌 分析纯（500ml/瓶）<br/>分析纯（500ml/瓶）<br/>版本号：-</td><td>800.00</td><td>数量组（瓶）</td><td>2020-06-09</td></tr>\",\"orderCode\":\"WBJQDD200527001\",\"purchaseUserName\":\"王家辉\",\"purchaseCompanyName\":\"徐州万邦金桥制药有限公司\",\"supplierCompanyName\":\"上海西陇化工有限公司\",\"writer\":\"立即登录一链网<a href=\\\"http://sc-supplier.onelinkplus.com/#/orderDetail?orderId=1432\\\">查看订单</a>\"},\"templateCode\":\"order_email_supplier_delivery\",\"receiverEmail\":\"375085274@qq.com\"}\n" +
                "2020-05-27 15:35:42.865  INFO 22154 --- [0880-thread-197] c.o.s.d.i.pdf.ShowDeliveryServiceImpl    : ===订单发邮件 begin==={\"dataModel\":{\"homeUrl\":\"http://www.onelinkplus.com/\",\"orderDList\":\"<tr><td>1</td><td>四氢呋喃 180Kg/桶<br/>180Kg/桶<br/>版本号：-</td><td>14400.00</td><td>重量组（千克）</td><td>2020-06-22</td></tr>\",\"orderCode\":\"WBJQDD200527003\",\"purchaseUserName\":\"王家辉\",\"purchaseCompanyName\":\"徐州万邦金桥制药有限公司\",\"supplierCompanyName\":\"成都市科隆化学品有限公司\",\"writer\":\"立即登录一链网<a href=\\\"http://sc-supplier.onelinkplus.com/#/orderDetail?orderId=1434\\\">查看订单</a>\"},\"templateCode\":\"order_email_supplier_delivery\",\"receiverEmail\":\"xiefeng588@163.com\"}\n" +
                "2020-05-27 15:35:43.703  INFO 22154 --- [0880-thread-197] c.o.s.d.i.pdf.ShowDeliveryServiceImpl    : ===订单发邮件 begin==={\"dataModel\":{\"homeUrl\":\"http://www.onelinkplus.com/\",\"orderDList\":\"<tr><td>1</td><td>四氢呋喃 180Kg/桶<br/>180Kg/桶<br/>版本号：-</td><td>14400.00</td><td>重量组（千克）</td><td>2020-06-05</td></tr><tr><td>2</td><td>甲基异丁基甲酮<br/>165kg/桶_108-10-1<br/>版本号：-</td><td>330.00</td><td>JK</td><td>2020-06-03</td></tr>\",\"orderCode\":\"WBJQDD200527002\",\"purchaseUserName\":\"王家辉\",\"purchaseCompanyName\":\"徐州万邦金桥制药有限公司\",\"supplierCompanyName\":\"南京化学试剂股份有限公司\",\"writer\":\"立即登录一链网<a href=\\\"http://sc-supplier.onelinkplus.com/#/orderDetail?orderId=1433\\\">查看订单</a>\"},\"templateCode\":\"order_email_supplier_delivery\",\"receiverEmail\":\"1561155157@qq.com\"}\n" +
                "2020-05-27 15:40:31.345  INFO 22154 --- [0880-thread-197] c.o.s.d.i.pdf.ShowDeliveryServiceImpl    : ===订单发邮件 begin==={\"dataModel\":{\"homeUrl\":\"http://www.onelinkplus.com/\",\"orderDList\":\"<tr><td>1</td><td>甲酸<br/>分析纯_250kg/桶<br/>版本号：-</td><td>7500.00</td><td>重量组（千克）</td><td>2020-06-02</td></tr>\",\"orderCode\":\"WBJQDD200527004\",\"purchaseUserName\":\"王家辉\",\"purchaseCompanyName\":\"徐州万邦金桥制药有限公司\",\"supplierCompanyName\":\"南京化学试剂股份有限公司\",\"writer\":\"立即登录一链网<a href=\\\"http://sc-supplier.onelinkplus.com/#/orderDetail?orderId=1435\\\">查看订单</a>\"},\"templateCode\":\"order_email_supplier_delivery\",\"receiverEmail\":\"1561155157@qq.com\"}";

        //String orderCodes = "WBJQDD200518001,WBYYDD200518001,WBYYDD200518004,WBJQDD200518004,WBJQDD200519001,FXXTDD200519001,FXXTDD200519002,FXXTDD200519003,FXXTDD200519004,FXXTDD200519005,FXXTDD200519006,WBYYDD200520007,WBYYDD200520008,WBYYDD200520009,WBYYDD200520010,FXXTDD200521009,FXXTDD200521010,WBJQDD200521001,WBYYDD200521005,WBYYDD200521007,WBYYDD200521008,WBYYDD200521009,WBYYDD200521010,WBYYDD200521011,WBYYDD200521012,WBYYDD200521014,WBYYDD200521016,FXXTDD200522001,FXXTDD200522003,FXXTDD200522006,WBYYDD200522001,FXXTDD200522007,WBYYDD200522003,WBYYDD200522004,WBYYDD200522005,WBYYDD200522006,WBYYDD200522007,WBYYDD200522008,WBYYDD200522009,WBYYDD200522010,WBYYDD200525001,WBYYDD200525005,WBYYDD200525006,WBYYDD200525008,WBYYDD200525009,WBYYDD200526001,WBYYDD200526002,WBJQDD200526001,WBYYDD200526003,WBYYDD200526004,WBYYDD200526005,WBKJDD200526001,WBKJDD200526002,WBYYDD200526006,WBJQDD200526002,WBYYDD200527001,WBYYDD200527002,WBYYDD200527003,WBYYDD200527004,WBYYDD200527005,WBYYDD200527006";

        String orderCodes = "WBJQDD200527001,WBJQDD200527002,WBJQDD200527003,WBJQDD200527004,WBJQDD200527005";

        String[] str = orderCodes.split(",");

        Map<String, String> codeMap = new HashMap<>();

        for (int i = 0; i < str.length; i++) {
            if (str3.contains(str[i])) {
                Integer orderCodeIndex = str3.indexOf(str[i]);
                String s1 = str3.substring(0, orderCodeIndex);
                Integer infoIndex = s1.lastIndexOf("INFO");
                String date = s1.substring(infoIndex - 25, infoIndex - 6);
                codeMap.put(str[i], date);
            }
        }

        /*for (int i = 0; i < str.length; i++) {
            if (str1.contains(str[i])) {
                Integer orderCodeIndex = str1.indexOf(str[i]);
                String s1 = str1.substring(0, orderCodeIndex);
                Integer infoIndex = s1.lastIndexOf("INFO");
                String date = s1.substring(infoIndex - 25, infoIndex - 6);
                codeMap.put(str[i], date);
            }
            if (str2.contains(str[i])) {
                Integer orderCodeIndex = str2.indexOf(str[i]);
                String s2 = str2.substring(0, orderCodeIndex);
                Integer infoIndex = s2.indexOf("INFO");
                String date = s2.substring(infoIndex - 25, infoIndex - 6);
                codeMap.put(str[i], date);
            }
        }*/

        System.out.println(codeMap.size());
        System.out.println(JSON.toJSONString(codeMap));

    }


    @Test
    public void test_16() {
        ExcelTestVo vo1 = new ExcelTestVo(1, "vo1", new BigDecimal(1), new Date());
        ExcelTestVo vo2 = new ExcelTestVo(2, "vo3", new BigDecimal(4), new Date());
        ExcelTestVo vo3 = new ExcelTestVo(3, "vo2", new BigDecimal(5), new Date());
        List<ExcelTestVo> list = Arrays.asList(vo1, vo2, vo3);

        String fileName = "D://test.xlsx";
        EasyExcel.write(fileName, ExcelTestVo.class).sheet("模板").doWrite(list);
    }

    @Test
    public void test_15() {
        City c = new City();
        c.setCityName("11");
        System.out.println(JSON.toJSONString(c));
        System.out.println(JSON.toJSONString(c, SerializerFeature.WriteMapNullValue));

    }

    public static String get(String url, Map<String, String> headerMap) {
        if (url == null) {
            return null;
        }

        Request request;
        if (headerMap == null) {
            request = new Request.Builder().url(url).get().build();
        } else {
            Request.Builder builder = new Request.Builder();
            for (String key : headerMap.keySet()) {
                builder.addHeader(key, headerMap.get(key));
            }
            request = builder.url(url).get().build();
        }

        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String post(String url, Map<String, String> headerMap, Object reqParam) {
        if (url == null) {
            return null;
        }

        MediaType json = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(json, JSON.toJSONString(reqParam));
        Request request;
        if (headerMap == null) {
            request = new Request.Builder().url(url).post(body).build();
        } else {
            Request.Builder builder = new Request.Builder();
            for (String key : headerMap.keySet()) {
                builder.addHeader(key, headerMap.get(key));
            }
            request = builder.url(url).post(body).build();
        }

        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Test
    public void test_14() throws IOException {
        City city = new City();
        city.setCityName("testInsert");
        String url = "http://localhost:8080/city/findById?id=1";
        //String url = "http://localhost:8080/city/insert";
        Map<String, String> map = new HashMap<>();
        map.put("aaa", "aaa");
        map.put("bbb", "bbb");
        String str = get(url, map);
        System.out.println(str);
        City result = JSON.parseObject(str, City.class);
        System.out.println(result);
    }


    @Test
    public void test_13() {
        List<Wife> wifes = new ArrayList<>();
        List<Husband> husbands = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            wifes.add(new Wife(i, i + "的妻子", "000" + i, null));
        }
        for (int i = 0; i < 10000; i++) {
            husbands.add(new Husband(i, "我是" + i, "000" + i, null));
        }

        LocalTime time1 = LocalTime.now();
        for (int i = 0; i < wifes.size(); i++) {
            for (int j = 0; j < husbands.size(); j++) {
                if (wifes.get(i).getCode().equals(husbands.get(i).getCode())) {
                    //wifes.get(i).setHusbandId(husbands.get(i).getId());
                    husbands.get(i).setWifeId(wifes.get(i).getId());
                }
            }
        }
        System.out.println("time1:" + Duration.between(time1, LocalTime.now()).toMillis());

        LocalTime time2 = LocalTime.now();
        wifes.forEach(a -> {
            husbands.forEach(b -> {
                if (a.getCode().equals(b.getCode())) {
                    //a.setHusbandId(b.getId());
                    b.setWifeId(a.getId());
                }
            });
        });
        System.out.println("time2:" + Duration.between(time2, LocalTime.now()).toMillis());

        LocalTime time3 = LocalTime.now();
        Map<String, Wife> wifeMap = wifes.stream().collect(Collectors.toMap(a -> a.getCode(), a -> a));
        husbands.stream().forEach(a -> {
            a.setWifeId(wifeMap.get(a.getCode()).getId());
        });
        System.out.println("time3:" + Duration.between(time3, LocalTime.now()).toMillis());

    }

    @Test
    public void test_12() {
        int n = 16;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println(n);
    }

    @Test
    public void test_11() {
        User u1;
        User u2;
        u1 = new User();
        u1.setId(1);
        u1.setName("1");
        u2 = u1;
        u2.setName("2");
        System.out.println();
    }

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
        reqDtoList = new ArrayList<>();
        ExpWarehousingDetailsOpenReqDto exp = new ExpWarehousingDetailsOpenReqDto();
        reqDtoList.add(exp);
        reqDtoList.add(exp);
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
                    ChineseFieldName ChineseFieldName = field.getAnnotation(ChineseFieldName.class);
                    if (ChineseFieldName != null) {
                        Object value = field.get(obj);
                        if (value == null || value == "") {
                            message.append("第" + (i + 1) + "行的" + ChineseFieldName.value() + "不能为空。");
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
