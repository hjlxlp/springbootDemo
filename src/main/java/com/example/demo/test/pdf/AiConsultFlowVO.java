package com.example.demo.test.pdf;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
public class AiConsultFlowVO {

	/**
	 * 自增键
	 */
	private Long id;

	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "性别，0、男 1、女")
	private String genderDesc;

	@ApiModelProperty(value = "性别，0、男 1、女")
	private Integer gender;

	@ApiModelProperty(value = "出生日期")
	private String birthday;

	@ApiModelProperty(value = "年龄")
	private BigDecimal age;

	/**
	 * 批次号
	 */
	private String reportId;

	/**
	 * 舌象属性
	 */
	private String tongueAttrs;

	/**
	 * 中医辨识批次号
	 */
	private String tongueBatchNo;

	/**
	 * 面象特征验证阶段:
	 *    1, 舌象预诊
	 *    2, 舌诊提交
	 *    3, 问诊提交
	 */
	private Integer tongueStep;

	/**
	 * 面象特征验证阶段状态：
	 * 0-待验证
	 * 1-已验证
	 * 2-验证失败
	 */
	private Integer tongueStepStatus;

	/**
	 * 功能：检测类别名称
	 */
	private String detectionTypeDesc;

	/**
	 * 额外过程图-云诊地址
	 */
	private String tongueProcessExtraImage;

	/**
	 * 额外过程图-内部地址
	 */
	private String tongueProcessExtraImage1;

	/**
	 * 体质名： 逗号隔开
	 */
	private String constitutionNames;

	/**
	 * 面象特征批次号
	 */
	private String faceBatchNo;

	/**
	 * 模板ID
	 */
	private Long templateId;

	/**
	 * 检测顺序
	 */
	private Integer checkIndex;

	/**
	 * 用户ID
	 */
	private Long checkId;

	/**
	 * 档案ID
	 */
	private Long userInfoId;

	/**
	 * 中医辨识验证阶段
	 */
	private Integer step;

	/**
	 * 中医辨识验证阶段状态：0-待验证 1-已验证 2-验证失败
	 */
	private Integer stepStatus;

	/**
	 * 面象特征验证阶段
	 */
	private Integer faceStep;

	/**
	 * 面象特征验证阶段状态：0-待验证 1-已验证 2-验证失败
	 */
	private Integer faceStepStatus;

	/**
	 * 功能：舌象预判失败信息
	 */
	private String tonguePreFailMsg;

	/**
	 * 功能：面象预判失败信息
	 */
	private String facePreFailMsg;

	/**
	 * 功能：检测类别
	 */
	private String detectionType;

	/**
	 * 功能：检测类别
	 */
	private String detectionTypeName;

	/**
	 * 功能：问题问诊
	 */
	private String questions;

	/**
	 * 功能：问诊回答
	 */
	private String answers;

	/**
	 * 报告锁定状态：0-否 1-是
	 */
	private Integer reportLockStatus;

	/**
	 * 舌面图
	 */
	private String tongueSurfaceImg;

	/**
	 * 舌下图
	 */
	private String tongueBackImg;

	/**
	 * 舌面分割图-云诊地址
	 */
	private String tongueSplitSurfaceImg;

	/**
	 * 舌面分割图-内部地址
	 */
	private String tongueSplitSurfaceImg1;

	/**
	 * 舌下分割图-云诊地址
	 */
	private String tongueSplitBackImg;

	/**
	 * 舌下分割图-内部地址
	 */
	private String tongueSplitBackImg1;

	/**
	 * 过程图-云诊地址
	 */
	private String tongueProcessImages;

	/**
	 * 过程图-内部地址
	 */
	private String tongueProcessImages1;

	/**
	 * 舌色（淡白舌、舌质淡、淡红舌、红舌、绛舌、青紫舌）
	 */
	private String tongueColor;

	/**
	 * 舌色描述
	 */
	private String tongueColorDesc;

	/**
	 * 苔色（白苔、淡黄苔、黄苔、焦黄苔、灰黑苔、焦黑苔）
	 */
	private String tongueMossColor;

	/**
	 * 苔色描述
	 */
	private String tongueMossColorDesc;

	/**
	 * 苔质（薄苔、厚苔、腐、腻、剥落、无苔、少苔）
	 */
	private String tongueMossNature;

	/**
	 * 苔质描述
	 */
	private String tongueMossNatureDesc;

	/**
	 * 舌形（胖、瘦、老、嫩、点刺、瘀斑、瘀点、裂纹、齿痕）
	 */
	private String tongueShap;

	/**
	 * 舌形描述
	 */
	private String tongueShapDesc;

	/**
	 * 津液（润、滑、燥）
	 */
	private String tongueBodyfluid;

	/**
	 * 津液描述
	 */
	private String tongueBodyfluidDesc;

	/**
	 * 络脉（舌下正常、舌下虚象、舌下瘀象、舌下图不清晰）
	 */
	private String tongueVein;

	/**
	 * 络脉描述
	 */
	private String tongueVeinDesc;

	/**
	 * 正面部图片
	 */
	private String faceFrontImg;

	/**
	 * 左侧面部图
	 */
	private String faceLeftImg;

	/**
	 * 右面部图
	 */
	private String faceRightImg;

	/**
	 * 面色（面色红黄隐隐，明润含蓄、面色晦暗枯槁或暴露浮现）
	 */
	private String faceMianse;
	private String faceMianseName;
	private String faceMianseDesc;
	private String faceMianseUrl;
	private Boolean faceMianseIsRed;

	/**
	 * 主色（五色诊）（正常、面赤、面黄、面白、面黑、面青）
	 */
	private String faceMainColor;
	private String faceMainColorName;
	private String faceMainColorDesc;
	private String faceMainColorUrl;
	private Boolean faceMainColorIsRed;

	/**
	 * 光泽（正常、少量、无）
	 */
	private String faceGloss;
	private String faceGlossName;
	private String faceGlossDesc;
	private String faceGlossUrl;
	private Boolean faceGlossIsRed;

	/**
	 * 黑眼圈左（正常、轻度、重度、不符合要求（装扮或眼部受伤））
	 */
	private String faceDarkCircles;
	private String faceDarkCirclesName;
	private String faceDarkCirclesDesc;
	private String faceDarkCirclesLeftUrl;
	private String faceDarkCirclesRightUrl;
	private Boolean faceDarkCirclesIsRed;

	/**
	 * 唇色（正常、白 、红、暗红、青紫 、黑色或斑点、不符合检测要求）
	 */
	private String faceLipColor;
	private String faceLipColorName;
	private String faceLipColorDesc;
	private String faceLipColorUrl;
	private Boolean faceLipColorIsRed;

	/**
	 * 眼神（有神、少神、不符合检测要求）
	 */
	private String faceEyes;
	private String faceEyesName;
	private String faceEyesDesc;
	private String faceEyesUrl;
	private Boolean faceEyesIsRed;

	/**
	 * 目色（正常、目赤肿胀、目黄、不符合检测要求）
	 */
	private String faceEyesColor;
	private String faceEyesColorName;
	private String faceEyesColorDesc;
	private String faceEyesColorLeftUrl;
	private String faceEyesColorRightUrl;
	private Boolean faceEyesColorIsRed;

	/**
	 * 两颧红（正常、轻度）
	 */
	private String faceCheekbones;
	private String faceCheekbonesName;
	private String faceCheekbonesDesc;
	private String faceCheekbonesUrl;
	private Boolean faceCheekbonesIsRed;

	/**
	 * 鼻褶（无 、轻度、重度）
	 */
	private String faceNasalFold;
	private String faceNasalFoldName;
	private String faceNasalFoldDesc;
	private String faceNasalFoldUrl;
	private Boolean faceNasalFoldIsRed;

	/**
	 * 眉间/鼻柱青色（正常、眉间有青色）
	 */
	private String faceGlabella;
	private String faceGlabellaName;
	private String faceGlabellaDesc;
	private String faceGlabellaUrl;
	private Boolean faceGlabellaIsRed;

	/**
	 * 面部皮损（正常、疑似色素痣、皮下颗粒物、疑似皮肤病，需进一步检测）
	 */
	private String faceSkinLesions;
	private String faceSkinLesionsName;
	private String faceSkinLesionsDesc;
	private String faceSkinLesionsUrl;
	private Boolean faceSkinLesionsIsRed;

	/**
	 * 耳色（正常、白、红、黄、青紫、不符合检测要求）
	 */
	private String faceEarColor;
	private String faceEarColorName;
	private String faceEarColorDesc;
	private String faceEarColorLeftUrl;
	private String faceEarColorRightUrl;
	private Boolean faceEarColorIsRed;

	/**
	 * 耳褶（无、轻度、重度、不符合检测要求）
	 */
	private String faceUricularFold;
	private String faceUricularFoldName;
	private String faceUricularFoldDesc;
	private String faceUricularFoldLeftUrl;
	private String faceUricularFoldRightUrl;
	private Boolean faceUricularFoldIsRed;

	/**
	 * 计算图-云诊地址
	 */
	private String faceCaluateImage;

	/**
	 * 计算图-内部地址
	 */
	private String faceCaluateImage1;

	/**
	 * 过程图-云诊地址
	 */
	private String faceProcessImage;

	/**
	 * 过程图-内部地址
	 */
	private String faceProcessImage1;

	/**
	 * 诊断结果
	 */
	private String diagnosticResults;

	/**
	 * 主要表现
	 */
	private String mainPerformance;

	/**
	 * 发生原因
	 */
	private String occurReason;

	/**
	 * 易患病症
	 */
	private String predisposition;

	/**
	 * 高风险提示
	 */
	private String highRiskDisease;

	/**
	 * 饮食习惯
	 */
	private String eatingHabits;

	/**
	 * 饮食健康
	 */
	private String dietRehabilitation;

	/**
	 * 膳食调理
	 */
	private String dietaryRecuperate;

	/**
	 * 运动保健
	 */
	private String sportsHealthCare;

	/**
	 * 药物保健
	 */
	private String drugHealthCare;

	/**
	 * 穴位保健
	 */
	private String massageHealthCare;

	/**
	 * 患病评分
	 */
	private String tongueScore;

	/**
	 * 提示语
	 */
	private String tongueTips;

	/**
	 * 情志起居调养
	 */
	private String recuperates;

	private Date createTime;

	private Date updateTime;

	private List<String> tongueColorList;

	private String tongueColorDescription;

	private List<AiReportItemVO> faceColorList;

	private UserShopDetailVO userShopDetailVO;

	private List<String> tongueColorAttrList;

	private List<String> tongueColorInfoList;

	private List<String> tongueMossAttrList;

	private List<String> tongueMossColorInfoList;

	private List<String> tongueNatureAttrList;

	private List<String> tongueShapAttrList;

	private List<String> tongueBodyAttrList;

	private List<String> tongueVeinAttrList;

	private List<AiReportItemVO> aiFaceItemVOList;
	private LinkedList<AiReportItemVO> aiTongueItemVOList;
	private LinkedList<AiReportItemVO> aiTongueVOList;
	private List<AiReportItemVO> aiReportItemVOList;

}
