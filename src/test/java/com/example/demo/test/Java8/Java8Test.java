package com.example.demo.test.Java8;

import com.example.demo.test.Java8.vo.Area;
import com.example.demo.test.Java8.vo.City;
import com.example.demo.test.Java8.vo.Cow;
import com.example.demo.test.Java8.vo.Province;
import com.example.demo.util.BeanMapperUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author huangjiale
 * @date 2021/11/28 14:14
 */
public class Java8Test {

	public static void main(String[] args) {
		//test01();
		//test02();
		//test03();
		/*test031();
		eatHotpot1();
		eatHotpot();*/
		//test04();
		//test05();
		//test06();
		test07();
		test08();
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
	 * 函数式接口代码演示
	 */
	//1.写了一个方法，参数是函数式接口，你可以传递Runnable的实现，也可以使用Lambda或方法引用
	public static void execute(Runnable runnable) {
		try {
			runnable.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class UserService {
		public void hello() {
			System.out.println("hello world");
		}

		public void anotherMethod() {
			System.out.println("另一个方法，不影响execute使用hello");
		}
	}

	private static void test031() {
		// 传入匿名对象
		execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("匿名对象");
			}
		});

		// 使用Lambda，()表示Runnable#run()的参数，println()是Runnable#run()的方法体
		execute(() -> System.out.println("使用lambda"));

		// 因为wrapPrintln和上面的println做的是同样的事，可以替换
		UserService userService = new UserService();
		execute(() -> userService.hello());

		// 你会发现上面的写法仍是对的，因为“仅有一个抽象方法”是对Runnable的约束
	}

	static class service {
		static void eatHotpot() {
			System.out.println("吃火锅");
		}
		static void eatBarbecue() {
			System.out.println("吃烧烤");
		}
		static void eatBuffet() {
			System.out.println("吃自助餐");
		}
	}

	public static void eatHotpot1() {
		try {
			System.out.println("拿餐具");
			service.eatHotpot();
			System.out.println("买单");
		} catch (Throwable t) {
			throw t;
		}
	}

	public static void eatBarbecue1() {
		try {
			service.eatBarbecue();
		} catch (Throwable t) {
			throw t;
		}
	}

	public static void eatBuffet1() {
		try {
			service.eatBuffet();
		} catch (Throwable t) {
			throw t;
		}
	}

	private static void executeTest(Runnable runnable) {
		try {
			System.out.println("拿餐具");
			runnable.run();
			System.out.println("买单");
		} catch (Throwable t) {
			throw t;
		}
	}

	public static void eatHotpot() {
		executeTest(() -> service.eatHotpot());
	}

	public static void eatBarbecue() {
		executeTest(() -> service.eatBarbecue());
	}

	public static void eatBuffet() {
		executeTest(() -> service.eatBuffet());
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
	 * 流的获取代码演示
	 */
	private static void test06() {
		// Collection提供的两个方法，stream()与stream()
		List<String> list = new ArrayList<>();
		Stream<String> stream = list.stream();
		Stream<String> parallelStream = list.stream();

		// 通过Arrays的stream()获取一个数组流
		Integer[] nums = new Integer[10];
		Stream<Integer> stream1 = Arrays.stream(nums);

		// 通过Stream类中静态方法of()
		Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5, 6);

		// 创建无限流
		Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2).limit(10);
		stream3.forEach(System.out::println);

