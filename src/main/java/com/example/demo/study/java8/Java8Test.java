package com.example.demo.study.java8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author hjl
 * @date 2019/11/6 10:47
 */
public class Java8Test {

    @Test
    public void test1() {
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());
        System.out.println(clock.instant());
        System.out.println(Date.from(clock.instant()));
        System.out.println(ZoneId.getAvailableZoneIds());
    }

    @Test
    public void test2() {
        LocalTime now1 = LocalTime.now();
        LocalTime now2 = LocalTime.now();
        System.out.println(now1.isAfter(now2));
        System.out.println(ChronoUnit.HOURS.between(now1,now2));
        System.out.println(ChronoUnit.MINUTES.between(now1,now2));
        System.out.println(LocalTime.of(15,32,12));
        DateTimeFormatter germanFormatter = DateTimeFormatter
                        .ofLocalizedTime(FormatStyle.SHORT)
                        .withLocale(Locale.GERMAN);
        System.out.println(LocalTime.parse("13:37", germanFormatter));
    }

    @Test
    public void test3() {
        LocalDate today = LocalDate.now();
        System.out.println("今天的日期: "+today);
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        System.out.println("明天的日期: "+tomorrow);
        LocalDate yesterday = tomorrow.minusDays(2);
        System.out.println("昨天的日期: "+yesterday);
        LocalDate independenceDay = LocalDate.of(2019, Month.MARCH, 12);
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        System.out.println("今天是周几:"+dayOfWeek);

        String str1 = "2014==04==12 01时06分09秒";
        // 根据需要解析的日期、时间字符串定义解析所用的格式器
        DateTimeFormatter fomatter1 = DateTimeFormatter
                .ofPattern("yyyy==MM==dd HH时mm分ss秒");
        LocalDateTime dt1 = LocalDateTime.parse(str1, fomatter1);
        System.out.println(dt1);
        String str2 = "2014$$$四月$$$13 20小时";
        DateTimeFormatter fomatter2 = DateTimeFormatter.ofPattern("yyy$$$MMM$$$dd HH小时");
        LocalDateTime dt2 = LocalDateTime.parse(str2, fomatter2);
        DateTimeFormatter fomatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dt2.format(fomatter3));
    }

    @Test
    public void test4() {
        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek);
        Month month = sylvester.getMonth();
        System.out.println(month);
        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);
        Instant instant = sylvester.atZone(ZoneId.systemDefault()).toInstant();
        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);
    }

    @Test
    public void test5() {
        String str = "a,b,,c,,,";
        String[] s = str.split(",");
        System.out.println(s.length);
        List<String> list = new ArrayList<>();
        String[] strings = list.toArray(new String[0]);
    }

}
