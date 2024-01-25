package com.example.demo.test.pdf2;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.example.demo.test.pdf.GeneratePdfVO;
import com.example.demo.test.pdf2.vo.AiConsultFlowVO;
import com.example.demo.test.pdf2.vo.AiReportItemVO;
import com.example.demo.test.pdf2.vo.AiTreatPlanJsonVo;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangjiale
 * @date 2023/10/10 13:25
 **/
@Data
public class PdfAi2 {
	private static final String UPLOAD_URL = "http://img.aikesaisi.com/";

	public static final String topUrl = "https://img.aikesaisi.com/ai/user/2024-01-22/2c37d5b4ef6b42d9ac83986c14185a94-top.png";
	public static final String iconUrl = "https://img.aikesaisi.com/ai/user/2024-01-23/71940a1f727f4d469f0d279d56fa1a8c-icon.png";
	public static final String testCodeUrl = "http://img.aikesaisi.com/jhbamin/images/2023-10-08/8ad97167797c4f0481bdb11f647a32f7-code.jpg";
	public static final String testSheUrl = "http://img.aikesaisi.com/jhbamin/images/2023-10-08/e895fbdc05c347fbad116e438058abe4-shetou.jpg";
	public static final String testLeftUrl = "https://img.aikesaisi.com/ai/user/2024-01-23/87f00a6a7441486f920beb69820b6db3-left.jpg";


	// 宋体，正常字体
	public static BaseFont bfChinese;

