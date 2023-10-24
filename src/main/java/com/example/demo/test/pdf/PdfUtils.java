package com.example.demo.test.pdf;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangjiale
 * @date 2022/3/8 10:15
 * 简单封装了下一些openpdf的方法，方便使用，无其他用处
 **/
public class PdfUtils {

	public static final String FontName = "STSong-Light";
	public static final String FontEncoding = "UniGB-UCS2-H";

	// 宋体，正常字体
	public static BaseFont bfChinese;

	static {
		try {
			bfChinese = BaseFont.createFont(PdfUtils.FontName, PdfUtils.FontEncoding, BaseFont.NOT_EMBEDDED);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Font font16 = new Font(bfChinese, 16);
	public static Font font16Bold = new Font(bfChinese, 16, Font.BOLD);

	public static Color borderColor1 = new Color(79, 128, 189);
	public static Color backgroundColor1 = new Color(219, 229, 241);

	public static Map<String, Image> imageMap = new HashMap<>();


	/**
	 * 生成指定网址大小及图片格式的二维码
	 *
	 * @param url      网址
	 * @param size     尺寸
	 * @param fileType 二维码图片格式
	 * @param qrFile   生成图片保存地址
	 */
	public static void createQRCode(String url, int size, String fileType, File qrFile) {
		try {
			HashMap<EncodeHintType, Object> hintMap = new HashMap<>();
			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, size, size, hintMap);

			int matrixWidth = bitMatrix.getWidth();
			BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();

			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, matrixWidth, matrixWidth);
			graphics.setColor(Color.BLACK);

			for (int i = 0; i < matrixWidth; i++) {
				for (int j = 0; j < matrixWidth; j++) {
					if (bitMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
			ImageIO.write(image, fileType, qrFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取二维码网址
	 */
	public static String scanQRCode(String filePath) {
		String url = "";
		try {
			File file = new File(filePath);
			BufferedImage image = ImageIO.read(file);
			LuminanceSource source = new RGBLuminanceSource(image.getWidth(), image.getHeight(), getPixels(image));
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			Result result = new MultiFormatReader().decode(bitmap);
			url = result.getText();
			System.out.println("二维码中的网址为：" + url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * 获取图像的像素数据
	 *
	 * @param image
	 * @return
	 */
	private static int[] getPixels(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		int[] pixels = new int[width * height];
		image.getRGB(0, 0, width, height, pixels, 0, width);
		return pixels;
	}

	/**
	 * 创建image
	 *
	 * @param fileName
	 * @param fitWidth
	 * @param fitHeight
	 * @param absoluteX
	 * @param absoluteY
	 * @return
	 */
	public static Element newImage(String fileName, Integer alignment, float fitWidth, float fitHeight, float absoluteX, float absoluteY) {
		try {
			if (StringUtils.isBlank(fileName)) {
				return new Paragraph("");
			}
			//Image image = Image.getInstance("http://img.aikesaisi.com/jhbamin/images/2023-10-08/8ad97167797c4f0481bdb11f647a32f7-code.jpg");
			Image image = null;
			if (imageMap.containsKey(fileName)) {
				image = imageMap.get(fileName);
			} else {
				image = Image.getInstance(fileName);
				if (imageMap.size() >= 100) {
					imageMap = new HashMap<>();
				}
				imageMap.put(fileName, image);
			}
			if (alignment != null && alignment > 0) {
				image.setAlignment(alignment);
			}
			// 设置图片大小
			image.scaleToFit(fitWidth, fitHeight);
			// 设置二维码位置
			if (absoluteX != 0 && absoluteY != 0) {
				image.setAbsolutePosition(absoluteX, absoluteY);
			}
			// 设置图片分辨率，没用
			//image.setDpi(72, 72);
			return image;
		} catch (Exception e) {
			e.printStackTrace();
			return new Paragraph("");
		}
	}

	public static Element newImage(String fileName, float fitWidth, float fitHeight) {
		return newImage(fileName, null, fitWidth, fitHeight, 0, 0);
	}

	public static Element newImage(String fileName, Integer alignment, float fitWidth, float fitHeight) {
		return newImage(fileName, alignment, fitWidth, fitHeight, 0, 0);
	}

	/**
	 * 创建段落
	 *
	 * @param text
	 * @param textList
	 * @param font
	 * @param indentationLeft
	 * @param spacingBefore
	 */
	public static Paragraph newParagraph(String text, List<String> textList, Font font, Integer alignment, float indentationLeft, float spacingBefore) {
		Paragraph paragraph = new Paragraph(text, font);
		if (CollectionUtils.isNotEmpty(textList)) {
			for (String str : textList) {
				paragraph.add(str);
			}
		}
		if (alignment != null) {
			paragraph.setAlignment(alignment);
		} else {
			paragraph.setAlignment(Element.ALIGN_LEFT);
		}
		if (indentationLeft != 0) {
			paragraph.setIndentationLeft(indentationLeft);
		}
		if (spacingBefore != 0) {
			paragraph.setSpacingBefore(spacingBefore);
		}
		return paragraph;
	}

	public static Paragraph newParagraph(String text, List<String> textList, Font font) {
		return newParagraph(text, textList, font, null, 0, 0);
	}

	public static Paragraph newParagraph(String text, Font font) {
		return newParagraph(text, null, font, null, 0, 0);
	}

	public static Paragraph newParagraph(List<String> textList, Font font) {
		return newParagraph(null, textList, font, null, 0, 0);
	}

	public static Paragraph newParagraph(String text, Font font, Integer alignment) {
		return newParagraph(text, null, font, alignment, 0, 0);
	}

	public static Paragraph newParagraph(List<String> textList, Font font, Integer alignment) {
		return newParagraph(null, textList, font, alignment, 0, 0);
	}

	/**
	 * 创建cell
	 *
	 * @param text
	 * @param font
	 * @param colspan
	 * @param rowspan
	 * @param element
	 * @return
	 */
	public static PdfPCell newCell(String text, Font font, Integer colspan, Integer rowspan, Integer element,
								   Integer height, Integer border, Color borderColor, Color backgroundColor) {
		PdfPCell cell = new PdfPCell(new Paragraph(text, font));
		// 边框
		if (border != null) {
			cell.setBorder(border);
		}
		// 边框颜色
		if (borderColor != null) {
			cell.setBorderColor(borderColor);
		}
		// 背景颜色
		if (backgroundColor != null) {
			cell.setBackgroundColor(backgroundColor);
		}
		// 高度
		if (height != null && height > 0) {
			cell.setFixedHeight(height);
		} else {
			// 自适应高度
			cell.setNoWrap(false);
		}
		// 垂直居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		// 水平
		if (element == null) {
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		} else {
			cell.setHorizontalAlignment(element);
		}
		// 合并单元格，10格
		if (colspan != null && colspan > 1) {
			cell.setColspan(colspan);
		}
		// 合并单元格，10格
		if (rowspan != null && rowspan > 1) {
			cell.setRowspan(rowspan);
		}
		// 行间距
		cell.setLeading(25, 0);
		// 边距
		//cell.setPaddingTop(5f);
		cell.setPaddingBottom(15f);
		cell.setPaddingLeft(10f);
		cell.setPaddingRight(10f);
		return cell;
	}

	public static PdfPCell newCell(String text) {
		return newCell(text, font16, null, null, null, null, null, null, null);
	}

	public static PdfPCell newCell(String text, Font font) {
		return newCell(text, font, null, null, null, null, null, null, null);
	}

	public static PdfPCell newCell(String text, Font font, Integer element) {
		return newCell(text, font, null, null, element, null, null, null, null);
	}

	public static PdfPCell newCell(String text, Font font, Integer height, Integer border) {
		return newCell(text, font, null, null, null, height, border, null, null);
	}

	public static PdfPCell newCell(String text, Font font, Integer colspan, Integer rowspan, Integer element, Integer border) {
		return newCell(text, font, colspan, rowspan, element, null, border, null, null);
	}

	public static PdfPCell newCell(String text, Font font, Integer colspan, Integer rowspan, Integer element, Integer border, Color borderColor) {
		return newCell(text, font, colspan, rowspan, element, null, border, borderColor, null);
	}

	public static PdfPCell newCell(String text, Font font, Integer colspan, Integer rowspan, Integer element, Integer height, Integer border) {
		return newCell(text, font, colspan, rowspan, element, height, border, null, null);
	}

	public static PdfPCell newCellLeft(String text) {
		return newCell(text, font16, null, null, Element.ALIGN_LEFT, null, null, borderColor1, null);
	}

	public static PdfPCell newCellLeft(String text, Integer colspan, Integer rowspan) {
		return newCell(text, font16, colspan, rowspan, Element.ALIGN_LEFT, null, null, borderColor1, null);
	}

	public static PdfPCell newCellCenter(String text) {
		return newCell(text, font16Bold, null, null, Element.ALIGN_CENTER, null, null, borderColor1, null);
	}

	public static PdfPCell newCellCenter(String text, Integer colspan, Integer rowspan) {
		return newCell(text, font16Bold, colspan, rowspan, Element.ALIGN_CENTER, null, null, borderColor1, null);
	}


}
