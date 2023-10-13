package com.example.demo.test.export;

import com.example.demo.entity.BillOfSalesEntity;
import com.example.demo.entity.BillOfSalesProductEntity;
import com.example.demo.util.PdfUtils3;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPRow;
import com.lowagie.text.pdf.PdfPTable;
import com.spire.pdf.PdfDocument;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
		File imgFile = new File("demo.jpg");
		File imgFile2 = new File("demo2.png");
		PDDocument pdDocument = null;
		try {
			// 宋体，正常字体
			BaseFont bfChinese = BaseFont.createFont(PdfUtils3.FontName, PdfUtils3.FontEncoding, BaseFont.NOT_EMBEDDED);
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
			cells1[0] = PdfUtils3.createCell(entity.getBillOfSalesName(), font, height, 10);
			// 创建行，添加到listRow
			listRow.add(new PdfPRow(cells1));

			// 第二行
			PdfPCell[] cells2 = new PdfPCell[10];
			cells2[0] = PdfUtils3.createCellLeft("购货单位：", font, height, 1);
			cells2[1] = PdfUtils3.createCellLeft(entity.getShopName(), font, height, 4);
			cells2[5] = PdfUtils3.createCellLeft("生成时间：", font, height, 1);
			cells2[6] = PdfUtils3.createCellLeft(entity.getGenerateTime(), font, height, 4);
			listRow.add(new PdfPRow(cells2));

			// 第三行
			PdfPCell[] cells3 = new PdfPCell[10];
			cells3[0] = PdfUtils3.createCellLeft("收货人：", font, height, 1);
			cells3[1] = PdfUtils3.createCellLeft(entity.getConsignee(), font, height, 4);
			cells3[5] = PdfUtils3.createCellLeft("订单编号：", font, height, 1);
			cells3[6] = PdfUtils3.createCellLeft(entity.getChildOrderId(), font, height, 4);
			listRow.add(new PdfPRow(cells3));

			// 第四行
			PdfPCell[] cells4 = new PdfPCell[10];
			cells4[0] = PdfUtils3.createCell("产品名称", font, height, 1);
			cells4[1] = PdfUtils3.createCell("商品条码", font, height, 1);
			cells4[2] = PdfUtils3.createCell("规格", font, height, 1);
			cells4[3] = PdfUtils3.createCell("批文号", font, height, 1);
			cells4[4] = PdfUtils3.createCell("批号", font, height, 1);
			cells4[5] = PdfUtils3.createCell("有效期至", font, height, 1);
			cells4[6] = PdfUtils3.createCell("单位", font, height, 1);
			cells4[7] = PdfUtils3.createCell("数量", font, height, 1);
			cells4[8] = PdfUtils3.createCell("单价", font, height, 1);
			cells4[9] = PdfUtils3.createCell("金额", font, height, 1);
			listRow.add(new PdfPRow(cells4));

			// 第五行，list
			for (BillOfSalesProductEntity productEntity : entity.getProductList()) {
				PdfPCell[] cells5 = new PdfPCell[10];
				cells5[0] = PdfUtils3.createCell(productEntity.getProductName(), font, getHeight(productEntity.getProductName(), height), 1);
				cells5[1] = PdfUtils3.createCell(productEntity.getBarCode(), font, getHeight(productEntity.getProductName(), height), 1);
				cells5[2] = PdfUtils3.createCell(productEntity.getSpecs(), font, getHeight(productEntity.getProductName(), height), 1);
				cells5[3] = PdfUtils3.createCell(productEntity.getApprovalNo(), font, getHeight(productEntity.getProductName(), height), 1);
				cells5[4] = PdfUtils3.createCell(productEntity.getBatchNo(), font, getHeight(productEntity.getProductName(), height), 1);
				cells5[5] = PdfUtils3.createCell(productEntity.getTermOfValidity(), font, getHeight(productEntity.getProductName(), height), 1);
				cells5[6] = PdfUtils3.createCell(productEntity.getUnit(), font, getHeight(productEntity.getProductName(), height), 1);
				cells5[7] = PdfUtils3.createCell(productEntity.getNumber(), font, getHeight(productEntity.getProductName(), height), 1);
				cells5[8] = PdfUtils3.createCell(productEntity.getUnitPrice(), font, getHeight(productEntity.getProductName(), height), 1);
				cells5[9] = PdfUtils3.createCell(productEntity.getAmount(), font, getHeight(productEntity.getProductName(), height), 1);
				listRow.add(new PdfPRow(cells5));
			}

			// 第六行
			PdfPCell[] cells6 = new PdfPCell[10];
			cells6[0] = PdfUtils3.createCellLeft("积分抵扣", font, height, 1);
			cells6[1] = PdfUtils3.createCell("", font, height, 1);
			cells6[2] = PdfUtils3.createCell("", font, height, 1);
			cells6[3] = PdfUtils3.createCell("", font, height, 1);
			cells6[4] = PdfUtils3.createCell("", font, height, 1);
			cells6[5] = PdfUtils3.createCell("", font, height, 1);
			cells6[6] = PdfUtils3.createCell("", font, height, 1);
			cells6[7] = PdfUtils3.createCell("", font, height, 1);
			cells6[8] = PdfUtils3.createCell("", font, height, 1);
			cells6[9] = PdfUtils3.createCell(entity.getScoreDeduct(), font, 20f, 1);
			listRow.add(new PdfPRow(cells6));

			// 第七行
			PdfPCell[] cells7 = new PdfPCell[10];
			cells7[0] = PdfUtils3.createCellLeft("金额（大写）", font, height, 1);
			cells7[1] = PdfUtils3.createCellLeft(entity.getBigTotalAmount(), font, height, 5);
			cells7[6] = PdfUtils3.createCellLeft("合计数量", font, height, 1);
			cells7[7] = PdfUtils3.createCell(entity.getTotalNumber(), font, height, 1);
			cells7[8] = PdfUtils3.createCellLeft("合计金额", font, height, 1);
			cells7[9] = PdfUtils3.createCell(entity.getTotalAmount(), font, height, 1);
			listRow.add(new PdfPRow(cells7));

			// 第八行
			PdfPCell[] cells8 = new PdfPCell[10];
			cells8[0] = PdfUtils3.createCellLeft("备注：" + (entity.getRemarks1() == null ? "" : entity.getRemarks1()), font, height, 5);
			cells8[5] = PdfUtils3.createCellLeft(entity.getRemarks2(), font, height, 5);
			listRow.add(new PdfPRow(cells8));

			// 第九行
			PdfPCell[] cells9 = new PdfPCell[10];
			cells9[0] = PdfUtils3.createCellLeft("业务员：" + (entity.getManagerName() == null ? "" : entity.getManagerName()), font, height, 2);
			cells9[2] = PdfUtils3.createCellLeft("电话：" + (entity.getMobile() == null ? "" : entity.getMobile()), font, height, 2);
			cells9[4] = PdfUtils3.createCellLeft("", font, height, 6);
			listRow.add(new PdfPRow(cells9));

			PdfUtils3.createDocument(table, file);

			/*PdfDocument pdf = new PdfDocument("demo.pdf");
			BufferedImage image = pdf.saveAsImage(0);
			File file2 = new File("demo.png");
			// 中文编码
			java.awt.Font font1 = new java.awt.Font("宋体", Font.NORMAL, 10);
			Graphics graphics = image.getGraphics();
			graphics.setFont(font1);
			ImageIO.write(image, "PNG", file2);*/

			pdDocument = PDDocument.load(file);
			PDFRenderer renderer = new PDFRenderer(pdDocument);
			BufferedImage image = renderer.renderImageWithDPI(0, 200);

			java.awt.Font font1 = new java.awt.Font("宋体", Font.NORMAL, 10);
			Graphics graphics = image.createGraphics();
			graphics.setFont(font1);

			ImageIO.write(image, "jpeg", imgFile);

			//System.out.println(System.getProperty("sun.jnu.encoding"));

			/*System.out.println(font1);
			System.out.println(image.getGraphics().getFont());
			System.out.println(graphics.getFont());*/

			// 编码为 Base64 字符串
			/*byte[] bytes = FileUtils.readFileToByteArray(imgFile);
			String base64 = new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8);
			System.out.println(base64);
			bytes = Base64.getDecoder().decode(base64);
			FileUtils.writeByteArrayToFile(imgFile2, bytes);*/


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pdDocument != null) {
				try {
					pdDocument.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//file.delete();
			//imgFile.delete();
			/*if (file.exists()) {
				file.delete();
			}
			if (imgFile.exists()) {
				imgFile.delete();
			}*/
		}
		System.out.println(Duration.between(begin, LocalDateTime.now()).toMillis());
	}

	private Font chineseFont(int size) throws Exception {
		// 设置中文
		//BaseFont b = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); //默认的字体调用
		//获取系统字体的路径
		String prefixFont = "";
		//获取系统类型
		String os = System.getProperties().getProperty("os.name");
		if (os.startsWith("win") || os.startsWith("Win")) {
			// win下获取字体的路径
			prefixFont = "C:\\Windows\\Fonts" + File.separator + "STSONG.TTF";
		} else {
			// linux下获取字体的路径,注意该目录下如果没有需额外安装，如我用的是STSONG字体
			prefixFont = "/usr/share/fonts" + File.separator + "STSONG.TTF";
			BaseFont b = null;
			try {
				b = BaseFont.createFont(prefixFont, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			} catch (Exception e) {
				//如果发生异常执行默认的字体
				b = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			}

			return new Font(b, size, Font.NORMAL);
		}
		return null;
	}


	private static float getHeight(String text, float height) {
		if (StringUtils.isBlank(text)) {
			return height;
		}
		/*System.out.println(text.length());
		System.out.println((text.length() / 8));
		System.out.println((text.length() / 8) * 10 + height);*/
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
