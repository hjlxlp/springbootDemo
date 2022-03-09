package com.example.demo.test.pdf;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.example.demo.entity.BillOfSalesEntity;
import com.example.demo.entity.BillOfSalesProductEntity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author huangjiale
 * @date 2022/3/8 16:03
 **/
public class ExcelTest {

	public static final String Dest = "D:\\demo.xlsx";

	public static void main(String[] args) {
		BillOfSalesEntity entity = new BillOfSalesEntity();
		List<BillOfSalesProductEntity> productEntityList = new ArrayList<>();
		BillOfSalesProductEntity product = new BillOfSalesProductEntity();
		productEntityList.add(product);
		entity.setProductList(productEntityList);
		createExcel(entity);
	}

	public static void createExcel(BillOfSalesEntity entity) {
		LocalDateTime begin = LocalDateTime.now();
		try {
			WriteCellStyle writeCellStyle = new WriteCellStyle();

			EasyExcel.write(Dest)
					//.head(head2())
					.registerWriteHandler(new MyMergeStrategy())
					//.registerWriteHandler(new MyStrategy())
					.sheet()
					.doWrite(dataList3(entity));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Duration.between(begin, LocalDateTime.now()).toMillis());
	}

	private static List<List<String>> dataList3(BillOfSalesEntity entity) {
		List<List<String>> list = new ArrayList<>();

		// 第一行
		List<String> data = new ArrayList<>();
		data.add(entity.getBillOfSalesName());
		list.add(data);

		// 第二行
		data = new ArrayList<>();
		data.add("购货单位：");
		data.add(entity.getShopName());
		data.add("");
		data.add("");
		data.add("");
		data.add("生成时间：");
		data.add(entity.getGenerateTime());
		data.add("");
		data.add("");
		data.add("");
		list.add(data);

		// 第三行
		data = new ArrayList<>();
		data.add("收货人：");
		data.add(entity.getConsignee());
		data.add("");
		data.add("");
		data.add("");
		data.add("订单编号：");
		data.add(entity.getChildOrderId());
		data.add("");
		data.add("");
		data.add("");
		list.add(data);

		// 第四行
		data = new ArrayList<>();
		data.add("产品名称");
		data.add("商品条码");
		data.add("规格");
		data.add("批文号");
		data.add("批号");
		data.add("有效期至");
		data.add("单位");
		data.add("数量");
		data.add("单价");
		data.add("金额");
		list.add(data);

		// 第五行
		for (BillOfSalesProductEntity productEntity : entity.getProductList()) {
			data = new ArrayList<>();
			data.add(productEntity.getProductName());
			data.add(productEntity.getBarCode());
			data.add(productEntity.getSpecs());
			data.add(productEntity.getApprovalNo());
			data.add(productEntity.getBatchNo());
			data.add(productEntity.getTermOfValidity());
			data.add(productEntity.getUnit());
			data.add(productEntity.getNumber());
			data.add(productEntity.getUnitPrice());
			data.add(productEntity.getAmount());
			list.add(data);
		}

		// 第六行
		data = new ArrayList<>();
		data.add("积分抵扣");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add(entity.getScoreDeduct());
		list.add(data);

		// 第七行
		data = new ArrayList<>();
		data.add("金额（大写）");
		data.add(entity.getBigTotalAmount());
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("合计数量");
		data.add(entity.getTotalNumber());
		data.add("合计金额");
		data.add(entity.getTotalAmount());
		list.add(data);

		// 第八行
		data = new ArrayList<>();
		data.add("备注：" + (entity.getRemarks1() == null ? "" : entity.getRemarks1()));
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add(entity.getRemarks2());
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		list.add(data);

		// 第九行
		data = new ArrayList<>();
		data.add("业务员：" + (entity.getManagerName() == null ? "" : entity.getManagerName()));
		data.add("");
		data.add("电话：" + (entity.getMobile() == null ? "" : entity.getMobile()));
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		data.add("");
		list.add(data);

		return list;
	}

	/**
	 * 创建数据
	 *
	 * @return
	 */
	private static List<List<String>> dataList2() {
		List<List<String>> list = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			List<String> data = new ArrayList<>();
			for (int j = 0; j < 10; j++) {
				data.add("创建数据");
			}
			list.add(data);
		}
		return list;
	}

	private static List<List<String>> head() {
		List<List<String>> list = ListUtils.newArrayList();
		List<String> head0 = ListUtils.newArrayList();
		head0.add("字符串" + System.currentTimeMillis());
		List<String> head1 = ListUtils.newArrayList();
		head1.add("数字" + System.currentTimeMillis());
		List<String> head2 = ListUtils.newArrayList();
		head2.add("日期" + System.currentTimeMillis());
		list.add(head0);
		list.add(head1);
		list.add(head2);
		return list;
	}

	private static List<List<String>> dataList() {
		List<List<String>> list = ListUtils.newArrayList();
		for (int i = 0; i < 10; i++) {
			List<String> data = ListUtils.newArrayList();
			data.add("字符串" + i);
			data.add(new Date().toString());
			data.add("0.56");
			list.add(data);
		}
		return list;
	}


	/**
	 * 创建表头
	 *
	 * @return
	 */
	private static List<List<String>> head2() {
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> head0 = new ArrayList<String>();
		head0.add("字符串");
		List<String> head1 = new ArrayList<String>();
		head1.add("数字");
		List<String> head2 = new ArrayList<String>();
		head2.add("日期");
		list.add(head0);
		list.add(head1);
		list.add(head2);
		return list;
	}


}
