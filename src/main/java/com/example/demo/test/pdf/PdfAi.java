package com.example.demo.test.pdf;

import cn.hutool.core.date.DateUtil;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.*;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author huangjiale
 * @date 2023/10/10 13:25
 **/
@Data
public class PdfAi {

	public static final String testTitleUrl1 = "http://img.aikesaisi.com/jhbamin/images/2023-10-16/a7ab5aa59ef642e8aeca5f015ae6c3d0-title.png";
	public static final String testImgUrl1 = "http://img.aikesaisi.com/jhbamin/images/2023-10-08/8ad97167797c4f0481bdb11f647a32f7-code.jpg";
	public static final String testImgUrl2 = "http://img.aikesaisi.com/jhbamin/images/2023-10-08/e895fbdc05c347fbad116e438058abe4-shetou.jpg";
	public static final String testImgUrl3 = "http://img.aikesaisi.com/jhbamin/images/2023-10-10/61b473f845c748beac6914cd1c3bc712-bj.jpg";

	// 宋体，正常字体
	public static BaseFont bfChinese;

	static {
		try {
			bfChinese = BaseFont.createFont(PdfUtils.FontName, PdfUtils.FontEncoding, BaseFont.NOT_EMBEDDED);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Font font10 = new Font(bfChinese, 10);
	public static Font font16 = new Font(bfChinese, 16);
	public static Font font20 = new Font(bfChinese, 20);
	public static Font font30 = new Font(bfChinese, 30);
	public static Font font16Bold = new Font(bfChinese, 16, Font.BOLD);
	public static Font font20Bold = new Font(bfChinese, 20, Font.BOLD);
	public static Font font24Bold = new Font(bfChinese, 24, Font.BOLD);

	public static Color borderColor1 = new Color(79, 128, 189);
	public static Color backgroundColor1 = new Color(219, 229, 241);

	public static PdfResVo pdfResVo = new PdfResVo();

	/**
	 * 查询pdf数据
	 *
	 * @param reportId
	 */
	public static PdfResVo queryPdfResVo(String reportId) {
		// todo
		pdfResVo = new PdfResVo();
		// 暂时用舌诊的
		pdfResVo.getFaceVo().setAiFaceVOList(pdfResVo.getTongueVo().getAiTongueVOList());
		pdfResVo.getDetailVo().setTongueTips("建议您一周后再次做舌诊检测。");
		pdfResVo.getDetailVo().setTongueScore("80.29 分（风险）");
		return pdfResVo;
	}

	/**
	 * 添加页眉和页脚和水印
	 */
	public static class HeaderAndFooterPageEventHelper extends PdfPageEventHelper {

		@Override
		public void onStartPage(PdfWriter writer, Document document) {
			// 数据库数据
			AiConsultFlowVO detailVo = pdfResVo.getDetailVo();

			PdfPTable table = new PdfPTable(3);
			table.setWidths(new int[]{38, 36, 36});
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setPaddingBottom(5);
			table.getDefaultCell().setBorder(PdfPCell.BOTTOM);
			table.getDefaultCell().setBorderColor(borderColor1);

			PdfPCell emptyCell = new PdfPCell(new Paragraph(""));
			emptyCell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(emptyCell);

			Paragraph title = new Paragraph("健康状态辨识报告", font16Bold);
			PdfPCell titleCell = new PdfPCell(title);
			titleCell.setPaddingBottom(10);
			titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			titleCell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(titleCell);
			table.addCell(emptyCell);

			/*table.addCell(new Paragraph("检测人：黄佳乐", font10));
			table.addCell(new Paragraph("性别：男", font10));
			table.addCell(new Paragraph("年龄：18", font10));*/
			table.addCell(new Paragraph("检测人：" + detailVo.getName(), font10));
			table.addCell(new Paragraph("性别：" + detailVo.getGenderDesc(), font10));
			table.addCell(new Paragraph("年龄：" + detailVo.getAge(), font10));

			float yOffset = -30;
			table.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
			table.writeSelectedRows(0, -1, document.left(), document.top() - yOffset, writer.getDirectContent());

			PdfPTable spacingTable = new PdfPTable(1);
			PdfPCell spacingCell = new PdfPCell();
			spacingCell.setFixedHeight(25);
			spacingCell.setBorder(PdfPCell.NO_BORDER);
			spacingTable.addCell(spacingCell);

			//document.add(table);
			document.add(spacingTable);
			//table.writeSelectedRows(0, -1, 34, 828, writer.getDirectContent());
		}

		@Override
		public void onEndPage(PdfWriter writer, Document document) {
			// 数据库数据
			AiConsultFlowVO detailVo = pdfResVo.getDetailVo();

			PdfPTable table = new PdfPTable(2);
			table.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
			table.setWidths(new int[]{50, 50});
			table.getDefaultCell().setPaddingBottom(5);
			table.getDefaultCell().setBorder(PdfPCell.TOP);

			/*Paragraph title = new Paragraph("检测时间：2023-09-15 17:15:56", font10);*/
			Paragraph title = new Paragraph("检测时间：" + DateUtil.formatDateTime(detailVo.getCreateTime()), font10);
			PdfPCell titleCell = new PdfPCell(title);
			titleCell.setPaddingTop(4);
			titleCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			titleCell.setBorder(PdfPCell.TOP);
			titleCell.setBorderColor(borderColor1);
			table.addCell(titleCell);

			Paragraph pageNumberText = new Paragraph("第 " + document.getPageNumber() + " 页", font10);
			PdfPCell pageNumberCell = new PdfPCell(pageNumberText);
			pageNumberCell.setPaddingTop(4);
			pageNumberCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			pageNumberCell.setBorder(PdfPCell.TOP);
			pageNumberCell.setBorderColor(borderColor1);
			table.addCell(pageNumberCell);

			table.writeSelectedRows(0, -1, 36, 30, writer.getDirectContent());

			// 水印
			PdfContentByte canvas = writer.getDirectContentUnder();
			canvas.beginText();
			canvas.setFontAndSize(bfChinese, 30);
			// 设置水印文本的颜色和透明度
			Color lightGray = new Color(192, 192, 192);
			PdfGState gState = new PdfGState();
			// 设置透明度，可以根据需要调整透明度值
			gState.setFillOpacity(0.4f);
			canvas.setGState(gState);
			canvas.setColorFill(lightGray);

			// 水印文本和位置
			String watermarkText = "\t安徽中医药大学云诊科技 提供技术支持\t\t\t安徽中医药大学云诊科技 提供技术支持";
			// 添加水印到不同的位置, 595.0,842.0
			canvas.showTextAligned(Element.ALIGN_LEFT, watermarkText, 200, 0, 45);
			//canvas.showTextAligned(Element.ALIGN_LEFT, watermarkText, 400, 0, 45);
			//canvas.showTextAligned(Element.ALIGN_LEFT, watermarkText, 0, 0, 45);
			canvas.showTextAligned(Element.ALIGN_LEFT, watermarkText, 0, 200, 45);
			//canvas.showTextAligned(Element.ALIGN_LEFT, watermarkText, 0, 400, 45);
			canvas.showTextAligned(Element.ALIGN_LEFT, watermarkText, 0, 600, 45);
			canvas.endText();

		}
	}

	/**
	 * 第一页，标题+个人信息+二维码
	 *
	 * @param document
	 */
	public static void onePage(Document document) throws Exception {
		// 数据库数据
		AiConsultFlowVO detailVo = pdfResVo.getDetailVo();

		// 标题图片
		document.add(PdfUtils.newParagraph("\n", font16));
		document.add(PdfUtils.newImage(testTitleUrl1, Element.ALIGN_CENTER, 400, 100));

		// 标题
		document.add(PdfUtils.newParagraph("\n中医舌诊健康状态辨识报告\n\n\n",
				new Font(bfChinese, 36, Font.BOLD, new Color(23, 53, 93)), Element.ALIGN_CENTER));

		// 个人信息
		PdfPTable table = new PdfPTable(2);
		// 宽度100%填充
		table.setWidthPercentage(100);
		// 设置表格无边框
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		// 创建表格的的行对象集合
		List<PdfPRow> listRow = table.getRows();
		// 将表格设置为2列，并指定列宽
		table.setWidths(new float[]{6f, 4f});

		/*PdfPCell[] cells1 = new PdfPCell[2];
		cells1[0] = PdfUtils.newCell("\t黄佳乐\n", font20, 45, PdfPCell.NO_BORDER);
		// 二维码
		cells1[1] = PdfUtils.newCell("", font20, 0, 5, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		cells1[1].addElement(PdfUtils.newImage(testImgUrl1, 120, 120));
		// 二维码底下文案
		cells1[1].addElement(PdfUtils.newParagraph(Arrays.asList("\t\t扫描二维码\n", "使用手机查看电子版\n"),
				new Font(bfChinese, 14, Font.BOLD, new Color(23, 53, 93)), Element.ALIGN_LEFT));

		PdfPCell[] cells2 = new PdfPCell[2];
		cells2[0] = PdfUtils.newCell("\t年龄：18\n", font20, 45, PdfPCell.NO_BORDER);
		PdfPCell[] cells3 = new PdfPCell[2];
		cells3[0] = PdfUtils.newCell("\t性别：男\n", font20, 45, PdfPCell.NO_BORDER);
		PdfPCell[] cells4 = new PdfPCell[2];
		cells4[0] = PdfUtils.newCell("\t联系电话：18616919418\n", font20, 45, PdfPCell.NO_BORDER);
		PdfPCell[] cells5 = new PdfPCell[2];
		cells5[0] = PdfUtils.newCell("\t报告时间：2023-9-15\n", font20, 45, PdfPCell.NO_BORDER);*/
		PdfPCell[] cells1 = new PdfPCell[2];
		cells1[0] = PdfUtils.newCell("\t" + detailVo.getName() + "\n", font20, 45, PdfPCell.NO_BORDER);
		// 二维码
		cells1[1] = PdfUtils.newCell("", font20, 0, 5, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		cells1[1].addElement(PdfUtils.newImage(testImgUrl1, 120, 120));
		// 二维码底下文案
		cells1[1].addElement(PdfUtils.newParagraph(Arrays.asList("\t\t扫描二维码\n", "使用手机查看电子版\n"),
				new Font(bfChinese, 14, Font.BOLD, new Color(23, 53, 93)), Element.ALIGN_LEFT));

		PdfPCell[] cells2 = new PdfPCell[2];
		cells2[0] = PdfUtils.newCell("\t年龄：" + detailVo.getAge() + "\n", font20, 45, PdfPCell.NO_BORDER);
		PdfPCell[] cells3 = new PdfPCell[2];
		cells3[0] = PdfUtils.newCell("\t性别：" + detailVo.getGenderDesc() + "\n", font20, 45, PdfPCell.NO_BORDER);
		PdfPCell[] cells4 = new PdfPCell[2];
		cells4[0] = PdfUtils.newCell("\t联系电话：" + detailVo.getUserShopDetailVO().getMobile() + "\n", font20, 45, PdfPCell.NO_BORDER);
		PdfPCell[] cells5 = new PdfPCell[2];
		cells5[0] = PdfUtils.newCell("\t报告时间：" + DateUtil.formatDate(detailVo.getCreateTime()) + "\n", font20, 45, PdfPCell.NO_BORDER);

		listRow.add(new PdfPRow(cells1));
		listRow.add(new PdfPRow(cells2));
		listRow.add(new PdfPRow(cells3));
		listRow.add(new PdfPRow(cells4));
		listRow.add(new PdfPRow(cells5));
		document.add(table);

	}

	/**
	 * 第二页，目录+图片
	 *
	 * @param document
	 * @throws Exception
	 */
	public static void twoPage(Document document) throws Exception {
		document.newPage();

		// 间距
		document.add(PdfUtils.newParagraph("\n\n", font30));

		PdfPTable table = new PdfPTable(2);
		// 宽度100%填充
		table.setWidthPercentage(100);
		// 设置表格无边框
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		// 创建表格的的行对象集合
		List<PdfPRow> listRow = table.getRows();
		// 将表格设置为2列，并指定列宽
		table.setWidths(new float[]{3f, 7f});

		PdfPCell[] cells0 = new PdfPCell[2];
		cells0[0] = PdfUtils.newCell("\t目\n\n\n\t录", new Font(bfChinese, 60, Font.BOLD),
				0, 5, Element.ALIGN_LEFT, null, PdfPCell.RIGHT);
		// 设置边框宽度为3
		cells0[0].setBorderWidth(3f);
		cells0[1] = PdfUtils.newCell("\t一、健康状态\n", font30, 60, PdfPCell.NO_BORDER);
		listRow.add(new PdfPRow(cells0));

		PdfPCell[] cells1 = new PdfPCell[2];
		cells1[1] = PdfUtils.newCell("\t二、舌象辨识\n", font30, 60, PdfPCell.NO_BORDER);
		listRow.add(new PdfPRow(cells1));

		PdfPCell[] cells2 = new PdfPCell[2];
		cells2[1] = PdfUtils.newCell("\t三、舌象辨识\n", font30, 60, PdfPCell.NO_BORDER);
		listRow.add(new PdfPRow(cells2));

		PdfPCell[] cells3 = new PdfPCell[2];
		cells3[1] = PdfUtils.newCell("\t四、健康分析\n", font30, 60, PdfPCell.NO_BORDER);
		listRow.add(new PdfPRow(cells3));

		PdfPCell[] cells4 = new PdfPCell[2];
		cells4[1] = PdfUtils.newCell("\t五、调理方案\n", font30, 60, PdfPCell.NO_BORDER);
		listRow.add(new PdfPRow(cells4));

		document.add(table);

		// 图片
		document.add(PdfUtils.newImage(testImgUrl3, null, 300, 300, 200, 50));
	}

	/**
	 * 第三页，健康状态+舌象辨识
	 *
	 * @param document
	 * @throws Exception
	 */
	public static void threePage(Document document) throws Exception {
		// 数据库数据
		AiConsultFlowVO detailVo = pdfResVo.getDetailVo();
		AiConsultFlowVO tongueVo = pdfResVo.getTongueVo();

		document.newPage();

		// 标题
		document.add(PdfUtils.newParagraph("一、健康状态\n\n", font24Bold));
		// 健康状态
		PdfPTable table0 = new PdfPTable(1);
		table0.setWidthPercentage(100);
		List<PdfPRow> listRow0 = table0.getRows();
		table0.setWidths(new float[]{1f});

		PdfPCell[] cells01 = new PdfPCell[1];
		//cells01[0] = PdfUtils.newCellLeft("健康状态：痰湿 (痰阻心脉证)");
		cells01[0] = PdfUtils.newCellLeft("健康状态：" + detailVo.getDiagnosticResults());
		cells01[0].setBackgroundColor(backgroundColor1);
		listRow0.add(new PdfPRow(cells01));
		PdfPCell[] cells02 = new PdfPCell[1];
		//cells02[0] = PdfUtils.newCellLeft("综合舌象、面象分析属于亚健康状态，建议您一周后再次检测");
		cells02[0] = PdfUtils.newCellLeft(detailVo.getTongueTips());
		listRow0.add(new PdfPRow(cells02));
		document.add(table0);
		document.add(PdfUtils.newParagraph("\n", font16));

		// 标题
		document.add(PdfUtils.newParagraph("二、舌象辨识\n", font24Bold));

		// 1.舌面图像采集结果
		document.add(PdfUtils.newParagraph("舌面图像采集结果\n", font20Bold, Element.ALIGN_CENTER));
		//document.add(PdfUtils.newImage(testImgUrl2, Element.ALIGN_CENTER, 100, 150));
		document.add(PdfUtils.newImage(tongueVo.getTongueSurfaceImg(), Element.ALIGN_CENTER, 100, 150));
		document.add(PdfUtils.newParagraph("舌面\n\n", font16, Element.ALIGN_CENTER));

		// 2.舌象分析结果汇总
		PdfPTable table1 = new PdfPTable(2);
		table1.setWidthPercentage(100);

		List<PdfPRow> listRow1 = table1.getRows();
		table1.setWidths(new float[]{1f, 3f});

		PdfPCell[] cells11 = new PdfPCell[2];
		cells11[0] = PdfUtils.newCell("舌象分析结果汇总", font16Bold, 2, 0, Element.ALIGN_CENTER, null, borderColor1);
		listRow1.add(new PdfPRow(cells11));

		/*Boolean flag = true;
		for (int i = 0; i < 6; i++) {
			PdfPCell[] cells12 = new PdfPCell[2];
			cells12[0] = PdfUtils.newCellCenter("舌色：" + i);
			if (flag) {
				cells12[0].setBackgroundColor(backgroundColor1);
			}
			cells12[1] = PdfUtils.newCellLeft("淡红舌");
			if (flag) {
				cells12[1].setBackgroundColor(backgroundColor1);
			}
			listRow1.add(new PdfPRow(cells12));
			flag = !flag;
		}*/
		Boolean flag = true;
		for (AiReportItemVO tongueItemVO : tongueVo.getAiTongueItemVOList()) {
			PdfPCell[] cells12 = new PdfPCell[2];
			cells12[0] = PdfUtils.newCellCenter(tongueItemVO.getKey());
			if (flag) {
				cells12[0].setBackgroundColor(backgroundColor1);
			}
			cells12[1] = PdfUtils.newCellLeft(tongueItemVO.getValue());
			if (flag) {
				cells12[1].setBackgroundColor(backgroundColor1);
			}
			listRow1.add(new PdfPRow(cells12));
			flag = !flag;
		}

		document.add(table1);
		document.add(PdfUtils.newParagraph("\n", font16));

		/* 暂无
		// 3.舌象特征变化解析
		PdfPTable table2 = new PdfPTable(2);
		table2.setWidthPercentage(100);
		List<PdfPRow> listRow2 = table2.getRows();
		table2.setWidths(new float[]{1f, 1f});

		PdfPCell[] cells21 = new PdfPCell[2];
		cells21[0] = PdfUtils.newCell("结合 2023-09-15 17:12 检测,舌象特征变化解析：", font16Bold, 2, 0, Element.ALIGN_CENTER, null, borderColor1);
		listRow2.add(new PdfPRow(cells21));

		PdfPCell[] cells22 = new PdfPCell[2];
		cells22[0] = PdfUtils.newCellCenter("特征变化");
		cells22[0].setBackgroundColor(backgroundColor1);
		cells22[1] = PdfUtils.newCellCenter("病理意义");
		cells22[1].setBackgroundColor(backgroundColor1);
		listRow2.add(new PdfPRow(cells22));
		PdfPCell[] cells23 = new PdfPCell[2];
		cells23[0] = PdfUtils.newCellLeft("【厚苔 腻】变成【薄苔 腻】");
		cells23[1] = PdfUtils.newCellLeft("邪气渐退，仍有余邪");
		listRow2.add(new PdfPRow(cells23));

		document.add(table2);
		document.add(PdfUtils.newParagraph("\n", font16));*/

		// 4.中医解析
		/*PdfPTable table3 = new PdfPTable(3);
		table3.setWidthPercentage(100);
		List<PdfPRow> listRow3 = table3.getRows();
		table3.setWidths(new float[]{1f, 2f, 6f});

		PdfPCell[] cells31 = new PdfPCell[3];
		cells31[0] = PdfUtils.newCell("舌色——中医解析", font16Bold, 3, 0, Element.ALIGN_CENTER, null, borderColor1);
		listRow3.add(new PdfPRow(cells31));

		PdfPCell[] cells32 = new PdfPCell[3];
		cells32[0] = PdfUtils.newCellCenter("人工智能分析", 0, 2);
		cells32[1] = PdfUtils.newCellCenter("舌色分析");
		cells32[2] = PdfUtils.newCell("");
		cells32[2].setBorderColor(borderColor1);
		cells32[2].addElement(PdfUtils.newImage(testImgUrl2, 100, 100));
		listRow3.add(new PdfPRow(cells32));

		PdfPCell[] cells33 = new PdfPCell[3];
		cells33[1] = PdfUtils.newCellCenter("辨识结果");
		cells33[1].setBackgroundColor(backgroundColor1);
		cells33[2] = PdfUtils.newCellLeft("淡红舌");
		cells33[2].setBackgroundColor(backgroundColor1);
		listRow3.add(new PdfPRow(cells33));

		PdfPCell[] cells34 = new PdfPCell[3];
		cells34[0] = PdfUtils.newCellCenter("中医解析", 0, 2);
		cells34[1] = PdfUtils.newCellCenter("中医描述");
		cells34[2] = PdfUtils.newCellLeft("【淡红舌】正常人舌色，舌质呈现润泽红活。【淡红舌】正常人舌色，舌质呈现润泽红活。【淡红舌】正常人舌色，舌质呈现润泽红活。【淡红舌】正常人舌色，舌质呈现润泽红活。");
		listRow3.add(new PdfPRow(cells34));

		PdfPCell[] cells35 = new PdfPCell[3];
		cells35[1] = PdfUtils.newCellCenter("病理意义");
		cells35[1].setBackgroundColor(backgroundColor1);
		cells35[2] = PdfUtils.newCellLeft("【淡红舌】正常舌色，或者处于病情轻浅阶段。");
		cells35[2].setBackgroundColor(backgroundColor1);
		listRow3.add(new PdfPRow(cells35));

		document.add(table3);
		document.add(PdfUtils.newParagraph("\n", font16));
		document.add(table3);
		document.add(PdfUtils.newParagraph("\n", font16));
		document.add(table3);
		document.add(PdfUtils.newParagraph("\n", font16));*/
		for (AiReportItemVO tongueDetailVo : tongueVo.getAiTongueVOList()) {
			PdfPTable table3 = new PdfPTable(4);
			table3.setWidthPercentage(100);
			List<PdfPRow> listRow3 = table3.getRows();
			table3.setWidths(new float[]{1f, 2f, 3f, 3f});

			PdfPCell[] cells31 = new PdfPCell[4];
			cells31[0] = PdfUtils.newCell(tongueDetailVo.getKey() + "——中医解析", font16Bold, 4, 0, Element.ALIGN_CENTER, null, borderColor1);
			listRow3.add(new PdfPRow(cells31));

			PdfPCell[] cells32 = new PdfPCell[4];
			cells32[0] = PdfUtils.newCellCenter("人工智能分析", 0, 2);
			cells32[1] = PdfUtils.newCellCenter(tongueDetailVo.getKey() + "分析");
			if (StringUtils.isNotBlank(tongueDetailVo.getPhotoTwo())) {
				cells32[2] = PdfUtils.newCellLeft("");
				cells32[2].setBorderColor(borderColor1);
				cells32[2].addElement(PdfUtils.newParagraph("\n", font10));
				cells32[2].addElement(PdfUtils.newImage(tongueDetailVo.getPhoto(), Element.ALIGN_CENTER, 100, 100));

				cells32[3] = PdfUtils.newCellLeft("");
				cells32[3].setBorderColor(borderColor1);
				cells32[3].addElement(PdfUtils.newParagraph("\n", font10));
				cells32[3].addElement(PdfUtils.newImage(tongueDetailVo.getPhotoTwo(), Element.ALIGN_CENTER, 100, 100));
			} else {
				cells32[2] = PdfUtils.newCellLeft("", 2, 0);
				cells32[2].setBorderColor(borderColor1);
				cells32[2].addElement(PdfUtils.newParagraph("\n", font10));
				cells32[2].addElement(PdfUtils.newImage(tongueDetailVo.getPhoto(), Element.ALIGN_CENTER, 100, 100));
			}
			listRow3.add(new PdfPRow(cells32));

			PdfPCell[] cells33 = new PdfPCell[4];
			cells33[1] = PdfUtils.newCellCenter("辨识结果");
			cells33[1].setBackgroundColor(backgroundColor1);
			cells33[2] = PdfUtils.newCellLeft(tongueDetailVo.getField(), 2, 0);
			cells33[2].setBackgroundColor(backgroundColor1);
			listRow3.add(new PdfPRow(cells33));

			// 详情，举例：（【润】舌苔干湿适中，不滑不燥为润苔。</br> 【病理意义】舌苔水分正常。）
			List<String> strList = new ArrayList<>();
			strList.add("");
			strList.add("");
			if (StringUtils.isNotBlank(tongueDetailVo.getValue())) {
				// 去除br
				String str = tongueDetailVo.getValue().replaceAll("</br>", "\n");
				// 提取病理意义
				String[] strs = str.split("【病理意义】");
				if (strs.length == 1) {
					strList.add(0, strs[0]);
				} else if (strs.length > 1) {
					strList.add(0, strs[0]);
					strList.add(1, "【病理意义】" + strs[1]);
				}
			}

			PdfPCell[] cells34 = new PdfPCell[4];
			cells34[0] = PdfUtils.newCellCenter("中医解析", 0, 2);
			cells34[1] = PdfUtils.newCellCenter("中医描述");
			cells34[2] = PdfUtils.newCellLeft(strList.get(0), 2, 0);
			listRow3.add(new PdfPRow(cells34));

			PdfPCell[] cells35 = new PdfPCell[4];
			cells35[1] = PdfUtils.newCellCenter("病理意义");
			cells35[1].setBackgroundColor(backgroundColor1);
			cells35[2] = PdfUtils.newCellLeft(strList.get(1), 2, 0);
			cells35[2].setBackgroundColor(backgroundColor1);
			listRow3.add(new PdfPRow(cells35));

			document.add(table3);
			if (tongueDetailVo != tongueVo.getAiTongueVOList().get(tongueVo.getAiTongueVOList().size() - 1)) {
				document.add(PdfUtils.newParagraph("\n", font16));
			}

		}

	}

	/**
	 * 第四页，面部望诊
	 *
	 * @param document
	 * @throws Exception
	 */
	public static void fourPage(Document document) throws Exception {
		// 数据库数据
		AiConsultFlowVO faceVo = pdfResVo.getFaceVo();

		document.newPage();

		// 标题
		document.add(PdfUtils.newParagraph("三、面部望诊\n", font24Bold));

		// 1.面部图像采集结果
		document.add(PdfUtils.newParagraph("面部图像采集结果\n", font20Bold, Element.ALIGN_CENTER));
		//document.add(PdfUtils.newImage(testImgUrl2, Element.ALIGN_CENTER, 100, 150));
		document.add(PdfUtils.newImage(faceVo.getFaceFrontImg(), Element.ALIGN_CENTER, 100, 150));
		document.add(PdfUtils.newParagraph("正面象\n\n", font16, Element.ALIGN_CENTER));

		// 2.面部分析结果汇总
		PdfPTable table1 = new PdfPTable(2);
		table1.setWidthPercentage(100);
		List<PdfPRow> listRow1 = table1.getRows();
		table1.setWidths(new float[]{1f, 3f});

		PdfPCell[] cells11 = new PdfPCell[2];
		cells11[0] = PdfUtils.newCell("面部分析结果汇总", font16Bold, 2, 0, Element.ALIGN_CENTER, null, borderColor1);
		listRow1.add(new PdfPRow(cells11));

		/*Boolean flag = true;
		for (int i = 0; i < 6; i++) {
			PdfPCell[] cells12 = new PdfPCell[2];
			cells12[0] = PdfUtils.newCellCenter("面色：" + i);
			if (flag) {
				cells12[0].setBackgroundColor(backgroundColor1);
			}
			cells12[1] = PdfUtils.newCellLeft("面色红黄隐隐，明润含蓄");
			if (flag) {
				cells12[1].setBackgroundColor(backgroundColor1);
			}
			listRow1.add(new PdfPRow(cells12));
			flag = !flag;
		}*/
		Boolean flag = true;
		for (AiReportItemVO faceItemVo : faceVo.getAiFaceItemVOList()) {
			PdfPCell[] cells12 = new PdfPCell[2];
			cells12[0] = PdfUtils.newCellCenter(faceItemVo.getKey());
			if (flag) {
				cells12[0].setBackgroundColor(backgroundColor1);
			}
			cells12[1] = PdfUtils.newCellLeft(faceItemVo.getValue());
			if (flag) {
				cells12[1].setBackgroundColor(backgroundColor1);
			}
			listRow1.add(new PdfPRow(cells12));
			flag = !flag;
		}

		document.add(table1);
		document.add(PdfUtils.newParagraph("\n", font16));

		// 4.中医解析
		/*PdfPTable table3 = new PdfPTable(3);
		table3.setWidthPercentage(100);
		List<PdfPRow> listRow3 = table3.getRows();
		table3.setWidths(new float[]{1f, 2f, 6f});

		PdfPCell[] cells31 = new PdfPCell[3];
		cells31[0] = PdfUtils.newCell("面色——中医解析", font16Bold, 3, 0, Element.ALIGN_CENTER, null, borderColor1);
		listRow3.add(new PdfPRow(cells31));

		PdfPCell[] cells32 = new PdfPCell[3];
		cells32[0] = PdfUtils.newCellCenter("人工智能分析", 0, 2);
		cells32[1] = PdfUtils.newCellCenter("面色分析");
		cells32[2] = PdfUtils.newCell("");
		cells32[2].setBorderColor(borderColor1);
		cells32[2].addElement(PdfUtils.newImage(testImgUrl2, 100, 100));
		listRow3.add(new PdfPRow(cells32));

		PdfPCell[] cells33 = new PdfPCell[3];
		cells33[1] = PdfUtils.newCellCenter("辨识结果");
		cells33[1].setBackgroundColor(backgroundColor1);
		cells33[2] = PdfUtils.newCellLeft("面色红黄隐隐，明润含蓄");
		cells33[2].setBackgroundColor(backgroundColor1);
		listRow3.add(new PdfPRow(cells33));

		PdfPCell[] cells34 = new PdfPCell[3];
		cells34[0] = PdfUtils.newCellCenter("中医解析", 0, 2);
		cells34[1] = PdfUtils.newCellCenter("中医描述");
		cells34[2] = PdfUtils.newCellLeft("【正常面色】面色红黄隐隐，明润含蓄。【正常面色】面色红黄隐隐，明润含蓄。【正常面色】面色红黄隐隐，明润含蓄。");
		listRow3.add(new PdfPRow(cells34));

		PdfPCell[] cells35 = new PdfPCell[3];
		cells35[1] = PdfUtils.newCellCenter("病理意义");
		cells35[1].setBackgroundColor(backgroundColor1);
		cells35[2] = PdfUtils.newCellLeft("【病理意义】神气、胃气的正常色泽表现。");
		cells35[2].setBackgroundColor(backgroundColor1);
		listRow3.add(new PdfPRow(cells35));

		document.add(table3);
		document.add(PdfUtils.newParagraph("\n", font16));
		document.add(table3);
		document.add(PdfUtils.newParagraph("\n", font16));
		document.add(table3);
		document.add(PdfUtils.newParagraph("\n", font16));*/
		for (AiReportItemVO faceDetailVo : faceVo.getAiFaceVOList()) {
			PdfPTable table3 = new PdfPTable(4);
			table3.setWidthPercentage(100);
			List<PdfPRow> listRow3 = table3.getRows();
			table3.setWidths(new float[]{1f, 2f, 3f, 3f});

			PdfPCell[] cells31 = new PdfPCell[4];
			cells31[0] = PdfUtils.newCell(faceDetailVo.getKey() + "——中医解析", font16Bold, 4, 0, Element.ALIGN_CENTER, null, borderColor1);
			listRow3.add(new PdfPRow(cells31));

			PdfPCell[] cells32 = new PdfPCell[4];
			cells32[0] = PdfUtils.newCellCenter("人工智能分析", 0, 2);
			cells32[1] = PdfUtils.newCellCenter(faceDetailVo.getKey() + "分析");
			if (StringUtils.isNotBlank(faceDetailVo.getPhotoTwo())) {
				cells32[2] = PdfUtils.newCellLeft("");
				cells32[2].setBorderColor(borderColor1);
				cells32[2].addElement(PdfUtils.newParagraph("\n", font10));
				cells32[2].addElement(PdfUtils.newImage(faceDetailVo.getPhoto(), Element.ALIGN_CENTER, 100, 100));

				cells32[3] = PdfUtils.newCellLeft("");
				cells32[3].setBorderColor(borderColor1);
				cells32[3].addElement(PdfUtils.newParagraph("\n", font10));
				cells32[3].addElement(PdfUtils.newImage(faceDetailVo.getPhotoTwo(), Element.ALIGN_CENTER, 100, 100));
			} else {
				cells32[2] = PdfUtils.newCellLeft("", 2, 0);
				cells32[2].setBorderColor(borderColor1);
				cells32[2].addElement(PdfUtils.newParagraph("\n", font10));
				cells32[2].addElement(PdfUtils.newImage(faceDetailVo.getPhoto(), Element.ALIGN_CENTER, 100, 100));
			}
			listRow3.add(new PdfPRow(cells32));

			PdfPCell[] cells33 = new PdfPCell[4];
			cells33[1] = PdfUtils.newCellCenter("辨识结果");
			cells33[1].setBackgroundColor(backgroundColor1);
			cells33[2] = PdfUtils.newCellLeft(faceDetailVo.getField(), 2, 0);
			cells33[2].setBackgroundColor(backgroundColor1);
			listRow3.add(new PdfPRow(cells33));

			// 详情，举例："【厚苔】透过舌苔不能见到舌体。</br>【病理意义】体内有湿，或有积食。</br>【腻苔】苔质颗粒细腻致密，融合成片，紧贴于舌面，揩之不去，刮之不易脱落。</br>【病理意义】多与湿浊，痰饮，食积相关。</br>";
			List<String> strList = new ArrayList<>();
			String str1 = "";
			String str2 = "";
			if (StringUtils.isNotBlank(faceDetailVo.getValue())) {
				// 去除br
				String str = faceDetailVo.getValue().replaceAll("</br>", "");
				// 分开
				String[] strs = str.split("【");
				for (String s : strs) {
					if (StringUtils.isNotBlank(s)) {
						if (s.startsWith("病理意义")) {
							str2 = str2 + "【" + s + "\n";
						} else {
							str1 = str1 + "【" + s + "\n";
						}
					}
				}
			}
			strList.add(str1.length() > 0 ? str1.substring(0, str1.length() - 1) : "");
			strList.add(str2.length() > 0 ? str2.substring(0, str2.length() - 1) : "");

			PdfPCell[] cells34 = new PdfPCell[4];
			cells34[0] = PdfUtils.newCellCenter("中医解析", 0, 2);
			cells34[1] = PdfUtils.newCellCenter("中医描述");
			cells34[2] = PdfUtils.newCellLeft(strList.get(0), 2, 0);
			listRow3.add(new PdfPRow(cells34));

			PdfPCell[] cells35 = new PdfPCell[4];
			cells35[1] = PdfUtils.newCellCenter("病理意义");
			cells35[1].setBackgroundColor(backgroundColor1);
			cells35[2] = PdfUtils.newCellLeft(strList.get(1), 2, 0);
			cells35[2].setBackgroundColor(backgroundColor1);
			listRow3.add(new PdfPRow(cells35));

			document.add(table3);
			if (faceDetailVo != faceVo.getAiFaceVOList().get(faceVo.getAiFaceVOList().size() - 1)) {
				document.add(PdfUtils.newParagraph("\n", font16));
			}

		}

	}

	/**
	 * 第五页，健康分析
	 *
	 * @param document
	 * @throws Exception
	 */
	public static void fivePage(Document document) throws Exception {
		// 数据库数据
		AiConsultFlowVO detailVo = pdfResVo.getDetailVo();
		AiTreatPlanJsonVo healthVo = pdfResVo.getHealthVo();

		document.newPage();

		// 标题
		document.add(PdfUtils.newParagraph("四、健康分析\n", font24Bold));

		// 1.健康分析结果
		document.add(PdfUtils.newParagraph("健康分析结果\n\n", font20Bold, Element.ALIGN_CENTER));

		PdfPTable table1 = new PdfPTable(2);
		table1.setWidthPercentage(100);
		List<PdfPRow> listRow1 = table1.getRows();
		table1.setWidths(new float[]{1f, 3f});

		PdfPCell[] cells12 = new PdfPCell[2];
		cells12[0] = PdfUtils.newCell("依据您的舌象、面象及近期身体感受，AI中医辨识出您的健康状态", font16Bold, 2, 0, Element.ALIGN_CENTER, null, borderColor1);
		listRow1.add(new PdfPRow(cells12));

		PdfPCell[] cells13 = new PdfPCell[2];
		cells13[0] = PdfUtils.newCellCenter("健康状态");
		cells13[0].setBackgroundColor(backgroundColor1);
		//cells13[1] = PdfUtils.newCellLeft("气虚痰湿");
		cells13[1] = PdfUtils.newCellLeft(detailVo.getDiagnosticResults());
		cells13[1].setBackgroundColor(backgroundColor1);
		listRow1.add(new PdfPRow(cells13));

		PdfPCell[] cells14 = new PdfPCell[2];
		cells14[0] = PdfUtils.newCellCenter("风险评分");
		cells14[0].setBackgroundColor(backgroundColor1);
		//cells14[1] = PdfUtils.newCellLeft("80.29 分（风险）\n综合舌象、面象分析属于亚健康状态，建议您一周后再次检测。");
		cells14[1] = PdfUtils.newCellLeft(detailVo.getTongueScore() + "\n" + detailVo.getTongueTips());
		cells14[1].setBackgroundColor(backgroundColor1);
		listRow1.add(new PdfPRow(cells14));

		document.add(table1);

		// 2.（一）主要表现
		document.add(PdfUtils.newParagraph("（一）主要表现\n\n", font20Bold, Element.ALIGN_CENTER));

		PdfPTable table2 = new PdfPTable(1);
		table2.setWidthPercentage(100);
		List<PdfPRow> listRow2 = table2.getRows();
		table2.setWidths(new float[]{1f});

		/*PdfPCell[] cells22 = new PdfPCell[1];
		cells22[0] = PdfUtils.newCellLeft("面色微黄或虚浮或淡黄而暗，唇色淡白，毛发少光泽，多油脂。");
		listRow2.add(new PdfPRow(cells22));

		PdfPCell[] cells23 = new PdfPCell[1];
		cells23[0] = PdfUtils.newCellLeft("口黏，痰多，气短胸闷，肢体酸重，易疲倦乏力。");
		cells23[0].setBackgroundColor(backgroundColor1);
		listRow2.add(new PdfPRow(cells23));

		PdfPCell[] cells24 = new PdfPCell[1];
		cells24[0] = PdfUtils.newCellLeft("性格喜安静，语声低弱或懒言。");
		listRow2.add(new PdfPRow(cells24));*/
		Boolean flag2 = true;
		for (AiTreatPlanJsonVo.HealthCareExample healthCareExample : healthVo.getMain_performance()) {
			PdfPCell[] cells22 = new PdfPCell[1];
			cells22[0] = PdfUtils.newCellLeft(healthCareExample.getMessage());
			if (flag2) {
				cells22[0].setBackgroundColor(backgroundColor1);
			}
			listRow2.add(new PdfPRow(cells22));
			flag2 = !flag2;
		}

		document.add(table2);

		// 3.健康风险
		document.add(PdfUtils.newParagraph("（二）风险提示\n\n", font20Bold, Element.ALIGN_CENTER));
		document.add(PdfUtils.newParagraph("1.健康风险\n\n", font20Bold, Element.ALIGN_CENTER));

		PdfPTable table3 = new PdfPTable(2);
		table3.setWidthPercentage(100);
		List<PdfPRow> listRow3 = table3.getRows();
		table3.setWidths(new float[]{1f, 1f});

		/*PdfPCell[] cells31 = new PdfPCell[2];
		cells31[0] = PdfUtils.newCellCenter("");
		cells31[0].addElement(PdfUtils.newImage(testImgUrl2, 200, 300));
		cells31[1] = PdfUtils.newCell("\n感冒\n眩晕\n流涕\n咳痰\n中风\n", font16Bold, 0, 0, Element.ALIGN_LEFT, null, borderColor1);
		listRow3.add(new PdfPRow(cells31));*/
		if (CollectionUtils.isNotEmpty(healthVo.getHighRiskDiseaseList())) {
			PdfPCell[] cells31 = new PdfPCell[2];
			cells31[0] = PdfUtils.newCellCenter("");
			cells31[0].addElement(PdfUtils.newImage(healthVo.getHighRiskDiseaseList().get(0).getImg(), 200, 300));
			//眩晕,健忘,精神不振,心悸,视力模糊
			String highStr = "";
			if (StringUtils.isNotBlank(healthVo.getHighRiskDiseaseList().get(0).getDesc())) {
				highStr = "\n" + healthVo.getHighRiskDiseaseList().get(0).getDesc().replaceAll(",", "\n") + "\n";
			}
			cells31[1] = PdfUtils.newCell(highStr, font16Bold, 0, 0, Element.ALIGN_LEFT, null, borderColor1);
			listRow3.add(new PdfPRow(cells31));
		}

		document.add(table3);

		// todo 暂无 4.节气风险
		document.add(PdfUtils.newParagraph("2、节气风险\n\n", font20Bold, Element.ALIGN_CENTER));

		PdfPTable table4 = new PdfPTable(1);
		table4.setWidthPercentage(100);
		List<PdfPRow> listRow4 = table4.getRows();
		table4.setWidths(new float[]{1f});

		PdfPCell[] cells41 = new PdfPCell[1];
		cells41[0] = PdfUtils.newCellLeft("当前节气：白露 \t\t\t 患病风险：中");
		listRow4.add(new PdfPRow(cells41));

		PdfPCell[] cells42 = new PdfPCell[1];
		cells42[0] = PdfUtils.newCellLeft("提示：是气候转凉的开始，昼夜温差变化大，注意避免雾天出行，预防哮喘和支气管炎等病发作");
		cells42[0].setBackgroundColor(backgroundColor1);
		listRow4.add(new PdfPRow(cells42));

		PdfPCell[] cells43 = new PdfPCell[1];
		cells43[0] = PdfUtils.newCellLeft("说明：气虚不能运化水湿，聚而生痰，常见头晕乏力、口中粘腻、胸闷痰多等表现，夏季降水量增多患病风险出现上升。");
		listRow4.add(new PdfPRow(cells43));

		document.add(table4);

		// 5.发生原因
		document.add(PdfUtils.newParagraph("（三）发生原因\n\n", font20Bold, Element.ALIGN_CENTER));

		PdfPTable table5 = new PdfPTable(1);
		table5.setWidthPercentage(100);
		List<PdfPRow> listRow5 = table5.getRows();
		table5.setWidths(new float[]{1f});

		/*PdfPCell[] cells51 = new PdfPCell[1];
		cells51[0] = PdfUtils.newCellLeft("多因素体气虚；或病久气虚；气虚不足以维持正常津液代谢，致使机体水湿停聚，形成气虚痰湿的证候。");
		listRow5.add(new PdfPRow(cells51));

		PdfPCell[] cells52 = new PdfPCell[1];
		cells52[0] = PdfUtils.newCellLeft("1、湿邪阻滞气机——气候潮湿，或淋雨涉水，或久居潮湿之所，湿邪侵袭人体，或喜食肥甘厚腻，损伤脾胃，致使湿浊内生，困阻于脾，致气虚痰湿共存。");
		cells52[0].setBackgroundColor(backgroundColor1);
		listRow5.add(new PdfPRow(cells52));

		PdfPCell[] cells53 = new PdfPCell[1];
		cells53[0] = PdfUtils.newCellLeft("2、先天不足或后天失养——禀赋不足，或久病伤正，或劳倦太过伤脾，气血亏乏，不能运化水湿，致气虚痰湿。");
		listRow5.add(new PdfPRow(cells53));

		PdfPCell[] cells54 = new PdfPCell[1];
		cells54[0] = PdfUtils.newCellLeft("3、久病——久病不愈，失于调养，导致气血亏乏，不能运化水湿，致气虚痰湿。");
		listRow5.add(new PdfPRow(cells54));*/
		Boolean flag5 = true;
		for (AiTreatPlanJsonVo.HealthCareExample healthCareExample : healthVo.getOccur_reason()) {
			PdfPCell[] cells51 = new PdfPCell[1];
			cells51[0] = PdfUtils.newCellLeft(healthCareExample.getMessage());
			if (flag5) {
				cells51[0].setBackgroundColor(backgroundColor1);
			}
			listRow5.add(new PdfPRow(cells51));
			flag5 = !flag5;
		}

		document.add(table5);

	}

	/**
	 * 第六页，调理方案+门店信息
	 *
	 * @param document
	 * @throws Exception
	 */
	public static void sixPage(Document document) throws Exception {
		// 数据库数据
		AiConsultFlowVO detailVo = pdfResVo.getDetailVo();
		AiTreatPlanJsonVo conditioningVo = pdfResVo.getConditioningVo();

		document.newPage();

		// 标题
		document.add(PdfUtils.newParagraph("五、调理方案\n", font24Bold));

		// 1.（一）药物保健
		document.add(PdfUtils.newParagraph("（一）药物保健\n\n", font20Bold, Element.ALIGN_CENTER));

		/*PdfPTable table1 = new PdfPTable(3);
		table1.setWidthPercentage(100);
		List<PdfPRow> listRow1 = table1.getRows();
		table1.setWidths(new float[]{1f, 1f, 1f});

		PdfPCell[] cells11 = new PdfPCell[3];
		cells11[0] = PdfUtils.newCell("①常用补气类中药单品：西洋参、黄精、白术等；", font16Bold, 3, 0, Element.ALIGN_LEFT, null, borderColor1);
		cells11[0].setBackgroundColor(backgroundColor1);
		listRow1.add(new PdfPRow(cells11));

		PdfPCell[] cells12 = new PdfPCell[3];
		cells12[0] = PdfUtils.newCell("", font16Bold, 0, 0, Element.ALIGN_CENTER, PdfPCell.LEFT, borderColor1);
		cells12[0].addElement(PdfUtils.newImage(testImgUrl3, Element.ALIGN_CENTER, 50, 50));
		cells12[1] = PdfUtils.newCell("", font16Bold, 0, 0, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, borderColor1);
		cells12[1].addElement(PdfUtils.newImage(testImgUrl3, Element.ALIGN_CENTER, 50, 50));
		cells12[2] = PdfUtils.newCell("", font16Bold, 0, 0, Element.ALIGN_CENTER, PdfPCell.RIGHT, borderColor1);
		cells12[2].addElement(PdfUtils.newImage(testImgUrl3, Element.ALIGN_CENTER, 50, 50));
		listRow1.add(new PdfPRow(cells12));

		PdfPCell[] cells13 = new PdfPCell[3];
		cells13[0] = PdfUtils.newCell("西洋参", font16Bold, 0, 0, Element.ALIGN_CENTER, PdfPCell.LEFT, borderColor1);
		cells13[1] = PdfUtils.newCell("黄精", font16Bold, 0, 0, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, borderColor1);
		cells13[2] = PdfUtils.newCell("白术", font16Bold, 0, 0, Element.ALIGN_CENTER, PdfPCell.RIGHT, borderColor1);
		listRow1.add(new PdfPRow(cells13));

		document.add(table1);*/
		if (CollectionUtils.isNotEmpty(conditioningVo.getDrug_health_care())) {
			Integer cellNum = 1;
			if (CollectionUtils.isNotEmpty(conditioningVo.getDrug_health_care().get(0).getItems())) {
				cellNum = conditioningVo.getDrug_health_care().get(0).getItems().size();
			}

			PdfPTable table1 = new PdfPTable(cellNum);
			table1.setWidthPercentage(100);
			List<PdfPRow> listRow1 = table1.getRows();
			float[] f = new float[cellNum];
			for (int i = 0; i < cellNum; i++) {
				f[i] = 1f;
			}
			table1.setWidths(f);

			PdfPCell[] cells11 = new PdfPCell[cellNum];
			cells11[0] = PdfUtils.newCell(conditioningVo.getDrug_health_care().get(0).getMessage(), font16Bold, cellNum, 0, Element.ALIGN_LEFT, null, borderColor1);
			cells11[0].setBackgroundColor(backgroundColor1);
			listRow1.add(new PdfPRow(cells11));

			if (CollectionUtils.isNotEmpty(conditioningVo.getDrug_health_care().get(0).getItems())) {
				PdfPCell[] cells12 = new PdfPCell[cellNum];
				for (int i = 0; i < conditioningVo.getDrug_health_care().get(0).getItems().size(); i++) {
					AiTreatPlanJsonVo.HealthCareExampleItems item = conditioningVo.getDrug_health_care().get(0).getItems().get(i);
					cells12[i] = PdfUtils.newCell("", font16Bold, 0, 0, Element.ALIGN_CENTER, PdfPCell.LEFT, borderColor1);
					cells12[i].addElement(PdfUtils.newImage(item.getPhoto(), Element.ALIGN_CENTER, 50, 50));
				}
				listRow1.add(new PdfPRow(cells12));

				PdfPCell[] cells13 = new PdfPCell[cellNum];
				for (int i = 0; i < conditioningVo.getDrug_health_care().get(0).getItems().size(); i++) {
					AiTreatPlanJsonVo.HealthCareExampleItems item = conditioningVo.getDrug_health_care().get(0).getItems().get(i);
					cells13[i] = PdfUtils.newCell(item.getName(), font16Bold, 0, 0, Element.ALIGN_CENTER, PdfPCell.LEFT, borderColor1);
				}
				listRow1.add(new PdfPRow(cells13));
			}
			document.add(table1);
			document.add(PdfUtils.newParagraph("", font16));
		}

		/*PdfPTable table2 = new PdfPTable(2);
		table2.setWidthPercentage(100);
		List<PdfPRow> listRow2 = table2.getRows();
		table2.setWidths(new float[]{1f, 1f});

		PdfPCell[] cells21 = new PdfPCell[2];
		cells21[0] = PdfUtils.newCell("②常用化湿类中药单品：砂仁、陈皮、豆蔻等；", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
		cells21[0].setBackgroundColor(backgroundColor1);
		listRow2.add(new PdfPRow(cells21));

		PdfPCell[] cells22 = new PdfPCell[2];
		cells22[0] = PdfUtils.newCell("③常用益气化湿类复方：二陈平胃散、导痰汤、四苓散等。", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
		cells22[0].setBackgroundColor(backgroundColor1);
		listRow2.add(new PdfPRow(cells22));

		for (int i = 0; i < 3; i++) {
			PdfPCell[] cells23 = new PdfPCell[2];
			cells23[0] = PdfUtils.newCellCenter("");
			cells23[0].addElement(PdfUtils.newImage(testImgUrl3, Element.ALIGN_CENTER, 150, 150));
			cells23[0].addElement(PdfUtils.newParagraph("二陈平胃散", font16, Element.ALIGN_CENTER));
			cells23[1] = PdfUtils.newCellLeft("【功效】燥湿化痰，理气和中\n" +
					"【主治】食积咳嗽，五更为甚，\n" +
					"胸脘满闷，舌苔白腻，脉濡滑者。\n" +
					"【组成】熟半夏、白茯苓、广\n" +
					"皮、甘草、熟苍术、厚朴\n" +
					"【来源】《症因脉治》");
			listRow2.add(new PdfPRow(cells23));
		}

		PdfPCell[] cells24 = new PdfPCell[2];
		cells24[0] = PdfUtils.newCell("以上方案仅供参考，需要在专业中医师的指导下根据自身情况服用", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
		cells24[0].setBackgroundColor(backgroundColor1);
		listRow2.add(new PdfPRow(cells24));

		document.add(table2);*/
		if (CollectionUtils.isNotEmpty(conditioningVo.getDrug_health_care()) && conditioningVo.getDrug_health_care().size() > 1) {
			for (int i = 1; i < conditioningVo.getDrug_health_care().size(); i++) {
				AiTreatPlanJsonVo.HealthCareExample healthCareExample = conditioningVo.getDrug_health_care().get(i);
				PdfPTable table2 = new PdfPTable(2);
				table2.setWidthPercentage(100);
				List<PdfPRow> listRow2 = table2.getRows();
				table2.setWidths(new float[]{1f, 1f});

				PdfPCell[] cells22 = new PdfPCell[2];
				cells22[0] = PdfUtils.newCell(healthCareExample.getMessage(), font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
				cells22[0].setBackgroundColor(backgroundColor1);
				listRow2.add(new PdfPRow(cells22));

				for (AiTreatPlanJsonVo.HealthCareExampleItems item : healthCareExample.getItems()) {
					PdfPCell[] cells23 = new PdfPCell[2];
					cells23[0] = PdfUtils.newCellCenter("");
					cells23[0].addElement(PdfUtils.newImage(item.getPhoto(), Element.ALIGN_CENTER, 150, 150));
					cells23[0].addElement(PdfUtils.newParagraph(item.getName(), font16, Element.ALIGN_CENTER));
					cells23[1] = PdfUtils.newCellLeft(item.getDesc());
					listRow2.add(new PdfPRow(cells23));
				}

				document.add(table2);
				document.add(PdfUtils.newParagraph("", font16));
			}
		}

		// 2.（二）穴位保健
		document.add(PdfUtils.newParagraph("（二）穴位保健\n\n", font20Bold, Element.ALIGN_CENTER));

		/*PdfPTable table3 = new PdfPTable(2);
		table3.setWidthPercentage(100);
		List<PdfPRow> listRow3 = table3.getRows();
		table3.setWidths(new float[]{1f, 1f});

		PdfPCell[] cells31 = new PdfPCell[2];
		cells31[0] = PdfUtils.newCell("气虚痰湿的人群需要选择益气健脾、祛湿化痰的穴位为主，通过在这些穴位上进行点揉、艾灸、针刺，以帮助改善体质状况。根据您的健康状态辨识结果为您推荐如下方案：", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
		cells31[0].setBackgroundColor(backgroundColor1);
		listRow3.add(new PdfPRow(cells31));

		PdfPCell[] cells32 = new PdfPCell[2];
		cells32[0] = PdfUtils.newCell("【取穴】气海穴、足三里穴、丰隆穴、承山穴、脾俞穴、关元穴【方法】按揉（或艾灸，或贴敷，或针刺），每穴20-30分钟，隔日一次。", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
		cells32[0].setBackgroundColor(backgroundColor1);
		listRow3.add(new PdfPRow(cells32));

		for (int i = 0; i < 3; i++) {
			PdfPCell[] cells33 = new PdfPCell[2];
			cells33[0] = PdfUtils.newCell("气海穴的保健方法：", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			cells33[0].setBackgroundColor(backgroundColor1);
			listRow3.add(new PdfPRow(cells33));

			PdfPCell[] cells34 = new PdfPCell[2];
			cells34[0] = PdfUtils.newCellCenter("");
			cells34[0].addElement(PdfUtils.newImage(testImgUrl3, Element.ALIGN_CENTER, 150, 150));
			cells34[0].addElement(PdfUtils.newParagraph("二陈平胃散", font16, Element.ALIGN_CENTER));
			cells34[1] = PdfUtils.newCellLeft("【位置】在下腹部，脐中下1.5\n" +
					"寸，前正中线上。\n" +
					"【取穴】在下腹部，肚脐中央向\n" +
					"下与关元之间的中点处。");
			listRow3.add(new PdfPRow(cells34));
		}

		PdfPCell[] cells35 = new PdfPCell[2];
		cells35[0] = PdfUtils.newCell("以上方案仅做参考，具体操作建议进一步咨询专业中医师（医疗行为需要由专业中医师进行操作）。", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
		cells35[0].setBackgroundColor(backgroundColor1);
		listRow3.add(new PdfPRow(cells35));

		document.add(table3);*/
		if (CollectionUtils.isNotEmpty(conditioningVo.getMassage_health_care())) {
			for (int i = 1; i < conditioningVo.getMassage_health_care().size(); i++) {
				AiTreatPlanJsonVo.MassageHealthCareItem massageHealthCareItem = conditioningVo.getMassage_health_care().get(i);
				PdfPTable table3 = new PdfPTable(2);
				table3.setWidthPercentage(100);
				List<PdfPRow> listRow3 = table3.getRows();
				table3.setWidths(new float[]{1f, 1f});

				PdfPCell[] cells33 = new PdfPCell[2];
				cells33[0] = PdfUtils.newCell(massageHealthCareItem.getMessage(), font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
				cells33[0].setBackgroundColor(backgroundColor1);
				listRow3.add(new PdfPRow(cells33));

				if (StringUtils.isNotBlank(massageHealthCareItem.getPhoto())) {
					PdfPCell[] cells34 = new PdfPCell[2];
					cells34[0] = PdfUtils.newCellCenter("");
					cells34[0].addElement(PdfUtils.newImage(massageHealthCareItem.getPhoto(), Element.ALIGN_CENTER, 150, 150));
					cells34[0].addElement(PdfUtils.newParagraph(massageHealthCareItem.getBottom(), font16, Element.ALIGN_CENTER));
					cells34[1] = PdfUtils.newCellLeft(massageHealthCareItem.getDesc());
					listRow3.add(new PdfPRow(cells34));
				}

				document.add(table3);
				document.add(PdfUtils.newParagraph("", font16));
			}
		}

		// 3.（三）饮食调养
		document.add(PdfUtils.newParagraph("（三）饮食调养\n\n", font20Bold, Element.ALIGN_CENTER));

		/*PdfPTable table4 = new PdfPTable(2);
		table4.setWidthPercentage(100);
		List<PdfPRow> listRow4 = table4.getRows();
		table4.setWidths(new float[]{1f, 1f});

		PdfPCell[] cells41 = new PdfPCell[2];
		cells41[0] = PdfUtils.newCell("【宜食】主要遵循低盐、低脂肪饮食，宜多食白萝卜、扁豆、荠菜、紫菜、海带、芹菜、冬瓜叶、丝瓜、荸荠、荷叶、山楂、冬瓜籽、黄瓜、山药、薏苡仁、胡萝卜、赤小豆等\n【忌食】少食肥甘油腻、酸涩食品、寒凉酸味水果。忌过饱食", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
		cells41[0].setBackgroundColor(backgroundColor1);
		listRow4.add(new PdfPRow(cells41));

		for (int i = 0; i < 3; i++) {
			PdfPCell[] cells33 = new PdfPCell[2];
			cells33[0] = PdfUtils.newCell("白术党参茯苓粥", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			cells33[0].setBackgroundColor(backgroundColor1);
			listRow4.add(new PdfPRow(cells33));

			PdfPCell[] cells34 = new PdfPCell[2];
			cells34[0] = PdfUtils.newCellCenter("");
			cells34[0].addElement(PdfUtils.newImage(testImgUrl3, Element.ALIGN_CENTER, 150, 150));
			cells34[0].addElement(PdfUtils.newParagraph("二陈平胃散", font16, Element.ALIGN_CENTER));
			cells34[1] = PdfUtils.newCellLeft("【药材】薏苡仁适量，白术、党\n" +
					"参、茯苓、甘草各15克\n" +
					"【食材】红枣3颗\n" +
					"【烹饪方法】 步骤1：将红枣、\n" +
					"薏苡仁洗净，红枣去核，备用；\n" +
					"步骤2：将白术、党参、茯苓、甘\n" +
					"草洗净，加入300毫升水煮沸后，以\n" +
					"小火煎成200毫升；\n" +
					"步骤3：滤取出药汁在煮好的药汁\n" +
					"中加入薏苡仁、红枣，以大火煮开，\n" +
					"再转入小火熬煮成粥，加入适当的调\n" +
					"味料即可。\n" +
					"【功效】健脾益气、祛湿利水\n" +
					"【引自】《超简单面诊消百病全\n" +
					"书》");
			listRow4.add(new PdfPRow(cells34));
		}

		document.add(table4);*/
		if (CollectionUtils.isNotEmpty(conditioningVo.getEatingHabits())) {
			PdfPTable table4 = new PdfPTable(1);
			table4.setWidthPercentage(100);
			List<PdfPRow> listRow4 = table4.getRows();
			table4.setWidths(new float[]{1f});

			String eatStr = "";
			for (AiDietRehabilitationVo.EatingHabitsItem eatingHabit : conditioningVo.getEatingHabits()) {
				if (eatStr.length() > 0) {
					eatStr = eatStr + "\n";
				}
				eatStr = eatStr + "【" + eatingHabit.getTitle() + "】" + eatingHabit.getContent();
			}
			PdfPCell[] cells41 = new PdfPCell[2];
			cells41[0] = PdfUtils.newCell(eatStr, font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			cells41[0].setBackgroundColor(backgroundColor1);
			listRow4.add(new PdfPRow(cells41));

			document.add(table4);
		}
		if (CollectionUtils.isNotEmpty(conditioningVo.getDiet_rehabilitation())) {
			PdfPTable table4 = new PdfPTable(2);
			table4.setWidthPercentage(100);
			List<PdfPRow> listRow4 = table4.getRows();
			table4.setWidths(new float[]{1f, 1f});

			for (AiTreatPlanJsonVo.HealthCareExample healthCareExample : conditioningVo.getDiet_rehabilitation()) {
				PdfPCell[] cells33 = new PdfPCell[2];
				cells33[0] = PdfUtils.newCell(healthCareExample.getMessage(), font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
				cells33[0].setBackgroundColor(backgroundColor1);
				listRow4.add(new PdfPRow(cells33));

				for (AiTreatPlanJsonVo.HealthCareExampleItems item : healthCareExample.getItems()) {
					PdfPCell[] cells34 = new PdfPCell[2];
					cells34[0] = PdfUtils.newCellCenter("");
					cells34[0].addElement(PdfUtils.newImage(item.getPhoto(), Element.ALIGN_CENTER, 150, 150));
					cells34[0].addElement(PdfUtils.newParagraph(item.getName(), font16, Element.ALIGN_CENTER));
					cells34[1] = PdfUtils.newCellLeft(item.getDesc());
					listRow4.add(new PdfPRow(cells34));
				}
			}

			document.add(table4);
		}

		// 5.（四）运动保健
		document.add(PdfUtils.newParagraph("（四）运动保健\n\n", font20Bold, Element.ALIGN_CENTER));

		/*PdfPTable table5 = new PdfPTable(2);
		table5.setWidthPercentage(100);
		List<PdfPRow> listRow5 = table5.getRows();
		table5.setWidths(new float[]{1f, 1f});

		PdfPCell[] cells51 = new PdfPCell[2];
		cells51[0] = PdfUtils.newCell("气虚痰湿体质的人群应尽量避免在炎热和潮湿的环境中锻炼，同时避免运动量大、短时间以及快速爆发的运动。可根据自身情况循序渐进，坚持长期规律的有氧运动。如：八段锦、五禽戏、太极拳、慢跑、乒乓球、羽毛球、网球、爬山等运动。其中八段锦的“调理脾胃须单举”和五禽戏的“熊戏”可重复多做几遍。", font16, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
		listRow5.add(new PdfPRow(cells51));

		PdfPCell[] cells52 = new PdfPCell[2];
		cells52[0] = PdfUtils.newCell("注意事项：", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
		cells52[0].setBackgroundColor(backgroundColor1);
		listRow5.add(new PdfPRow(cells52));

		PdfPCell[] cells53 = new PdfPCell[2];
		cells53[0] = PdfUtils.newCell("若出现呼吸困难、眩晕、发力、面色发白、大汗淋漓不止、胸闷、心胸刺痛等症状时，请立即停止运动，并前往最近的医院就诊，或拨打120急救。", font16, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
		listRow5.add(new PdfPRow(cells53));

		document.add(table5);*/
		if (CollectionUtils.isNotEmpty(conditioningVo.getSports_health_care())) {
			PdfPTable table5 = new PdfPTable(2);
			table5.setWidthPercentage(100);
			List<PdfPRow> listRow5 = table5.getRows();
			table5.setWidths(new float[]{1f, 1f});

			Boolean flag5 = true;
			for (AiTreatPlanJsonVo.HealthCareExample healthCareExample : conditioningVo.getSports_health_care()) {
				PdfPCell[] cells51 = new PdfPCell[2];
				cells51[0] = PdfUtils.newCell(healthCareExample.getMessage(), font16, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
				if (flag5) {
					cells51[0].setBackgroundColor(backgroundColor1);
				}
				listRow5.add(new PdfPRow(cells51));
				flag5 = !flag5;
			}
			document.add(table5);
		}

		// 6.（五）情志调养
		document.add(PdfUtils.newParagraph("（五）情志调养\n\n", font20Bold, Element.ALIGN_CENTER));

		/*PdfPTable table6 = new PdfPTable(2);
		table6.setWidthPercentage(100);
		List<PdfPRow> listRow6 = table6.getRows();
		table6.setWidths(new float[]{1f, 1f});

		PdfPCell[] cells61 = new PdfPCell[2];
		cells61[0] = PdfUtils.newCell("①精神调摄", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
		listRow6.add(new PdfPRow(cells61));

		PdfPCell[] cells62 = new PdfPCell[2];
		cells62[0] = PdfUtils.newCell("日常生活中保持平和的心态，宜多参加社会活动，培养广泛的兴趣爱好，宜欣赏激进、振奋的音乐。", font16, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
		cells62[0].setBackgroundColor(backgroundColor1);
		listRow6.add(new PdfPRow(cells62));

		PdfPCell[] cells63 = new PdfPCell[2];
		cells63[0] = PdfUtils.newCell("②起居调摄", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
		listRow6.add(new PdfPRow(cells63));

		PdfPCell[] cells64 = new PdfPCell[2];
		cells64[0] = PdfUtils.newCell("居住环境宜干燥，不宜潮湿。穿衣面料以棉、麻、丝等透气散湿的天然纤维为佳，尽量保持宽松，有利于汗液蒸发，祛除体内湿气。起居宜规律，保证充足睡眠，劳逸相结合，根据气候变化适时增减衣物。", font16, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
		cells64[0].setBackgroundColor(backgroundColor1);
		listRow6.add(new PdfPRow(cells64));

		PdfPCell[] cells65 = new PdfPCell[2];
		cells65[0] = PdfUtils.newCell("②起居调摄", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
		listRow6.add(new PdfPRow(cells65));

		PdfPCell[] cells66 = new PdfPCell[2];
		cells66[0] = PdfUtils.newCell("居住环境宜干燥，不宜潮湿。穿衣面料以棉、麻、丝等透气散湿的天然纤维为佳，尽量保持宽松，有利于汗液蒸发，祛除体内湿气。起居宜规律，保证充足睡眠，劳逸相结合，根据气候变化适时增减衣物。", font16, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
		cells66[0].setBackgroundColor(backgroundColor1);
		listRow6.add(new PdfPRow(cells66));

		document.add(table6);*/
		if (CollectionUtils.isNotEmpty(conditioningVo.getRecuperates())) {
			PdfPTable table6 = new PdfPTable(1);
			table6.setWidthPercentage(100);
			List<PdfPRow> listRow6 = table6.getRows();
			table6.setWidths(new float[]{1f});

			Boolean flag6 = true;
			for (AiTreatPlanJsonVo.HealthCareExample recuperate : conditioningVo.getRecuperates()) {
				PdfPCell[] cells61 = new PdfPCell[1];
				cells61[0] = PdfUtils.newCellLeft(recuperate.getMessage());
				if (flag6) {
					cells61[0].setBackgroundColor(backgroundColor1);
				}
				listRow6.add(new PdfPRow(cells61));
				flag6 = !flag6;
			}

			document.add(table6);
		}

		// 7.门店信息
		document.add(PdfUtils.newParagraph("\n\n门店信息\n\n", font20Bold, Element.ALIGN_CENTER));

		PdfPTable table7 = new PdfPTable(1);
		table7.setWidthPercentage(100);
		List<PdfPRow> listRow7 = table7.getRows();
		table7.setWidths(new float[]{1f});

		PdfPCell[] cells71 = new PdfPCell[1];
		//cells71[0] = PdfUtils.newCell("门店名称：桥美郡天使宝贝母婴店", font16Bold, 0, 0, Element.ALIGN_LEFT, null, borderColor1);
		cells71[0] = PdfUtils.newCell("门店名称：" + detailVo.getUserShopDetailVO().getShopName(), font16Bold, 0, 0, Element.ALIGN_LEFT, null, borderColor1);
		listRow7.add(new PdfPRow(cells71));

		PdfPCell[] cells72 = new PdfPCell[1];
		//cells72[0] = PdfUtils.newCell("门店地址：湖南省株洲市天元区湖南省天元区康", font16Bold, 0, 0, Element.ALIGN_LEFT, null, borderColor1);
		cells72[0] = PdfUtils.newCell("门店地址：" + detailVo.getUserShopDetailVO().getShopAddressDetail(), font16Bold, 0, 0, Element.ALIGN_LEFT, null, borderColor1);
		listRow7.add(new PdfPRow(cells72));

		PdfPCell[] cells73 = new PdfPCell[1];
		//cells73[0] = PdfUtils.newCell("手机号：13787338089", font16Bold, 0, 0, Element.ALIGN_LEFT, null, borderColor1);
		cells73[0] = PdfUtils.newCell("手机号：" + detailVo.getUserShopDetailVO().getMobile(), font16Bold, 0, 0, Element.ALIGN_LEFT, null, borderColor1);
		listRow7.add(new PdfPRow(cells73));

		PdfPCell[] cells74 = new PdfPCell[1];
		cells74[0] = PdfUtils.newCellLeft("");
		//cells74[0].addElement(PdfUtils.newImage(testTitleUrl1, 500, 400));
		cells74[0].addElement(PdfUtils.newImage(detailVo.getUserShopDetailVO().getShopPhoto(), 500, 400));
		listRow7.add(new PdfPRow(cells74));

		document.add(table7);

	}

	public static void main(String[] args) throws Exception {
		// 创建文档对象
		Document document = new Document();

		try {
			LocalDateTime begin = LocalDateTime.now();
			System.out.println("===begin===" + begin);

			// 查询pdf数据
			pdfResVo = queryPdfResVo("1163516326795415552");

			LocalDateTime queryPdf = LocalDateTime.now();
			System.out.println("===queryPdf===" + Duration.between(begin, queryPdf).toMillis());

			// 指定PDF文件的输出路径
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\example.pdf"));

			// 添加页眉和页脚和水印
			writer.setPageEvent(new HeaderAndFooterPageEventHelper());

			LocalDateTime setPageEvent = LocalDateTime.now();
			System.out.println("===setPageEvent===" + Duration.between(queryPdf, setPageEvent).toMillis());

			// 打开文档
			document.open();

			/*// 第一页，标题+个人信息+二维码
			onePage(document);
			LocalDateTime end1 = LocalDateTime.now();
			System.out.println("===end1===" + Duration.between(setPageEvent, end1).toMillis());

			// 第二页，目录+图片
			twoPage(document);
			LocalDateTime end2 = LocalDateTime.now();
			System.out.println("===end2===" + Duration.between(end1, end2).toMillis());

			// 第三页，健康状态+舌象辨识
			threePage(document);
			LocalDateTime end3 = LocalDateTime.now();
			System.out.println("===end3===" + Duration.between(end2, end3).toMillis());

			// 第四页，面部望诊
			fourPage(document);
			LocalDateTime end4 = LocalDateTime.now();
			System.out.println("===end4===" + Duration.between(end3, end4).toMillis());

			// 第五页，健康分析
			fivePage(document);
			LocalDateTime end5 = LocalDateTime.now();
			System.out.println("===end5===" + Duration.between(end4, end5).toMillis());*/

			// 第六页，调理方案+门店信息
			sixPage(document);
			LocalDateTime end = LocalDateTime.now();
			//System.out.println("===end===" + Duration.between(end5, end).toMillis());

			System.out.println("===all===" + Duration.between(begin, end).toMillis());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭文档
			document.close();
			System.out.println("PDF生成成功！");
		}
	}

}
