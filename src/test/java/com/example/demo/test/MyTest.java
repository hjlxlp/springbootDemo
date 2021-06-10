package com.example.demo.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.entity.City;
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
        //12,21
        Integer b = 10;
        Integer k = 0;
        Integer m;
        Integer e;
        for (int i = 0; i < 12; i++) {
            m = b + i > 12 ? b + i - 12 : b + i;
            if (Arrays.asList(2, 5, 8, 11).contains(m)) {
                e = 12;
            } else {
                e = 21;
            }
            System.out.println(m + ": " + k + "+" + e + "=" + (k = k + e));
        }
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
        for (int i = 0; i < 100000; i++) {
            wifes.add(new Wife(i + 10, i + "的妻子", "000" + i, null));
        }
        for (int i = 0; i < 100000; i++) {
            husbands.add(new Husband(i, "我是" + i, "000" + i, null));
        }

        /*LocalTime time1 = LocalTime.now();
        for (int i = 0; i < wifes.size(); i++) {
            for (int j = 0; j < husbands.size(); j++) {
                if (wifes.get(i).getCode().equals(husbands.get(i).getCode())) {
                    husbands.get(i).setWifeId(wifes.get(i).getId());
                }
            }
        }
        System.out.println("time1:" + Duration.between(time1, LocalTime.now()).toMillis());*/

        LocalTime time3 = LocalTime.now();
        Map<String, Wife> wifeMap = wifes.stream().collect(Collectors.toMap(a -> a.getCode(), a -> a));
        husbands.forEach(a -> {
            a.setWifeId(wifeMap.get(a.getCode()).getId());
        });
        System.out.println("time3:" + Duration.between(time3, LocalTime.now()).toMillis());

    }

    @Test
    public void test_10() {
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


}
