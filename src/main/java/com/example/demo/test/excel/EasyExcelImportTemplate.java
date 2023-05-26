package com.example.demo.test.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.demo.vo.ImportResVo;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author huangjiale
 * @date 2022/9/7 15:26
 **/
@Component
public class EasyExcelImportTemplate {

	/**
	 * 执行器
	 *
	 * @param <T>
	 */
	private class MyListener<T> extends AnalysisEventListener<T> {

		// 每次执行数据的数量
		private static final int batchCount = 10000;
		// 每次存放数据的list
		private List<T> list = new ArrayList<>();
		// 导入返回结果
		public ImportResVo resVo = new ImportResVo();

		// 业务执行方法
		public Function<List<T>, ImportResVo> service = ts -> new ImportResVo();

		@Override
		public void invoke(T data, AnalysisContext context) {
			list.add(data);
			if (list.size() >= batchCount) {
				ImportResVo vo = service.apply(list);
				if (vo != null) {
					resVo.setSuccessSize(resVo.getSuccessSize() + vo.getSuccessSize());
					resVo.getErrorList().addAll(vo.getErrorList());
				}
				list.clear();
			}
		}

		@Override
		public void doAfterAllAnalysed(AnalysisContext context) {
			if (list.size() > 0) {
				ImportResVo vo = service.apply(list);
				if (vo != null) {
					resVo.setSuccessSize(resVo.getSuccessSize() + vo.getSuccessSize());
					resVo.getErrorList().addAll(vo.getErrorList());
				}
				list.clear();
			}
		}

	}

	/**
	 * 导入excel
	 *
	 * @param file
	 * @param cls
	 * @param service
	 * @param <T>
	 * @return
	 */
	public <T> ImportResVo importExcel(MultipartFile file, Class<T> cls, Function<List<T>, ImportResVo> service) {
		try {
			InputStream inputStream = file.getInputStream();
			MyListener<T> myListener = new MyListener<>();
			myListener.service = service;
			EasyExcel.read(inputStream, cls, myListener).sheet().doRead();
			return myListener.resVo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ImportResVo();
	}

}
