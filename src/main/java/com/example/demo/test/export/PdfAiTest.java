package com.example.demo.test.export;


import com.example.demo.test.pdf.PdfUtils;
import com.example.demo.util.PdfUtils2;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.*;
import com.spire.pdf.graphics.PdfCanvas;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author huangjiale
 * @date 2023/10/8 13:19
 **/
public class PdfAiTest {

	public static final String FontName = "STSong-Light";
	public static final String FontEncoding = "UniGB-UCS2-H";
	public static final String imgUrl = "http://img.aikesaisi.com/billOfSales/%E8%89%BE%E7%8E%96%E7%88%B1%EF%BC%88%E4%B8%8A%E6%B5%B7%EF%BC%89%E5%81%A5%E5%BA%B7%E7%A7%91%E6%8A%80%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%E5%87%BA%E8%B4%A7%E6%B8%85%E5%8D%9534427152774882.png";
	public static final String imgUrl2 = "http://img.aikesaisi.com/jhbamin/images/2023-10-08/8ad97167797c4f0481bdb11f647a32f7-code.jpg";
	public static final String imgUrl3 = "http://img.aikesaisi.com/jhbamin/images/2023-10-08/e895fbdc05c347fbad116e438058abe4-shetou.jpg";

	public static void main(String[] args) {
		// 创建文档对象
		Document document = new Document();

		try {
			// 指定PDF文件的输出路径
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\example.pdf"));

			// 打开文档
			document.open();

			// 宋体，正常字体
			BaseFont bfChinese = BaseFont.createFont(FontName, FontEncoding, BaseFont.NOT_EMBEDDED);
			Font font10 = new Font(bfChinese, 10);
			Font font20 = new Font(bfChinese, 20);
			Font font30 = new Font(bfChinese, 30);
			Font fontBold50 = new Font(bfChinese, 50, Font.BOLD);


			//firstPage(document);

			/**
			 * 第一页：标题+个人信息+二维码+目录
			 */

			// 间距
			Paragraph emptySpace1 = new Paragraph();
			emptySpace1.setSpacingAfter(40f);
			document.add(emptySpace1);
			// 标题
			Paragraph title = new Paragraph("中医舌诊健康状态辨识报告", font30);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
			// 间距
			Paragraph emptySpace2 = new Paragraph();
			emptySpace2.setSpacingAfter(80f);
			document.add(emptySpace2);

			// 个人信息
			Paragraph personMessage = new Paragraph("", font20);
			personMessage.setAlignment(Element.ALIGN_LEFT);
			personMessage.add("\t黄佳乐\n");
			personMessage.add("\t年龄：18\n");
			personMessage.add("\t性别：男\n");
			personMessage.add("\t联系电话：18616919418\n");
			personMessage.add("\t报告时间：2023-9-15\n");
			document.add(personMessage);

			// 二维码
			Image qrCode = Image.getInstance(imgUrl2);
			// 设置图片大小
			qrCode.scaleToFit(120, 120);
			// 设置二维码位置
			qrCode.setAbsolutePosition(365, 580);
			document.add(qrCode);
			// 二维码底下文案
			Paragraph qrCodeText = new Paragraph("", font20);
			qrCodeText.add("\t\t扫描二维码\n");
			qrCodeText.add("使用手机查看电子版\n");
			qrCodeText.setIndentationLeft(300);
			qrCodeText.setSpacingBefore(-50);
			document.add(qrCodeText);

			// 间距
			Paragraph emptySpace3 = new Paragraph();
			emptySpace3.setSpacingAfter(100f);
			document.add(emptySpace3);

			// 目录左
			Paragraph catalogueLeftText = new Paragraph("", fontBold50);
			catalogueLeftText.add("\t目\n");
			catalogueLeftText.add("\t录\n");
			document.add(catalogueLeftText);
			// 目录右
			Paragraph catalogueRightText = new Paragraph("", font30);
			catalogueRightText.add("一、舌象辨识\n");
			catalogueRightText.add("二、面部望诊\n");
			catalogueRightText.add("三、健康分析\n");
			catalogueRightText.add("四、调理方案\n");
			catalogueRightText.setIndentationLeft(200);
			catalogueRightText.setSpacingBefore(-150);
			document.add(catalogueRightText);


			/**
			 * 第二页
			 */

			document.newPage();

			// 标题
			Paragraph oneTitle = new Paragraph("", font30);
			oneTitle.setAlignment(Element.ALIGN_LEFT);
			oneTitle.add("一、舌象辨识\n");
			document.add(oneTitle);

			Paragraph text1 = new Paragraph("", font30);
			text1.setAlignment(Element.ALIGN_CENTER);
			text1.add("舌面图像采集结果\n");
			document.add(text1);
			// 图片
			Image textUrl1 = Image.getInstance(imgUrl2);
			textUrl1.scaleToFit(100, 100);
			textUrl1.setAlignment(Element.ALIGN_CENTER);
			document.add(textUrl1);
			Paragraph text2 = new Paragraph("", font20);
			text2.setAlignment(Element.ALIGN_CENTER);
			text2.add("舌面\n");
			text2.add("\n");
			document.add(text2);


			// 表格1
			PdfPTable table1 = new PdfPTable(2);
			// 宽度100%填充
			table1.setWidthPercentage(100);
			// 设置表格边框颜色为蓝色
			//table1.getDefaultCell().setBorderColor(Color.BLUE);

			// 创建表格的的行对象集合
			List<PdfPRow> listRow = table1.getRows();
			// 将表格设置为2列，并指定列宽
			table1.setWidths(new float[]{1f, 3f});
			float height = 40f;

			// 第1行
			PdfPCell[] cells1 = new PdfPCell[2];
			cells1[0] = PdfUtils2.createCell("舌象分析结果汇总", font20, height, 2);
			listRow.add(new PdfPRow(cells1));

			for (int i = 0; i < 6; i++) {
				// 第2行
				PdfPCell[] cells2 = new PdfPCell[2];
				cells2[0] = PdfUtils2.createCellLeft("\t\t舌色：" + i, font20, height, 1);
				cells2[1] = PdfUtils2.createCellLeft("\t\t淡红舌" + i, font20, height, 1);
				cells2[0].setBackgroundColor(new Color(173, 216, 230));
				cells2[0].setBorderColor(Color.BLUE);
				listRow.add(new PdfPRow(cells2));
			}

			document.add(table1);


			// 间距
			Paragraph emptySpace4 = new Paragraph();
			emptySpace4.setSpacingAfter(100f);
			document.add(emptySpace4);

			// 表格2
			PdfPTable table2 = new PdfPTable(3);
			table2.setWidthPercentage(100);
			List<PdfPRow> listRow2 = table2.getRows();
			table2.setWidths(new float[]{1f, 2f, 6f});

			// 第1行
			PdfPCell[] cells21 = new PdfPCell[3];
			cells21[0] = PdfUtils2.createCellLeft("人工智能分析", font20, height, 1, 2);
			cells21[1] = PdfUtils2.createCellLeft("舌色分析", font20, height, 1);

			cells21[2] = PdfUtils2.createCellLeft("", font20, height, 1);
			Image image = Image.getInstance(imgUrl3);
			image.scaleToFit(100, 100);
			// 将图像添加到单元格中
			cells21[2].addElement(image);
			listRow2.add(new PdfPRow(cells21));

			// 第2行
			PdfPCell[] cells22 = new PdfPCell[3];
			//cells22[0] = PdfUtils.createCellLeft("", font20, height, 1);
			cells22[1] = PdfUtils2.createCellLeft("辨识结果", font20, height, 1);
			cells22[2] = PdfUtils2.createCellLeft("淡红舌", font20, height, 1);
			listRow2.add(new PdfPRow(cells22));

			// 第3行
			PdfPCell[] cells23 = new PdfPCell[3];
			cells23[0] = PdfUtils2.createCellLeft("中医解析", font20, height, 1, 2);
			cells23[1] = PdfUtils2.createCellLeft("中医描述", font20, height, 1);
			cells23[2] = PdfUtils2.createCellLeft("【淡红舌】正常人舌色，舌质呈现润泽红活。【淡红舌】正常人舌色，舌质呈现润泽红活。【淡红舌】正常人舌色，舌质呈现润泽红活。", font20, height, 1);
			listRow2.add(new PdfPRow(cells23));

			// 第3行
			PdfPCell[] cells24 = new PdfPCell[3];
			//cells24[0] = PdfUtils.createCellLeft("", font20, height, 1);
			cells24[1] = PdfUtils2.createCellLeft("病理意义", font20, height, 1);
			cells24[2] = PdfUtils2.createCellLeft("【淡红舌】正常舌色，或者处于病情轻浅阶段。", font20, height, 1);
			listRow2.add(new PdfPRow(cells24));

			document.add(table2);







		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭文档
			document.close();
			System.out.println("PDF生成成功！");
		}

	}

}
