package com.example.demo.test.export;

import com.example.demo.entity.BillOfSalesEntity;
import com.example.demo.entity.BillOfSalesProductEntity;
import com.example.demo.util.PdfUtils;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPRow;
import com.lowagie.text.pdf.PdfPTable;
import com.spire.pdf.PdfDocument;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author huangjiale
 * @date 2022/3/4 16:19
 **/
public class PdfTest2 {

	public static final String Dest = "D:\\demo.pdf";

	public static void main(String[] args) {
		BillOfSalesEntity entity = new BillOfSalesEntity();
		entity.setBillOfSalesName("纽曼思藻油DdsaHA软胶囊（成人型）国产中国");
		List<BillOfSalesProductEntity> productEntityList = new ArrayList<>();
		BillOfSalesProductEntity product = new BillOfSalesProductEntity();
		product.setProductName("纽曼思藻asd油DHA软dsad胶囊（成人型）国产中国的撒dsad谎的三大看");
		productEntityList.add(product);
		entity.setProductList(productEntityList);
		createPdf(entity);
	}

	public static void createPdf(BillOfSalesEntity entity) {
		LocalDateTime begin = LocalDateTime.now();

		File file = new File("demo.pdf");
		File imgFile = new File("demo.png");
		try {
			// 宋体，正常字体
			BaseFont bfChinese = BaseFont.createFont(PdfUtils.FontName, PdfUtils.FontEncoding, BaseFont.NOT_EMBEDDED);
			Font font = new Font(bfChinese, 10, Font.NORMAL);

			// 创建一个pdf的表格
			PdfPTable table = new PdfPTable(10);
			// 宽度100%填充
			table.setWidthPercentage(100);

			// 创建表格的的行对象集合
			List<PdfPRow> listRow = table.getRows();
			// 将表格设置为10列，并指定列宽
			table.setWidths(new float[]{1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f});
			float height = 20f;

			// 第一行
			PdfPCell[] cells1 = new PdfPCell[10];
			cells1[0] = PdfUtils.createCell(entity.getBillOfSalesName(), font, height, 10);
			// 创建行，添加到listRow
			listRow.add(new PdfPRow(cells1));

			// 第二行
			PdfPCell[] cells2 = new PdfPCell[10];
			cells2[0] = PdfUtils.createCellLeft("购货单位：", font, height, 1);
			cells2[1] = PdfUtils.createCellLeft(entity.getShopName(), font, height, 4);
			cells2[5] = PdfUtils.createCellLeft("生成时间：", font, height, 1);
			cells2[6] = PdfUtils.createCellLeft(entity.getGenerateTime(), font, height, 4);
			listRow.add(new PdfPRow(cells2));

			// 第三行
			PdfPCell[] cells3 = new PdfPCell[10];
			cells3[0] = PdfUtils.createCellLeft("收货人：", font, height, 1);
			cells3[1] = PdfUtils.createCellLeft(entity.getConsignee(), font, height, 4);
			cells3[5] = PdfUtils.createCellLeft("订单编号：", font, height, 1);
			cells3[6] = PdfUtils.createCellLeft(entity.getChildOrderId(), font, height, 4);
			listRow.add(new PdfPRow(cells3));

			// 第四行
			PdfPCell[] cells4 = new PdfPCell[10];
			cells4[0] = PdfUtils.createCell("产品名称", font, height, 1);
			cells4[1] = PdfUtils.createCell("商品条码", font, height, 1);
			cells4[2] = PdfUtils.createCell("规格", font, height, 1);
			cells4[3] = PdfUtils.createCell("批文号", font, height, 1);
			cells4[4] = PdfUtils.createCell("批号", font, height, 1);
			cells4[5] = PdfUtils.createCell("有效期至", font, height, 1);
			cells4[6] = PdfUtils.createCell("单位", font, height, 1);
			cells4[7] = PdfUtils.createCell("数量", font, height, 1);
			cells4[8] = PdfUtils.createCell("单价", font, height, 1);
			cells4[9] = PdfUtils.createCell("金额", font, height, 1);
			listRow.add(new PdfPRow(cells4));

			// 第五行，list
			for (BillOfSalesProductEntity productEntity : entity.getProductList()) {
				PdfPCell[] cells5 = new PdfPCell[10];
				cells5[0] = PdfUtils.createCell(productEntity.getProductName(), font, getHeight(productEntity.getProductName(), height), 1);
				cells5[1] = PdfUtils.createCell(productEntity.getBarCode(), font, getHeight(productEntity.getProductName(), height), 1);
				cells5[2] = PdfUtils.createCell(productEntity.getSpecs(), font, getHeight(productEntity.getProductName(), height), 1);
				cells5[3] = PdfUtils.createCell(productEntity.getApprovalNo(), font, getHeight(productEntity.getProductName(), height), 1);
				cells5[4] = PdfUtils.createCell(productEntity.getBatchNo(), font, getHeight(productEntity.getProductName(), height), 1);
				cells5[5] = PdfUtils.createCell(productEntity.getTermOfValidity(), font, getHeight(productEntity.getProductName(), height), 1);
				cells5[6] = PdfUtils.createCell(productEntity.getUnit(), font, getHeight(productEntity.getProductName(), height), 1);
				cells5[7] = PdfUtils.createCell(productEntity.getNumber(), font, getHeight(productEntity.getProductName(), height), 1);
				cells5[8] = PdfUtils.createCell(productEntity.getUnitPrice(), font, getHeight(productEntity.getProductName(), height), 1);
				cells5[9] = PdfUtils.createCell(productEntity.getAmount(), font, getHeight(productEntity.getProductName(), height), 1);
				listRow.add(new PdfPRow(cells5));
			}

			// 第六行
			PdfPCell[] cells6 = new PdfPCell[10];
			cells6[0] = PdfUtils.createCellLeft("积分抵扣", font, height, 1);
			cells6[1] = PdfUtils.createCell("", font, height, 1);
			cells6[2] = PdfUtils.createCell("", font, height, 1);
			cells6[3] = PdfUtils.createCell("", font, height, 1);
			cells6[4] = PdfUtils.createCell("", font, height, 1);
			cells6[5] = PdfUtils.createCell("", font, height, 1);
			cells6[6] = PdfUtils.createCell("", font, height, 1);
			cells6[7] = PdfUtils.createCell("", font, height, 1);
			cells6[8] = PdfUtils.createCell("", font, height, 1);
			cells6[9] = PdfUtils.createCell(entity.getScoreDeduct(), font, 20f, 1);
			listRow.add(new PdfPRow(cells6));

			// 第七行
			PdfPCell[] cells7 = new PdfPCell[10];
			cells7[0] = PdfUtils.createCellLeft("金额（大写）", font, height, 1);
			cells7[1] = PdfUtils.createCellLeft(entity.getBigTotalAmount(), font, height, 5);
			cells7[6] = PdfUtils.createCellLeft("合计数量", font, height, 1);
			cells7[7] = PdfUtils.createCell(entity.getTotalNumber(), font, height, 1);
			cells7[8] = PdfUtils.createCellLeft("合计金额", font, height, 1);
			cells7[9] = PdfUtils.createCell(entity.getTotalAmount(), font, height, 1);
			listRow.add(new PdfPRow(cells7));

			// 第八行
			PdfPCell[] cells8 = new PdfPCell[10];
			cells8[0] = PdfUtils.createCellLeft("备注：" + (entity.getRemarks1() == null ? "" : entity.getRemarks1()), font, height, 5);
			cells8[5] = PdfUtils.createCellLeft(entity.getRemarks2(), font, height, 5);
			listRow.add(new PdfPRow(cells8));

			// 第九行
			PdfPCell[] cells9 = new PdfPCell[10];
			cells9[0] = PdfUtils.createCellLeft("业务员：" + (entity.getManagerName() == null ? "" : entity.getManagerName()), font, height, 2);
			cells9[2] = PdfUtils.createCellLeft("电话：" + (entity.getMobile() == null ? "" : entity.getMobile()), font, height, 2);
			cells9[4] = PdfUtils.createCellLeft("", font, height, 6);
			listRow.add(new PdfPRow(cells9));

			PdfUtils.createDocument(table, file);

			PdfDocument pdf = new PdfDocument("demo.pdf");
			BufferedImage image = pdf.saveAsImage(0);
			File file2 = new File("demo.png");
			ImageIO.write(image, "PNG", file2);

			// 编码为 Base64 字符串
			/*byte[] bytes = FileUtils.readFileToByteArray(file2);
			String base64 = new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8);
			System.out.println(base64);*/

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//file.delete();
			//imgFile.delete();
		}
		System.out.println(Duration.between(begin, LocalDateTime.now()).toMillis());
	}

	private static float getHeight(String text, float height) {
		if (StringUtils.isBlank(text)) {
			return height;
		}
		System.out.println(text.length());
		System.out.println((text.length() / 8));
		System.out.println((text.length() / 8) * 10 + height);
		return (text.length() / 8) * 10 + height;
	}

	public static void main1(String[] args) throws IOException {
		PdfDocument pdf = new PdfDocument("D://test.pdf");
		BufferedImage image;
		for (int i = 0; i < pdf.getPages().getCount(); i++) {
			image = pdf.saveAsImage(i);
			File file = new File(String.format("ToImage-img-%d.png", i));
			ImageIO.write(image, "PNG", file);
		}
		pdf.close();
	}

}
