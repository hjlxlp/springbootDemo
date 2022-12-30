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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.file.Files;
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
	public void test10() {
		List<OVO> ovoList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			OVO ovo = new OVO();
			ovo.setId(i);
			ovo.setName("ovoname"+i);

			// 里面的krvoList
			List<KRVO> krvoList = new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				KRVO krvo = new KRVO();
				krvo.setId(j);
				krvo.setName("krvoname"+j);
				krvoList.add(krvo);
			}
			ovo.setList(krvoList);

			ovoList.add(ovo);
		}


		System.out.println(JSON.toJSONString(ovoList));


		List<OVO> ovoList1 = new ArrayList<>();//sql
		for (OVO ovo : ovoList1) {

			// 里面的krvoList
			List<KRVO> krvoList = new ArrayList<>();//sql,ovoid

			ovo.setList(krvoList);
		}


		for (int i = 0; i < ovoList1.size(); i++) {
			// table
			// ovoid,ovoname

			for (int j = 0; j < ovoList1.get(i).getList().size(); j++) {
				// table
				// krvoid,krvoname
			}


		}




	}

	Integer Add(Integer num) {
		if (num == 1) {
			return 1;
		}
		Integer result2 = Add(num - 1);
		Integer res = result2 + num;
		return res;
	}


	@Test
	public void test() {
		Integer result = Add(2);
		System.out.println(result);


	}

	@Test
	public void testP() {
		for (int j = 1; j <= 7; j++) {
			System.out.print("第" + j + "天提现活动：");
			Integer money = 800;
			Integer num = new Random().nextInt(100);
			List<String> peopleList = new ArrayList<>();
			for (int i = 0; i < 10000; i++) {
				peopleList.add("黄美女" + i + "号选手");
			}
			System.out.print("有10000个黄某参与活动：");
			Integer a = 0;
			Integer b = 0;
			Integer c = 0;
			String xye = "";
			for (int i = 0; i < 10000; i++) {
				String p = peopleList.get(i);
				Integer n = new Random().nextInt(100);
				String str = "";
				if (n == num) {
					str += p + "抽中了";
					if (i > (n * 100)) {
						if (money == 0) {
							str += "但是奖金池已经为0，返回用户：抽到福了，再接再厉";
							a++;
						} else {
							money = 0;
							xye = p;
							str += "成为幸运儿，提现800成功。";
							System.out.print(str);
						}
					} else {
						str += "但是还没达到参与人数最低5000人条件：返回用户：抽到福了，再接再厉";
						c++;
					}
				} else {
					str += p + "没抽中，返回用户：抽到福了，再接再厉";
					b++;
				}
				//System.out.println(str);
			}
			//System.out.println("幸运儿：" + xye);
			System.out.println();
			System.out.print(a + "个大傻子抽到了，但是奖金池已经被领完。");
			System.out.print(b + "个二傻子抽都没抽到。");
			System.out.print(c + "个三傻子抽的时候，奖金池激活最低人数不够。");
			System.out.println();
		}
	}

	@Test
	public void testRemove() throws Exception {
		removeFile(new File("D:\\photo\\test"));
	}

	public static String getStringRandom(int length) {
		String val = "";
		Random random = new Random();
		// 参数length，表示生成几位随机数
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (random.nextInt(26) + temp);
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	public static void removeFile(File file) throws Exception {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				removeFile(f);
			}
		} else {
			File newDir = new File("D:\\BaiduNetdiskDownload\\all" + "/" + file.getName());
			if (newDir.exists()) {
				newDir = new File("D:\\BaiduNetdiskDownload\\all" + "/" + getStringRandom(8) + file.getName());
			}
			Files.copy(file.toPath(), newDir.toPath());
		}
	}

	@Test
	public void testRename() {
		File file = new File("D:\\photo\\ecy");
		List<File> files = Arrays.stream(file.listFiles()).sorted(Comparator.comparing(a -> a.length())).collect(Collectors.toList());
		//文件所在文件夹路径+新文件名
		File newDir = null;
		for (int i = 0; i < files.size(); i++) {
			File old = files.get(i);
			System.out.println(old.length());
			if (old.getName().endsWith(".png")) {
				//文件所在文件夹路径+新文件名
				newDir = new File(old.getParentFile() + "/" + (i + 10000) + ".png");
				old.renameTo(newDir);//重命名
			} else if (old.getName().endsWith(".jpg")) {
				newDir = new File(old.getParentFile() + "/" + (i + 10000) + ".jpg");
				old.renameTo(newDir);
			}
		}
	}

	@Test
	public void test_fd() {
		List<Integer> sizeList = Arrays.asList(100);
		List<Integer> priceList = Arrays.asList(10000, 11000, 12000, 13000, 14000);
		List<Integer> yearList = Arrays.asList(30);
		List<Integer> payList = Arrays.asList(300000, 400000, 500000, 600000);
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
