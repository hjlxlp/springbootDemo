package com.example.demo.test.pdf;

import com.alibaba.fastjson.JSON;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import lombok.Data;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author huangjiale
 * @date 2023/10/10 13:25
 **/
@Data
public class PdfAi2 {

	public static String str1 = "{\"pdfUrl\":null,\"openid\":null,\"id\":86,\"name\":\"测试2\",\"genderDesc\":\"女\",\"gender\":1,\"birthday\":\"2010-07-21\",\"age\":13.3,\"reportId\":\"1166030276602761216\",\"tongueAttrs\":\"[{\\\"attrName\\\":\\\"舌色程度\\\",\\\"attrValue\\\":\\\"舌边淡\\\"},{\\\"attrName\\\":\\\"舌根苔色\\\",\\\"attrValue\\\":\\\"舌根白苔\\\"},{\\\"attrName\\\":\\\"瘀点数量\\\",\\\"attrValue\\\":\\\"0\\\"},{\\\"attrName\\\":\\\"瘀点程度\\\",\\\"attrValue\\\":\\\"全舌无瘀点\\\"},{\\\"attrName\\\":\\\"齿痕\\\",\\\"attrValue\\\":\\\"轻度齿痕\\\"}]\",\"tongueBatchNo\":\"1166030277122854912\",\"tongueStep\":3,\"tongueStepStatus\":1,\"detectionTypeDesc\":\"脾胃病\",\"tongueProcessExtraImage\":\"{}\",\"tongueProcessExtraImage1\":null,\"constitutionNames\":\"通用结论\",\"faceBatchNo\":\"1166030285750538240\",\"templateId\":1705,\"checkIndex\":null,\"checkId\":14,\"userInfoId\":8,\"step\":null,\"stepStatus\":null,\"faceStep\":2,\"faceStepStatus\":1,\"tonguePreFailMsg\":\"\",\"facePreFailMsg\":\"\",\"detectionType\":\"C08.D00\",\"detectionTypeName\":null,\"questions\":\"[{\\\"group\\\":\\\"\\\",\\\"types\\\":[{\\\"code\\\":\\\"IS0105\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"形体肌肤\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"唇红\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"霰粒肿\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"面色少华\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"形体消瘦\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"眼皮浮肿\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"面色萎黄\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"皮肤粗糙\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"嘴角溃烂\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"形体肥胖\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"鼻梁有青筋\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"眼睑发紫发红\\\"}],\\\"sort\\\":1},{\\\"code\\\":\\\"IS0106\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"头身不适\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"呃逆\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"呕吐\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腹胀\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腹痛\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"黄痰\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"肚子硬\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"脘痞胀\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"易感冒\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腿抽筋\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"倦怠乏力\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"面色晦暗\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"牙龈出血\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腹痛欲泻\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"咽喉红肿\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"头发易打结\\\"}],\\\"sort\\\":2},{\\\"code\\\":\\\"IS0107\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"寒热\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"盗汗\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"手足心热\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"身体发热\\\"}],\\\"sort\\\":3},{\\\"code\\\":\\\"IS0108\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"饮食\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"口臭\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"口渴\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"贪吃\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"食欲不振\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"喜欢吃凉东西\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"消化系统紊乱\\\"}],\\\"sort\\\":4},{\\\"code\\\":\\\"IS0109\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"二便\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"排便不爽\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"大便干结\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"小便短黄\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"便秘或腹泻\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"大便有臭鸡蛋味\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"大便夹杂不消化食物\\\"}],\\\"sort\\\":5},{\\\"code\\\":\\\"IS0110\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"情志睡眠\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"嗳气\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"烦躁\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"失眠\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"磨牙\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"善悲易哭\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"急躁易怒\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"睡觉露睛\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"夜眠不安\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"燥扰不宁\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"睡觉流口水\\\"}],\\\"sort\\\":6}]}]\",\"answers\":\"[{\\\"answerCode\\\":\\\"腹胀\\\",\\\"code\\\":\\\"IS0106\\\",\\\"questionIndex\\\":2},{\\\"answerCode\\\":\\\"唇红\\\",\\\"code\\\":\\\"IS0105\\\",\\\"questionIndex\\\":1},{\\\"answerCode\\\":\\\"手足心热\\\",\\\"code\\\":\\\"IS0107\\\",\\\"questionIndex\\\":3},{\\\"answerCode\\\":\\\"食欲不振\\\",\\\"code\\\":\\\"IS0108\\\",\\\"questionIndex\\\":4},{\\\"answerCode\\\":\\\"便秘或腹泻\\\",\\\"code\\\":\\\"IS0109\\\",\\\"questionIndex\\\":5},{\\\"answerCode\\\":\\\"失眠\\\",\\\"code\\\":\\\"IS0110\\\",\\\"questionIndex\\\":6}]\",\"reportLockStatus\":0,\"tongueSurfaceImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/f89cc8108b47471994f37e834eab1fcd-tmp_be0673ac3c80cf3597be30659f7b70d00b73a0489851eab3.jpg\",\"tongueBackImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/9a27ae484edf4abab461187a08bd3f27-tmp_1ee8eebbe1ec0a7394569b85a03d35c080e1950f0556bca8.jpg\",\"tongueSplitSurfaceImg\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"tongueSplitSurfaceImg1\":\"\",\"tongueSplitBackImg\":\"https://td-platform-split-back.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"tongueSplitBackImg1\":\"\",\"tongueProcessImages\":\"{\\\"ossYudianImgUrl\\\":\\\"\\\",\\\"ossSheseImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/shese/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossShexingImgUrl\\\":\\\"\\\",\\\"ossToothtraceImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/toothtrace/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossBoluoImgUrl\\\":\\\"\\\",\\\"ossLiewenImgUrl\\\":\\\"\\\",\\\"ossTaiseImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/taise/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossYubanImgUrl\\\":\\\"\\\",\\\"ossLuomaiLabelImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/luomai_label/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossPrickImgUrl\\\":\\\"\\\"}\",\"tongueProcessImages1\":null,\"tongueColor\":\"淡红舌\",\"tongueColorDesc\":\"【淡红舌】正常人舌色，舌质呈现润泽红活。</br>【病理意义】正常舌色，或者处于病情轻浅阶段。</br>\",\"tongueMossColor\":\"白苔\",\"tongueMossColorDesc\":\"【白苔】舌苔呈现白色。</br>【病理意义】正常苔色。</br>\",\"tongueMossNature\":\"薄苔\",\"tongueMossNatureDesc\":\"【薄苔】透过舌苔能隐隐地见到舌体。 </br>【病理意义】脾胃状态良好。</br>\",\"tongueShap\":\"齿痕\",\"tongueShapDesc\":\"【齿痕】舌体边缘有牙齿压迫的痕迹。</br>【病理意义】脾虚、气虚或体内有湿。</br>\",\"tongueBodyfluid\":\"\",\"tongueBodyfluidDesc\":\"【润】舌苔干湿适中，不滑不燥为润苔。 </br>【病理意义】舌苔水分正常。</br>\",\"tongueVein\":\"舌下正常\",\"tongueVeinDesc\":\"【舌下正常】正常情况下，其管径不超过2.7mm,长度不超过舌尖至舌下肉阜连线的3/5,颜色暗红，无分支和紫点。脉络无怒张、紧束、弯曲、增生，排列有序，绝大多数为单支，极少有双支出现。</br>【临床表现】舌下络脉正常。</br>\",\"faceFrontImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/f1a3ae630da945b5a78dc013e9d915fb-tmp_445d54aa67689e29f9a54c757abc1c69bad16f834eac7d84.jpg\",\"faceLeftImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/e1d71c630f174ab69236428e721a6aca-tmp_c0a0bf082e9e0f3c2676ecae84df531cb7426fb21848d3e1.jpg\",\"faceRightImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/d867390967b64836bd7044d2ecace326-tmp_6ff305bf982195f64fe58701a28c19a0c6a75f70358452e3.jpg\",\"faceMianse\":\"{\\\"code\\\":\\\"m-10\\\",\\\"description\\\":\\\"【正常面色】面色红黄隐隐，明润含蓄。</br>\\\\n【病理意义】神气、胃气的正常色泽表现。</br>\\\",\\\"name\\\":\\\"面色红黄隐隐，明润含蓄\\\"}\",\"faceMianseName\":null,\"faceMianseDesc\":null,\"faceMianseUrl\":null,\"faceMianseIsRed\":null,\"faceMainColor\":\"{\\\"code\\\":\\\"m-14\\\",\\\"description\\\":\\\"【正常色】面色红黄隐隐，明润含蓄。</br>\\\\n【病理意义】神气、胃气的正常色泽表现。</br>\\\\n\\\",\\\"name\\\":\\\"正常色\\\"}\",\"faceMainColorName\":null,\"faceMainColorDesc\":null,\"faceMainColorUrl\":null,\"faceMainColorIsRed\":null,\"faceGloss\":\"{\\\"code\\\":\\\"m-21\\\",\\\"description\\\":\\\"【少量】面色稍许晦暗、少泽。</br>\\\\n【病理意义】为脏腑精气稍衰，介于病轻和病重之间。</br>\\\\n\\\",\\\"name\\\":\\\"少量\\\"}\",\"faceGlossName\":null,\"faceGlossDesc\":null,\"faceGlossUrl\":null,\"faceGlossIsRed\":null,\"faceDarkCircles\":\"{\\\"code\\\":\\\"ml-30,mr-30\\\",\\\"description\\\":\\\"【左眼正常】左眼眼圈不发黑，眼眶颜色正常。</br>\\\\n【病理意义】左眼无黑眼圈。</br>\\\\n,【右眼正常】右眼眼圈不发黑，眼眶颜色正常。</br>\\\\n【病理意义】右眼无黑眼圈。</br>\\\\n\\\",\\\"name\\\":\\\"左眼正常,右眼正常\\\"}\",\"faceDarkCirclesName\":null,\"faceDarkCirclesDesc\":null,\"faceDarkCirclesLeftUrl\":null,\"faceDarkCirclesRightUrl\":null,\"faceDarkCirclesIsRed\":null,\"faceLipColor\":\"{\\\"code\\\":\\\"m-40\\\",\\\"description\\\":\\\"【正常】唇色是明亮、润泽、含蓄的红色。</br>\\\\n【病理意义】胃气充足，气血调匀的表现。</br>\\\",\\\"name\\\":\\\"正常\\\"}\",\"faceLipColorName\":null,\"faceLipColorDesc\":null,\"faceLipColorUrl\":null,\"faceLipColorIsRed\":null,\"faceEyes\":\"{\\\"code\\\":\\\"m-51\\\",\\\"description\\\":\\\"【少神】两目乏神，动作迟缓，眼神晦暗。</br>\\\\n【病理意义】是正气不足的表现，见于虚证或轻病。</br>\\\\n\\\",\\\"name\\\":\\\"少神\\\"}\",\"faceEyesName\":null,\"faceEyesDesc\":null,\"faceEyesUrl\":null,\"faceEyesIsRed\":null,\"faceEyesColor\":\"{\\\"code\\\":\\\"ml-55,mr-55\\\",\\\"description\\\":\\\"【左眼正常】左眼黑白分明，精彩内含。</br>\\\\n【病理意义】精充、气足、神旺的表现。</br>\\\\n,【右眼正常】右眼黑白分明，精彩内含。</br>\\\\n【病理意义】精充、气足、神旺的表现。</br>\\\\n\\\",\\\"name\\\":\\\"左眼正常,右眼正常\\\"}\",\"faceEyesColorName\":null,\"faceEyesColorDesc\":null,\"faceEyesColorLeftUrl\":null,\"faceEyesColorRightUrl\":null,\"faceEyesColorIsRed\":null,\"faceCheekbones\":\"{\\\"code\\\":\\\"m-60\\\",\\\"description\\\":\\\"【无】面部两颧未出现红色，颜色跟正常面色一致。</br>\\\\n【病理意义】胃气充足，气血调匀的表现。</br>\\\\n\\\",\\\"name\\\":\\\"无\\\"}\",\"faceCheekbonesName\":null,\"faceCheekbonesDesc\":null,\"faceCheekbonesUrl\":null,\"faceCheekbonesIsRed\":null,\"faceNasalFold\":\"{\\\"code\\\":\\\"m-70\\\",\\\"description\\\":\\\"【无】鼻根部未出现横纹沟。</br>\\\\n【病理意义】横纹沟代表鼻褶心征。代表动脉硬化，冠动脉供血不足。</br>\\\",\\\"name\\\":\\\"无\\\"}\",\"faceNasalFoldName\":null,\"faceNasalFoldDesc\":null,\"faceNasalFoldUrl\":null,\"faceNasalFoldIsRed\":null,\"faceGlabella\":\"{\\\"code\\\":\\\"m-80\\\",\\\"description\\\":\\\"【无】眉间或鼻柱之间不发青，无青色。</br>\\\\n【病理意义】眉间无青色。</br>\\\\n\\\",\\\"name\\\":\\\"无\\\"}\",\"faceGlabellaName\":null,\"faceGlabellaDesc\":null,\"faceGlabellaUrl\":null,\"faceGlabellaIsRed\":null,\"faceSkinLesions\":\"[{\\\"code\\\":\\\"m-90\\\",\\\"description\\\":\\\"【无】面部未出现色素痣、皮下颗粒物或疑似皮肤病等。</br>\\\\n【病理意义】面部正常。</br>\\\\n\\\",\\\"name\\\":\\\"无\\\",\\\"simpleDescription\\\":\\\"【无】面部未出现色素痣、皮下颗粒物或疑似皮肤病等。</br>\\\\n【病理意义】面部正常。</br>\\\\n\\\"}]\",\"faceSkinLesionsName\":null,\"faceSkinLesionsDesc\":null,\"faceSkinLesionsUrl\":null,\"faceSkinLesionsIsRed\":null,\"faceEarColor\":\"{\\\"code\\\":\\\"el-70,er-70\\\",\\\"description\\\":\\\"【左耳色正常】左耳颜色微黄而红润。</br>\\\\n【病理意义】左耳颜色正常。</br>\\\\n,【右耳色正常】右耳颜色微黄而红润。</br>\\\\n【病理意义】右耳颜色正常。</br>\\\\n\\\",\\\"name\\\":\\\"左耳色正常,右耳色正常\\\"}\",\"faceEarColorName\":null,\"faceEarColorDesc\":null,\"faceEarColorLeftUrl\":null,\"faceEarColorRightUrl\":null,\"faceEarColorIsRed\":null,\"faceUricularFold\":\"{\\\"code\\\":\\\"fl-70,fr-70\\\",\\\"description\\\":\\\"【左耳正常】左耳耳垂上没有褶皱。</br>\\\\n【病理意义】左耳无耳褶心征</br>\\\\n,【右耳正常】右耳耳垂上没有褶皱。</br>\\\\n【病理意义】右耳无耳褶心征</br>\\\\n\\\",\\\"name\\\":\\\"左耳正常,右耳正常\\\"}\",\"faceUricularFoldName\":null,\"faceUricularFoldDesc\":null,\"faceUricularFoldLeftUrl\":null,\"faceUricularFoldRightUrl\":null,\"faceUricularFoldIsRed\":null,\"faceCaluateImage\":\"{\\\"doubleEye\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/double_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"faceSplit\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"leftEars\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"leftEye\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rectLip\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/rect_lip/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rightEars\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rightEye\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\"}\",\"faceCaluateImage1\":null,\"faceProcessImage\":\"{\\\"faceAbnormal\\\":\\\"\\\",\\\"faceAreaLabel\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/face_area_label/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"faceBzxz\\\":\\\"\\\",\\\"leftFaceEzxz\\\":\\\"\\\",\\\"leftSidefaceAbnormal\\\":\\\"\\\",\\\"mainMosaic\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/main_mosaic/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rightFaceEzxz\\\":\\\"\\\",\\\"rightSidefaceAbnormal\\\":\\\"\\\"}\",\"faceProcessImage1\":null,\"diagnosticResults\":\" 积食（积食化热）\",\"mainPerformance\":\"[{\\\"items\\\":[],\\\"message\\\":\\\" 积食（积食化热）主要表现为不思乳食，口干，胃腹胀满，腹部灼热。或见平时好动，心烦易怒，夜卧不安，手脚心热，小便黄，大便臭秽或秘结。\\\"}]\",\"occurReason\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"1、母乳和食物聚集在体内时间长了，积食产生热性病变，从而损伤了津液。饮食不节制、损伤了脾胃，脾脏运化功能失常，体内的气运行不畅通，所以不喜欢喝母乳和吃东西，肚子胀；食物聚集在胃肠导致热性病变，消耗损伤了津液，所以口干，腹部像火烧着、烫着那样热，两手心与两脚心有发热感觉，小便比较黄，大便特别臭或大便干涩拉不下来；心神被扰动所以心里烦躁或烦闷、容易生气，晚上睡觉的时候出现不安稳和不踏实；舌头颜色红，舌苔颜色黄，舌苔质地比较腻。\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"2、食物堆积胃肠久了会化热，热会使血行加速，气血扰动则心神被扰，易出现嘴唇红、手足心热的症状。\\\"}]\",\"predisposition\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"无\\\"}]\",\"highRiskDisease\":null,\"eatingHabits\":\"[{\\\"content\\\":\\\"蒲公英与焦三仙和炒鸡内金一同熬水\\\",\\\"title\\\":\\\"宜食\\\"}]\",\"dietRehabilitation\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"蒲公英与焦三仙和炒鸡内金一同熬水\\\"}]\",\"dietaryRecuperate\":null,\"sportsHealthCare\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"蒲公英与焦三仙和炒鸡内金一同熬水\\\"}]\",\"drugHealthCare\":\"[{\\\"items\\\":[{\\\"desc\\\":\\\"【厂家】葵花药业<br> 【用法用量】口服。小儿一次0.5g，每日3次周岁内酌减。<br> 【产品成分】鸡内金、六神曲。辅料为：蔗糖、玉米淀粉。<br> 【功能主治】健脾开胃，消食化积。用于小儿因脾胃不和引起的食积胀满，饮食停滞，呕吐泄泻。<br>\\\",\\\"name\\\":\\\"小儿复方鸡内金散\\\",\\\"photo\\\":\\\"https://labelsys-images.oss-cn-hangzhou.aliyuncs.com/healthcare/葵花药业/小儿复方鸡内金散.jpg\\\"}],\\\"message\\\":\\\"\\\"},{\\\"items\\\":[{\\\"desc\\\":\\\"【厂家】广州王老吉药业股份有限公司<br> 【用法用量】开始冲服。一次3.5~7克，一日3次。<br> 【产品成分】薏苡仁、稻芽、山楂、淡竹叶、钩藤、蝉蜕、甘草。辅料为蔗糖粉。<br> 【功能主治】开胃消滞，清热定惊。用于小儿积滞化热，消化不良，不思饮食，烦躁易惊，夜寐不安，大便不畅，小便短赤。<br>\\\",\\\"name\\\":\\\"小儿七星茶颗粒\\\",\\\"photo\\\":\\\"https://labelsys-images.oss-cn-hangzhou.aliyuncs.com/healthcare/葵花药业/小儿七星茶颗粒.png\\\"}],\\\"message\\\":\\\"\\\"}]\",\"massageHealthCare\":\"[{\\\"bottom\\\":\\\"\\\",\\\"desc\\\":\\\"\\\",\\\"message\\\":\\\"1、逆运内八卦（调理因积食导致的咳嗽)\\\",\\\"photo\\\":\\\"\\\"},{\\\"bottom\\\":\\\"\\\",\\\"desc\\\":\\\"\\\",\\\"message\\\":\\\"2、以上取穴，加清天河水、清大肠。烦躁不安加清心平肝，揉曲池。\\\",\\\"photo\\\":\\\"\\\"}]\",\"tongueScore\":\"3.04\",\"tongueTips\":\"建议您一周后再次做舌诊检测。\",\"recuperates\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"1、避免食物种类过杂\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"每次饮食的种类不宜过多，四到五种即可，如若想吃多种类食物，可延长进食的时间间隔，注意营养均衡。\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"2、适量进食勿过多\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"小儿喂食应适量，吃饱就好，不是吃的越多越好，一次性吃太多的食物容易导致积食，请家长把握好分寸。\\\"}]\",\"illProbability\":\"{\\\"scope\\\":\\\"3.04\\\",\\\"tip\\\":\\\"建议您一周后再次做舌诊检测。\\\"}\",\"solarTerms\":\"寒露\",\"createTime\":\"2023-10-23 15:08:03\",\"updateTime\":\"2023-10-23 17:47:13\",\"tongueColorList\":null,\"tongueColorDescription\":\"<span style=\\\"color:#F25D4C\\\">淡红舌</span>、白苔、薄苔、<span style=\\\"color:#F25D4C\\\">齿痕</span>、舌下正常\",\"faceColorList\":[{\"name\":\"面色\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">面色红黄隐隐，明润含蓄</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"主色\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">正常色</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"光泽\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">少量</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"眼神\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">少神</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"目色\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">左眼正常</span>;<span style=\\\"color:#F25D4C\\\">右眼正常</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"两颧红\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">无</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"眉间/鼻柱青色\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">无</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"耳色\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">左耳色正常</span>;<span style=\\\"color:#F25D4C\\\">右耳色正常</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"耳褶\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">左耳正常</span>;<span style=\\\"color:#F25D4C\\\">右耳正常</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"鼻褶\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"无\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"唇色\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"正常\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"黑眼圈\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">左眼正常</span>;<span style=\\\"color:#F25D4C\\\">右眼正常</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"面部皮损\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">无</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null}],\"userShopDetailVO\":{\"id\":null,\"uid\":2688338885,\"isContract\":null,\"contractPhoto1\":null,\"contractPhoto2\":null,\"contractPhoto3\":null,\"contractPhoto4\":null,\"shopName\":\"测试600\",\"jobNumber\":null,\"username\":null,\"mobile\":\"18291891994\",\"mobileList\":null,\"uuid\":null,\"checkMobile\":null,\"managerMobile\":null,\"managerPassTime\":null,\"passTime\":null,\"shopType\":null,\"province\":\"上海市\",\"city\":\"上海市\",\"district\":\"黄浦区\",\"status\":null,\"street\":\"半淞园路街道\",\"addressDetail\":\"盛夏路560号\",\"shopAddressDetail\":\"上海市上海市黄浦区半淞园路街道盛夏路560号\",\"catena\":null,\"catenaNumber\":null,\"idFacePhoto\":null,\"idBackPhoto\":null,\"licensePhoto\":null,\"shopPhoto\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/de41e8271faa418fb051ef83cd568817-tmp_5578e4961f07e5048567a3688d7843c8e0575d8b73a2033f.jpg\",\"goodsPhoto\":null,\"checkUid\":null,\"checkRealName\":\"武帅帅\",\"belongArea\":null,\"checkAreaLayer\":null,\"managerRealName\":\"张莹\",\"managerAreaLayer\":null,\"managerUid\":null,\"managerOrCheckAreaLayer\":null,\"managerOrCheckName\":null,\"hisManagerAreaLayer\":null,\"hisManagerRealName\":null,\"hisManagerUid\":null,\"cityName\":null,\"cityUpdateTime\":null,\"cityStatus\":null,\"applyStatus\":null,\"areaName\":null,\"areaUpdateTime\":null,\"areaStatus\":null,\"headName\":null,\"headUpdateTime\":null,\"headStatus\":null,\"loginTime\":null,\"userStatus\":2,\"createTime\":null,\"updateTime\":null,\"isSameCardCorporate\":null,\"officeCode\":null,\"userAddressVOList\":null,\"shopId\":null,\"homePageElementList\":null,\"isDefaultSort\":null,\"fleeRecord\":null,\"isFleeRecord\":null,\"area\":\"上海省公司\",\"officeName\":\"上海办\",\"bname\":null,\"userDingQueryVO\":null,\"userIdList\":null,\"riskLevel\":null,\"remark\":null,\"riskRemark\":null,\"freezeStatus\":0,\"riskUserDto\":null,\"shopBname\":null},\"tongueColorAttrList\":null,\"tongueColorInfoList\":null,\"tongueMossAttrList\":null,\"tongueMossColorInfoList\":null,\"tongueNatureAttrList\":null,\"tongueShapAttrList\":null,\"tongueBodyAttrList\":null,\"tongueVeinAttrList\":null,\"aiFaceItemVOList\":null,\"aiFaceVOList\":null,\"aiTongueItemVOList\":null,\"aiTongueVOList\":null,\"aiReportItemVOList\":null}";
	public static String str2 = "{\"pdfUrl\":null,\"openid\":null,\"id\":86,\"name\":\"测试2\",\"genderDesc\":null,\"gender\":1,\"birthday\":\"2010-07-21\",\"age\":13.3,\"reportId\":\"1166030276602761216\",\"tongueAttrs\":\"[{\\\"attrName\\\":\\\"舌色程度\\\",\\\"attrValue\\\":\\\"舌边淡\\\"},{\\\"attrName\\\":\\\"舌根苔色\\\",\\\"attrValue\\\":\\\"舌根白苔\\\"},{\\\"attrName\\\":\\\"瘀点数量\\\",\\\"attrValue\\\":\\\"0\\\"},{\\\"attrName\\\":\\\"瘀点程度\\\",\\\"attrValue\\\":\\\"全舌无瘀点\\\"},{\\\"attrName\\\":\\\"齿痕\\\",\\\"attrValue\\\":\\\"轻度齿痕\\\"}]\",\"tongueBatchNo\":\"1166030277122854912\",\"tongueStep\":3,\"tongueStepStatus\":1,\"detectionTypeDesc\":\"脾胃病\",\"tongueProcessExtraImage\":\"{}\",\"tongueProcessExtraImage1\":null,\"constitutionNames\":\"通用结论\",\"faceBatchNo\":\"1166030285750538240\",\"templateId\":1705,\"checkIndex\":null,\"checkId\":14,\"userInfoId\":8,\"step\":null,\"stepStatus\":null,\"faceStep\":2,\"faceStepStatus\":1,\"tonguePreFailMsg\":\"\",\"facePreFailMsg\":\"\",\"detectionType\":\"C08.D00\",\"detectionTypeName\":null,\"questions\":\"[{\\\"group\\\":\\\"\\\",\\\"types\\\":[{\\\"code\\\":\\\"IS0105\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"形体肌肤\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"唇红\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"霰粒肿\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"面色少华\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"形体消瘦\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"眼皮浮肿\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"面色萎黄\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"皮肤粗糙\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"嘴角溃烂\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"形体肥胖\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"鼻梁有青筋\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"眼睑发紫发红\\\"}],\\\"sort\\\":1},{\\\"code\\\":\\\"IS0106\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"头身不适\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"呃逆\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"呕吐\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腹胀\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腹痛\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"黄痰\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"肚子硬\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"脘痞胀\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"易感冒\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腿抽筋\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"倦怠乏力\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"面色晦暗\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"牙龈出血\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腹痛欲泻\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"咽喉红肿\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"头发易打结\\\"}],\\\"sort\\\":2},{\\\"code\\\":\\\"IS0107\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"寒热\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"盗汗\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"手足心热\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"身体发热\\\"}],\\\"sort\\\":3},{\\\"code\\\":\\\"IS0108\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"饮食\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"口臭\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"口渴\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"贪吃\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"食欲不振\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"喜欢吃凉东西\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"消化系统紊乱\\\"}],\\\"sort\\\":4},{\\\"code\\\":\\\"IS0109\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"二便\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"排便不爽\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"大便干结\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"小便短黄\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"便秘或腹泻\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"大便有臭鸡蛋味\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"大便夹杂不消化食物\\\"}],\\\"sort\\\":5},{\\\"code\\\":\\\"IS0110\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"情志睡眠\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"嗳气\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"烦躁\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"失眠\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"磨牙\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"善悲易哭\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"急躁易怒\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"睡觉露睛\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"夜眠不安\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"燥扰不宁\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"睡觉流口水\\\"}],\\\"sort\\\":6}]}]\",\"answers\":\"[{\\\"answerCode\\\":\\\"腹胀\\\",\\\"code\\\":\\\"IS0106\\\",\\\"questionIndex\\\":2},{\\\"answerCode\\\":\\\"唇红\\\",\\\"code\\\":\\\"IS0105\\\",\\\"questionIndex\\\":1},{\\\"answerCode\\\":\\\"手足心热\\\",\\\"code\\\":\\\"IS0107\\\",\\\"questionIndex\\\":3},{\\\"answerCode\\\":\\\"食欲不振\\\",\\\"code\\\":\\\"IS0108\\\",\\\"questionIndex\\\":4},{\\\"answerCode\\\":\\\"便秘或腹泻\\\",\\\"code\\\":\\\"IS0109\\\",\\\"questionIndex\\\":5},{\\\"answerCode\\\":\\\"失眠\\\",\\\"code\\\":\\\"IS0110\\\",\\\"questionIndex\\\":6}]\",\"reportLockStatus\":0,\"tongueSurfaceImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/f89cc8108b47471994f37e834eab1fcd-tmp_be0673ac3c80cf3597be30659f7b70d00b73a0489851eab3.jpg\",\"tongueBackImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/9a27ae484edf4abab461187a08bd3f27-tmp_1ee8eebbe1ec0a7394569b85a03d35c080e1950f0556bca8.jpg\",\"tongueSplitSurfaceImg\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"tongueSplitSurfaceImg1\":\"\",\"tongueSplitBackImg\":\"https://td-platform-split-back.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"tongueSplitBackImg1\":\"\",\"tongueProcessImages\":\"{\\\"ossYudianImgUrl\\\":\\\"\\\",\\\"ossSheseImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/shese/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossShexingImgUrl\\\":\\\"\\\",\\\"ossToothtraceImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/toothtrace/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossBoluoImgUrl\\\":\\\"\\\",\\\"ossLiewenImgUrl\\\":\\\"\\\",\\\"ossTaiseImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/taise/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossYubanImgUrl\\\":\\\"\\\",\\\"ossLuomaiLabelImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/luomai_label/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossPrickImgUrl\\\":\\\"\\\"}\",\"tongueProcessImages1\":null,\"tongueColor\":\"淡红舌\",\"tongueColorDesc\":\"【淡红舌】正常人舌色，舌质呈现润泽红活。</br>【病理意义】正常舌色，或者处于病情轻浅阶段。</br>\",\"tongueMossColor\":\"白苔\",\"tongueMossColorDesc\":\"【白苔】舌苔呈现白色。</br>【病理意义】正常苔色。</br>\",\"tongueMossNature\":\"薄苔\",\"tongueMossNatureDesc\":\"【薄苔】透过舌苔能隐隐地见到舌体。 </br>【病理意义】脾胃状态良好。</br>\",\"tongueShap\":\"齿痕\",\"tongueShapDesc\":\"【齿痕】舌体边缘有牙齿压迫的痕迹。</br>【病理意义】脾虚、气虚或体内有湿。</br>\",\"tongueBodyfluid\":\"\",\"tongueBodyfluidDesc\":\"【润】舌苔干湿适中，不滑不燥为润苔。 </br>【病理意义】舌苔水分正常。</br>\",\"tongueVein\":\"舌下正常\",\"tongueVeinDesc\":\"【舌下正常】正常情况下，其管径不超过2.7mm,长度不超过舌尖至舌下肉阜连线的3/5,颜色暗红，无分支和紫点。脉络无怒张、紧束、弯曲、增生，排列有序，绝大多数为单支，极少有双支出现。</br>【临床表现】舌下络脉正常。</br>\",\"faceFrontImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/f1a3ae630da945b5a78dc013e9d915fb-tmp_445d54aa67689e29f9a54c757abc1c69bad16f834eac7d84.jpg\",\"faceLeftImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/e1d71c630f174ab69236428e721a6aca-tmp_c0a0bf082e9e0f3c2676ecae84df531cb7426fb21848d3e1.jpg\",\"faceRightImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/d867390967b64836bd7044d2ecace326-tmp_6ff305bf982195f64fe58701a28c19a0c6a75f70358452e3.jpg\",\"faceMianse\":\"{\\\"code\\\":\\\"m-10\\\",\\\"description\\\":\\\"【正常面色】面色红黄隐隐，明润含蓄。</br>\\\\n【病理意义】神气、胃气的正常色泽表现。</br>\\\",\\\"name\\\":\\\"面色红黄隐隐，明润含蓄\\\"}\",\"faceMianseName\":null,\"faceMianseDesc\":null,\"faceMianseUrl\":null,\"faceMianseIsRed\":null,\"faceMainColor\":\"{\\\"code\\\":\\\"m-14\\\",\\\"description\\\":\\\"【正常色】面色红黄隐隐，明润含蓄。</br>\\\\n【病理意义】神气、胃气的正常色泽表现。</br>\\\\n\\\",\\\"name\\\":\\\"正常色\\\"}\",\"faceMainColorName\":null,\"faceMainColorDesc\":null,\"faceMainColorUrl\":null,\"faceMainColorIsRed\":null,\"faceGloss\":\"{\\\"code\\\":\\\"m-21\\\",\\\"description\\\":\\\"【少量】面色稍许晦暗、少泽。</br>\\\\n【病理意义】为脏腑精气稍衰，介于病轻和病重之间。</br>\\\\n\\\",\\\"name\\\":\\\"少量\\\"}\",\"faceGlossName\":null,\"faceGlossDesc\":null,\"faceGlossUrl\":null,\"faceGlossIsRed\":null,\"faceDarkCircles\":\"{\\\"code\\\":\\\"ml-30,mr-30\\\",\\\"description\\\":\\\"【左眼正常】左眼眼圈不发黑，眼眶颜色正常。</br>\\\\n【病理意义】左眼无黑眼圈。</br>\\\\n,【右眼正常】右眼眼圈不发黑，眼眶颜色正常。</br>\\\\n【病理意义】右眼无黑眼圈。</br>\\\\n\\\",\\\"name\\\":\\\"左眼正常,右眼正常\\\"}\",\"faceDarkCirclesName\":null,\"faceDarkCirclesDesc\":null,\"faceDarkCirclesLeftUrl\":null,\"faceDarkCirclesRightUrl\":null,\"faceDarkCirclesIsRed\":null,\"faceLipColor\":\"{\\\"code\\\":\\\"m-40\\\",\\\"description\\\":\\\"【正常】唇色是明亮、润泽、含蓄的红色。</br>\\\\n【病理意义】胃气充足，气血调匀的表现。</br>\\\",\\\"name\\\":\\\"正常\\\"}\",\"faceLipColorName\":null,\"faceLipColorDesc\":null,\"faceLipColorUrl\":null,\"faceLipColorIsRed\":null,\"faceEyes\":\"{\\\"code\\\":\\\"m-51\\\",\\\"description\\\":\\\"【少神】两目乏神，动作迟缓，眼神晦暗。</br>\\\\n【病理意义】是正气不足的表现，见于虚证或轻病。</br>\\\\n\\\",\\\"name\\\":\\\"少神\\\"}\",\"faceEyesName\":null,\"faceEyesDesc\":null,\"faceEyesUrl\":null,\"faceEyesIsRed\":null,\"faceEyesColor\":\"{\\\"code\\\":\\\"ml-55,mr-55\\\",\\\"description\\\":\\\"【左眼正常】左眼黑白分明，精彩内含。</br>\\\\n【病理意义】精充、气足、神旺的表现。</br>\\\\n,【右眼正常】右眼黑白分明，精彩内含。</br>\\\\n【病理意义】精充、气足、神旺的表现。</br>\\\\n\\\",\\\"name\\\":\\\"左眼正常,右眼正常\\\"}\",\"faceEyesColorName\":null,\"faceEyesColorDesc\":null,\"faceEyesColorLeftUrl\":null,\"faceEyesColorRightUrl\":null,\"faceEyesColorIsRed\":null,\"faceCheekbones\":\"{\\\"code\\\":\\\"m-60\\\",\\\"description\\\":\\\"【无】面部两颧未出现红色，颜色跟正常面色一致。</br>\\\\n【病理意义】胃气充足，气血调匀的表现。</br>\\\\n\\\",\\\"name\\\":\\\"无\\\"}\",\"faceCheekbonesName\":null,\"faceCheekbonesDesc\":null,\"faceCheekbonesUrl\":null,\"faceCheekbonesIsRed\":null,\"faceNasalFold\":\"{\\\"code\\\":\\\"m-70\\\",\\\"description\\\":\\\"【无】鼻根部未出现横纹沟。</br>\\\\n【病理意义】横纹沟代表鼻褶心征。代表动脉硬化，冠动脉供血不足。</br>\\\",\\\"name\\\":\\\"无\\\"}\",\"faceNasalFoldName\":null,\"faceNasalFoldDesc\":null,\"faceNasalFoldUrl\":null,\"faceNasalFoldIsRed\":null,\"faceGlabella\":\"{\\\"code\\\":\\\"m-80\\\",\\\"description\\\":\\\"【无】眉间或鼻柱之间不发青，无青色。</br>\\\\n【病理意义】眉间无青色。</br>\\\\n\\\",\\\"name\\\":\\\"无\\\"}\",\"faceGlabellaName\":null,\"faceGlabellaDesc\":null,\"faceGlabellaUrl\":null,\"faceGlabellaIsRed\":null,\"faceSkinLesions\":\"[{\\\"code\\\":\\\"m-90\\\",\\\"description\\\":\\\"【无】面部未出现色素痣、皮下颗粒物或疑似皮肤病等。</br>\\\\n【病理意义】面部正常。</br>\\\\n\\\",\\\"name\\\":\\\"无\\\",\\\"simpleDescription\\\":\\\"【无】面部未出现色素痣、皮下颗粒物或疑似皮肤病等。</br>\\\\n【病理意义】面部正常。</br>\\\\n\\\"}]\",\"faceSkinLesionsName\":null,\"faceSkinLesionsDesc\":null,\"faceSkinLesionsUrl\":null,\"faceSkinLesionsIsRed\":null,\"faceEarColor\":\"{\\\"code\\\":\\\"el-70,er-70\\\",\\\"description\\\":\\\"【左耳色正常】左耳颜色微黄而红润。</br>\\\\n【病理意义】左耳颜色正常。</br>\\\\n,【右耳色正常】右耳颜色微黄而红润。</br>\\\\n【病理意义】右耳颜色正常。</br>\\\\n\\\",\\\"name\\\":\\\"左耳色正常,右耳色正常\\\"}\",\"faceEarColorName\":null,\"faceEarColorDesc\":null,\"faceEarColorLeftUrl\":null,\"faceEarColorRightUrl\":null,\"faceEarColorIsRed\":null,\"faceUricularFold\":\"{\\\"code\\\":\\\"fl-70,fr-70\\\",\\\"description\\\":\\\"【左耳正常】左耳耳垂上没有褶皱。</br>\\\\n【病理意义】左耳无耳褶心征</br>\\\\n,【右耳正常】右耳耳垂上没有褶皱。</br>\\\\n【病理意义】右耳无耳褶心征</br>\\\\n\\\",\\\"name\\\":\\\"左耳正常,右耳正常\\\"}\",\"faceUricularFoldName\":null,\"faceUricularFoldDesc\":null,\"faceUricularFoldLeftUrl\":null,\"faceUricularFoldRightUrl\":null,\"faceUricularFoldIsRed\":null,\"faceCaluateImage\":\"{\\\"doubleEye\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/double_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"faceSplit\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"leftEars\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"leftEye\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rectLip\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/rect_lip/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rightEars\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rightEye\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\"}\",\"faceCaluateImage1\":null,\"faceProcessImage\":\"{\\\"faceAbnormal\\\":\\\"\\\",\\\"faceAreaLabel\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/face_area_label/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"faceBzxz\\\":\\\"\\\",\\\"leftFaceEzxz\\\":\\\"\\\",\\\"leftSidefaceAbnormal\\\":\\\"\\\",\\\"mainMosaic\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/main_mosaic/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rightFaceEzxz\\\":\\\"\\\",\\\"rightSidefaceAbnormal\\\":\\\"\\\"}\",\"faceProcessImage1\":null,\"diagnosticResults\":\" 积食（积食化热）\",\"mainPerformance\":\"[{\\\"items\\\":[],\\\"message\\\":\\\" 积食（积食化热）主要表现为不思乳食，口干，胃腹胀满，腹部灼热。或见平时好动，心烦易怒，夜卧不安，手脚心热，小便黄，大便臭秽或秘结。\\\"}]\",\"occurReason\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"1、母乳和食物聚集在体内时间长了，积食产生热性病变，从而损伤了津液。饮食不节制、损伤了脾胃，脾脏运化功能失常，体内的气运行不畅通，所以不喜欢喝母乳和吃东西，肚子胀；食物聚集在胃肠导致热性病变，消耗损伤了津液，所以口干，腹部像火烧着、烫着那样热，两手心与两脚心有发热感觉，小便比较黄，大便特别臭或大便干涩拉不下来；心神被扰动所以心里烦躁或烦闷、容易生气，晚上睡觉的时候出现不安稳和不踏实；舌头颜色红，舌苔颜色黄，舌苔质地比较腻。\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"2、食物堆积胃肠久了会化热，热会使血行加速，气血扰动则心神被扰，易出现嘴唇红、手足心热的症状。\\\"}]\",\"predisposition\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"无\\\"}]\",\"highRiskDisease\":null,\"eatingHabits\":\"[{\\\"content\\\":\\\"蒲公英与焦三仙和炒鸡内金一同熬水\\\",\\\"title\\\":\\\"宜食\\\"}]\",\"dietRehabilitation\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"蒲公英与焦三仙和炒鸡内金一同熬水\\\"}]\",\"dietaryRecuperate\":null,\"sportsHealthCare\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"蒲公英与焦三仙和炒鸡内金一同熬水\\\"}]\",\"drugHealthCare\":\"[{\\\"items\\\":[{\\\"desc\\\":\\\"【厂家】葵花药业<br> 【用法用量】口服。小儿一次0.5g，每日3次周岁内酌减。<br> 【产品成分】鸡内金、六神曲。辅料为：蔗糖、玉米淀粉。<br> 【功能主治】健脾开胃，消食化积。用于小儿因脾胃不和引起的食积胀满，饮食停滞，呕吐泄泻。<br>\\\",\\\"name\\\":\\\"小儿复方鸡内金散\\\",\\\"photo\\\":\\\"https://labelsys-images.oss-cn-hangzhou.aliyuncs.com/healthcare/葵花药业/小儿复方鸡内金散.jpg\\\"}],\\\"message\\\":\\\"\\\"},{\\\"items\\\":[{\\\"desc\\\":\\\"【厂家】广州王老吉药业股份有限公司<br> 【用法用量】开始冲服。一次3.5~7克，一日3次。<br> 【产品成分】薏苡仁、稻芽、山楂、淡竹叶、钩藤、蝉蜕、甘草。辅料为蔗糖粉。<br> 【功能主治】开胃消滞，清热定惊。用于小儿积滞化热，消化不良，不思饮食，烦躁易惊，夜寐不安，大便不畅，小便短赤。<br>\\\",\\\"name\\\":\\\"小儿七星茶颗粒\\\",\\\"photo\\\":\\\"https://labelsys-images.oss-cn-hangzhou.aliyuncs.com/healthcare/葵花药业/小儿七星茶颗粒.png\\\"}],\\\"message\\\":\\\"\\\"}]\",\"massageHealthCare\":\"[{\\\"bottom\\\":\\\"\\\",\\\"desc\\\":\\\"\\\",\\\"message\\\":\\\"1、逆运内八卦（调理因积食导致的咳嗽)\\\",\\\"photo\\\":\\\"\\\"},{\\\"bottom\\\":\\\"\\\",\\\"desc\\\":\\\"\\\",\\\"message\\\":\\\"2、以上取穴，加清天河水、清大肠。烦躁不安加清心平肝，揉曲池。\\\",\\\"photo\\\":\\\"\\\"}]\",\"tongueScore\":\"3.04\",\"tongueTips\":\"建议您一周后再次做舌诊检测。\",\"recuperates\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"1、避免食物种类过杂\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"每次饮食的种类不宜过多，四到五种即可，如若想吃多种类食物，可延长进食的时间间隔，注意营养均衡。\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"2、适量进食勿过多\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"小儿喂食应适量，吃饱就好，不是吃的越多越好，一次性吃太多的食物容易导致积食，请家长把握好分寸。\\\"}]\",\"illProbability\":\"{\\\"scope\\\":\\\"3.04\\\",\\\"tip\\\":\\\"建议您一周后再次做舌诊检测。\\\"}\",\"solarTerms\":\"寒露\",\"createTime\":\"2023-10-23 15:08:03\",\"updateTime\":\"2023-10-23 17:47:13\",\"tongueColorList\":null,\"tongueColorDescription\":null,\"faceColorList\":null,\"userShopDetailVO\":null,\"tongueColorAttrList\":null,\"tongueColorInfoList\":null,\"tongueMossAttrList\":null,\"tongueMossColorInfoList\":null,\"tongueNatureAttrList\":null,\"tongueShapAttrList\":null,\"tongueBodyAttrList\":null,\"tongueVeinAttrList\":null,\"aiFaceItemVOList\":null,\"aiFaceVOList\":null,\"aiTongueItemVOList\":[{\"name\":null,\"key\":\"舌色\",\"field\":\"淡红舌\",\"value\":\"淡红舌\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"苔色\",\"field\":\"白苔\",\"value\":\"白苔\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"点刺\",\"field\":\"未见点刺\",\"value\":\"未见点刺\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"瘀点\",\"field\":\"未见瘀点\",\"value\":\"未见瘀点\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"瘀斑\",\"field\":\"未见瘀斑\",\"value\":\"未见瘀斑\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"裂纹\",\"field\":\"未见裂纹\",\"value\":\"未见裂纹\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"齿痕\",\"field\":\"<span style=\\\"color:#F25D4C\\\">齿痕</span>\",\"value\":\"未见齿痕\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"舌形\",\"field\":\"正常\",\"value\":\"正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"厚薄\",\"field\":\"薄苔\",\"value\":\"薄苔\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"腐腻\",\"field\":\"正常\",\"value\":\"正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"苔质\",\"field\":\"正常\",\"value\":\"正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"津液\",\"field\":\"润\",\"value\":\"润\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"异常舌形\",\"field\":\"否\",\"value\":\"否\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"肝郁线\",\"field\":\"未见肝郁线\",\"value\":\"未见肝郁线\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"舌下络脉异常程度\",\"field\":\"舌下正常\",\"value\":\"舌下正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null}],\"aiTongueVOList\":[{\"name\":null,\"key\":\"舌色\",\"field\":\"淡红舌\",\"value\":\"【淡红舌】正常人舌色，舌质呈现润泽红活。</br>【病理意义】正常舌色，或者处于病情轻浅阶段。</br>\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":[],\"tongueColorAttrList\":[{\"name\":null,\"key\":\"<span style=\\\"color:#A46C7A\\\">淡白舌</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#B4586B\\\">舌质淡</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#B89EA4\\\">淡红舌</span>\",\"field\":null,\"value\":\"正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#B61617\\\">红舌</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#802924\\\">绛舌</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#7E7198\\\">青紫舌</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null}],\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"苔色\",\"field\":\"白苔\",\"value\":\"【白苔】舌苔呈现白色。</br>【病理意义】正常苔色。</br>\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":[\"舌根白苔\"],\"tongueColorAttrList\":null,\"tongueMossAttrList\":[{\"name\":null,\"key\":\"<span style=\\\"color:#EED6DC\\\">白苔</span>\",\"field\":null,\"value\":\"正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#BEAD7D\\\">淡黄苔</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#B29954\\\">黄苔</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#706136\\\">焦黄苔</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#51424B\\\">灰黑苔</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#27181C\\\">焦黑苔</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null}]},{\"name\":null,\"key\":\"点刺\",\"field\":\"未见点刺\",\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"瘀点\",\"field\":\"未见瘀点\",\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"瘀斑\",\"field\":\"未见瘀斑\",\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"裂纹\",\"field\":\"未见裂纹\",\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"齿痕\",\"field\":\"<span style=\\\"color:#F25D4C\\\">齿痕</span>\",\"value\":\"【齿痕】舌体边缘有牙齿压迫的痕迹。</br>【病理意义】脾虚、气虚或体内有湿。</br>\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":true,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":[\"轻度齿痕\"],\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"舌形\",\"field\":\"正常\",\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"厚薄\",\"field\":\"薄苔\",\"value\":\"【薄苔】透过舌苔能隐隐地见到舌体。 </br>【病理意义】脾胃状态良好。</br>\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":[],\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"腐腻\",\"field\":\"正常\",\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"苔质\",\"field\":\"正常\",\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"津液\",\"field\":\"润\",\"value\":\"【润】舌苔干湿适中，不滑不燥为润苔。 【病理意义】舌苔水分正常。\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"异常舌形\",\"field\":\"否\",\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"舌下络脉异常程度\",\"field\":\"舌下正常\",\"value\":\"【舌下正常】正常情况下，其管径不超过2.7mm,长度不超过舌尖至舌下肉阜连线的3/5,颜色暗红，无分支和紫点。脉络无怒张、紧束、弯曲、增生，排列有序，绝大多数为单支，极少有双支出现。</br>【临床表现】舌下络脉正常。</br>\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/luomai_label/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":[],\"tongueColorAttrList\":null,\"tongueMossAttrList\":null}],\"aiReportItemVOList\":null}";
	public static String str3 = "{\"pdfUrl\":null,\"openid\":null,\"id\":86,\"name\":\"测试2\",\"genderDesc\":null,\"gender\":1,\"birthday\":\"2010-07-21\",\"age\":13.3,\"reportId\":\"1166030276602761216\",\"tongueAttrs\":\"[{\\\"attrName\\\":\\\"舌色程度\\\",\\\"attrValue\\\":\\\"舌边淡\\\"},{\\\"attrName\\\":\\\"舌根苔色\\\",\\\"attrValue\\\":\\\"舌根白苔\\\"},{\\\"attrName\\\":\\\"瘀点数量\\\",\\\"attrValue\\\":\\\"0\\\"},{\\\"attrName\\\":\\\"瘀点程度\\\",\\\"attrValue\\\":\\\"全舌无瘀点\\\"},{\\\"attrName\\\":\\\"齿痕\\\",\\\"attrValue\\\":\\\"轻度齿痕\\\"}]\",\"tongueBatchNo\":\"1166030277122854912\",\"tongueStep\":3,\"tongueStepStatus\":1,\"detectionTypeDesc\":\"脾胃病\",\"tongueProcessExtraImage\":\"{}\",\"tongueProcessExtraImage1\":null,\"constitutionNames\":\"通用结论\",\"faceBatchNo\":\"1166030285750538240\",\"templateId\":1705,\"checkIndex\":null,\"checkId\":14,\"userInfoId\":8,\"step\":null,\"stepStatus\":null,\"faceStep\":2,\"faceStepStatus\":1,\"tonguePreFailMsg\":\"\",\"facePreFailMsg\":\"\",\"detectionType\":\"C08.D00\",\"detectionTypeName\":null,\"questions\":\"[{\\\"group\\\":\\\"\\\",\\\"types\\\":[{\\\"code\\\":\\\"IS0105\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"形体肌肤\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"唇红\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"霰粒肿\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"面色少华\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"形体消瘦\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"眼皮浮肿\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"面色萎黄\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"皮肤粗糙\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"嘴角溃烂\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"形体肥胖\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"鼻梁有青筋\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"眼睑发紫发红\\\"}],\\\"sort\\\":1},{\\\"code\\\":\\\"IS0106\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"头身不适\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"呃逆\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"呕吐\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腹胀\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腹痛\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"黄痰\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"肚子硬\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"脘痞胀\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"易感冒\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腿抽筋\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"倦怠乏力\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"面色晦暗\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"牙龈出血\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腹痛欲泻\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"咽喉红肿\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"头发易打结\\\"}],\\\"sort\\\":2},{\\\"code\\\":\\\"IS0107\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"寒热\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"盗汗\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"手足心热\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"身体发热\\\"}],\\\"sort\\\":3},{\\\"code\\\":\\\"IS0108\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"饮食\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"口臭\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"口渴\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"贪吃\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"食欲不振\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"喜欢吃凉东西\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"消化系统紊乱\\\"}],\\\"sort\\\":4},{\\\"code\\\":\\\"IS0109\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"二便\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"排便不爽\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"大便干结\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"小便短黄\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"便秘或腹泻\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"大便有臭鸡蛋味\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"大便夹杂不消化食物\\\"}],\\\"sort\\\":5},{\\\"code\\\":\\\"IS0110\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"情志睡眠\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"嗳气\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"烦躁\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"失眠\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"磨牙\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"善悲易哭\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"急躁易怒\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"睡觉露睛\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"夜眠不安\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"燥扰不宁\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"睡觉流口水\\\"}],\\\"sort\\\":6}]}]\",\"answers\":\"[{\\\"answerCode\\\":\\\"腹胀\\\",\\\"code\\\":\\\"IS0106\\\",\\\"questionIndex\\\":2},{\\\"answerCode\\\":\\\"唇红\\\",\\\"code\\\":\\\"IS0105\\\",\\\"questionIndex\\\":1},{\\\"answerCode\\\":\\\"手足心热\\\",\\\"code\\\":\\\"IS0107\\\",\\\"questionIndex\\\":3},{\\\"answerCode\\\":\\\"食欲不振\\\",\\\"code\\\":\\\"IS0108\\\",\\\"questionIndex\\\":4},{\\\"answerCode\\\":\\\"便秘或腹泻\\\",\\\"code\\\":\\\"IS0109\\\",\\\"questionIndex\\\":5},{\\\"answerCode\\\":\\\"失眠\\\",\\\"code\\\":\\\"IS0110\\\",\\\"questionIndex\\\":6}]\",\"reportLockStatus\":0,\"tongueSurfaceImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/f89cc8108b47471994f37e834eab1fcd-tmp_be0673ac3c80cf3597be30659f7b70d00b73a0489851eab3.jpg\",\"tongueBackImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/9a27ae484edf4abab461187a08bd3f27-tmp_1ee8eebbe1ec0a7394569b85a03d35c080e1950f0556bca8.jpg\",\"tongueSplitSurfaceImg\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"tongueSplitSurfaceImg1\":\"\",\"tongueSplitBackImg\":\"https://td-platform-split-back.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"tongueSplitBackImg1\":\"\",\"tongueProcessImages\":\"{\\\"ossYudianImgUrl\\\":\\\"\\\",\\\"ossSheseImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/shese/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossShexingImgUrl\\\":\\\"\\\",\\\"ossToothtraceImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/toothtrace/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossBoluoImgUrl\\\":\\\"\\\",\\\"ossLiewenImgUrl\\\":\\\"\\\",\\\"ossTaiseImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/taise/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossYubanImgUrl\\\":\\\"\\\",\\\"ossLuomaiLabelImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/luomai_label/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossPrickImgUrl\\\":\\\"\\\"}\",\"tongueProcessImages1\":null,\"tongueColor\":\"淡红舌\",\"tongueColorDesc\":\"【淡红舌】正常人舌色，舌质呈现润泽红活。</br>【病理意义】正常舌色，或者处于病情轻浅阶段。</br>\",\"tongueMossColor\":\"白苔\",\"tongueMossColorDesc\":\"【白苔】舌苔呈现白色。</br>【病理意义】正常苔色。</br>\",\"tongueMossNature\":\"薄苔\",\"tongueMossNatureDesc\":\"【薄苔】透过舌苔能隐隐地见到舌体。 </br>【病理意义】脾胃状态良好。</br>\",\"tongueShap\":\"齿痕\",\"tongueShapDesc\":\"【齿痕】舌体边缘有牙齿压迫的痕迹。</br>【病理意义】脾虚、气虚或体内有湿。</br>\",\"tongueBodyfluid\":\"\",\"tongueBodyfluidDesc\":\"【润】舌苔干湿适中，不滑不燥为润苔。 </br>【病理意义】舌苔水分正常。</br>\",\"tongueVein\":\"舌下正常\",\"tongueVeinDesc\":\"【舌下正常】正常情况下，其管径不超过2.7mm,长度不超过舌尖至舌下肉阜连线的3/5,颜色暗红，无分支和紫点。脉络无怒张、紧束、弯曲、增生，排列有序，绝大多数为单支，极少有双支出现。</br>【临床表现】舌下络脉正常。</br>\",\"faceFrontImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/f1a3ae630da945b5a78dc013e9d915fb-tmp_445d54aa67689e29f9a54c757abc1c69bad16f834eac7d84.jpg\",\"faceLeftImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/e1d71c630f174ab69236428e721a6aca-tmp_c0a0bf082e9e0f3c2676ecae84df531cb7426fb21848d3e1.jpg\",\"faceRightImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/d867390967b64836bd7044d2ecace326-tmp_6ff305bf982195f64fe58701a28c19a0c6a75f70358452e3.jpg\",\"faceMianse\":\"{\\\"code\\\":\\\"m-10\\\",\\\"description\\\":\\\"【正常面色】面色红黄隐隐，明润含蓄。</br>\\\\n【病理意义】神气、胃气的正常色泽表现。</br>\\\",\\\"name\\\":\\\"面色红黄隐隐，明润含蓄\\\"}\",\"faceMianseName\":\"面色红黄隐隐，明润含蓄\",\"faceMianseDesc\":\"【正常面色】面色红黄隐隐，明润含蓄。</br>\\n【病理意义】神气、胃气的正常色泽表现。</br>\",\"faceMianseUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/face_area_label/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceMianseIsRed\":false,\"faceMainColor\":\"{\\\"code\\\":\\\"m-14\\\",\\\"description\\\":\\\"【正常色】面色红黄隐隐，明润含蓄。</br>\\\\n【病理意义】神气、胃气的正常色泽表现。</br>\\\\n\\\",\\\"name\\\":\\\"正常色\\\"}\",\"faceMainColorName\":\"正常色\",\"faceMainColorDesc\":\"【正常色】面色红黄隐隐，明润含蓄。</br>\\n【病理意义】神气、胃气的正常色泽表现。</br>\\n\",\"faceMainColorUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceMainColorIsRed\":false,\"faceGloss\":\"{\\\"code\\\":\\\"m-21\\\",\\\"description\\\":\\\"【少量】面色稍许晦暗、少泽。</br>\\\\n【病理意义】为脏腑精气稍衰，介于病轻和病重之间。</br>\\\\n\\\",\\\"name\\\":\\\"少量\\\"}\",\"faceGlossName\":\"少量\",\"faceGlossDesc\":\"【少量】面色稍许晦暗、少泽。</br>\\n【病理意义】为脏腑精气稍衰，介于病轻和病重之间。</br>\\n\",\"faceGlossUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceGlossIsRed\":true,\"faceDarkCircles\":\"{\\\"code\\\":\\\"ml-30,mr-30\\\",\\\"description\\\":\\\"【左眼正常】左眼眼圈不发黑，眼眶颜色正常。</br>\\\\n【病理意义】左眼无黑眼圈。</br>\\\\n,【右眼正常】右眼眼圈不发黑，眼眶颜色正常。</br>\\\\n【病理意义】右眼无黑眼圈。</br>\\\\n\\\",\\\"name\\\":\\\"左眼正常,右眼正常\\\"}\",\"faceDarkCirclesName\":\"左眼正常;右眼正常\",\"faceDarkCirclesDesc\":\"【左眼正常】左眼眼圈不发黑，眼眶颜色正常。</br>\\n【病理意义】左眼无黑眼圈。</br>\\n,【右眼正常】右眼眼圈不发黑，眼眶颜色正常。</br>\\n【病理意义】右眼无黑眼圈。</br>\\n\",\"faceDarkCirclesLeftUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceDarkCirclesRightUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceDarkCirclesIsRed\":false,\"faceLipColor\":\"{\\\"code\\\":\\\"m-40\\\",\\\"description\\\":\\\"【正常】唇色是明亮、润泽、含蓄的红色。</br>\\\\n【病理意义】胃气充足，气血调匀的表现。</br>\\\",\\\"name\\\":\\\"正常\\\"}\",\"faceLipColorName\":\"正常\",\"faceLipColorDesc\":\"【正常】唇色是明亮、润泽、含蓄的红色。</br>\\n【病理意义】胃气充足，气血调匀的表现。</br>\",\"faceLipColorUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/rect_lip/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceLipColorIsRed\":false,\"faceEyes\":\"{\\\"code\\\":\\\"m-51\\\",\\\"description\\\":\\\"【少神】两目乏神，动作迟缓，眼神晦暗。</br>\\\\n【病理意义】是正气不足的表现，见于虚证或轻病。</br>\\\\n\\\",\\\"name\\\":\\\"少神\\\"}\",\"faceEyesName\":\"少神\",\"faceEyesDesc\":\"【少神】两目乏神，动作迟缓，眼神晦暗。</br>\\n【病理意义】是正气不足的表现，见于虚证或轻病。</br>\\n\",\"faceEyesUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/double_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceEyesIsRed\":true,\"faceEyesColor\":\"{\\\"code\\\":\\\"ml-55,mr-55\\\",\\\"description\\\":\\\"【左眼正常】左眼黑白分明，精彩内含。</br>\\\\n【病理意义】精充、气足、神旺的表现。</br>\\\\n,【右眼正常】右眼黑白分明，精彩内含。</br>\\\\n【病理意义】精充、气足、神旺的表现。</br>\\\\n\\\",\\\"name\\\":\\\"左眼正常,右眼正常\\\"}\",\"faceEyesColorName\":\"左眼正常;右眼正常\",\"faceEyesColorDesc\":\"【左眼正常】左眼黑白分明，精彩内含。</br>\\n【病理意义】精充、气足、神旺的表现。</br>\\n,【右眼正常】右眼黑白分明，精彩内含。</br>\\n【病理意义】精充、气足、神旺的表现。</br>\\n\",\"faceEyesColorLeftUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceEyesColorRightUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceEyesColorIsRed\":false,\"faceCheekbones\":\"{\\\"code\\\":\\\"m-60\\\",\\\"description\\\":\\\"【无】面部两颧未出现红色，颜色跟正常面色一致。</br>\\\\n【病理意义】胃气充足，气血调匀的表现。</br>\\\\n\\\",\\\"name\\\":\\\"无\\\"}\",\"faceCheekbonesName\":\"无\",\"faceCheekbonesDesc\":\"【无】面部两颧未出现红色，颜色跟正常面色一致。</br>\\n【病理意义】胃气充足，气血调匀的表现。</br>\\n\",\"faceCheekbonesUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/face_area_label/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceCheekbonesIsRed\":false,\"faceNasalFold\":\"{\\\"code\\\":\\\"m-70\\\",\\\"description\\\":\\\"【无】鼻根部未出现横纹沟。</br>\\\\n【病理意义】横纹沟代表鼻褶心征。代表动脉硬化，冠动脉供血不足。</br>\\\",\\\"name\\\":\\\"无\\\"}\",\"faceNasalFoldName\":\"无\",\"faceNasalFoldDesc\":\"【无】鼻根部未出现横纹沟。</br>\\n【病理意义】横纹沟代表鼻褶心征。代表动脉硬化，冠动脉供血不足。</br>\",\"faceNasalFoldUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceNasalFoldIsRed\":false,\"faceGlabella\":\"{\\\"code\\\":\\\"m-80\\\",\\\"description\\\":\\\"【无】眉间或鼻柱之间不发青，无青色。</br>\\\\n【病理意义】眉间无青色。</br>\\\\n\\\",\\\"name\\\":\\\"无\\\"}\",\"faceGlabellaName\":\"无\",\"faceGlabellaDesc\":\"【无】眉间或鼻柱之间不发青，无青色。</br>\\n【病理意义】眉间无青色。</br>\\n\",\"faceGlabellaUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/double_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceGlabellaIsRed\":false,\"faceSkinLesions\":\"[{\\\"code\\\":\\\"m-90\\\",\\\"description\\\":\\\"【无】面部未出现色素痣、皮下颗粒物或疑似皮肤病等。</br>\\\\n【病理意义】面部正常。</br>\\\\n\\\",\\\"name\\\":\\\"无\\\",\\\"simpleDescription\\\":\\\"【无】面部未出现色素痣、皮下颗粒物或疑似皮肤病等。</br>\\\\n【病理意义】面部正常。</br>\\\\n\\\"}]\",\"faceSkinLesionsName\":\"无\",\"faceSkinLesionsDesc\":\"【无】面部未出现色素痣、皮下颗粒物或疑似皮肤病等。</br>\\n【病理意义】面部正常。</br>\\n\",\"faceSkinLesionsUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceSkinLesionsIsRed\":false,\"faceEarColor\":\"{\\\"code\\\":\\\"el-70,er-70\\\",\\\"description\\\":\\\"【左耳色正常】左耳颜色微黄而红润。</br>\\\\n【病理意义】左耳颜色正常。</br>\\\\n,【右耳色正常】右耳颜色微黄而红润。</br>\\\\n【病理意义】右耳颜色正常。</br>\\\\n\\\",\\\"name\\\":\\\"左耳色正常,右耳色正常\\\"}\",\"faceEarColorName\":\"左耳色正常;右耳色正常\",\"faceEarColorDesc\":\"【左耳色正常】左耳颜色微黄而红润。</br>\\n【病理意义】左耳颜色正常。</br>\\n,【右耳色正常】右耳颜色微黄而红润。</br>\\n【病理意义】右耳颜色正常。</br>\\n\",\"faceEarColorLeftUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceEarColorRightUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceEarColorIsRed\":false,\"faceUricularFold\":\"{\\\"code\\\":\\\"fl-70,fr-70\\\",\\\"description\\\":\\\"【左耳正常】左耳耳垂上没有褶皱。</br>\\\\n【病理意义】左耳无耳褶心征</br>\\\\n,【右耳正常】右耳耳垂上没有褶皱。</br>\\\\n【病理意义】右耳无耳褶心征</br>\\\\n\\\",\\\"name\\\":\\\"左耳正常,右耳正常\\\"}\",\"faceUricularFoldName\":\"左耳正常;右耳正常\",\"faceUricularFoldDesc\":\"【左耳正常】左耳耳垂上没有褶皱。</br>\\n【病理意义】左耳无耳褶心征</br>\\n,【右耳正常】右耳耳垂上没有褶皱。</br>\\n【病理意义】右耳无耳褶心征</br>\\n\",\"faceUricularFoldLeftUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceUricularFoldRightUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceUricularFoldIsRed\":false,\"faceCaluateImage\":\"{\\\"doubleEye\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/double_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"faceSplit\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"leftEars\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"leftEye\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rectLip\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/rect_lip/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rightEars\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rightEye\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\"}\",\"faceCaluateImage1\":null,\"faceProcessImage\":\"{\\\"faceAbnormal\\\":\\\"\\\",\\\"faceAreaLabel\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/face_area_label/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"faceBzxz\\\":\\\"\\\",\\\"leftFaceEzxz\\\":\\\"\\\",\\\"leftSidefaceAbnormal\\\":\\\"\\\",\\\"mainMosaic\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/main_mosaic/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rightFaceEzxz\\\":\\\"\\\",\\\"rightSidefaceAbnormal\\\":\\\"\\\"}\",\"faceProcessImage1\":null,\"diagnosticResults\":\" 积食（积食化热）\",\"mainPerformance\":\"[{\\\"items\\\":[],\\\"message\\\":\\\" 积食（积食化热）主要表现为不思乳食，口干，胃腹胀满，腹部灼热。或见平时好动，心烦易怒，夜卧不安，手脚心热，小便黄，大便臭秽或秘结。\\\"}]\",\"occurReason\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"1、母乳和食物聚集在体内时间长了，积食产生热性病变，从而损伤了津液。饮食不节制、损伤了脾胃，脾脏运化功能失常，体内的气运行不畅通，所以不喜欢喝母乳和吃东西，肚子胀；食物聚集在胃肠导致热性病变，消耗损伤了津液，所以口干，腹部像火烧着、烫着那样热，两手心与两脚心有发热感觉，小便比较黄，大便特别臭或大便干涩拉不下来；心神被扰动所以心里烦躁或烦闷、容易生气，晚上睡觉的时候出现不安稳和不踏实；舌头颜色红，舌苔颜色黄，舌苔质地比较腻。\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"2、食物堆积胃肠久了会化热，热会使血行加速，气血扰动则心神被扰，易出现嘴唇红、手足心热的症状。\\\"}]\",\"predisposition\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"无\\\"}]\",\"highRiskDisease\":null,\"eatingHabits\":\"[{\\\"content\\\":\\\"蒲公英与焦三仙和炒鸡内金一同熬水\\\",\\\"title\\\":\\\"宜食\\\"}]\",\"dietRehabilitation\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"蒲公英与焦三仙和炒鸡内金一同熬水\\\"}]\",\"dietaryRecuperate\":null,\"sportsHealthCare\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"蒲公英与焦三仙和炒鸡内金一同熬水\\\"}]\",\"drugHealthCare\":\"[{\\\"items\\\":[{\\\"desc\\\":\\\"【厂家】葵花药业<br> 【用法用量】口服。小儿一次0.5g，每日3次周岁内酌减。<br> 【产品成分】鸡内金、六神曲。辅料为：蔗糖、玉米淀粉。<br> 【功能主治】健脾开胃，消食化积。用于小儿因脾胃不和引起的食积胀满，饮食停滞，呕吐泄泻。<br>\\\",\\\"name\\\":\\\"小儿复方鸡内金散\\\",\\\"photo\\\":\\\"https://labelsys-images.oss-cn-hangzhou.aliyuncs.com/healthcare/葵花药业/小儿复方鸡内金散.jpg\\\"}],\\\"message\\\":\\\"\\\"},{\\\"items\\\":[{\\\"desc\\\":\\\"【厂家】广州王老吉药业股份有限公司<br> 【用法用量】开始冲服。一次3.5~7克，一日3次。<br> 【产品成分】薏苡仁、稻芽、山楂、淡竹叶、钩藤、蝉蜕、甘草。辅料为蔗糖粉。<br> 【功能主治】开胃消滞，清热定惊。用于小儿积滞化热，消化不良，不思饮食，烦躁易惊，夜寐不安，大便不畅，小便短赤。<br>\\\",\\\"name\\\":\\\"小儿七星茶颗粒\\\",\\\"photo\\\":\\\"https://labelsys-images.oss-cn-hangzhou.aliyuncs.com/healthcare/葵花药业/小儿七星茶颗粒.png\\\"}],\\\"message\\\":\\\"\\\"}]\",\"massageHealthCare\":\"[{\\\"bottom\\\":\\\"\\\",\\\"desc\\\":\\\"\\\",\\\"message\\\":\\\"1、逆运内八卦（调理因积食导致的咳嗽)\\\",\\\"photo\\\":\\\"\\\"},{\\\"bottom\\\":\\\"\\\",\\\"desc\\\":\\\"\\\",\\\"message\\\":\\\"2、以上取穴，加清天河水、清大肠。烦躁不安加清心平肝，揉曲池。\\\",\\\"photo\\\":\\\"\\\"}]\",\"tongueScore\":\"3.04\",\"tongueTips\":\"建议您一周后再次做舌诊检测。\",\"recuperates\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"1、避免食物种类过杂\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"每次饮食的种类不宜过多，四到五种即可，如若想吃多种类食物，可延长进食的时间间隔，注意营养均衡。\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"2、适量进食勿过多\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"小儿喂食应适量，吃饱就好，不是吃的越多越好，一次性吃太多的食物容易导致积食，请家长把握好分寸。\\\"}]\",\"illProbability\":\"{\\\"scope\\\":\\\"3.04\\\",\\\"tip\\\":\\\"建议您一周后再次做舌诊检测。\\\"}\",\"solarTerms\":\"寒露\",\"createTime\":\"2023-10-23 15:08:03\",\"updateTime\":\"2023-10-23 17:47:13\",\"tongueColorList\":null,\"tongueColorDescription\":null,\"faceColorList\":null,\"userShopDetailVO\":null,\"tongueColorAttrList\":null,\"tongueColorInfoList\":null,\"tongueMossAttrList\":null,\"tongueMossColorInfoList\":null,\"tongueNatureAttrList\":null,\"tongueShapAttrList\":null,\"tongueBodyAttrList\":null,\"tongueVeinAttrList\":null,\"aiFaceItemVOList\":[{\"name\":null,\"key\":\"面色\",\"field\":\"面色红黄隐隐，明润含蓄\",\"value\":\"面色红黄隐隐，明润含蓄\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"光泽\",\"field\":\"<span style=\\\"color:#F25D4C\\\">少量</span>\",\"value\":\"正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"五色诊\",\"field\":\"正常色\",\"value\":\"正常色\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"两颧红\",\"field\":\"无\",\"value\":\"无\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"眉间/鼻柱青色\",\"field\":\"无\",\"value\":\"无\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"眼神\",\"field\":\"<span style=\\\"color:#F25D4C\\\">少神</span>\",\"value\":\"有神\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"黑眼圈\",\"field\":\"左眼正常;右眼正常\",\"value\":\"右眼正常;左眼正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"目色\",\"field\":\"左眼正常;右眼正常\",\"value\":\"右眼正常;左眼正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"鼻褶\",\"field\":\"无\",\"value\":\"无\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"唇色\",\"field\":\"正常\",\"value\":\"正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"耳色\",\"field\":\"左耳色正常;右耳色正常\",\"value\":\"右耳色正常;左耳色正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"耳褶\",\"field\":\"左耳正常;右耳正常\",\"value\":\"右耳正常;左耳正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"面部皮损\",\"field\":\"无\",\"value\":\"无\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null}],\"aiFaceVOList\":[{\"name\":null,\"key\":\"面色\",\"field\":\"面色红黄隐隐，明润含蓄\",\"value\":\"【正常面色】面色红黄隐隐，明润含蓄。</br>\\n【病理意义】神气、胃气的正常色泽表现。</br>\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/face_area_label/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"五色诊\",\"field\":\"正常色\",\"value\":\"【正常色】面色红黄隐隐，明润含蓄。</br>\\n【病理意义】神气、胃气的正常色泽表现。</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"光泽\",\"field\":\"少量\",\"value\":\"【少量】面色稍许晦暗、少泽。</br>\\n【病理意义】为脏腑精气稍衰，介于病轻和病重之间。</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"眼神\",\"field\":\"少神\",\"value\":\"【少神】两目乏神，动作迟缓，眼神晦暗。</br>\\n【病理意义】是正气不足的表现，见于虚证或轻病。</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/double_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"目色\",\"field\":\"左眼正常,右眼正常\",\"value\":\"【左眼正常】左眼黑白分明，精彩内含。</br>\\n【病理意义】精充、气足、神旺的表现。</br>\\n,【右眼正常】右眼黑白分明，精彩内含。</br>\\n【病理意义】精充、气足、神旺的表现。</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"两颧红\",\"field\":\"无\",\"value\":\"【无】面部两颧未出现红色，颜色跟正常面色一致。</br>\\n【病理意义】胃气充足，气血调匀的表现。</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/face_area_label/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"眉间/鼻柱青色\",\"field\":\"无\",\"value\":\"【无】眉间或鼻柱之间不发青，无青色。</br>\\n【病理意义】眉间无青色。</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/double_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"鼻褶\",\"field\":\"无\",\"value\":\"【无】鼻根部未出现横纹沟。</br>\\n【病理意义】横纹沟代表鼻褶心征。代表动脉硬化，冠动脉供血不足。</br>\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"耳色\",\"field\":\"左耳色正常,右耳色正常\",\"value\":\"【左耳色正常】左耳颜色微黄而红润。</br>\\n【病理意义】左耳颜色正常。</br>\\n,【右耳色正常】右耳颜色微黄而红润。</br>\\n【病理意义】右耳颜色正常。</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"耳褶\",\"field\":\"左耳正常,右耳正常\",\"value\":\"【左耳正常】左耳耳垂上没有褶皱。</br>\\n【病理意义】左耳无耳褶心征</br>\\n,【右耳正常】右耳耳垂上没有褶皱。</br>\\n【病理意义】右耳无耳褶心征</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"面部皮损\",\"field\":\"无\",\"value\":\"【无】面部未出现色素痣、皮下颗粒物或疑似皮肤病等。</br>\\n【病理意义】面部正常。</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"唇色\",\"field\":\"正常\",\"value\":\"【正常】唇色是明亮、润泽、含蓄的红色。</br>\\n【病理意义】胃气充足，气血调匀的表现。</br>\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/rect_lip/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"黑眼圈\",\"field\":\"左眼正常,右眼正常\",\"value\":\"【左眼正常】左眼眼圈不发黑，眼眶颜色正常。</br>\\n【病理意义】左眼无黑眼圈。</br>\\n,【右眼正常】右眼眼圈不发黑，眼眶颜色正常。</br>\\n【病理意义】右眼无黑眼圈。</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null}],\"aiTongueItemVOList\":null,\"aiTongueVOList\":null,\"aiReportItemVOList\":null}";
	public static String str4 = "{\"aiUserRoleEnum\":\"tourist\",\"diagnostic_results\":null,\"sports_health_care\":null,\"eatingHabits\":null,\"diet_rehabilitation\":null,\"diet_rehabilitation_v2022\":null,\"predisposition\":null,\"eatingHabitsItemList\":null,\"massage_health_care\":null,\"main_performance\":[{\"message\":\" 积食（积食化热）主要表现为不思乳食，口干，胃腹胀满，腹部灼热。或见平时好动，心烦易怒，夜卧不安，手脚心热，小便黄，大便臭秽或秘结。\",\"items\":[]}],\"occur_reason\":[{\"message\":\"1、母乳和食物聚集在体内时间长了，积食产生热性病变，从而损伤了津液。饮食不节制、损伤了脾胃，脾脏运化功能失常，体内的气运行不畅通，所以不喜欢喝母乳和吃东西，肚子胀；食物聚集在胃肠导致热性病变，消耗损伤了津液，所以口干，腹部像火烧着、烫着那样热，两手心与两脚心有发热感觉，小便比较黄，大便特别臭或大便干涩拉不下来；心神被扰动所以心里烦躁或烦闷、容易生气，晚上睡觉的时候出现不安稳和不踏实；舌头颜色红，舌苔颜色黄，舌苔质地比较腻。\",\"items\":[]},{\"message\":\"2、食物堆积胃肠久了会化热，热会使血行加速，气血扰动则心神被扰，易出现嘴唇红、手足心热的症状。\",\"items\":[]}],\"illProbabilities\":null,\"drug_health_care\":null,\"recuperates\":null,\"dietaryConditioningList\":null,\"nutritionalMatchList\":null,\"highRiskDiseaseList\":[]}";
	public static String str5 = "{\"aiUserRoleEnum\":\"tourist\",\"diagnostic_results\":null,\"sports_health_care\":null,\"eatingHabits\":null,\"diet_rehabilitation\":null,\"diet_rehabilitation_v2022\":null,\"predisposition\":null,\"eatingHabitsItemList\":[{\"title\":\"宜食\",\"content\":\"蒲公英与焦三仙和炒鸡内金一同熬水\"}],\"massage_health_care\":null,\"main_performance\":null,\"occur_reason\":null,\"illProbabilities\":null,\"drug_health_care\":[{\"message\":\"\",\"items\":[{\"name\":\"小儿复方鸡内金散\",\"desc\":\"【厂家】葵花药业<br> 【用法用量】口服。小儿一次0.5g，每日3次周岁内酌减。<br> 【产品成分】鸡内金、六神曲。辅料为：蔗糖、玉米淀粉。<br> 【功能主治】健脾开胃，消食化积。用于小儿因脾胃不和引起的食积胀满，饮食停滞，呕吐泄泻。<br>\",\"player_url\":null,\"photo\":\"https://labelsys-images.oss-cn-hangzhou.aliyuncs.com/healthcare/葵花药业/小儿复方鸡内金散.jpg\",\"jumpUrl\":null,\"audio_url\":null}]},{\"message\":\"\",\"items\":[{\"name\":\"小儿七星茶颗粒\",\"desc\":\"【厂家】广州王老吉药业股份有限公司<br> 【用法用量】开始冲服。一次3.5~7克，一日3次。<br> 【产品成分】薏苡仁、稻芽、山楂、淡竹叶、钩藤、蝉蜕、甘草。辅料为蔗糖粉。<br> 【功能主治】开胃消滞，清热定惊。用于小儿积滞化热，消化不良，不思饮食，烦躁易惊，夜寐不安，大便不畅，小便短赤。<br>\",\"player_url\":null,\"photo\":\"https://labelsys-images.oss-cn-hangzhou.aliyuncs.com/healthcare/葵花药业/小儿七星茶颗粒.png\",\"jumpUrl\":null,\"audio_url\":null}]}],\"recuperates\":[{\"message\":\"1、避免食物种类过杂\",\"items\":[]},{\"message\":\"每次饮食的种类不宜过多，四到五种即可，如若想吃多种类食物，可延长进食的时间间隔，注意营养均衡。\",\"items\":[]},{\"message\":\"2、适量进食勿过多\",\"items\":[]},{\"message\":\"小儿喂食应适量，吃饱就好，不是吃的越多越好，一次性吃太多的食物容易导致积食，请家长把握好分寸。\",\"items\":[]}],\"dietaryConditioningList\":[{\"title\":\"\",\"content\":\"1、调节饮食，合理喂养，乳食宜定时定量，选择富含营养、清热的食物如苦瓜粥。\"},{\"title\":\"\",\"content\":\"2、忌暴饮暴食、过食肥甘、熏烤、生冷瓜果，偏食零食及妄加滋补。\"}],\"nutritionalMatchList\":[{\"name\":\"健脾益气\",\"notice\":\"\",\"productInfoList\":[{\"img\":\"https://img.aikesaisi.com/ai/product/pph.png\",\"message\":\"补充爱欣童脾皮虎风味饮料，每日1-3瓶；调理周期1-3个月。\",\"productName\":\"爱欣童脾皮虎风味饮料\"}]},{\"name\":\"清热祛火\",\"notice\":\"\",\"productInfoList\":[{\"img\":\"https://img.aikesaisi.com/ai/product/qqh.png\",\"message\":\"补充爱欣童顷清虎风味饮料，每日1-2瓶；调理周期5-7天。\",\"productName\":\"爱欣童顷清虎风味饮料\"}]},{\"name\":\"滋阴润燥\",\"notice\":\"\",\"productInfoList\":[{\"img\":\"https://img.aikesaisi.com/ai/product/ffh.png\",\"message\":\"补充爱欣童肺扉虎风味饮料，每日2次，每次1瓶；调理周期1-3个月。\",\"productName\":\"爱欣童肺扉虎风味饮料\"}]},{\"name\":\"均衡营养\",\"notice\":\"\",\"productInfoList\":[{\"img\":\"https://img.aikesaisi.com/ai/product/xzw.png\",\"message\":\"补充挑剔麻咪酵母富锌多种维生素补充膳食摄入不足造成的营养不均衡，每日1-3粒。\",\"productName\":\"挑剔麻咪酵母富锌多种维生素\"}]}],\"highRiskDiseaseList\":null}";

	private static final String UPLOAD_URL = "http://img.aikesaisi.com/";

	public static final String topUrl = "https://img.aikesaisi.com/ai/user/2024-01-22/2c37d5b4ef6b42d9ac83986c14185a94-top.png";
	public static final String iconUrl = "https://img.aikesaisi.com/ai/user/2024-01-23/71940a1f727f4d469f0d279d56fa1a8c-icon.png";
	public static final String testCodeUrl = "http://img.aikesaisi.com/jhbamin/images/2023-10-08/8ad97167797c4f0481bdb11f647a32f7-code.jpg";
	public static final String testSheUrl = "http://img.aikesaisi.com/jhbamin/images/2023-10-08/e895fbdc05c347fbad116e438058abe4-shetou.jpg";
	public static final String testLeftUrl = "https://img.aikesaisi.com/ai/user/2024-01-23/87f00a6a7441486f920beb69820b6db3-left.jpg";


	// 宋体，正常字体
	public static BaseFont bfChinese;

	static {
		try {
			bfChinese = BaseFont.createFont(PdfUtils2.FontName, PdfUtils2.FontEncoding, BaseFont.EMBEDDED);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Font font5 = new Font(bfChinese, 5);
	public static Font font10 = new Font(bfChinese, 10);
	public static Font font12 = new Font(bfChinese, 12);
	public static Font font14 = new Font(bfChinese, 14);
	public static Font font16 = new Font(bfChinese, 16);
	public static Font font20 = new Font(bfChinese, 20);
	public static Font font30 = new Font(bfChinese, 30);
	public static Font font10Bold = new Font(bfChinese, 10, Font.BOLD);
	public static Font font12Bold = new Font(bfChinese, 12, Font.BOLD);
	public static Font font14Bold = new Font(bfChinese, 14, Font.BOLD);
	public static Font font16Bold = new Font(bfChinese, 16, Font.BOLD);
	public static Font font20Bold = new Font(bfChinese, 20, Font.BOLD);
	public static Font font24Bold = new Font(bfChinese, 24, Font.BOLD);
	public static Font font30Bold = new Font(bfChinese, 30, Font.BOLD);

	public static Font font8Black = new Font(bfChinese, 9, Font.NORMAL, new Color(104, 119, 116));
	public static Font font10White = new Font(bfChinese, 10, Font.NORMAL, new Color(255, 255, 255));
	public static Font font10Red = new Font(bfChinese, 10, Font.NORMAL, new Color(252, 134, 117));
	public static Font font10Blue = new Font(bfChinese, 10, Font.NORMAL, new Color(2, 162, 157));
	public static Font font10BoldBlue = new Font(bfChinese, 10, Font.BOLD, new Color(2, 162, 157));
	public static Font font12BoldBlue = new Font(bfChinese, 12, Font.BOLD, new Color(2, 162, 157));
	public static Font font14BoldBlue = new Font(bfChinese, 14, Font.BOLD, new Color(2, 162, 157));

	public static Color borderColor1 = new Color(79, 128, 189);
	public static Color backgroundColor1 = new Color(219, 229, 241);
	public static Color backgroundColor2 = new Color(245, 255, 255);

	public static PdfResVo pdfResVo = new PdfResVo();


	/**
	 * 查询pdf数据
	 *
	 * @param vo
	 */
	private static PdfResVo queryPdfResVo(GeneratePdfVO vo) {
		pdfResVo = new PdfResVo();

		pdfResVo.setDetailVo(JSON.parseObject(str1, AiConsultFlowVO.class));
		pdfResVo.setTongueVo(JSON.parseObject(str2, AiConsultFlowVO.class));
		pdfResVo.setFaceVo(JSON.parseObject(str3, AiConsultFlowVO.class));
		pdfResVo.setHealthVo(JSON.parseObject(str4, AiTreatPlanJsonVo.class));
		pdfResVo.setConditioningVo(JSON.parseObject(str5, AiTreatPlanJsonVo.class));

		GeneratePdfVO generatePdfVO = new GeneratePdfVO();
		generatePdfVO.setReportId(vo.getReportId());
		pdfResVo.setGeneratePdfVO(generatePdfVO);

		return pdfResVo;
	}

	/**
	 * 第一页，标题+个人信息+二维码
	 *
	 * @param document
	 */
	private static void onePage(Document document) {
		// 图片表格，4行3列
		PdfPTable table1 = new PdfPTable(3);
		// 宽度100%填充
		table1.setWidthPercentage(100);
		// 设置表格无边框
		table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		// 创建表格的的行对象集合
		List<PdfPRow> listRow1 = table1.getRows();
		// 将表格设置为3列，并指定列宽
		table1.setWidths(new float[]{7f, 2f, 1f});

		PdfPCell[] cells1 = new PdfPCell[3];
		cells1[0] = PdfUtils2.newCell("", font10, 0, 4, Element.ALIGN_LEFT, null, PdfPCell.NO_BORDER);
		cells1[0].addElement(PdfUtils2.newImage(topUrl, Element.ALIGN_LEFT, 350, 100));
		cells1[1] = PdfUtils2.newCell(" ", font10, null, PdfPCell.NO_BORDER);
		cells1[2] = PdfUtils2.newCell("", font10, 0, 4, Element.ALIGN_LEFT, null, PdfPCell.NO_BORDER);
		cells1[2].addElement(PdfUtils2.newImage(testCodeUrl, Element.ALIGN_LEFT, 50, 50));
		listRow1.add(new PdfPRow(cells1));

		PdfPCell[] cells2 = new PdfPCell[3];
		cells2[1] = PdfUtils2.newCell("微信扫一扫", font10, 0, 0, Element.ALIGN_RIGHT, null, PdfPCell.NO_BORDER);
		listRow1.add(new PdfPRow(cells2));

		PdfPCell[] cells3 = new PdfPCell[3];
		cells3[1] = PdfUtils2.newCell("查看电子版报告", font10, 0, 0, Element.ALIGN_RIGHT, null, PdfPCell.NO_BORDER);
		listRow1.add(new PdfPRow(cells3));

		PdfPCell[] cells4 = new PdfPCell[3];
		cells4[1] = PdfUtils2.newCell(" ", font10, null, PdfPCell.NO_BORDER);
		listRow1.add(new PdfPRow(cells4));

		document.add(table1);

		Paragraph paragraph = new Paragraph();
		paragraph.add(new Chunk("健康状态报告", font30Bold));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setLeading(30);
		document.add(paragraph);
		document.add(PdfUtils2.newParagraph("\n", font10));

		PdfPTable table2 = new PdfPTable(3);
		table2.setWidthPercentage(100);
		table2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		List<PdfPRow> listRow2 = table2.getRows();
		table2.setWidths(new float[]{3f, 3f, 4f});

		PdfPCell[] cells21 = new PdfPCell[3];
		cells21[0] = PdfUtils2.newCell("检测人：毛毛（5岁，男）", font10, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER);
		cells21[1] = PdfUtils2.newCell("检测类别：儿童体质辨识", font10, Element.ALIGN_CENTER, PdfPCell.NO_BORDER);
		cells21[2] = PdfUtils2.newCell("检测时间：2024-01-23 12:12:12", font10, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		listRow2.add(new PdfPRow(cells21));

		document.add(table2);
	}

	/**
	 * 第二页，健康状态
	 *
	 * @param document
	 */
	private static void twoPage(Document document) {
		document.add(PdfUtils2.newParagraph("\n", font10));

		Paragraph paragraph = new Paragraph();
		paragraph.add(new Chunk("一、健康状态：", font14Bold));
		paragraph.add(new Chunk("阳盛质", font14BoldBlue));
		paragraph.setLeading(20);
		document.add(paragraph);

		document.add(PdfUtils2.newParagraph20("1、主要表现", font12Bold));
		document.add(PdfUtils2.newParagraph18("可能出现或者即将出现以下一个或者几个症状，不排除出现其他特殊情况", font10Blue));
		document.add(PdfUtils2.newParagraph("\n", font5));

		PdfPTable table0 = new PdfPTable(2);
		table0.setWidthPercentage(100);
		table0.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		List<PdfPRow> listRow0 = table0.getRows();
		table0.setWidths(new float[]{0.4f, 9.6f});

		PdfPCell[] cells01 = new PdfPCell[2];
		cells01[0] = PdfUtils2.newCell("", font10, Element.ALIGN_CENTER, PdfPCell.NO_BORDER);
		cells01[0].addElement(PdfUtils2.newImage(iconUrl, Element.ALIGN_LEFT, 16, 16));
		cells01[1] = PdfUtils2.newCell("面色微黄面色微黄面色微黄面色微黄面色微黄", font10, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		listRow0.add(new PdfPRow(cells01));

		PdfPCell[] cells02 = new PdfPCell[2];
		cells02[0] = PdfUtils2.newCell("", font10, Element.ALIGN_CENTER, PdfPCell.NO_BORDER);
		cells02[0].addElement(PdfUtils2.newImage(iconUrl, Element.ALIGN_LEFT, 16, 16));
		cells02[1] = PdfUtils2.newCell("气短胸闷气短胸闷气短胸闷气短胸闷气短胸闷", font10, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		listRow0.add(new PdfPRow(cells02));

		PdfPCell[] cells03 = new PdfPCell[2];
		cells03[0] = PdfUtils2.newCell("", font10, Element.ALIGN_CENTER, PdfPCell.NO_BORDER);
		cells03[0].addElement(PdfUtils2.newImage(iconUrl, Element.ALIGN_LEFT, 16, 16));
		cells03[1] = PdfUtils2.newCell("面色微黄面色微黄面色微黄面色微黄面色微黄", font10, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		listRow0.add(new PdfPRow(cells03));

		document.add(table0);

		document.add(PdfUtils2.newParagraph20("2、发生原因", font12Bold));
		document.add(PdfUtils2.newParagraph18("多因素体气虚多因素体气虚多因素体气虚多因素体气虚多因素体气虚", font10));

		document.add(PdfUtils2.newParagraph20("3、健康指数及趋势", font12Bold));

		PdfPTable table1 = new PdfPTable(2);
		table1.setWidthPercentage(100);
		table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		List<PdfPRow> listRow1 = table1.getRows();
		table1.setWidths(new float[]{5f, 5f});

		PdfPCell[] cells1 = new PdfPCell[2];
		cells1[0] = PdfUtils2.newCell("", font10, null, PdfPCell.NO_BORDER);
		cells1[0].addElement(PdfUtils2.newImage(testLeftUrl, Element.ALIGN_CENTER, 250, 150));
		cells1[1] = PdfUtils2.newCell("", font10, null, PdfPCell.NO_BORDER);
		cells1[1].addElement(PdfUtils2.newImage(testLeftUrl, Element.ALIGN_CENTER, 250, 150));
		listRow1.add(new PdfPRow(cells1));

		document.add(table1);
	}

	/**
	 * 第三页，舌象辨识
	 *
	 * @param document
	 */
	private static void threePage(Document document) {
		document.add(PdfUtils2.newParagraph("\n", font10));
		document.add(PdfUtils2.newParagraph20("二、舌象辨识：", font14Bold));

		document.add(PdfUtils2.newParagraph20("1、舌象分析结果汇总", font12Bold));

		Paragraph paragraph1 = new Paragraph();
		paragraph1.add(new Chunk("结合上次检测(2023-10-17)，", font10));
		paragraph1.add(new Chunk("舌象特征变化解析：", font10Red));
		paragraph1.setLeading(18);
		document.add(paragraph1);
		Paragraph paragraph2 = new Paragraph();
		paragraph2.add(new Chunk("【薄苔腻】变成【薄苔腻】：", font10Blue));
		paragraph2.add(new Chunk("舌象特征变化解析舌象特征变化解析", font10));
		paragraph2.setLeading(18);
		document.add(paragraph2);
		Paragraph paragraph3 = new Paragraph();
		paragraph3.add(new Chunk("【薄苔腻】变成【薄苔腻】：", font10Blue));
		paragraph3.add(new Chunk("舌象特征变化解析舌象特征变化解析", font10));
		paragraph3.setLeading(18);
		document.add(paragraph3);

		document.add(PdfUtils2.newParagraph20("2、异常舌象人工智能解析", font12Bold));
		document.add(PdfUtils2.newParagraph("\n", font10));

		// 表格
		PdfPTable table1 = new PdfPTable(6);
		table1.setWidthPercentage(100);
		table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		List<PdfPRow> listRow1 = table1.getRows();
		table1.setWidths(new float[]{1.2f, 1.2f, 1.2f, 1.2f, 1.2f, 4f});

		PdfPCell[] cells1 = new PdfPCell[6];
		cells1[0] = PdfUtils2.newCell("异常项", font12BoldBlue, 0, 0, Element.ALIGN_CENTER, 20, PdfPCell.NO_BORDER);
		cells1[0].setBorder(PdfPCell.BOX);
		cells1[0].setBackgroundColor(backgroundColor2);
		cells1[1] = PdfUtils2.newCell("人工智能分析", font12BoldBlue, 5, 0, Element.ALIGN_CENTER, 20, PdfPCell.NO_BORDER);
		cells1[1].setBorder(PdfPCell.BOX);
		cells1[1].setBackgroundColor(backgroundColor2);
		listRow1.add(new PdfPRow(cells1));

		PdfPCell[] cells2 = new PdfPCell[6];
		cells2[0] = PdfUtils2.newCell("舌色", font10, Element.ALIGN_CENTER, PdfPCell.NO_BORDER);
		cells2[0].setBorder(PdfPCell.BOX);

		cells2[1] = PdfUtils2.newCell("", font10, Element.ALIGN_CENTER, PdfPCell.BOTTOM);
		cells2[1].addElement(PdfUtils2.newImage(testSheUrl, Element.ALIGN_CENTER, 50, 50));
		cells2[1].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);

		Paragraph paragraphCell22 = new Paragraph();
		// 第一行
		paragraphCell22.add(new Chunk("\n淡红舌(本次)", font10Red));
		paragraphCell22.add(new Chunk(" \n ", font5));
		// 第二行
		Chunk chunk221 = new Chunk("\n舌边红", font10White);
		chunk221.setBackground(new Color(252, 134, 117));
		paragraphCell22.add(chunk221);
		paragraphCell22.add(new Chunk(" \n ", font5));
		// 第三行
		Chunk chunk222 = new Chunk("\n舌中淡黄苔", font10White);
		chunk222.setBackground(new Color(252, 134, 117));
		paragraphCell22.add(chunk222);
		paragraphCell22.add(new Chunk(" \n ", font5));
		// 添加
		cells2[2] = new PdfPCell(paragraphCell22);
		cells2[2].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);

		cells2[3] = PdfUtils2.newCell("", font10, Element.ALIGN_CENTER, PdfPCell.BOTTOM);
		cells2[3].addElement(PdfUtils2.newImage(testSheUrl, Element.ALIGN_CENTER, 50, 50));
		cells2[3].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);

		Paragraph paragraphCell44 = new Paragraph();
		// 第一行
		paragraphCell44.add(new Chunk("\n淡白舌(上次)", font10Red));
		paragraphCell44.add(new Chunk(" \n ", font5));
		// 第二行
		Chunk chunk441 = new Chunk("\n正常", font10White);
		chunk441.setBackground(new Color(6, 192, 96));
		paragraphCell44.add(chunk441);
		paragraphCell44.add(new Chunk(" \n ", font5));
		// 添加
		cells2[4] = new PdfPCell(paragraphCell44);
		cells2[4].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);

		Paragraph paragraphCell55 = new Paragraph();
		paragraphCell55.add(new Chunk("\n【淡红色】正常人舌色正常人舌色正常人舌色正常人舌色。", font10));
		paragraphCell55.add(new Chunk(" \n ", font5));
		paragraphCell55.add(new Chunk("\n【健康分析】正常人舌色正常人舌色正常人舌色正常人舌色。", font10));
		paragraphCell55.add(new Chunk(" \n ", font5));
		cells2[5] = new PdfPCell(paragraphCell55);
		//cells2[5].setPaddingBottom(10);
		cells2[5].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP | PdfPCell.RIGHT);

		listRow1.add(new PdfPRow(cells2));
		listRow1.add(new PdfPRow(cells2));
		listRow1.add(new PdfPRow(cells2));

		document.add(table1);
	}

	/**
	 * 第四页，面象辨识
	 *
	 * @param document
	 */
	private static void fourPage(Document document) {
		document.add(PdfUtils2.newParagraph("\n", font10));
		document.add(PdfUtils2.newParagraph20("三、面象辨识：", font14Bold));

		document.add(PdfUtils2.newParagraph20("1、异常面象人工智能解析", font12Bold));
		document.add(PdfUtils2.newParagraph("\n", font10));

		// 表格
		PdfPTable table1 = new PdfPTable(6);
		table1.setWidthPercentage(100);
		table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		List<PdfPRow> listRow1 = table1.getRows();
		table1.setWidths(new float[]{1.2f, 1.2f, 1.2f, 1.2f, 1.2f, 4f});

		PdfPCell[] cells1 = new PdfPCell[6];
		cells1[0] = PdfUtils2.newCell("异常项", font12BoldBlue, 0, 0, Element.ALIGN_CENTER, 20, PdfPCell.NO_BORDER);
		cells1[0].setBorder(PdfPCell.BOX);
		cells1[0].setBackgroundColor(backgroundColor2);
		cells1[1] = PdfUtils2.newCell("人工智能分析", font12BoldBlue, 5, 0, Element.ALIGN_CENTER, 20, PdfPCell.NO_BORDER);
		cells1[1].setBorder(PdfPCell.BOX);
		cells1[1].setBackgroundColor(backgroundColor2);
		listRow1.add(new PdfPRow(cells1));

		PdfPCell[] cells2 = new PdfPCell[6];
		cells2[0] = PdfUtils2.newCell("舌色", font10, Element.ALIGN_CENTER, PdfPCell.NO_BORDER);
		cells2[0].setBorder(PdfPCell.BOX);

		cells2[1] = PdfUtils2.newCell("", font10, Element.ALIGN_CENTER, PdfPCell.BOTTOM);
		cells2[1].addElement(PdfUtils2.newImage(testSheUrl, Element.ALIGN_CENTER, 50, 50));
		cells2[1].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);

		Paragraph paragraphCell22 = new Paragraph();
		// 第一行
		paragraphCell22.add(new Chunk("\n淡红舌(本次)", font10Red));
		paragraphCell22.add(new Chunk(" \n ", font5));
		// 第二行
		Chunk chunk221 = new Chunk("\n舌边红", font10White);
		chunk221.setBackground(new Color(252, 134, 117));
		paragraphCell22.add(chunk221);
		paragraphCell22.add(new Chunk(" \n ", font5));
		// 第三行
		Chunk chunk222 = new Chunk("\n舌中淡黄苔", font10White);
		chunk222.setBackground(new Color(252, 134, 117));
		paragraphCell22.add(chunk222);
		paragraphCell22.add(new Chunk(" \n ", font5));
		// 添加
		cells2[2] = new PdfPCell(paragraphCell22);
		cells2[2].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);

		cells2[3] = PdfUtils2.newCell("", font10, Element.ALIGN_CENTER, PdfPCell.BOTTOM);
		cells2[3].addElement(PdfUtils2.newImage(testSheUrl, Element.ALIGN_CENTER, 50, 50));
		cells2[3].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);

