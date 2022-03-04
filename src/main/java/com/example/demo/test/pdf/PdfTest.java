package com.example.demo.test.pdf;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author huangjiale
 * @date 2022/3/4 13:11
 **/
public class PdfTest {

	public static final String DEST = "D:\\demo.pdf";

	public static void main(String[] args) throws Exception {
		PdfWriter writer = new PdfWriter(DEST);
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf, PageSize.A4.rotate());
		document.setMargins(20, 20, 20, 20);
		//PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
		//PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
		Table table = new Table(new float[]{80, 80, 80, 80, 80, 80, 80, 80, 80, 80});
		table.setWidth(800);

		for (int i = 0; i < 80; i++) {
			Cell cell = new Cell();
			cell.getRow();
			cell.add(new Paragraph("aaa"));
			table.addCell(cell);
		}

		document.add(table);
		document.close();
	}


	public static void main1(String[] args) throws Exception {
		PdfWriter writer = new PdfWriter(DEST);
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf);
		// Create a PdfFont
		// PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
		PdfFont font = PdfFontFactory.createFont();
		// Add a Paragraph
		document.add(new Paragraph("iText is:").setFont(font));
		// Create a List
		List list = new List()
				.setSymbolIndent(12)
				.setListSymbol("\u2022");
		//.setFont(font);
		// Add ListItem objects
		list.add(new ListItem("Never gonna give you up"))
				.add(new ListItem("Never gonna let you down"))
				.add(new ListItem("Never gonna run around and desert you"))
				.add(new ListItem("Never gonna make you cry"))
				.add(new ListItem("Never gonna say goodbye"))
				.add(new ListItem("Never gonna tell a lie and hurt you"));
		// Add the list
		document.add(list);
		document.close();
	}



	/*public static void main(String[] args) throws Exception {
		PdfWriter writer = new PdfWriter(DEST);
		PdfDocument pdf = new PdfDocument(writer);

		ClassPathResource resource = new ClassPathResource("fonts/simfang.ttf");
		// 项目指定字体, 通过类加载项目字体资源
		PdfFont font = PdfFontFactory.createFont(toByteArray(resource.getInputStream()), null, PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED, false);
		Document document = new Document(pdf);
		document.setFont(font);

		document.add(new Paragraph("呵呵哒"));
		document.close();
		//getDocument();
	}*/

	// 字体
	public static final String FONT = "/fonts/simfang.ttf";

	public static Document getDocument() {
		PdfDocument pdfDocument;
		Document document;
		PdfFont font;
		try {
			ClassPathResource resource = new ClassPathResource("fonts/simfang.ttf");
			pdfDocument = new PdfDocument(new PdfWriter(DEST));
			// 项目指定字体, 通过类加载项目字体资源
			font = PdfFontFactory.createFont(toByteArray(resource.getInputStream()), null, PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED, false);
			document = new Document(pdfDocument);
			//document.setFont(font);
			return document;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		return output.toByteArray();
	}


}
