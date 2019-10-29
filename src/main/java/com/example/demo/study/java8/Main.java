package com.example.demo.study.java8;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

/**
 * @author hjl
 * @date 2019/10/29 10:14
 */
public class Main {
    static int num2;

    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>();
        stringList.add("ddd2");
        stringList.add("aaa2");
        stringList.add("bbb1");
        stringList.add("aaa1");
        stringList.add("bbb3");
        stringList.add("ccc");
        stringList.add("bbb2");
        stringList.add("ddd1");

        stringList.stream().map(String::toUpperCase)
                .sorted(comparing(String::toString).reversed())
                .forEach(System.out::println);
        System.out.println(stringList.stream().anyMatch(a -> a.startsWith("a")));
        System.out.println(stringList.stream().filter(a -> a.startsWith("a")).count());
        stringList.stream().reduce((s1, s2) -> s1 + "#" + s2).ifPresent(System.out::println);

        System.out.println(Stream.of("A", "B", "C", "D").reduce("", String::concat));
        System.out.println(Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min));
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 2).forEach(System.out::println);

        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        System.out.println(formula.calculate(100));
        System.out.println(formula.sqrt(16));

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        names.sort((a, b) -> b.compareTo(a));
        names.sort(comparing(String::toString).reversed());
        System.out.println(names);

        final int num1 = 1;
        Converter<String, Integer> converter = (f) -> Integer.valueOf(f) + num1 + num2;
        Integer converted = converter.convert("1");
        System.out.println(converted.getClass() + ":" + converted);
        //num = 3;

        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");

        Supplier<Person> personSupplier = Person::new;
        System.out.println(personSupplier.get());

        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");
        System.out.println(comparator.compare(p1, p2));
        System.out.println(comparator.reversed().compare(p1, p2));

        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(1, 2),
                Arrays.asList(2, 3, 4)
        );
        Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
        outputStream.distinct().forEach(System.out::println);
        //forEach已经消耗掉了上面的流
        //outputStream.forEach(System.out::println);

    }

}