		Paragraph paragraphCell44 = new Paragraph();
		// 第一行
		paragraphCell44.add(new Chunk("\n淡白舌(上次)", font10Red));
		paragraphCell44.add(new Chunk(" \n ", font5));
		// 第二行
		Chunk chunk441 = new Chunk("\n正常", font10White);
		chunk441.setBackground(new Color(6, 192, 96));
		paragraphCell44.add(chunk441);
		paragraphCell44.add(new Chunk(" \n ", font5));
		// 添加
		cells2[4] = new PdfPCell(paragraphCell44);
		cells2[4].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP);

		Paragraph paragraphCell55 = new Paragraph();
		paragraphCell55.add(new Chunk("\n【淡红色】正常人舌色正常人舌色正常人舌色正常人舌色正常人舌色正常人舌色。", font10));
		paragraphCell55.add(new Chunk(" \n ", font5));
		paragraphCell55.add(new Chunk("\n【健康分析】正常人舌色正常人舌色正常人舌色正常人舌色正常人舌色正常人舌色。", font10));
		paragraphCell55.add(new Chunk(" \n ", font5));
		cells2[5] = new PdfPCell(paragraphCell55);
		//cells2[5].setPaddingBottom(10);
		cells2[5].setBorder(PdfPCell.BOTTOM | PdfPCell.TOP | PdfPCell.RIGHT);

		listRow1.add(new PdfPRow(cells2));
		listRow1.add(new PdfPRow(cells2));
		listRow1.add(new PdfPRow(cells2));

		document.add(table1);
	}

	/**
	 * 第五页，调理方案
	 *
	 * @param document
	 */
	private static void fivePage(Document document) {
		document.add(PdfUtils2.newParagraph("\n", font10));
		document.add(PdfUtils2.newParagraph20("四、调理方案：", font14Bold));

		document.add(PdfUtils2.newParagraph20("（一）膳食调理", font12Bold));
		document.add(PdfUtils2.newParagraph18("1、应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡", font10));
		document.add(PdfUtils2.newParagraph18("2、应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡", font10));
		document.add(PdfUtils2.newParagraph18("3、应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡", font10));

		document.add(PdfUtils2.newParagraph20("（二）膳食调理", font12Bold));

		document.add(PdfUtils2.newParagraph20("必选营养", font12BoldBlue));
		document.add(PdfUtils2.newParagraph18("1、应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡", font10));
		document.add(PdfUtils2.newParagraph18("2、应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡", font10));
		document.add(PdfUtils2.newParagraph("\n", font10));

		PdfPTable table1 = new PdfPTable(6);
		table1.setWidthPercentage(100);
		table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		List<PdfPRow> listRow1 = table1.getRows();
		table1.setWidths(new float[]{1.6f, 1.6f, 1.6f, 1.6f, 1.6f, 1.6f});

		PdfPCell[] cells1 = new PdfPCell[6];
		cells1[0] = PdfUtils2.newCell("", font12, null, PdfPCell.NO_BORDER);
		cells1[0].addElement(PdfUtils2.newImage(testSheUrl, Element.ALIGN_CENTER, 50, 50));
		cells1[1] = PdfUtils2.newCell("爱欣童脾皮虎", font12, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		cells1[2] = PdfUtils2.newCell("", font12, null, PdfPCell.NO_BORDER);
		cells1[2].addElement(PdfUtils2.newImage(testSheUrl, Element.ALIGN_CENTER, 50, 50));
		cells1[3] = PdfUtils2.newCell("挑剔麻咪酵母富锌多种维生素", font12, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		cells1[4] = PdfUtils2.newCell("", font12, null, PdfPCell.NO_BORDER);
		cells1[4].addElement(PdfUtils2.newImage(testSheUrl, Element.ALIGN_CENTER, 50, 50));
		cells1[5] = PdfUtils2.newCell("挑剔麻咪酵母富锌多种维生素", font12, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		listRow1.add(new PdfPRow(cells1));

		document.add(table1);

		document.add(PdfUtils2.newParagraph20("可选营养", font12BoldBlue));
		document.add(PdfUtils2.newParagraph18("1、应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡应多吃清淡", font10));
		document.add(PdfUtils2.newParagraph("\n", font10));

		PdfPTable table2 = new PdfPTable(6);
		table2.setWidthPercentage(100);
		table2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		List<PdfPRow> listRow2 = table2.getRows();
		table2.setWidths(new float[]{1.6f, 1.6f, 1.6f, 1.6f, 1.6f, 1.6f});

		PdfPCell[] cells2 = new PdfPCell[6];
		cells2[0] = PdfUtils2.newCell("", font12, null, PdfPCell.NO_BORDER);
		cells2[0].addElement(PdfUtils2.newImage(testSheUrl, Element.ALIGN_CENTER, 50, 50));
		cells2[1] = PdfUtils2.newCell("爱欣童脾皮虎", font12, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		cells2[2] = PdfUtils2.newCell("", font12, null, PdfPCell.NO_BORDER);
		cells2[2].addElement(PdfUtils2.newImage(testSheUrl, Element.ALIGN_CENTER, 50, 50));
		cells2[3] = PdfUtils2.newCell("挑剔麻咪酵母富锌多种维生素", font12, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		listRow2.add(new PdfPRow(cells2));

		document.add(table2);

		document.add(PdfUtils2.newParagraph("\n", font16));
		document.add(PdfUtils2.newParagraph("感谢您对智能营养助手认可，建议在7天后再次进行检测，帮您建立私人的健康管理档案，随时在线查阅。",
				font8Black, Element.ALIGN_CENTER, 18f));
		document.add(PdfUtils2.newParagraph("智能营养助手，由安徽中医药大学董昌武教授带队研发，已助力700多万人健康生活，全国200多家医院都在使用，准确率高达95%。",
				font8Black, Element.ALIGN_CENTER, 18f));
	}

	public static void main(String[] args) {
		GeneratePdfVO vo = new GeneratePdfVO();
		vo.setReportId("1166030276602761216");
		vo.setOpenid("oEpd363KwdU5Pdab4e2ojmnxRZ4o");

		// 创建文档对象
		Document document = new Document();
		// 设置pdf间距，默认36，改成18
		document.setMargins(20, 20, 15, 15);

		File file = null;
		Boolean isSuccess = false;

		try {
			LocalDateTime begin = LocalDateTime.now();
			System.out.println("===test===begin===" + begin);

			// 数据正确性判断，和pdf是否要生成
			LocalDateTime queryData = LocalDateTime.now();
			System.out.println("===test===queryData===" + Duration.between(begin, queryData).toMillis());

			// 文件
			file = new File(vo.getReportId() + ".pdf");

			// 查询pdf数据
			LocalDateTime queryPdf = LocalDateTime.now();
			pdfResVo = queryPdfResVo(vo);
			System.out.println("===test===queryPdf===" + Duration.between(begin, queryPdf).toMillis());

			// 指定PDF文件的输出路径
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));

			// 打开文档
			document.open();

			// 第一页，标题+个人信息+二维码
			LocalDateTime end1 = LocalDateTime.now();
			onePage(document);
			System.out.println("===test===end1===" + Duration.between(begin, end1).toMillis());

			// 第二页，健康状态
			LocalDateTime end2 = LocalDateTime.now();
			twoPage(document);
			System.out.println("===test===end2===" + Duration.between(end1, end2).toMillis());

			// 第三页，舌象辨识
			LocalDateTime end3 = LocalDateTime.now();
			threePage(document);
			System.out.println("===test===end3===" + Duration.between(end2, end3).toMillis());

			// 第四页，面象辨识
			LocalDateTime end4 = LocalDateTime.now();
			fourPage(document);
			System.out.println("===test===end4===" + Duration.between(end3, end4).toMillis());

			// 第五页，调理方案
			LocalDateTime end5 = LocalDateTime.now();
			fivePage(document);
			System.out.println("===test===end5===" + Duration.between(end4, end5).toMillis());

			LocalDateTime end6 = LocalDateTime.now();

			isSuccess = true;
			System.out.println("===test===all===" + Duration.between(begin, end6).toMillis());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭文档
			document.close();
			System.out.println("===PDF生成成功！==={}" + JSON.toJSONString(vo));
			//pdfResVo = new PdfResVo();

			// 上传
			try {
				if (isSuccess) {
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				/*if (file != null) {
					file.delete();
				}*/
			}

			//System.out.println("===vo===end==={}" + JSON.toJSONString(vo));
			//return BaseResponse.success(vo.getPdfUrl());
		}
	}


}