		// 生成
		Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
		stream4.forEach(System.out::println);
	}

	/**
	 * 举个例子代码演示
	 * <p>
	 * 年龄小于10。
	 * 产地不要日本的。
	 * 黄色的红烧，其他烧烤。
	 * 只吃肉，不要骨头，不要内脏。
	 * 每个产地的每个性别，只需要一头。
	 * 按重量从大到小排序。
	 * 要前面10头。
	 * 按年龄分组。
	 */
	private static List<Cow> getCowList() {
		List<Cow> cowList = new ArrayList<>();
		Cow c = new Cow();
		c.setOrigin("中国");
		c.setColor("黄色");
		c.setWeight(1000);
		c.setPrice(100);
		c.setAge(5);
		c.setSex("公牛");
		c.setGut(100);
		c.setBones(100);

		for (int i = 0; i < 100; i++) {
			Cow c1 = new Cow();
			BeanUtils.copyProperties(c, c1);
			cowList.add(c1);

			Cow c2 = new Cow();
			BeanUtils.copyProperties(c, c2);
			c2.setAge(1);
			cowList.add(c2);
			Cow c22 = new Cow();
			BeanUtils.copyProperties(c, c22);
			c22.setAge(10);
			cowList.add(c22);


			Cow c3 = new Cow();
			BeanUtils.copyProperties(c, c3);
			c3.setSex("母牛");
			cowList.add(c3);

			Cow c4 = new Cow();
			BeanUtils.copyProperties(c, c4);
			c4.setColor("黑色");
			cowList.add(c4);
			Cow c44 = new Cow();
			BeanUtils.copyProperties(c, c44);
			c44.setColor("蓝色");
			cowList.add(c44);

			Cow c6 = new Cow();
			BeanUtils.copyProperties(c, c6);
			c6.setWeight(2000);
			cowList.add(c6);
			Cow c66 = new Cow();
			BeanUtils.copyProperties(c, c66);
			c66.setWeight(3000);
			cowList.add(c66);
		}

		for (int i = 0; i < cowList.size(); i++) {
			if (i > 0 && i < 100) {
				cowList.get(i).setOrigin("日本");
			}
			if (i > 100 && i < 200) {
				cowList.get(i).setOrigin("俄罗斯");
			}
			if (i > 200 && i < 300) {
				cowList.get(i).setOrigin("英国");
			}
			if (i > 300 && i < 400) {
				cowList.get(i).setOrigin("法国");
			}
			if (i > 400 && i < 500) {
				cowList.get(i).setOrigin("意大利");
			}
			if (i > 500 && i < 600) {
				cowList.get(i).setOrigin("韩国");
			}
			if (i > 600 && i < 700) {
				cowList.get(i).setOrigin("印度");
			}
			if (i > 700 && i < 800) {
				cowList.get(i).setOrigin("西班牙");
			}
		}

		return cowList;
	}

	/**
	 * java7
	 */
	private static void test07() {
		List<Cow> cowList = getCowList();
		LocalDateTime localDateTime = LocalDateTime.now();
		Map<Integer, List<Cow>> resultMap1 = new HashMap<>();
		List<Cow> cowList1 = new ArrayList<>();
		for (Cow a : cowList) {
			if (a.getAge() < 10 && !"日本".equals(a.getOrigin())) {
				Boolean isFlag = true;
				for (Cow cow : cowList1) {
					if ((a.getOrigin() + ":" + a.getSex()).equals(cow.getOrigin() + ":" + cow.getSex())) {
						isFlag = false;
						break;
					}
				}
				if (isFlag) {
					a.setTaste("黄色".equals(a.getColor()) ? "红烧" : "烧烤");
					a.setWeight(a.getWeight() - a.getGut() - a.getBones());
					cowList1.add(a);
				}
			}
		}
		Collections.sort(cowList1, new Comparator<Cow>() {
			@Override
			public int compare(Cow o1, Cow o2) {
				return o1.getWeight() > o2.getWeight() ? 0 : 1;
			}
		});
		cowList1 = cowList1.subList(0, 10);
		for (Cow cow : cowList1) {
			List<Cow> cList = resultMap1.get(cow.getAge());
			if (CollectionUtils.isEmpty(cList)) {
				cList = new ArrayList<>();
			}
			cList.add(cow);
			resultMap1.put(cow.getAge(), cList);
		}
		Integer money1 = 0;
		for (Map.Entry<Integer, List<Cow>> entry : resultMap1.entrySet()) {
			for (Cow cow : entry.getValue()) {
				money1 += cow.getWeight() * cow.getPrice();
			}
		}
		System.out.println(money1);
		System.out.println("耗时：" + Duration.between(localDateTime, LocalDateTime.now()).toMillis());
	}

	/**
	 * java8
	 */
	private static void test08() {
		List<Cow> cowList = getCowList();
		LocalDateTime localDateTime = LocalDateTime.now();
		Map<Integer, List<Cow>> resultMap = cowList.stream()
				.filter(a -> a.getAge() < 10 && !"日本".equals(a.getOrigin()))
				.map(a -> {
					a.setTaste("黄色".equals(a.getColor()) ? "红烧" : "烧烤");
					a.setWeight(a.getWeight() - a.getGut() - a.getBones());
					return a;
				})
				.collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
						new TreeSet<>(Comparator.comparing(a -> a.getOrigin() + ":" + a.getSex()))), ArrayList::new))
				.stream().sorted(Comparator.comparing(Cow::getWeight).reversed())
				.skip(0).limit(10).collect(Collectors.groupingBy(a -> a.getAge()));

		Integer money = resultMap.values().stream().flatMap(a -> a.stream()).collect(Collectors.toList())
				.stream().map(a -> a.getWeight() * a.getPrice()).reduce(0, Integer::sum);
		System.out.println(money);
		System.out.println("耗时：" + Duration.between(localDateTime, LocalDateTime.now()).toMillis());
	}

}
