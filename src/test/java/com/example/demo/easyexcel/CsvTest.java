package com.example.demo.easyexcel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangjiale
 * @date 2022/7/18 15:57
 **/
public class CsvTest {

	@Test
	public void csvTest2() throws IOException {
		File file = new File("D://view");
		List<File> files = Arrays.stream(file.listFiles()).sorted(Comparator.comparing(a -> a.getName())).collect(Collectors.toList());
		for (File f : files) {
			StringBuffer sb = new StringBuffer();
			sb.append("insert into jhb_user_acts_log (acts_type,user_id,spu_id,create_time,update_time) values \n");
			FileInputStream inputStream = new FileInputStream(f);
			CSVParser csvParser = CSVFormat.DEFAULT.parse(new InputStreamReader(inputStream, "UTF-8"));
			for (CSVRecord record : csvParser) {
				String str = record.get(1);
				if (str.startsWith("首页商品-") || str.startsWith("分类商品-")
						|| str.startsWith("品牌页商品-") || str.startsWith("同行都在买-")) {
					String[] s = str.split("\\+点击\\+")[1].split(":");
					if (s.length >= 5) {
						String userId = s[0];
						String spuId = s[1];
						String createTime = s[2] + ":" + s[3] + ":" + s[4];
						if (StringUtils.isNotBlank(userId) && !userId.equals("NaN") && !userId.equals("undefined")
								&& StringUtils.isNotBlank(spuId) && !spuId.equals("NaN") && !spuId.equals("undefined")) {
							sb.append("(")
									.append("2,")
									.append(userId).append(",")
									.append(spuId).append(",")
									.append("'").append(createTime).append("'").append(",")
									.append("'").append(createTime).append("'").append("),\n");
						}
					}
				}
			}
			String res = sb.toString();
			System.out.println(res.substring(0, res.length() - 2) + ";\n");
		}
	}

	@Test
	public void csvTest() throws IOException {
		File file = new File("D://view");
		List<File> files = Arrays.stream(file.listFiles()).sorted(Comparator.comparing(a -> a.getName())).collect(Collectors.toList());
		for (File f : files) {
			StringBuffer sb = new StringBuffer();
			//File file = new File("D://test2.csv");
			FileInputStream inputStream = new FileInputStream(f);
			CSVParser csvParser = CSVFormat.DEFAULT.parse(new InputStreamReader(inputStream, "UTF-8"));
			for (CSVRecord record : csvParser) {
				String str = record.get(1);
				if (str.startsWith("首页商品-") || str.startsWith("分类商品-")
						|| str.startsWith("品牌页商品-") || str.startsWith("同行都在买-")) {
					String[] s = str.split("\\+点击\\+")[1].split(":");
					if (s.length >= 5) {
						String userId = s[0];
						String spuId = s[1];
						String createTime = s[2] + ":" + s[3] + ":" + s[4];
						if (StringUtils.isNotBlank(userId) && !userId.equals("NaN") && !userId.equals("undefined")
								&& StringUtils.isNotBlank(spuId) && !spuId.equals("NaN") && !spuId.equals("undefined")) {
							sb.append("insert into jhb_user_acts_log set acts_type = 2, user_id = " + userId + ", spu_id = " + spuId
									+ ", create_time = '" + createTime + "', update_time = '" + createTime + "';\n");
						}
					}
				}
			}
			System.out.println(sb.toString());
		}
	}

}