	static {
		try {
			bfChinese = BaseFont.createFont(PdfUtils2.FontName, PdfUtils2.FontEncoding, BaseFont.EMBEDDED);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Font font5 = new Font(bfChinese, 5);
	public static Font font10 = new Font(bfChinese, 10);
	public static Font font12 = new Font(bfChinese, 12);
	public static Font font14 = new Font(bfChinese, 14);
	public static Font font16 = new Font(bfChinese, 16);
	public static Font font20 = new Font(bfChinese, 20);
	public static Font font30 = new Font(bfChinese, 30);
	public static Font font10Bold = new Font(bfChinese, 10, Font.BOLD);
	public static Font font12Bold = new Font(bfChinese, 12, Font.BOLD);
	public static Font font14Bold = new Font(bfChinese, 14, Font.BOLD);
	public static Font font16Bold = new Font(bfChinese, 16, Font.BOLD);
	public static Font font20Bold = new Font(bfChinese, 20, Font.BOLD);
	public static Font font24Bold = new Font(bfChinese, 24, Font.BOLD);
	public static Font font30Bold = new Font(bfChinese, 30, Font.BOLD);

	public static Font font8Black = new Font(bfChinese, 9, Font.NORMAL, new Color(104, 119, 116));
	public static Font font10White = new Font(bfChinese, 10, Font.NORMAL, new Color(255, 255, 255));
	public static Font font10Red = new Font(bfChinese, 10, Font.NORMAL, new Color(252, 134, 117));
	public static Font font10Blue = new Font(bfChinese, 10, Font.NORMAL, new Color(2, 162, 157));
	public static Font font10BoldBlue = new Font(bfChinese, 10, Font.BOLD, new Color(2, 162, 157));
	public static Font font12BoldBlue = new Font(bfChinese, 12, Font.BOLD, new Color(2, 162, 157));
	public static Font font14BoldBlue = new Font(bfChinese, 14, Font.BOLD, new Color(2, 162, 157));

	public static Color borderColor1 = new Color(79, 128, 189);
	public static Color backgroundColor1 = new Color(219, 229, 241);
	public static Color backgroundColor2 = new Color(245, 255, 255);

	public static PdfResVo2 pdfResVo = new PdfResVo2();


	/**
	 * 查询pdf数据
	 *
	 * @param vo
	 */
	private static PdfResVo2 queryPdfResVo(GeneratePdfVO vo) {
		pdfResVo = new PdfResVo2();

		pdfResVo.setDetailVo(JSON.parseObject(PdfUtils2.str1, AiConsultFlowVO.class));
		pdfResVo.setTongueVo(JSON.parseObject(PdfUtils2.str2, AiConsultFlowVO.class));
		pdfResVo.setFaceVo(JSON.parseObject(PdfUtils2.str3, AiConsultFlowVO.class));
		pdfResVo.setConditioningVo(JSON.parseObject(PdfUtils2.str4, AiTreatPlanJsonVo.class));

		GeneratePdfVO generatePdfVO = new GeneratePdfVO();
		generatePdfVO.setReportId(vo.getReportId());
		pdfResVo.setGeneratePdfVO(generatePdfVO);

		return pdfResVo;
	}

	/**
	 * 第一页，标题+个人信息+二维码
	 *
	 * @param document
	 */
	private static void onePage(Document document) {
		// 数据库数据
		AiConsultFlowVO detailVo = pdfResVo.getDetailVo();

		// 图片表格，4行3列
		PdfPTable table1 = new PdfPTable(3);
		// 宽度100%填充
		table1.setWidthPercentage(100);
		// 设置表格无边框
		table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		// 创建表格的的行对象集合
		List<PdfPRow> listRow1 = table1.getRows();
		// 将表格设置为3列，并指定列宽
		table1.setWidths(new float[]{7f, 2f, 1f});

		PdfPCell[] cells1 = new PdfPCell[3];
		cells1[0] = PdfUtils2.newCell("", font10, 0, 4, Element.ALIGN_LEFT, null, PdfPCell.NO_BORDER);
		cells1[0].addElement(PdfUtils2.newImage(topUrl, Element.ALIGN_LEFT, 350, 100));
		cells1[1] = PdfUtils2.newCell(" ", font10, null, PdfPCell.NO_BORDER);
		cells1[2] = PdfUtils2.newCell("", font10, 0, 4, Element.ALIGN_LEFT, null, PdfPCell.NO_BORDER);
		cells1[2].addElement(PdfUtils2.newImage(testCodeUrl, Element.ALIGN_LEFT, 50, 50));
		listRow1.add(new PdfPRow(cells1));

		PdfPCell[] cells2 = new PdfPCell[3];
		cells2[1] = PdfUtils2.newCell("微信扫一扫", font10, 0, 0, Element.ALIGN_RIGHT, null, PdfPCell.NO_BORDER);
		listRow1.add(new PdfPRow(cells2));

		PdfPCell[] cells3 = new PdfPCell[3];
		cells3[1] = PdfUtils2.newCell("查看电子版报告", font10, 0, 0, Element.ALIGN_RIGHT, null, PdfPCell.NO_BORDER);
		listRow1.add(new PdfPRow(cells3));

		PdfPCell[] cells4 = new PdfPCell[3];
		cells4[1] = PdfUtils2.newCell(" ", font10, null, PdfPCell.NO_BORDER);
		listRow1.add(new PdfPRow(cells4));

		document.add(table1);

		Paragraph paragraph = new Paragraph();
		paragraph.add(new Chunk("健康状态报告", font30Bold));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setLeading(30);
		document.add(paragraph);
		document.add(PdfUtils2.newParagraph("\n", font10));

		PdfPTable table2 = new PdfPTable(3);
		table2.setWidthPercentage(100);
		table2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		List<PdfPRow> listRow2 = table2.getRows();
		table2.setWidths(new float[]{3f, 3f, 4f});

		PdfPCell[] cells21 = new PdfPCell[3];
		/*cells21[0] = PdfUtils2.newCell("检测人：毛毛（5岁，男）", font10, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER);
		cells21[1] = PdfUtils2.newCell("检测类别：儿童体质辨识", font10, Element.ALIGN_CENTER, PdfPCell.NO_BORDER);
		cells21[2] = PdfUtils2.newCell("检测时间：2024-01-23 12:12:12", font10, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);*/
		cells21[0] = PdfUtils2.newCell("检测人：" + detailVo.getName() + "（" + detailVo.getAge() + "岁，"
				+ detailVo.getGenderDesc() + "）", font10, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER);
		cells21[1] = PdfUtils2.newCell("检测类别：" + detailVo.getDetectionTypeDesc(), font10, Element.ALIGN_CENTER, PdfPCell.NO_BORDER);
		cells21[2] = PdfUtils2.newCell("检测时间：" + detailVo.getCreateTime() == null ?
				"" : DateUtil.formatDateTime(detailVo.getCreateTime()), font10, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		listRow2.add(new PdfPRow(cells21));

		document.add(table2);
	}

	/**
	 * 第二页，健康状态
	 *
	 * @param document
	 */
	private static void twoPage(Document document) {
		// 数据库数据
		AiConsultFlowVO detailVo = pdfResVo.getDetailVo();

		document.add(PdfUtils2.newParagraph("\n", font10));

		Paragraph paragraph = new Paragraph();
		paragraph.add(new Chunk("一、健康状态：", font14Bold));
		//paragraph.add(new Chunk("阳盛质", font14BoldBlue));
		paragraph.add(new Chunk(detailVo.getConstitutionNames(), font14BoldBlue));
		paragraph.setLeading(20);
		document.add(paragraph);

		document.add(PdfUtils2.newParagraph20("1、主要表现", font12Bold));
		document.add(PdfUtils2.newParagraph18("可能出现或者即将出现以下一个或者几个症状，不排除出现其他特殊情况", font10Blue));
		document.add(PdfUtils2.newParagraph("\n", font5));

		/*PdfPTable table0 = new PdfPTable(2);
		table0.setWidthPercentage(100);
		table0.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		List<PdfPRow> listRow0 = table0.getRows();
		table0.setWidths(new float[]{0.4f, 9.6f});

		PdfPCell[] cells01 = new PdfPCell[2];
		cells01[0] = PdfUtils2.newCell("", font10, Element.ALIGN_CENTER, PdfPCell.NO_BORDER);
		cells01[0].addElement(PdfUtils2.newImage(iconUrl, Element.ALIGN_LEFT, 16, 16));
		cells01[1] = PdfUtils2.newCell("面色微黄面色微黄面色微黄面色微黄面色微黄", font10, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		listRow0.add(new PdfPRow(cells01));

		document.add(table0);*/
		if (detailVo.getAiTreatPlanJsonVo() != null && (CollectionUtils.isNotEmpty(detailVo.getAiTreatPlanJsonVo().getMain_performance())
				|| CollectionUtils.isNotEmpty(detailVo.getAiTreatPlanJsonVo().getAnswerList()))) {
			PdfPTable table0 = new PdfPTable(2);
			table0.setWidthPercentage(100);
			table0.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			List<PdfPRow> listRow0 = table0.getRows();
			table0.setWidths(new float[]{0.4f, 9.6f});

			if (CollectionUtils.isNotEmpty(detailVo.getAiTreatPlanJsonVo().getMain_performance())) {
				for (com.example.demo.test.pdf2.vo.AiTreatPlanJsonVo.HealthCareExample healthCareExample : detailVo.getAiTreatPlanJsonVo().getMain_performance()) {
					PdfPCell[] cells01 = new PdfPCell[2];
					cells01[0] = PdfUtils2.newCell("", font10, Element.ALIGN_CENTER, PdfPCell.NO_BORDER);
					cells01[0].addElement(PdfUtils2.newImage(iconUrl, Element.ALIGN_LEFT, 16, 16));
					cells01[1] = PdfUtils2.newCell(healthCareExample.getMessage(), font10, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
					listRow0.add(new PdfPRow(cells01));
				}
			}
			if (CollectionUtils.isNotEmpty(detailVo.getAiTreatPlanJsonVo().getAnswerList())) {
				for (String answer : detailVo.getAiTreatPlanJsonVo().getAnswerList()) {
					PdfPCell[] cells01 = new PdfPCell[2];
					cells01[0] = PdfUtils2.newCell("", font10, Element.ALIGN_CENTER, PdfPCell.NO_BORDER);
					cells01[0].addElement(PdfUtils2.newImage(iconUrl, Element.ALIGN_LEFT, 16, 16));
					cells01[1] = PdfUtils2.newCell(answer, font10, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
					listRow0.add(new PdfPRow(cells01));
				}
			}

			document.add(table0);
		}

		document.add(PdfUtils2.newParagraph20("2、发生原因", font12Bold));
		//document.add(PdfUtils2.newParagraph18("多因素体气虚多因素体气虚多因素体气虚多因素体气虚多因素体气虚", font10));
		if (detailVo.getAiTreatPlanJsonVo() != null && CollectionUtils.isNotEmpty(detailVo.getAiTreatPlanJsonVo().getOccur_reason())) {
			for (com.example.demo.test.pdf2.vo.AiTreatPlanJsonVo.HealthCareExample healthCareExample : detailVo.getAiTreatPlanJsonVo().getOccur_reason()) {
				document.add(PdfUtils2.newParagraph18(healthCareExample.getMessage(), font10));
			}
		}

		document.add(PdfUtils2.newParagraph20("3、健康指数及趋势", font12Bold));

		/*PdfPTable table1 = new PdfPTable(2);
		table1.setWidthPercentage(100);
		table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		List<PdfPRow> listRow1 = table1.getRows();
		table1.setWidths(new float[]{5f, 5f});

		PdfPCell[] cells1 = new PdfPCell[2];
		cells1[0] = PdfUtils2.newCell("", font10, null, PdfPCell.NO_BORDER);
		cells1[0].addElement(PdfUtils2.newImage(testLeftUrl, Element.ALIGN_CENTER, 250, 150));
		cells1[1] = PdfUtils2.newCell("", font10, null, PdfPCell.NO_BORDER);
		cells1[1].addElement(PdfUtils2.newImage(testLeftUrl, Element.ALIGN_CENTER, 250, 150));
		listRow1.add(new PdfPRow(cells1));*/
		if (StringUtils.isNotBlank(detailVo.getHealthScoreImages()) || StringUtils.isNotBlank(detailVo.getHealthTrendImages())) {
			PdfPTable table1 = new PdfPTable(2);
			table1.setWidthPercentage(100);
			table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			List<PdfPRow> listRow1 = table1.getRows();
			table1.setWidths(new float[]{5f, 5f});

			PdfPCell[] cells1 = new PdfPCell[2];
			if (StringUtils.isNotBlank(detailVo.getHealthScoreImages())) {
				cells1[0] = PdfUtils2.newCell("", font10, null, PdfPCell.NO_BORDER);
				cells1[0].addElement(PdfUtils2.newImage(detailVo.getHealthScoreImages(), Element.ALIGN_CENTER, 250, 150));
				if (StringUtils.isNotBlank(detailVo.getHealthTrendImages())) {
					cells1[1] = PdfUtils2.newCell("", font10, null, PdfPCell.NO_BORDER);
					cells1[1].addElement(PdfUtils2.newImage(detailVo.getHealthTrendImages(), Element.ALIGN_CENTER, 250, 150));
				}
			} else {
				cells1[0] = PdfUtils2.newCell("", font10, null, PdfPCell.NO_BORDER);
				cells1[0].addElement(PdfUtils2.newImage(detailVo.getHealthTrendImages(), Element.ALIGN_CENTER, 250, 150));
			}

			listRow1.add(new PdfPRow(cells1));

			document.add(table1);
		}

	}

	/**
	 * 第三页，舌象辨识
	 *
	 * @param document
	 */
	private static void threePage(Document document) {
		// 数据库数据
		AiConsultFlowVO detailVo = pdfResVo.getDetailVo();
		AiConsultFlowVO tongueVo = pdfResVo.getTongueVo();

		document.add(PdfUtils2.newParagraph("\n", font10));
		document.add(PdfUtils2.newParagraph20("二、舌象辨识：", font14Bold));

		document.add(PdfUtils2.newParagraph20("1、舌象分析结果汇总", font12Bold));

		Paragraph paragraph1 = new Paragraph();
		//paragraph1.add(new Chunk("结合上次检测(2023-10-17)，", font10));
		paragraph1.add(new Chunk(detailVo.getTongueTimeDesc(), font10));
		paragraph1.add(new Chunk("舌象特征变化解析：", font10Red));
		paragraph1.setLeading(18);
		document.add(paragraph1);

		/*Paragraph paragraph2 = new Paragraph();
		paragraph2.add(new Chunk("舌象特征变化解析舌象特征变化解析：", font10));
		paragraph2.add(new Chunk("【薄苔腻】变成【薄苔腻】", font10Blue));
		paragraph2.setLeading(18);
		document.add(paragraph2);*/
		if (CollectionUtils.isNotEmpty(detailVo.getTongueFeatureAnalysisList())) {
			for (AiReportItemVO aiReportItemVO : detailVo.getTongueFeatureAnalysisList()) {
				Paragraph paragraph2 = new Paragraph();
				paragraph2.add(new Chunk(aiReportItemVO.getKey() + "：", font10));
				paragraph2.add(new Chunk(aiReportItemVO.getValue(), font10Blue));
				paragraph2.setLeading(18);
				document.add(paragraph2);
			}
		}

		document.add(PdfUtils2.newParagraph20("2、异常舌象人工智能解析", font12Bold));
		document.add(PdfUtils2.newParagraph("\n", font10));

		/*// 表格
		PdfPTable table1 = new PdfPTable(6);
		table1.setWidthPercentage(100);
		table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		List<PdfPRow> listRow1 = table1.getRows();
		table1.setWidths(new float[]{1f, 1f, 1.2f, 1f, 1.2f, 4.6f});

		PdfPCell[] cells1 = new PdfPCell[6];
		cells1[0] = PdfUtils2.newCell("异常项", font12BoldBlue, 0, 0, Element.ALIGN_CENTER, 20, PdfPCell.NO_BORDER);
		cells1[0].setBorder(PdfPCell.BOX);
		cells1[0].setBackgroundColor(backgroundColor2);
		cells1[1] = PdfUtils2.newCell("人工智能分析", font12BoldBlue, 5, 0, Element.ALIGN_CENTER, 20, PdfPCell.NO_BORDER);
		cells1[1].setBorder(PdfPCell.BOX);
		cells1[1].setBackgroundColor(backgroundColor2);
		listRow1.add(new PdfPRow(cells1));

		PdfPCell[] cells2 = new PdfPCell[6];
		cells2[0] = PdfUtils2.newCell("舌色", font10, Element.ALIGN_CENTER, PdfPCell.NO_BORDER);
		cells2[0].setBorder(PdfPCell.BOX);

		cells2[1] = PdfUtils2.newCell("", font10, Element.ALIGN_CENTER, PdfPCell.BOTTOM);
		cells2[1].addElement(PdfUtils2.newImage(testSheUrl, Element.ALIGN_CENTER, 50, 50));
		cells2[1].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);

		Paragraph paragraphCell22 = new Paragraph();
		// 第一行
		paragraphCell22.add(new Chunk("\n淡红舌(本次)", font10Red));
		paragraphCell22.add(new Chunk(" \n ", font5));
		// 第二行
		Chunk chunk221 = new Chunk("\n舌边红", font10White);
		chunk221.setBackground(new Color(252, 134, 117));
		paragraphCell22.add(chunk221);
		paragraphCell22.add(new Chunk(" \n ", font5));
		// 第三行
		Chunk chunk222 = new Chunk("\n舌中淡黄苔", font10White);
		chunk222.setBackground(new Color(252, 134, 117));
		paragraphCell22.add(chunk222);
		paragraphCell22.add(new Chunk(" \n ", font5));
		// 添加
		cells2[2] = new PdfPCell(paragraphCell22);
		cells2[2].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);

		cells2[3] = PdfUtils2.newCell("", font10, Element.ALIGN_CENTER, PdfPCell.BOTTOM);
		cells2[3].addElement(PdfUtils2.newImage(testSheUrl, Element.ALIGN_CENTER, 50, 50));
		cells2[3].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);

		Paragraph paragraphCell44 = new Paragraph();
		// 第一行
		paragraphCell44.add(new Chunk("\n淡白舌(上次)", font10Red));
		paragraphCell44.add(new Chunk(" \n ", font5));
		// 第二行
		Chunk chunk441 = new Chunk("\n正常", font10White);
		chunk441.setBackground(new Color(6, 192, 96));
		paragraphCell44.add(chunk441);
		paragraphCell44.add(new Chunk(" \n ", font5));
		// 添加
		cells2[4] = new PdfPCell(paragraphCell44);
		cells2[4].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);

		Paragraph paragraphCell55 = new Paragraph();
		paragraphCell55.add(new Chunk("\n【淡红色】正常人舌色正常人舌色正常人舌色正常人舌色。", font10));
		paragraphCell55.add(new Chunk(" \n ", font5));
		paragraphCell55.add(new Chunk("\n【健康分析】正常人舌色正常人舌色正常人舌色正常人舌色。", font10));
		paragraphCell55.add(new Chunk(" \n ", font5));
		cells2[5] = new PdfPCell(paragraphCell55);
		//cells2[5].setPaddingBottom(10);
		cells2[5].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP | PdfPCell.RIGHT);

		listRow1.add(new PdfPRow(cells2));
		listRow1.add(new PdfPRow(cells2));
		listRow1.add(new PdfPRow(cells2));

		document.add(table1);*/

		if (CollectionUtils.isNotEmpty(tongueVo.getAiTongueVOList())) {
			// 判断是否有上次数据
			Boolean isFlag = tongueVo.getAiTongueVOList().stream().anyMatch(a -> a.getOldReportInfo() != null);
			Integer row = 4;
			if (isFlag) {
				row = 6;
			}

			// 表格
			PdfPTable table1 = new PdfPTable(6);
			table1.setWidthPercentage(100);
			table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			List<PdfPRow> listRow1 = table1.getRows();
			if (row == 6) {
				table1.setWidths(new float[]{1f, 1f, 1.2f, 1f, 1.2f, 4.6f});
			} else {
				table1.setWidths(new float[]{2f, 2f, 2.2f, 5.6f});
			}

			PdfPCell[] cells1 = new PdfPCell[row];
			cells1[0] = PdfUtils2.newCell("异常项", font12BoldBlue, 0, 0, Element.ALIGN_CENTER, 20, PdfPCell.NO_BORDER);
			cells1[0].setBorder(PdfPCell.BOX);
			cells1[0].setBackgroundColor(backgroundColor2);
			cells1[1] = PdfUtils2.newCell("人工智能分析", font12BoldBlue, row - 1, 0, Element.ALIGN_CENTER, 20, PdfPCell.NO_BORDER);
			cells1[1].setBorder(PdfPCell.BOX);
			cells1[1].setBackgroundColor(backgroundColor2);
			listRow1.add(new PdfPRow(cells1));

			for (AiReportItemVO itemVO : tongueVo.getAiTongueVOList()) {
				PdfPCell[] cells2 = new PdfPCell[row];
				cells2[0] = PdfUtils2.newCell(itemVO.getKey(), font10, Element.ALIGN_CENTER, PdfPCell.NO_BORDER);
				cells2[0].setBorder(PdfPCell.BOX);

				if (itemVO.getNewReportInfo() != null) {
					AiReportItemVO infoVo = itemVO.getNewReportInfo();

					cells2[1] = PdfUtils2.newCell("", font10, Element.ALIGN_CENTER, PdfPCell.BOTTOM);
					cells2[1].addElement(PdfUtils2.newImage(infoVo.getPhoto(), Element.ALIGN_CENTER, 50, 50));
					cells2[1].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);

					Paragraph paragraphCell22 = new Paragraph();
					// 第一行
					paragraphCell22.add(new Chunk("\n" + infoVo.getField() + (isFlag ? "(本次)" : ""), font10Red));
					paragraphCell22.add(new Chunk(" \n ", font5));
					// 第二行
					if (CollectionUtils.isNotEmpty(infoVo.getTongueNatureAttrList())) {
						for (String attr : infoVo.getTongueNatureAttrList()) {
							Chunk chunk221 = new Chunk("\n" + attr, font10White);
							if (infoVo.getIsRed()) {
								chunk221.setBackground(new Color(252, 134, 117));
							} else {
								chunk221.setBackground(new Color(6, 192, 96));
							}
							paragraphCell22.add(chunk221);
							paragraphCell22.add(new Chunk(" \n ", font5));
						}
					}
					// 添加
					cells2[2] = new PdfPCell(paragraphCell22);
					cells2[2].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);
				}

				if (itemVO.getOldReportInfo() != null) {
					AiReportItemVO infoVo = itemVO.getOldReportInfo();

					cells2[3] = PdfUtils2.newCell("", font10, Element.ALIGN_CENTER, PdfPCell.BOTTOM);
					cells2[3].addElement(PdfUtils2.newImage(infoVo.getPhoto(), Element.ALIGN_CENTER, 50, 50));
					cells2[3].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);

					Paragraph paragraphCell44 = new Paragraph();
					// 第一行
					paragraphCell44.add(new Chunk("\n" + infoVo.getField() + (isFlag ? "(上次)" : ""), font10Red));
					paragraphCell44.add(new Chunk(" \n ", font5));
					// 第二行
					if (CollectionUtils.isNotEmpty(infoVo.getTongueNatureAttrList())) {
						for (String attr : infoVo.getTongueNatureAttrList()) {
							Chunk chunk441 = new Chunk("\n" + attr, font10White);
							if (infoVo.getIsRed()) {
								chunk441.setBackground(new Color(252, 134, 117));
							} else {
								chunk441.setBackground(new Color(6, 192, 96));
							}
							paragraphCell44.add(chunk441);
							paragraphCell44.add(new Chunk(" \n ", font5));
						}
					}
					// 添加
					cells2[4] = new PdfPCell(paragraphCell44);
					cells2[4].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);
				}

				Paragraph paragraphCell55 = new Paragraph();
				if (itemVO.getNewReportInfo() != null && StringUtils.isNotBlank(itemVO.getNewReportInfo().getValue())) {
					String[] valueList = itemVO.getNewReportInfo().getValue().split("</br>");
					for (String val : valueList) {
						paragraphCell55.add(new Chunk("\n" + val, font10));
						paragraphCell55.add(new Chunk(" \n ", font5));
					}
				}
				cells2[row - 1] = new PdfPCell(paragraphCell55);
				//cells2[5].setPaddingBottom(10);
				cells2[row - 1].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP | PdfPCell.RIGHT);

				listRow1.add(new PdfPRow(cells2));
			}

			document.add(table1);
		}
	}

