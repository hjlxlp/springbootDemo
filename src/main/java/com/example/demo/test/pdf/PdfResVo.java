package com.example.demo.test.pdf;

import lombok.Data;

/**
 * @author huangjiale
 * @date 2023/10/17 13:26
 **/
@Data
public class PdfResVo {

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
	 * 健康分析
	 */
	private AiTreatPlanJsonVo healthVo;

	/**
	 * 调理方案
	 */
	private AiTreatPlanJsonVo conditioningVo;

	/**
	 * 入参
	 */
	private GeneratePdfVO generatePdfVO;

}
