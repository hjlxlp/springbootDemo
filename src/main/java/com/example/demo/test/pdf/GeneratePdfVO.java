package com.example.demo.test.pdf;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huangjiale
 * @date 2023/10/19 10:47
 **/
@Data
public class GeneratePdfVO {

	private Long id;

	private String reportId;

	private String openid;

	@ApiModelProperty(value = "模板ID")
	private Long templateId;

	@ApiModelProperty("0-存在则不生成，1-重新生成")
	private Integer type;

	@ApiModelProperty("是否要生成上传")
	private Boolean isGenerate;

	@ApiModelProperty("pdfUrl")
	private String pdfUrl;

}
