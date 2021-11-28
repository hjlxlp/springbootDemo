package com.example.demo.test.Java8;

import com.example.demo.test.Java8.vo.Area;
import com.example.demo.test.Java8.vo.City;
import com.example.demo.test.Java8.vo.Province;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author huangjiale
 * @date 2021/11/28 14:14
 */
public class Java8Test {

    public static void main(String[] args) {
        //test01();
        //test02();
        //test03();
        //test04();
        //test05();
        test06();
    }

    /**
     * Lambda代码演示
     */
    private static void test01() {
        List<Integer> idList = Arrays.asList(4, 5, 3, 1, 2);

        // 不使用lambda表达式
        Collections.sort(idList, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a.compareTo(b);
            }
        });

        // ( ) 替换匿名内部类
        Collections.sort(idList, (Integer a, Integer b) -> {
            return b.compareTo(a);
        });

        // 一行代码可以不写 { }
        Collections.sort(idList, (Integer a, Integer b) -> b.compareTo(a));

        // 类型推断机制，参数可以不用写类型
        Collections.sort(idList, (a, b) -> b.compareTo(a));

        // 输出结果：1，2，3，4，5
        System.out.println(idList);
    }

    /**
     * 内置函数式接口代码演示
     */
    private static void test02() {
        // Consumer 消费型
        Consumer<String> consumer = (t) -> {
            System.out.println("上菜，吃饭，结账。收入：" + t);
        };
        consumer.accept("1000元");

        // Supplier 生产型
        Supplier<String> supplier = () -> {
            return "无情的造钱机器工作中：获得1000元";
        };
        System.out.println(supplier.get());

        // Function 函数型
        Function<String, String> function = (t) -> {
            return "6000人民币";
        };
        System.out.println(function.apply("1000美元"));

        // Predicate 断言型
        Predicate<Integer> predicate = (t) -> {
            return t > 1000;
        };
        System.out.println(predicate.test(1000));
    }

    /**
     * 自定义函数式接口代码演示
     */
    @FunctionalInterface
    public interface MyFunc<T> {
        T getMoney(T t);
    }

    private static void test03() {
        // 实现该函数式接口，方法里面逻辑自定义
        MyFunc<Integer> myFunc = (t) -> {
            System.out.println("插入银行卡前余额：" + t);
            t = t - 1000;
            System.out.println("插入银行卡后余额：" + t);
            return t;
        };

        // 输出结果
        System.out.println(myFunc.getMoney(10000));
    }

    /**
     * Optional代码演示
     */
    private static Province getProvince() {
        // 初始化数据
        Area area = new Area();
        area.setAreaName("临川区");
        area.setManName("黄大帅");
        City city = new City();
        city.setCityName("抚州市");
        city.setArea(area);
        Province province = new Province();
        province.setProvinceName("江西省");
        province.setCity(city);
        return province;
    }

    private static void test04() {
        Province province = getProvince();

        // 不使用Optional
        if (province != null) {
            City city = province.getCity();
            if (city != null) {
                Area area = city.getArea();
                if (area != null) {
                    System.out.println(area.getManName());
                }
            }
        }

        // 使用 Optional
        String manName = Optional.ofNullable(province)
                .map(p -> p.getCity())
                .map(c -> c.getArea())
                .map(a -> a.getManName())
                .orElse("");
        System.out.println(manName);
    }

    /**
     * 新的日期API代码演示
     */
    private static void test05() {

        // Instant
        Instant instant = Instant.now();
        System.out.println(instant);
        System.out.println(instant.atZone(ZoneId.systemDefault()));

        // LocalDate
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate:" + localDate);
        System.out.println("year: " + localDate.getYear());
        System.out.println("month: " + localDate.getMonthValue());
        System.out.println("day: " + localDate.getDayOfMonth());
        LocalDate localDate1 = LocalDate.of(2022, 1, 1);
        System.out.println(localDate1);

        // LocalTime
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime:" + localTime);
        System.out.println("hour: " + localTime.getHour());
        System.out.println("minute: " + localTime.getMinute());
        System.out.println("second: " + localTime.getSecond());
        LocalTime localTime1 = LocalTime.of(18, 0, 0);
        System.out.println(localTime1);

        // LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime: " + localDateTime);
        // 年月日时分秒...
        LocalDateTime localDateTime1 = LocalDateTime.of(2021, 12, 1, 18, 0, 0);
        System.out.println("localDateTime1: " + localDateTime1);

        // DateTimeFormatter
        System.out.println(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println(localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // Duration 和 Period
        System.out.println("Duration：" + Duration.between(localDateTime, localDateTime1).toMinutes());
        System.out.println("Period：" + Period.between(localDate, localDate1).getDays());

        // Instant和Date的转换
        Date date = Date.from(instant);
        System.out.println("Date：" + date);
        System.out.println("Instant：" + date.toInstant());

        // LocalDateTime转Date
        Date date2 = new Date();
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println("Date：" + date2);
        System.out.println("LocalDateTime：" + localDateTime2);

        // Date转LocalDateTime
        LocalDateTime localDateTime3 = LocalDateTime.now();
        Date date3 = Date.from(localDateTime3.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("LocalDateTime：" + localDateTime3);
        System.out.println("Date：" + date3);
    }

    /**
     *
     */
    private static void test06() {

    }

}
