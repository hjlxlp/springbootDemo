package com.example.demo.test.pdf2;

import com.example.demo.test.pdf.GeneratePdfVO;
import com.example.demo.test.pdf2.vo.AiConsultFlowVO;
import com.example.demo.test.pdf2.vo.AiTreatPlanJsonVo;
import lombok.Data;

/**
 * @author huangjiale
 * @date 2023/10/17 13:26
 **/
@Data
public class PdfResVo2 {

	/**
	 * 详情
	 */
	private AiConsultFlowVO detailVo;

	/**
	 * 舌诊
	 */
	private AiConsultFlowVO tongueVo;

	/**
	 * 面诊
	 */
	private AiConsultFlowVO faceVo;

	/**
	 * 调理方案
	 */
	private AiTreatPlanJsonVo conditioningVo;

	/**
	 * 入参
	 */
	private GeneratePdfVO generatePdfVO;

}
