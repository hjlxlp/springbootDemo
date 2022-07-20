package com.example.demo.test.supplier;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author huangjiale
 * @date 2021/2/24 10:04
 **/
public class SupplierTest {

	@Test
	public void test1() {
		LocalDateTime beginTime = LocalDateTime.now();
		String fileName = "D:/test3.xlsx";
		List<Map<Integer, String>> tableList = new ArrayList<>();
		AnalysisEventListener listener = new AnalysisEventListener<Map<Integer, String>>() {
			@Override
			public void invoke(Map<Integer, String> o, AnalysisContext analysisContext) {
				System.out.println(analysisContext.readRowHolder());
				tableList.add(o);
			}

			@Override
			public void doAfterAllAnalysed(AnalysisContext analysisContext) {
			}

			@Override
			public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
				System.out.println(JSON.toJSONString(headMap));
			}
		};
		EasyExcel.read(fileName, listener).sheet().doRead();

		System.out.println(Duration.between(beginTime, LocalDateTime.now()).toMillis());
	}

	@Test
	public void test() {

		String fileName1 = "D:/supplierpro.xlsx";
		List<SupplierExcelVo> supplierList = new ArrayList<>();
		AnalysisEventListener listener1 = new AnalysisEventListener<SupplierExcelVo>() {
			@Override
			public void invoke(SupplierExcelVo o, AnalysisContext analysisContext) {
				supplierList.add(o);
			}

			@Override
			public void doAfterAllAnalysed(AnalysisContext analysisContext) {
			}
		};
		EasyExcel.read(fileName1, SupplierExcelVo.class, listener1).sheet().doRead();


		String fileName2 = "D:/skupro4.xlsx";
		List<SkuExcelVo> skuList = new ArrayList<>();
		AnalysisEventListener listener2 = new AnalysisEventListener<SkuExcelVo>() {
			@Override
			public void invoke(SkuExcelVo o, AnalysisContext analysisContext) {
				skuList.add(o);
			}

			@Override
			public void doAfterAllAnalysed(AnalysisContext analysisContext) {
			}
		};
		EasyExcel.read(fileName2, SkuExcelVo.class, listener2).sheet().doRead();

		String fileName3 = "D:/brandpro.xlsx";
		List<BrandExcelVo> brandList = new ArrayList<>();
		AnalysisEventListener listener3 = new AnalysisEventListener<BrandExcelVo>() {
			@Override
			public void invoke(BrandExcelVo o, AnalysisContext analysisContext) {
				brandList.add(o);
			}

			@Override
			public void doAfterAllAnalysed(AnalysisContext analysisContext) {
			}
		};
		EasyExcel.read(fileName3, BrandExcelVo.class, listener3).sheet().doRead();


		List<SupplierEntity> supplierEntityList = new ArrayList<>();
		List<SupplierSplitEntity> entityList = new ArrayList<>();

		List<String> supplierNameList = supplierList.stream().filter(a -> StringUtils.isNotBlank(a.getSupplierName()))
				.map(a -> a.getSupplierName().trim()).distinct().collect(Collectors.toList());

		for (int i = 0; i < supplierNameList.size(); i++) {
			SupplierEntity entity = new SupplierEntity();
			entity.setId(i + 1);
			entity.setSupplierName(supplierNameList.get(i));
			supplierEntityList.add(entity);
		}

		for (SupplierExcelVo supplierVo : supplierList) {
			// 查询所有brandId
			List<Integer> brandIdList = new ArrayList<>();
			BrandExcelVo brand = brandList.stream().filter(a -> a.getBrandName().equals(supplierVo.getBrandName())).findFirst().orElse(null);
			if (brand == null) {
				System.out.println("===brand为空===" + JSON.toJSONString(supplierVo));
				continue;
			}
			brandIdList.add(brand.getBrandId());
			// 查询二级brandId
			List<BrandExcelVo> brandTwoList = brandList.stream().filter(a -> a.getParentId().equals(brand.getBrandId())).collect(Collectors.toList());
			if (CollectionUtils.isNotEmpty(brandTwoList)) {
				brandIdList.addAll(brandTwoList.stream().map(a -> a.getBrandId()).collect(Collectors.toList()));
			}
			/*if (brand.getParentId().intValue() != 0) {
				BrandExcelVo brandOne = brandList.stream().filter(a -> a.getBrandId().equals(brand.getParentId())).findFirst().orElse(null);
				if (brandOne == null) {
					System.out.println("===brandOne为空===" + JSON.toJSONString(supplierVo));
					continue;
				}
				brandIdList.add(brandOne.getBrandId());
			}*/

			// 查询所有sku
			List<SkuExcelVo> skuVoList = skuList.stream().filter(a -> brandIdList.contains(a.getBrandId())).collect(Collectors.toList());
			if (CollectionUtils.isEmpty(skuVoList)) {
				System.out.println("===skuVoList为空===" + JSON.toJSONString(supplierVo));
				continue;
			}

			// 组装数据
			for (SkuExcelVo skuVo : skuVoList) {
				SupplierSplitEntity entity = new SupplierSplitEntity();
				entity.setSupplierId(supplierNameList.indexOf(supplierVo.getSupplierName()) + 1);
				entity.setSkuId(skuVo.getSkuId());
				entity.setSpuId(skuVo.getSpuId());
				entity.setBrandId(skuVo.getBrandId());
				entity.setSplitRatio(supplierVo.getSplitRatio() * 100);
				entity.setSplitType(1);
				entityList.add(entity);
			}

		}

		//System.out.println(JSON.toJSONString(supplierEntityList));
		//System.out.println(JSON.toJSONString(entityList));

		// 组装sql
		StringBuffer supplierStr = new StringBuffer();
		for (SupplierEntity entity : supplierEntityList) {
			supplierStr.append("insert into supplier set id = ").append(entity.getId())
					.append(", supplier_name = '").append(entity.getSupplierName()).append("';\n");
		}
		//System.out.println(supplierStr.toString());

		StringBuffer splitStr = new StringBuffer();
		for (SupplierSplitEntity entity : entityList) {
			splitStr.append("insert into supplier_split set ")
					.append("supplier_id = ").append(entity.getSupplierId())
					.append(", sku_id = ").append(entity.getSkuId())
					.append(", spu_id = ").append(entity.getSpuId())
					.append(", brand_id = ").append(entity.getBrandId())
					.append(", split_ratio = ").append(entity.getSplitRatio())
					.append(", split_type = ").append(entity.getSplitType())
					.append(";\n");
		}
		System.out.println(splitStr.toString());

	}

}