	/**
	 * 第四页，面象辨识
	 *
	 * @param document
	 */
	private static void fourPage(Document document) {
		// 数据库数据
		AiConsultFlowVO faceVo = pdfResVo.getFaceVo();

		document.add(PdfUtils2.newParagraph("\n", font10));
		document.add(PdfUtils2.newParagraph20("三、面象辨识：", font14Bold));

		document.add(PdfUtils2.newParagraph20("1、异常面象人工智能解析", font12Bold));
		document.add(PdfUtils2.newParagraph("\n", font10));

		if (CollectionUtils.isNotEmpty(faceVo.getAiFaceVOList())) {
			// 判断是否有上次数据
			Boolean isFlag = faceVo.getAiFaceVOList().stream().anyMatch(a -> a.getOldReportInfo() != null);
			Integer row = 4;
			if (isFlag) {
				row = 6;
			}

			// 表格
			PdfPTable table1 = new PdfPTable(6);
			table1.setWidthPercentage(100);
			table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			List<PdfPRow> listRow1 = table1.getRows();
			if (row == 6) {
				table1.setWidths(new float[]{1f, 1f, 1.2f, 1f, 1.2f, 4.6f});
			} else {
				table1.setWidths(new float[]{2f, 2f, 2.2f, 5.6f});
			}

			PdfPCell[] cells1 = new PdfPCell[row];
			cells1[0] = PdfUtils2.newCell("异常项", font12BoldBlue, 0, 0, Element.ALIGN_CENTER, 20, PdfPCell.NO_BORDER);
			cells1[0].setBorder(PdfPCell.BOX);
			cells1[0].setBackgroundColor(backgroundColor2);
			cells1[1] = PdfUtils2.newCell("人工智能分析", font12BoldBlue, row - 1, 0, Element.ALIGN_CENTER, 20, PdfPCell.NO_BORDER);
			cells1[1].setBorder(PdfPCell.BOX);
			cells1[1].setBackgroundColor(backgroundColor2);
			listRow1.add(new PdfPRow(cells1));

			for (AiReportItemVO itemVO : faceVo.getAiFaceVOList()) {
				PdfPCell[] cells2 = new PdfPCell[row];
				cells2[0] = PdfUtils2.newCell(itemVO.getKey(), font10, Element.ALIGN_CENTER, PdfPCell.NO_BORDER);
				cells2[0].setBorder(PdfPCell.BOX);

				if (itemVO.getNewReportInfo() != null) {
					AiReportItemVO infoVo = itemVO.getNewReportInfo();

					cells2[1] = PdfUtils2.newCell("", font10, Element.ALIGN_CENTER, PdfPCell.BOTTOM);
					cells2[1].addElement(PdfUtils2.newImage(infoVo.getPhoto(), Element.ALIGN_CENTER, 50, 50));
					cells2[1].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);

					Paragraph paragraphCell22 = new Paragraph();
					// 第一行
					paragraphCell22.add(new Chunk("\n" + infoVo.getField() + (isFlag ? "(本次)" : ""), font10Red));
					paragraphCell22.add(new Chunk(" \n ", font5));
					// 第二行
					if (CollectionUtils.isNotEmpty(infoVo.getTongueNatureAttrList())) {
						for (String attr : infoVo.getTongueNatureAttrList()) {
							Chunk chunk221 = new Chunk("\n" + attr, font10White);
							if (infoVo.getIsRed()) {
								chunk221.setBackground(new Color(252, 134, 117));
							} else {
								chunk221.setBackground(new Color(6, 192, 96));
							}
							paragraphCell22.add(chunk221);
							paragraphCell22.add(new Chunk(" \n ", font5));
						}
					}
					// 添加
					cells2[2] = new PdfPCell(paragraphCell22);
					cells2[2].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);
				}

				if (itemVO.getOldReportInfo() != null) {
					AiReportItemVO infoVo = itemVO.getOldReportInfo();

					cells2[3] = PdfUtils2.newCell("", font10, Element.ALIGN_CENTER, PdfPCell.BOTTOM);
					cells2[3].addElement(PdfUtils2.newImage(infoVo.getPhoto(), Element.ALIGN_CENTER, 50, 50));
					cells2[3].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);

					Paragraph paragraphCell44 = new Paragraph();
					// 第一行
					paragraphCell44.add(new Chunk("\n" + infoVo.getField() + (isFlag ? "(上次)" : ""), font10Red));
					paragraphCell44.add(new Chunk(" \n ", font5));
					// 第二行
					if (CollectionUtils.isNotEmpty(infoVo.getTongueNatureAttrList())) {
						for (String attr : infoVo.getTongueNatureAttrList()) {
							Chunk chunk441 = new Chunk("\n" + attr, font10White);
							if (infoVo.getIsRed()) {
								chunk441.setBackground(new Color(252, 134, 117));
							} else {
								chunk441.setBackground(new Color(6, 192, 96));
							}
							paragraphCell44.add(chunk441);
							paragraphCell44.add(new Chunk(" \n ", font5));
						}
					}
					// 添加
					cells2[4] = new PdfPCell(paragraphCell44);
					cells2[4].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);
				}

				Paragraph paragraphCell55 = new Paragraph();
				if (itemVO.getNewReportInfo() != null && StringUtils.isNotBlank(itemVO.getNewReportInfo().getValue())) {
					String[] valueList = itemVO.getNewReportInfo().getValue().split("</br>");
					for (String val : valueList) {
						paragraphCell55.add(new Chunk("\n" + val, font10));
						paragraphCell55.add(new Chunk(" \n ", font5));
					}
				}
				cells2[row - 1] = new PdfPCell(paragraphCell55);
				//cells2[5].setPaddingBottom(10);
				cells2[row - 1].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP | PdfPCell.RIGHT);

				listRow1.add(new PdfPRow(cells2));
			}

			document.add(table1);
		}

	}

	/**
	 * 第五页，调理方案
	 *
	 * @param document
	 */
	private static void fivePage(Document document) {
		// 数据库数据
		AiTreatPlanJsonVo conditioningVo = pdfResVo.getConditioningVo();

		document.add(PdfUtils2.newParagraph("\n", font10));
		document.add(PdfUtils2.newParagraph20("四、调理方案：", font14Bold));

		document.add(PdfUtils2.newParagraph20("（一）膳食调理", font12Bold));
		/*document.add(PdfUtils2.newParagraph18("1、应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡", font10));
		document.add(PdfUtils2.newParagraph18("2、应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡", font10));
		document.add(PdfUtils2.newParagraph18("3、应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡", font10));*/
		if (CollectionUtils.isNotEmpty(conditioningVo.getDietaryConditioningList())) {
			for (AiTreatPlanJsonVo.DietaryConditioningItem item : conditioningVo.getDietaryConditioningList()) {
				document.add(PdfUtils2.newParagraph18(item.getContent(), font10));
			}
		}

		document.add(PdfUtils2.newParagraph20("（二）膳食调理", font12Bold));

		/*document.add(PdfUtils2.newParagraph20("必选营养", font12BoldBlue));
		document.add(PdfUtils2.newParagraph18("1、应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡", font10));
		document.add(PdfUtils2.newParagraph18("2、应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡", font10));
		document.add(PdfUtils2.newParagraph("\n", font10));

		PdfPTable table1 = new PdfPTable(6);
		table1.setWidthPercentage(100);
		table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		List<PdfPRow> listRow1 = table1.getRows();
		table1.setWidths(new float[]{1.6f, 1.6f, 1.6f, 1.6f, 1.6f, 1.6f});

		PdfPCell[] cells1 = new PdfPCell[6];
		cells1[0] = PdfUtils2.newCell("", font12, null, PdfPCell.NO_BORDER);
		cells1[0].addElement(PdfUtils2.newImage(testSheUrl, Element.ALIGN_CENTER, 50, 50));
		cells1[1] = PdfUtils2.newCell("爱欣童脾皮虎", font12, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		cells1[2] = PdfUtils2.newCell("", font12, null, PdfPCell.NO_BORDER);
		cells1[2].addElement(PdfUtils2.newImage(testSheUrl, Element.ALIGN_CENTER, 50, 50));
		cells1[3] = PdfUtils2.newCell("挑剔麻咪酵母富锌多种维生素", font12, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		cells1[4] = PdfUtils2.newCell("", font12, null, PdfPCell.NO_BORDER);
		cells1[4].addElement(PdfUtils2.newImage(testSheUrl, Element.ALIGN_CENTER, 50, 50));
		cells1[5] = PdfUtils2.newCell("挑剔麻咪酵母富锌多种维生素", font12, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		listRow1.add(new PdfPRow(cells1));

		document.add(table1);

		document.add(PdfUtils2.newParagraph20("可选营养", font12BoldBlue));
		document.add(PdfUtils2.newParagraph18("1、应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡", font10));
		document.add(PdfUtils2.newParagraph("\n", font10));

		PdfPTable table2 = new PdfPTable(6);
		table2.setWidthPercentage(100);
		table2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		List<PdfPRow> listRow2 = table2.getRows();
		table2.setWidths(new float[]{1.6f, 1.6f, 1.6f, 1.6f, 1.6f, 1.6f});

		PdfPCell[] cells2 = new PdfPCell[6];
		cells2[0] = PdfUtils2.newCell("", font12, null, PdfPCell.NO_BORDER);
		cells2[0].addElement(PdfUtils2.newImage(testSheUrl, Element.ALIGN_CENTER, 50, 50));
		cells2[1] = PdfUtils2.newCell("爱欣童脾皮虎", font12, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		cells2[2] = PdfUtils2.newCell("", font12, null, PdfPCell.NO_BORDER);
		cells2[2].addElement(PdfUtils2.newImage(testSheUrl, Element.ALIGN_CENTER, 50, 50));
		cells2[3] = PdfUtils2.newCell("挑剔麻咪酵母富锌多种维生素", font12, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		listRow2.add(new PdfPRow(cells2));

		document.add(table2);*/

		if (CollectionUtils.isNotEmpty(conditioningVo.getNutritionalMatchList())) {
			List<AiTreatPlanJsonVo.NutritionalMatchItem> list1 = conditioningVo.getNutritionalMatchList().stream()
					.filter(a -> "1".equals(a.getSelectModel())).collect(Collectors.toList());
			if (CollectionUtils.isNotEmpty(list1)) {
				document.add(PdfUtils2.newParagraph20("必选营养", font12BoldBlue));
				for (AiTreatPlanJsonVo.NutritionalMatchItem item : list1) {
					document.add(PdfUtils2.newParagraph18(item.getName(), font10));
				}
				document.add(PdfUtils2.newParagraph("\n", font10));

				if (CollectionUtils.isNotEmpty(list1.get(0).getProductInfoList())) {
					PdfPTable table1 = new PdfPTable(6);
					table1.setWidthPercentage(100);
					table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
					List<PdfPRow> listRow1 = table1.getRows();
					table1.setWidths(new float[]{1.7f, 1.7f, 1.6f, 1.6f, 1.7f, 1.7f});

					PdfPCell[] cells1 = new PdfPCell[6];
					Integer j = 0;
					for (int i = 0; i < list1.get(0).getProductInfoList().size(); i++) {
						AiTreatPlanJsonVo.productInfoItem item = list1.get(0).getProductInfoList().get(i);
						cells1[j] = PdfUtils2.newCell("", font12, null, PdfPCell.NO_BORDER);
						cells1[j].addElement(PdfUtils2.newImage(item.getImg(), Element.ALIGN_CENTER, 50, 50));
						j++;
						cells1[j] = PdfUtils2.newCell(item.getProductName(), font12, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
						j++;

						if (j == 6 || i == list1.get(0).getProductInfoList().size() - 1) {
							listRow1.add(new PdfPRow(cells1));
							cells1 = new PdfPCell[6];
							j = 0;
						}
					}

					document.add(table1);
				}
			}

			List<AiTreatPlanJsonVo.NutritionalMatchItem> list0 = conditioningVo.getNutritionalMatchList().stream()
					.filter(a -> "0".equals(a.getSelectModel())).collect(Collectors.toList());
			if (CollectionUtils.isNotEmpty(list0)) {
				document.add(PdfUtils2.newParagraph20("可选营养", font12BoldBlue));
				for (AiTreatPlanJsonVo.NutritionalMatchItem item : list0) {
					document.add(PdfUtils2.newParagraph18(item.getName(), font10));
				}
				document.add(PdfUtils2.newParagraph("\n", font10));

				if (CollectionUtils.isNotEmpty(list0.get(0).getProductInfoList())) {
					PdfPTable table1 = new PdfPTable(6);
					table1.setWidthPercentage(100);
					table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
					List<PdfPRow> listRow1 = table1.getRows();
					table1.setWidths(new float[]{1.7f, 1.7f, 1.6f, 1.6f, 1.7f, 1.7f});

					PdfPCell[] cells1 = new PdfPCell[6];
					Integer j = 0;
					for (int i = 0; i < list0.get(0).getProductInfoList().size(); i++) {
						AiTreatPlanJsonVo.productInfoItem item = list0.get(0).getProductInfoList().get(i);
						cells1[j] = PdfUtils2.newCell("", font12, null, PdfPCell.NO_BORDER);
						cells1[j].addElement(PdfUtils2.newImage(item.getImg(), Element.ALIGN_CENTER, 50, 50));
						j++;
						cells1[j] = PdfUtils2.newCell(item.getProductName(), font12, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
						j++;

						if (j == 6 || i == list0.get(0).getProductInfoList().size() - 1) {
							listRow1.add(new PdfPRow(cells1));
							cells1 = new PdfPCell[6];
							j = 0;
						}
					}

					document.add(table1);
				}
			}
		}

		document.add(PdfUtils2.newParagraph("\n", font16));
		document.add(PdfUtils2.newParagraph("感谢您对智能营养助手认可，建议在7天后再次进行检测，帮您建立私人的健康管理档案，随时在线查阅。",
				font8Black, Element.ALIGN_CENTER, 18f));
		document.add(PdfUtils2.newParagraph("智能营养助手，由安徽中医药大学董昌武教授带队研发，已助力700多万人健康生活，全国200多家医院都在使用，准确率高达95%。",
				font8Black, Element.ALIGN_CENTER, 18f));
	}

	public static void main(String[] args) {
		GeneratePdfVO vo = new GeneratePdfVO();
		vo.setReportId("1166030276602761216");
		vo.setOpenid("oEpd363KwdU5Pdab4e2ojmnxRZ4o");

		// 创建文档对象
		Document document = new Document();
		// 设置pdf间距，默认36，改成18
		document.setMargins(20, 20, 15, 15);

		File file = null;
		Boolean isSuccess = false;

		try {
			LocalDateTime begin = LocalDateTime.now();
			System.out.println("===test===begin===" + begin);

			// 数据正确性判断，和pdf是否要生成
			LocalDateTime queryData = LocalDateTime.now();
			System.out.println("===test===queryData===" + Duration.between(begin, queryData).toMillis());

			// 文件
			file = new File(vo.getReportId() + ".pdf");

			// 查询pdf数据
			LocalDateTime queryPdf = LocalDateTime.now();
			pdfResVo = queryPdfResVo(vo);
			System.out.println("===test===queryPdf===" + Duration.between(begin, queryPdf).toMillis());

			// 指定PDF文件的输出路径
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));

			// 打开文档
			document.open();

			// 第一页，标题+个人信息+二维码
			LocalDateTime end1 = LocalDateTime.now();
			onePage(document);
			System.out.println("===test===end1===" + Duration.between(begin, end1).toMillis());

			// 第二页，健康状态
			LocalDateTime end2 = LocalDateTime.now();
			twoPage(document);
			System.out.println("===test===end2===" + Duration.between(end1, end2).toMillis());

			// 第三页，舌象辨识
			LocalDateTime end3 = LocalDateTime.now();
			threePage(document);
			System.out.println("===test===end3===" + Duration.between(end2, end3).toMillis());

			// 第四页，面象辨识
			LocalDateTime end4 = LocalDateTime.now();
			fourPage(document);
			System.out.println("===test===end4===" + Duration.between(end3, end4).toMillis());

			// 第五页，调理方案
			LocalDateTime end5 = LocalDateTime.now();
			fivePage(document);
			System.out.println("===test===end5===" + Duration.between(end4, end5).toMillis());

			LocalDateTime end6 = LocalDateTime.now();

			isSuccess = true;
			System.out.println("===test===all===" + Duration.between(begin, end6).toMillis());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭文档
			document.close();
			System.out.println("===PDF生成成功！==={}" + JSON.toJSONString(vo));
			//pdfResVo = new PdfResVo();

			// 上传
			try {
				if (isSuccess) {
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				/*if (file != null) {
					file.delete();
				}*/
			}

			//System.out.println("===vo===end==={}" + JSON.toJSONString(vo));
			//return BaseResponse.success(vo.getPdfUrl());
		}
	}


}
