package com.example.demo.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author huangjiale
 * @date 2022/3/8 10:15
 **/
public class PdfUtils {

	public static final String Dest = "D:\\demo.pdf";
	public static final String FontName = "STSong-Light";
	public static final String FontEncoding = "UniGB-UCS2-H";

	/**
	 * 根据PdfPTable表格创建pdf
	 *
	 * @param pdfPTable
	 * @return
	 */
	public static Document createDocument(PdfPTable pdfPTable, File file) {
		// A4翻转
		Document document = new Document(PageSize.A4.rotate());
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			//把表格添加到文档中
			document.add(pdfPTable);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
		return document;
	}

	/**
	 * 创建cell
	 *
	 * @param text
	 * @param font
	 * @param height
	 * @param colspan
	 * @return
	 */
	public static PdfPCell createCell(String text, Font font, float height, int colspan) {
		PdfPCell cell = new PdfPCell(new Paragraph(text, font));
		// 水平居住
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		// 垂直居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		// 固定高度
		cell.setFixedHeight(height);
		// 合并单元格，10格
		if (colspan > 1) {
			cell.setColspan(colspan);
		}
		return cell;
	}

	/**
	 * 创建cell，靠左
	 *
	 * @param text
	 * @param font
	 * @param height
	 * @param colspan
	 * @return
	 */
	public static PdfPCell createCellLeft(String text, Font font, float height, int colspan) {
		PdfPCell cell = new PdfPCell(new Paragraph(text, font));
		// 水平居住
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		// 垂直居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		// 固定高度
		cell.setFixedHeight(height);
		// 合并单元格，10格
		if (colspan > 1) {
			cell.setColspan(colspan);
		}
		return cell;
	}

}
