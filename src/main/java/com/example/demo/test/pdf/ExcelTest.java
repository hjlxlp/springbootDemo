package com.example.demo.test.pdf;

import com.example.demo.entity.BillOfSalesEntity;
import com.example.demo.entity.BillOfSalesProductEntity;

import java.util.ArrayList;
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
		try {




		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
