package com.example.demo.test.export;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PdfUtils {

	public static void main(String[] args) throws IOException {
		PDDocument pdDocument = null;
		/* dpi越大转换后越清晰，相对转换速度越慢 */
		int dpi = 200;
		try {
			File file = new File("demo.pdf");
			File file2 = new File("demo.png");
			pdDocument = PDDocument.load(file);

			PDFRenderer renderer = new PDFRenderer(pdDocument);
			BufferedImage image = renderer.renderImageWithDPI(0, dpi);
			ImageIO.write(image, "png", file2);

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
		}
	}

	/**
	 * PDF转图片 根据页码一页一页转
	 *
	 * @throws IOException imgType:转换后的图片类型 jpg,png
	 */
	public static List<String> pdfToImg(String pdfUrl, String basePath, String imgType) {
		List<String> files = new ArrayList<>();
		PDDocument pdDocument = null;
		/* dpi越大转换后越清晰，相对转换速度越慢 */
		int dpi = 200;
		try {
			File file = new File("demo.pdf");
			File file2 = new File("demo.png");
			pdDocument = PDDocument.load(file);

			PDFRenderer renderer = new PDFRenderer(pdDocument);
			BufferedImage image = renderer.renderImageWithDPI(0, dpi);
			ImageIO.write(image, imgType, file2);

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
		}
		return files;
	}

	public static List<String> pdfToImg(File file, String basePath, String imgType) {
		List<String> files = new ArrayList<>();
		PDDocument pdDocument = null;
		/* dpi越大转换后越清晰，相对转换速度越慢 */
		int dpi = 200;
		try {
			pdDocument = getPDDocument(file);
			PDFRenderer renderer = new PDFRenderer(pdDocument);
			int pages = pdDocument.getNumberOfPages();
			for (int page = 0; page < pages; page++) {
				String fileName = basePath
						+ "/" + UUID.randomUUID().toString().replaceAll("-", "")
						+ "." + imgType;
				File f = new File(fileName);
				BufferedImage image = renderer.renderImageWithDPI(page, dpi);
				ImageIO.write(image, imgType, f);
				files.add(fileName);
			}
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
		}
		return files;
	}

	private static PDDocument getPDDocument(String fileUrl) throws IOException {
		File file = new File(fileUrl);
		FileInputStream inputStream = new FileInputStream(file);
		return PDDocument.load(inputStream);
	}


	private static PDDocument getPDDocument(File file) throws IOException {
		FileInputStream inputStream = new FileInputStream(file);
		return PDDocument.load(inputStream);
	}
}
