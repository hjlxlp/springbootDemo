package com.example.demo.service.impl;

import com.example.demo.service.ExcelService;
import com.example.demo.vo.ExportInputVo;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author hjl
 * @date 2019/3/14 16:49
 */
@Service
public class ExcelServiceImpl implements ExcelService {

	@Override
	public void export(ExportInputVo vo) {

		// 声明一个工作薄
		XSSFWorkbook wb = new XSSFWorkbook();
		// 生成一个样式
		XSSFCellStyle style = wb.createCellStyle();
		//样式字体居中
		//style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		//声明第一个sheet名称
		XSSFSheet sheet1 = wb.createSheet("图片");

		byte[] image = new byte[1024 * 1024];
		try {
			//image = new BASE64Decoder().decodeBuffer(vo.getImagesBase64().substring(22));
		} catch (Exception e) {
			e.printStackTrace();
		}

		//将获取到的base64 编码转换成图片，画到excel中
		XSSFDrawing patriarch = sheet1.createDrawingPatriarch();
		XSSFClientAnchor anchor = null;
		int index = 0;
		anchor = new XSSFClientAnchor(0, 0, 0, 0, (short) (8 * (index % 3)), ((index / 3) * 18), (short) (7 + 8 * (index % 3)), 16 + ((index / 3) * 18));
		patriarch.createPicture(anchor, wb.addPicture(image, XSSFWorkbook.PICTURE_TYPE_PNG));

		//导出excel到本地
		try {
			// 创建输出流对象
			FileOutputStream stream = new FileOutputStream(new File("./text.xlsx"));
			wb.write(stream);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
