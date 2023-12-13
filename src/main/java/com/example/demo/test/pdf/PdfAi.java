package com.example.demo.test.pdf;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.google.zxing.WriterException;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.*;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author huangjiale
 * @date 2023/10/10 13:25
 **/
@Data
public class PdfAi {

	public static String str1 = "{\"pdfUrl\":null,\"openid\":null,\"id\":86,\"name\":\"测试2\",\"genderDesc\":\"女\",\"gender\":1,\"birthday\":\"2010-07-21\",\"age\":13.3,\"reportId\":\"1166030276602761216\",\"tongueAttrs\":\"[{\\\"attrName\\\":\\\"舌色程度\\\",\\\"attrValue\\\":\\\"舌边淡\\\"},{\\\"attrName\\\":\\\"舌根苔色\\\",\\\"attrValue\\\":\\\"舌根白苔\\\"},{\\\"attrName\\\":\\\"瘀点数量\\\",\\\"attrValue\\\":\\\"0\\\"},{\\\"attrName\\\":\\\"瘀点程度\\\",\\\"attrValue\\\":\\\"全舌无瘀点\\\"},{\\\"attrName\\\":\\\"齿痕\\\",\\\"attrValue\\\":\\\"轻度齿痕\\\"}]\",\"tongueBatchNo\":\"1166030277122854912\",\"tongueStep\":3,\"tongueStepStatus\":1,\"detectionTypeDesc\":\"脾胃病\",\"tongueProcessExtraImage\":\"{}\",\"tongueProcessExtraImage1\":null,\"constitutionNames\":\"通用结论\",\"faceBatchNo\":\"1166030285750538240\",\"templateId\":1705,\"checkIndex\":null,\"checkId\":14,\"userInfoId\":8,\"step\":null,\"stepStatus\":null,\"faceStep\":2,\"faceStepStatus\":1,\"tonguePreFailMsg\":\"\",\"facePreFailMsg\":\"\",\"detectionType\":\"C08.D00\",\"detectionTypeName\":null,\"questions\":\"[{\\\"group\\\":\\\"\\\",\\\"types\\\":[{\\\"code\\\":\\\"IS0105\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"形体肌肤\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"唇红\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"霰粒肿\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"面色少华\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"形体消瘦\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"眼皮浮肿\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"面色萎黄\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"皮肤粗糙\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"嘴角溃烂\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"形体肥胖\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"鼻梁有青筋\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"眼睑发紫发红\\\"}],\\\"sort\\\":1},{\\\"code\\\":\\\"IS0106\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"头身不适\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"呃逆\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"呕吐\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腹胀\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腹痛\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"黄痰\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"肚子硬\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"脘痞胀\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"易感冒\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腿抽筋\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"倦怠乏力\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"面色晦暗\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"牙龈出血\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腹痛欲泻\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"咽喉红肿\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"头发易打结\\\"}],\\\"sort\\\":2},{\\\"code\\\":\\\"IS0107\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"寒热\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"盗汗\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"手足心热\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"身体发热\\\"}],\\\"sort\\\":3},{\\\"code\\\":\\\"IS0108\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"饮食\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"口臭\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"口渴\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"贪吃\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"食欲不振\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"喜欢吃凉东西\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"消化系统紊乱\\\"}],\\\"sort\\\":4},{\\\"code\\\":\\\"IS0109\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"二便\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"排便不爽\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"大便干结\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"小便短黄\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"便秘或腹泻\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"大便有臭鸡蛋味\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"大便夹杂不消化食物\\\"}],\\\"sort\\\":5},{\\\"code\\\":\\\"IS0110\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"情志睡眠\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"嗳气\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"烦躁\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"失眠\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"磨牙\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"善悲易哭\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"急躁易怒\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"睡觉露睛\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"夜眠不安\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"燥扰不宁\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"睡觉流口水\\\"}],\\\"sort\\\":6}]}]\",\"answers\":\"[{\\\"answerCode\\\":\\\"腹胀\\\",\\\"code\\\":\\\"IS0106\\\",\\\"questionIndex\\\":2},{\\\"answerCode\\\":\\\"唇红\\\",\\\"code\\\":\\\"IS0105\\\",\\\"questionIndex\\\":1},{\\\"answerCode\\\":\\\"手足心热\\\",\\\"code\\\":\\\"IS0107\\\",\\\"questionIndex\\\":3},{\\\"answerCode\\\":\\\"食欲不振\\\",\\\"code\\\":\\\"IS0108\\\",\\\"questionIndex\\\":4},{\\\"answerCode\\\":\\\"便秘或腹泻\\\",\\\"code\\\":\\\"IS0109\\\",\\\"questionIndex\\\":5},{\\\"answerCode\\\":\\\"失眠\\\",\\\"code\\\":\\\"IS0110\\\",\\\"questionIndex\\\":6}]\",\"reportLockStatus\":0,\"tongueSurfaceImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/f89cc8108b47471994f37e834eab1fcd-tmp_be0673ac3c80cf3597be30659f7b70d00b73a0489851eab3.jpg\",\"tongueBackImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/9a27ae484edf4abab461187a08bd3f27-tmp_1ee8eebbe1ec0a7394569b85a03d35c080e1950f0556bca8.jpg\",\"tongueSplitSurfaceImg\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"tongueSplitSurfaceImg1\":\"\",\"tongueSplitBackImg\":\"https://td-platform-split-back.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"tongueSplitBackImg1\":\"\",\"tongueProcessImages\":\"{\\\"ossYudianImgUrl\\\":\\\"\\\",\\\"ossSheseImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/shese/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossShexingImgUrl\\\":\\\"\\\",\\\"ossToothtraceImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/toothtrace/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossBoluoImgUrl\\\":\\\"\\\",\\\"ossLiewenImgUrl\\\":\\\"\\\",\\\"ossTaiseImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/taise/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossYubanImgUrl\\\":\\\"\\\",\\\"ossLuomaiLabelImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/luomai_label/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossPrickImgUrl\\\":\\\"\\\"}\",\"tongueProcessImages1\":null,\"tongueColor\":\"淡红舌\",\"tongueColorDesc\":\"【淡红舌】正常人舌色，舌质呈现润泽红活。</br>【病理意义】正常舌色，或者处于病情轻浅阶段。</br>\",\"tongueMossColor\":\"白苔\",\"tongueMossColorDesc\":\"【白苔】舌苔呈现白色。</br>【病理意义】正常苔色。</br>\",\"tongueMossNature\":\"薄苔\",\"tongueMossNatureDesc\":\"【薄苔】透过舌苔能隐隐地见到舌体。 </br>【病理意义】脾胃状态良好。</br>\",\"tongueShap\":\"齿痕\",\"tongueShapDesc\":\"【齿痕】舌体边缘有牙齿压迫的痕迹。</br>【病理意义】脾虚、气虚或体内有湿。</br>\",\"tongueBodyfluid\":\"\",\"tongueBodyfluidDesc\":\"【润】舌苔干湿适中，不滑不燥为润苔。 </br>【病理意义】舌苔水分正常。</br>\",\"tongueVein\":\"舌下正常\",\"tongueVeinDesc\":\"【舌下正常】正常情况下，其管径不超过2.7mm,长度不超过舌尖至舌下肉阜连线的3/5,颜色暗红，无分支和紫点。脉络无怒张、紧束、弯曲、增生，排列有序，绝大多数为单支，极少有双支出现。</br>【临床表现】舌下络脉正常。</br>\",\"faceFrontImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/f1a3ae630da945b5a78dc013e9d915fb-tmp_445d54aa67689e29f9a54c757abc1c69bad16f834eac7d84.jpg\",\"faceLeftImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/e1d71c630f174ab69236428e721a6aca-tmp_c0a0bf082e9e0f3c2676ecae84df531cb7426fb21848d3e1.jpg\",\"faceRightImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/d867390967b64836bd7044d2ecace326-tmp_6ff305bf982195f64fe58701a28c19a0c6a75f70358452e3.jpg\",\"faceMianse\":\"{\\\"code\\\":\\\"m-10\\\",\\\"description\\\":\\\"【正常面色】面色红黄隐隐，明润含蓄。</br>\\\\n【病理意义】神气、胃气的正常色泽表现。</br>\\\",\\\"name\\\":\\\"面色红黄隐隐，明润含蓄\\\"}\",\"faceMianseName\":null,\"faceMianseDesc\":null,\"faceMianseUrl\":null,\"faceMianseIsRed\":null,\"faceMainColor\":\"{\\\"code\\\":\\\"m-14\\\",\\\"description\\\":\\\"【正常色】面色红黄隐隐，明润含蓄。</br>\\\\n【病理意义】神气、胃气的正常色泽表现。</br>\\\\n\\\",\\\"name\\\":\\\"正常色\\\"}\",\"faceMainColorName\":null,\"faceMainColorDesc\":null,\"faceMainColorUrl\":null,\"faceMainColorIsRed\":null,\"faceGloss\":\"{\\\"code\\\":\\\"m-21\\\",\\\"description\\\":\\\"【少量】面色稍许晦暗、少泽。</br>\\\\n【病理意义】为脏腑精气稍衰，介于病轻和病重之间。</br>\\\\n\\\",\\\"name\\\":\\\"少量\\\"}\",\"faceGlossName\":null,\"faceGlossDesc\":null,\"faceGlossUrl\":null,\"faceGlossIsRed\":null,\"faceDarkCircles\":\"{\\\"code\\\":\\\"ml-30,mr-30\\\",\\\"description\\\":\\\"【左眼正常】左眼眼圈不发黑，眼眶颜色正常。</br>\\\\n【病理意义】左眼无黑眼圈。</br>\\\\n,【右眼正常】右眼眼圈不发黑，眼眶颜色正常。</br>\\\\n【病理意义】右眼无黑眼圈。</br>\\\\n\\\",\\\"name\\\":\\\"左眼正常,右眼正常\\\"}\",\"faceDarkCirclesName\":null,\"faceDarkCirclesDesc\":null,\"faceDarkCirclesLeftUrl\":null,\"faceDarkCirclesRightUrl\":null,\"faceDarkCirclesIsRed\":null,\"faceLipColor\":\"{\\\"code\\\":\\\"m-40\\\",\\\"description\\\":\\\"【正常】唇色是明亮、润泽、含蓄的红色。</br>\\\\n【病理意义】胃气充足，气血调匀的表现。</br>\\\",\\\"name\\\":\\\"正常\\\"}\",\"faceLipColorName\":null,\"faceLipColorDesc\":null,\"faceLipColorUrl\":null,\"faceLipColorIsRed\":null,\"faceEyes\":\"{\\\"code\\\":\\\"m-51\\\",\\\"description\\\":\\\"【少神】两目乏神，动作迟缓，眼神晦暗。</br>\\\\n【病理意义】是正气不足的表现，见于虚证或轻病。</br>\\\\n\\\",\\\"name\\\":\\\"少神\\\"}\",\"faceEyesName\":null,\"faceEyesDesc\":null,\"faceEyesUrl\":null,\"faceEyesIsRed\":null,\"faceEyesColor\":\"{\\\"code\\\":\\\"ml-55,mr-55\\\",\\\"description\\\":\\\"【左眼正常】左眼黑白分明，精彩内含。</br>\\\\n【病理意义】精充、气足、神旺的表现。</br>\\\\n,【右眼正常】右眼黑白分明，精彩内含。</br>\\\\n【病理意义】精充、气足、神旺的表现。</br>\\\\n\\\",\\\"name\\\":\\\"左眼正常,右眼正常\\\"}\",\"faceEyesColorName\":null,\"faceEyesColorDesc\":null,\"faceEyesColorLeftUrl\":null,\"faceEyesColorRightUrl\":null,\"faceEyesColorIsRed\":null,\"faceCheekbones\":\"{\\\"code\\\":\\\"m-60\\\",\\\"description\\\":\\\"【无】面部两颧未出现红色，颜色跟正常面色一致。</br>\\\\n【病理意义】胃气充足，气血调匀的表现。</br>\\\\n\\\",\\\"name\\\":\\\"无\\\"}\",\"faceCheekbonesName\":null,\"faceCheekbonesDesc\":null,\"faceCheekbonesUrl\":null,\"faceCheekbonesIsRed\":null,\"faceNasalFold\":\"{\\\"code\\\":\\\"m-70\\\",\\\"description\\\":\\\"【无】鼻根部未出现横纹沟。</br>\\\\n【病理意义】横纹沟代表鼻褶心征。代表动脉硬化，冠动脉供血不足。</br>\\\",\\\"name\\\":\\\"无\\\"}\",\"faceNasalFoldName\":null,\"faceNasalFoldDesc\":null,\"faceNasalFoldUrl\":null,\"faceNasalFoldIsRed\":null,\"faceGlabella\":\"{\\\"code\\\":\\\"m-80\\\",\\\"description\\\":\\\"【无】眉间或鼻柱之间不发青，无青色。</br>\\\\n【病理意义】眉间无青色。</br>\\\\n\\\",\\\"name\\\":\\\"无\\\"}\",\"faceGlabellaName\":null,\"faceGlabellaDesc\":null,\"faceGlabellaUrl\":null,\"faceGlabellaIsRed\":null,\"faceSkinLesions\":\"[{\\\"code\\\":\\\"m-90\\\",\\\"description\\\":\\\"【无】面部未出现色素痣、皮下颗粒物或疑似皮肤病等。</br>\\\\n【病理意义】面部正常。</br>\\\\n\\\",\\\"name\\\":\\\"无\\\",\\\"simpleDescription\\\":\\\"【无】面部未出现色素痣、皮下颗粒物或疑似皮肤病等。</br>\\\\n【病理意义】面部正常。</br>\\\\n\\\"}]\",\"faceSkinLesionsName\":null,\"faceSkinLesionsDesc\":null,\"faceSkinLesionsUrl\":null,\"faceSkinLesionsIsRed\":null,\"faceEarColor\":\"{\\\"code\\\":\\\"el-70,er-70\\\",\\\"description\\\":\\\"【左耳色正常】左耳颜色微黄而红润。</br>\\\\n【病理意义】左耳颜色正常。</br>\\\\n,【右耳色正常】右耳颜色微黄而红润。</br>\\\\n【病理意义】右耳颜色正常。</br>\\\\n\\\",\\\"name\\\":\\\"左耳色正常,右耳色正常\\\"}\",\"faceEarColorName\":null,\"faceEarColorDesc\":null,\"faceEarColorLeftUrl\":null,\"faceEarColorRightUrl\":null,\"faceEarColorIsRed\":null,\"faceUricularFold\":\"{\\\"code\\\":\\\"fl-70,fr-70\\\",\\\"description\\\":\\\"【左耳正常】左耳耳垂上没有褶皱。</br>\\\\n【病理意义】左耳无耳褶心征</br>\\\\n,【右耳正常】右耳耳垂上没有褶皱。</br>\\\\n【病理意义】右耳无耳褶心征</br>\\\\n\\\",\\\"name\\\":\\\"左耳正常,右耳正常\\\"}\",\"faceUricularFoldName\":null,\"faceUricularFoldDesc\":null,\"faceUricularFoldLeftUrl\":null,\"faceUricularFoldRightUrl\":null,\"faceUricularFoldIsRed\":null,\"faceCaluateImage\":\"{\\\"doubleEye\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/double_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"faceSplit\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"leftEars\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"leftEye\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rectLip\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/rect_lip/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rightEars\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rightEye\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\"}\",\"faceCaluateImage1\":null,\"faceProcessImage\":\"{\\\"faceAbnormal\\\":\\\"\\\",\\\"faceAreaLabel\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/face_area_label/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"faceBzxz\\\":\\\"\\\",\\\"leftFaceEzxz\\\":\\\"\\\",\\\"leftSidefaceAbnormal\\\":\\\"\\\",\\\"mainMosaic\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/main_mosaic/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rightFaceEzxz\\\":\\\"\\\",\\\"rightSidefaceAbnormal\\\":\\\"\\\"}\",\"faceProcessImage1\":null,\"diagnosticResults\":\" 积食（积食化热）\",\"mainPerformance\":\"[{\\\"items\\\":[],\\\"message\\\":\\\" 积食（积食化热）主要表现为不思乳食，口干，胃腹胀满，腹部灼热。或见平时好动，心烦易怒，夜卧不安，手脚心热，小便黄，大便臭秽或秘结。\\\"}]\",\"occurReason\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"1、母乳和食物聚集在体内时间长了，积食产生热性病变，从而损伤了津液。饮食不节制、损伤了脾胃，脾脏运化功能失常，体内的气运行不畅通，所以不喜欢喝母乳和吃东西，肚子胀；食物聚集在胃肠导致热性病变，消耗损伤了津液，所以口干，腹部像火烧着、烫着那样热，两手心与两脚心有发热感觉，小便比较黄，大便特别臭或大便干涩拉不下来；心神被扰动所以心里烦躁或烦闷、容易生气，晚上睡觉的时候出现不安稳和不踏实；舌头颜色红，舌苔颜色黄，舌苔质地比较腻。\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"2、食物堆积胃肠久了会化热，热会使血行加速，气血扰动则心神被扰，易出现嘴唇红、手足心热的症状。\\\"}]\",\"predisposition\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"无\\\"}]\",\"highRiskDisease\":null,\"eatingHabits\":\"[{\\\"content\\\":\\\"蒲公英与焦三仙和炒鸡内金一同熬水\\\",\\\"title\\\":\\\"宜食\\\"}]\",\"dietRehabilitation\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"蒲公英与焦三仙和炒鸡内金一同熬水\\\"}]\",\"dietaryRecuperate\":null,\"sportsHealthCare\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"蒲公英与焦三仙和炒鸡内金一同熬水\\\"}]\",\"drugHealthCare\":\"[{\\\"items\\\":[{\\\"desc\\\":\\\"【厂家】葵花药业<br> 【用法用量】口服。小儿一次0.5g，每日3次周岁内酌减。<br> 【产品成分】鸡内金、六神曲。辅料为：蔗糖、玉米淀粉。<br> 【功能主治】健脾开胃，消食化积。用于小儿因脾胃不和引起的食积胀满，饮食停滞，呕吐泄泻。<br>\\\",\\\"name\\\":\\\"小儿复方鸡内金散\\\",\\\"photo\\\":\\\"https://labelsys-images.oss-cn-hangzhou.aliyuncs.com/healthcare/葵花药业/小儿复方鸡内金散.jpg\\\"}],\\\"message\\\":\\\"\\\"},{\\\"items\\\":[{\\\"desc\\\":\\\"【厂家】广州王老吉药业股份有限公司<br> 【用法用量】开始冲服。一次3.5~7克，一日3次。<br> 【产品成分】薏苡仁、稻芽、山楂、淡竹叶、钩藤、蝉蜕、甘草。辅料为蔗糖粉。<br> 【功能主治】开胃消滞，清热定惊。用于小儿积滞化热，消化不良，不思饮食，烦躁易惊，夜寐不安，大便不畅，小便短赤。<br>\\\",\\\"name\\\":\\\"小儿七星茶颗粒\\\",\\\"photo\\\":\\\"https://labelsys-images.oss-cn-hangzhou.aliyuncs.com/healthcare/葵花药业/小儿七星茶颗粒.png\\\"}],\\\"message\\\":\\\"\\\"}]\",\"massageHealthCare\":\"[{\\\"bottom\\\":\\\"\\\",\\\"desc\\\":\\\"\\\",\\\"message\\\":\\\"1、逆运内八卦（调理因积食导致的咳嗽)\\\",\\\"photo\\\":\\\"\\\"},{\\\"bottom\\\":\\\"\\\",\\\"desc\\\":\\\"\\\",\\\"message\\\":\\\"2、以上取穴，加清天河水、清大肠。烦躁不安加清心平肝，揉曲池。\\\",\\\"photo\\\":\\\"\\\"}]\",\"tongueScore\":\"3.04\",\"tongueTips\":\"建议您一周后再次做舌诊检测。\",\"recuperates\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"1、避免食物种类过杂\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"每次饮食的种类不宜过多，四到五种即可，如若想吃多种类食物，可延长进食的时间间隔，注意营养均衡。\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"2、适量进食勿过多\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"小儿喂食应适量，吃饱就好，不是吃的越多越好，一次性吃太多的食物容易导致积食，请家长把握好分寸。\\\"}]\",\"illProbability\":\"{\\\"scope\\\":\\\"3.04\\\",\\\"tip\\\":\\\"建议您一周后再次做舌诊检测。\\\"}\",\"solarTerms\":\"寒露\",\"createTime\":\"2023-10-23 15:08:03\",\"updateTime\":\"2023-10-23 17:47:13\",\"tongueColorList\":null,\"tongueColorDescription\":\"<span style=\\\"color:#F25D4C\\\">淡红舌</span>、白苔、薄苔、<span style=\\\"color:#F25D4C\\\">齿痕</span>、舌下正常\",\"faceColorList\":[{\"name\":\"面色\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">面色红黄隐隐，明润含蓄</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"主色\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">正常色</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"光泽\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">少量</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"眼神\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">少神</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"目色\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">左眼正常</span>;<span style=\\\"color:#F25D4C\\\">右眼正常</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"两颧红\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">无</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"眉间/鼻柱青色\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">无</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"耳色\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">左耳色正常</span>;<span style=\\\"color:#F25D4C\\\">右耳色正常</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"耳褶\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">左耳正常</span>;<span style=\\\"color:#F25D4C\\\">右耳正常</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"鼻褶\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"无\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"唇色\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"正常\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"黑眼圈\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">左眼正常</span>;<span style=\\\"color:#F25D4C\\\">右眼正常</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":\"面部皮损\",\"key\":null,\"field\":null,\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":\"<span style=\\\"color:#F25D4C\\\">无</span>\",\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null}],\"userShopDetailVO\":{\"id\":null,\"uid\":2688338885,\"isContract\":null,\"contractPhoto1\":null,\"contractPhoto2\":null,\"contractPhoto3\":null,\"contractPhoto4\":null,\"shopName\":\"测试600\",\"jobNumber\":null,\"username\":null,\"mobile\":\"18291891994\",\"mobileList\":null,\"uuid\":null,\"checkMobile\":null,\"managerMobile\":null,\"managerPassTime\":null,\"passTime\":null,\"shopType\":null,\"province\":\"上海市\",\"city\":\"上海市\",\"district\":\"黄浦区\",\"status\":null,\"street\":\"半淞园路街道\",\"addressDetail\":\"盛夏路560号\",\"shopAddressDetail\":\"上海市上海市黄浦区半淞园路街道盛夏路560号\",\"catena\":null,\"catenaNumber\":null,\"idFacePhoto\":null,\"idBackPhoto\":null,\"licensePhoto\":null,\"shopPhoto\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/de41e8271faa418fb051ef83cd568817-tmp_5578e4961f07e5048567a3688d7843c8e0575d8b73a2033f.jpg\",\"goodsPhoto\":null,\"checkUid\":null,\"checkRealName\":\"武帅帅\",\"belongArea\":null,\"checkAreaLayer\":null,\"managerRealName\":\"张莹\",\"managerAreaLayer\":null,\"managerUid\":null,\"managerOrCheckAreaLayer\":null,\"managerOrCheckName\":null,\"hisManagerAreaLayer\":null,\"hisManagerRealName\":null,\"hisManagerUid\":null,\"cityName\":null,\"cityUpdateTime\":null,\"cityStatus\":null,\"applyStatus\":null,\"areaName\":null,\"areaUpdateTime\":null,\"areaStatus\":null,\"headName\":null,\"headUpdateTime\":null,\"headStatus\":null,\"loginTime\":null,\"userStatus\":2,\"createTime\":null,\"updateTime\":null,\"isSameCardCorporate\":null,\"officeCode\":null,\"userAddressVOList\":null,\"shopId\":null,\"homePageElementList\":null,\"isDefaultSort\":null,\"fleeRecord\":null,\"isFleeRecord\":null,\"area\":\"上海省公司\",\"officeName\":\"上海办\",\"bname\":null,\"userDingQueryVO\":null,\"userIdList\":null,\"riskLevel\":null,\"remark\":null,\"riskRemark\":null,\"freezeStatus\":0,\"riskUserDto\":null,\"shopBname\":null},\"tongueColorAttrList\":null,\"tongueColorInfoList\":null,\"tongueMossAttrList\":null,\"tongueMossColorInfoList\":null,\"tongueNatureAttrList\":null,\"tongueShapAttrList\":null,\"tongueBodyAttrList\":null,\"tongueVeinAttrList\":null,\"aiFaceItemVOList\":null,\"aiFaceVOList\":null,\"aiTongueItemVOList\":null,\"aiTongueVOList\":null,\"aiReportItemVOList\":null}";
	public static String str2 = "{\"pdfUrl\":null,\"openid\":null,\"id\":86,\"name\":\"测试2\",\"genderDesc\":null,\"gender\":1,\"birthday\":\"2010-07-21\",\"age\":13.3,\"reportId\":\"1166030276602761216\",\"tongueAttrs\":\"[{\\\"attrName\\\":\\\"舌色程度\\\",\\\"attrValue\\\":\\\"舌边淡\\\"},{\\\"attrName\\\":\\\"舌根苔色\\\",\\\"attrValue\\\":\\\"舌根白苔\\\"},{\\\"attrName\\\":\\\"瘀点数量\\\",\\\"attrValue\\\":\\\"0\\\"},{\\\"attrName\\\":\\\"瘀点程度\\\",\\\"attrValue\\\":\\\"全舌无瘀点\\\"},{\\\"attrName\\\":\\\"齿痕\\\",\\\"attrValue\\\":\\\"轻度齿痕\\\"}]\",\"tongueBatchNo\":\"1166030277122854912\",\"tongueStep\":3,\"tongueStepStatus\":1,\"detectionTypeDesc\":\"脾胃病\",\"tongueProcessExtraImage\":\"{}\",\"tongueProcessExtraImage1\":null,\"constitutionNames\":\"通用结论\",\"faceBatchNo\":\"1166030285750538240\",\"templateId\":1705,\"checkIndex\":null,\"checkId\":14,\"userInfoId\":8,\"step\":null,\"stepStatus\":null,\"faceStep\":2,\"faceStepStatus\":1,\"tonguePreFailMsg\":\"\",\"facePreFailMsg\":\"\",\"detectionType\":\"C08.D00\",\"detectionTypeName\":null,\"questions\":\"[{\\\"group\\\":\\\"\\\",\\\"types\\\":[{\\\"code\\\":\\\"IS0105\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"形体肌肤\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"唇红\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"霰粒肿\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"面色少华\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"形体消瘦\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"眼皮浮肿\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"面色萎黄\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"皮肤粗糙\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"嘴角溃烂\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"形体肥胖\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"鼻梁有青筋\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"眼睑发紫发红\\\"}],\\\"sort\\\":1},{\\\"code\\\":\\\"IS0106\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"头身不适\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"呃逆\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"呕吐\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腹胀\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腹痛\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"黄痰\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"肚子硬\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"脘痞胀\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"易感冒\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腿抽筋\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"倦怠乏力\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"面色晦暗\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"牙龈出血\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腹痛欲泻\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"咽喉红肿\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"头发易打结\\\"}],\\\"sort\\\":2},{\\\"code\\\":\\\"IS0107\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"寒热\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"盗汗\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"手足心热\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"身体发热\\\"}],\\\"sort\\\":3},{\\\"code\\\":\\\"IS0108\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"饮食\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"口臭\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"口渴\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"贪吃\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"食欲不振\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"喜欢吃凉东西\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"消化系统紊乱\\\"}],\\\"sort\\\":4},{\\\"code\\\":\\\"IS0109\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"二便\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"排便不爽\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"大便干结\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"小便短黄\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"便秘或腹泻\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"大便有臭鸡蛋味\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"大便夹杂不消化食物\\\"}],\\\"sort\\\":5},{\\\"code\\\":\\\"IS0110\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"情志睡眠\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"嗳气\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"烦躁\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"失眠\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"磨牙\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"善悲易哭\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"急躁易怒\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"睡觉露睛\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"夜眠不安\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"燥扰不宁\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"睡觉流口水\\\"}],\\\"sort\\\":6}]}]\",\"answers\":\"[{\\\"answerCode\\\":\\\"腹胀\\\",\\\"code\\\":\\\"IS0106\\\",\\\"questionIndex\\\":2},{\\\"answerCode\\\":\\\"唇红\\\",\\\"code\\\":\\\"IS0105\\\",\\\"questionIndex\\\":1},{\\\"answerCode\\\":\\\"手足心热\\\",\\\"code\\\":\\\"IS0107\\\",\\\"questionIndex\\\":3},{\\\"answerCode\\\":\\\"食欲不振\\\",\\\"code\\\":\\\"IS0108\\\",\\\"questionIndex\\\":4},{\\\"answerCode\\\":\\\"便秘或腹泻\\\",\\\"code\\\":\\\"IS0109\\\",\\\"questionIndex\\\":5},{\\\"answerCode\\\":\\\"失眠\\\",\\\"code\\\":\\\"IS0110\\\",\\\"questionIndex\\\":6}]\",\"reportLockStatus\":0,\"tongueSurfaceImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/f89cc8108b47471994f37e834eab1fcd-tmp_be0673ac3c80cf3597be30659f7b70d00b73a0489851eab3.jpg\",\"tongueBackImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/9a27ae484edf4abab461187a08bd3f27-tmp_1ee8eebbe1ec0a7394569b85a03d35c080e1950f0556bca8.jpg\",\"tongueSplitSurfaceImg\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"tongueSplitSurfaceImg1\":\"\",\"tongueSplitBackImg\":\"https://td-platform-split-back.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"tongueSplitBackImg1\":\"\",\"tongueProcessImages\":\"{\\\"ossYudianImgUrl\\\":\\\"\\\",\\\"ossSheseImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/shese/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossShexingImgUrl\\\":\\\"\\\",\\\"ossToothtraceImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/toothtrace/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossBoluoImgUrl\\\":\\\"\\\",\\\"ossLiewenImgUrl\\\":\\\"\\\",\\\"ossTaiseImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/taise/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossYubanImgUrl\\\":\\\"\\\",\\\"ossLuomaiLabelImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/luomai_label/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossPrickImgUrl\\\":\\\"\\\"}\",\"tongueProcessImages1\":null,\"tongueColor\":\"淡红舌\",\"tongueColorDesc\":\"【淡红舌】正常人舌色，舌质呈现润泽红活。</br>【病理意义】正常舌色，或者处于病情轻浅阶段。</br>\",\"tongueMossColor\":\"白苔\",\"tongueMossColorDesc\":\"【白苔】舌苔呈现白色。</br>【病理意义】正常苔色。</br>\",\"tongueMossNature\":\"薄苔\",\"tongueMossNatureDesc\":\"【薄苔】透过舌苔能隐隐地见到舌体。 </br>【病理意义】脾胃状态良好。</br>\",\"tongueShap\":\"齿痕\",\"tongueShapDesc\":\"【齿痕】舌体边缘有牙齿压迫的痕迹。</br>【病理意义】脾虚、气虚或体内有湿。</br>\",\"tongueBodyfluid\":\"\",\"tongueBodyfluidDesc\":\"【润】舌苔干湿适中，不滑不燥为润苔。 </br>【病理意义】舌苔水分正常。</br>\",\"tongueVein\":\"舌下正常\",\"tongueVeinDesc\":\"【舌下正常】正常情况下，其管径不超过2.7mm,长度不超过舌尖至舌下肉阜连线的3/5,颜色暗红，无分支和紫点。脉络无怒张、紧束、弯曲、增生，排列有序，绝大多数为单支，极少有双支出现。</br>【临床表现】舌下络脉正常。</br>\",\"faceFrontImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/f1a3ae630da945b5a78dc013e9d915fb-tmp_445d54aa67689e29f9a54c757abc1c69bad16f834eac7d84.jpg\",\"faceLeftImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/e1d71c630f174ab69236428e721a6aca-tmp_c0a0bf082e9e0f3c2676ecae84df531cb7426fb21848d3e1.jpg\",\"faceRightImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/d867390967b64836bd7044d2ecace326-tmp_6ff305bf982195f64fe58701a28c19a0c6a75f70358452e3.jpg\",\"faceMianse\":\"{\\\"code\\\":\\\"m-10\\\",\\\"description\\\":\\\"【正常面色】面色红黄隐隐，明润含蓄。</br>\\\\n【病理意义】神气、胃气的正常色泽表现。</br>\\\",\\\"name\\\":\\\"面色红黄隐隐，明润含蓄\\\"}\",\"faceMianseName\":null,\"faceMianseDesc\":null,\"faceMianseUrl\":null,\"faceMianseIsRed\":null,\"faceMainColor\":\"{\\\"code\\\":\\\"m-14\\\",\\\"description\\\":\\\"【正常色】面色红黄隐隐，明润含蓄。</br>\\\\n【病理意义】神气、胃气的正常色泽表现。</br>\\\\n\\\",\\\"name\\\":\\\"正常色\\\"}\",\"faceMainColorName\":null,\"faceMainColorDesc\":null,\"faceMainColorUrl\":null,\"faceMainColorIsRed\":null,\"faceGloss\":\"{\\\"code\\\":\\\"m-21\\\",\\\"description\\\":\\\"【少量】面色稍许晦暗、少泽。</br>\\\\n【病理意义】为脏腑精气稍衰，介于病轻和病重之间。</br>\\\\n\\\",\\\"name\\\":\\\"少量\\\"}\",\"faceGlossName\":null,\"faceGlossDesc\":null,\"faceGlossUrl\":null,\"faceGlossIsRed\":null,\"faceDarkCircles\":\"{\\\"code\\\":\\\"ml-30,mr-30\\\",\\\"description\\\":\\\"【左眼正常】左眼眼圈不发黑，眼眶颜色正常。</br>\\\\n【病理意义】左眼无黑眼圈。</br>\\\\n,【右眼正常】右眼眼圈不发黑，眼眶颜色正常。</br>\\\\n【病理意义】右眼无黑眼圈。</br>\\\\n\\\",\\\"name\\\":\\\"左眼正常,右眼正常\\\"}\",\"faceDarkCirclesName\":null,\"faceDarkCirclesDesc\":null,\"faceDarkCirclesLeftUrl\":null,\"faceDarkCirclesRightUrl\":null,\"faceDarkCirclesIsRed\":null,\"faceLipColor\":\"{\\\"code\\\":\\\"m-40\\\",\\\"description\\\":\\\"【正常】唇色是明亮、润泽、含蓄的红色。</br>\\\\n【病理意义】胃气充足，气血调匀的表现。</br>\\\",\\\"name\\\":\\\"正常\\\"}\",\"faceLipColorName\":null,\"faceLipColorDesc\":null,\"faceLipColorUrl\":null,\"faceLipColorIsRed\":null,\"faceEyes\":\"{\\\"code\\\":\\\"m-51\\\",\\\"description\\\":\\\"【少神】两目乏神，动作迟缓，眼神晦暗。</br>\\\\n【病理意义】是正气不足的表现，见于虚证或轻病。</br>\\\\n\\\",\\\"name\\\":\\\"少神\\\"}\",\"faceEyesName\":null,\"faceEyesDesc\":null,\"faceEyesUrl\":null,\"faceEyesIsRed\":null,\"faceEyesColor\":\"{\\\"code\\\":\\\"ml-55,mr-55\\\",\\\"description\\\":\\\"【左眼正常】左眼黑白分明，精彩内含。</br>\\\\n【病理意义】精充、气足、神旺的表现。</br>\\\\n,【右眼正常】右眼黑白分明，精彩内含。</br>\\\\n【病理意义】精充、气足、神旺的表现。</br>\\\\n\\\",\\\"name\\\":\\\"左眼正常,右眼正常\\\"}\",\"faceEyesColorName\":null,\"faceEyesColorDesc\":null,\"faceEyesColorLeftUrl\":null,\"faceEyesColorRightUrl\":null,\"faceEyesColorIsRed\":null,\"faceCheekbones\":\"{\\\"code\\\":\\\"m-60\\\",\\\"description\\\":\\\"【无】面部两颧未出现红色，颜色跟正常面色一致。</br>\\\\n【病理意义】胃气充足，气血调匀的表现。</br>\\\\n\\\",\\\"name\\\":\\\"无\\\"}\",\"faceCheekbonesName\":null,\"faceCheekbonesDesc\":null,\"faceCheekbonesUrl\":null,\"faceCheekbonesIsRed\":null,\"faceNasalFold\":\"{\\\"code\\\":\\\"m-70\\\",\\\"description\\\":\\\"【无】鼻根部未出现横纹沟。</br>\\\\n【病理意义】横纹沟代表鼻褶心征。代表动脉硬化，冠动脉供血不足。</br>\\\",\\\"name\\\":\\\"无\\\"}\",\"faceNasalFoldName\":null,\"faceNasalFoldDesc\":null,\"faceNasalFoldUrl\":null,\"faceNasalFoldIsRed\":null,\"faceGlabella\":\"{\\\"code\\\":\\\"m-80\\\",\\\"description\\\":\\\"【无】眉间或鼻柱之间不发青，无青色。</br>\\\\n【病理意义】眉间无青色。</br>\\\\n\\\",\\\"name\\\":\\\"无\\\"}\",\"faceGlabellaName\":null,\"faceGlabellaDesc\":null,\"faceGlabellaUrl\":null,\"faceGlabellaIsRed\":null,\"faceSkinLesions\":\"[{\\\"code\\\":\\\"m-90\\\",\\\"description\\\":\\\"【无】面部未出现色素痣、皮下颗粒物或疑似皮肤病等。</br>\\\\n【病理意义】面部正常。</br>\\\\n\\\",\\\"name\\\":\\\"无\\\",\\\"simpleDescription\\\":\\\"【无】面部未出现色素痣、皮下颗粒物或疑似皮肤病等。</br>\\\\n【病理意义】面部正常。</br>\\\\n\\\"}]\",\"faceSkinLesionsName\":null,\"faceSkinLesionsDesc\":null,\"faceSkinLesionsUrl\":null,\"faceSkinLesionsIsRed\":null,\"faceEarColor\":\"{\\\"code\\\":\\\"el-70,er-70\\\",\\\"description\\\":\\\"【左耳色正常】左耳颜色微黄而红润。</br>\\\\n【病理意义】左耳颜色正常。</br>\\\\n,【右耳色正常】右耳颜色微黄而红润。</br>\\\\n【病理意义】右耳颜色正常。</br>\\\\n\\\",\\\"name\\\":\\\"左耳色正常,右耳色正常\\\"}\",\"faceEarColorName\":null,\"faceEarColorDesc\":null,\"faceEarColorLeftUrl\":null,\"faceEarColorRightUrl\":null,\"faceEarColorIsRed\":null,\"faceUricularFold\":\"{\\\"code\\\":\\\"fl-70,fr-70\\\",\\\"description\\\":\\\"【左耳正常】左耳耳垂上没有褶皱。</br>\\\\n【病理意义】左耳无耳褶心征</br>\\\\n,【右耳正常】右耳耳垂上没有褶皱。</br>\\\\n【病理意义】右耳无耳褶心征</br>\\\\n\\\",\\\"name\\\":\\\"左耳正常,右耳正常\\\"}\",\"faceUricularFoldName\":null,\"faceUricularFoldDesc\":null,\"faceUricularFoldLeftUrl\":null,\"faceUricularFoldRightUrl\":null,\"faceUricularFoldIsRed\":null,\"faceCaluateImage\":\"{\\\"doubleEye\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/double_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"faceSplit\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"leftEars\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"leftEye\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rectLip\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/rect_lip/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rightEars\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rightEye\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\"}\",\"faceCaluateImage1\":null,\"faceProcessImage\":\"{\\\"faceAbnormal\\\":\\\"\\\",\\\"faceAreaLabel\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/face_area_label/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"faceBzxz\\\":\\\"\\\",\\\"leftFaceEzxz\\\":\\\"\\\",\\\"leftSidefaceAbnormal\\\":\\\"\\\",\\\"mainMosaic\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/main_mosaic/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rightFaceEzxz\\\":\\\"\\\",\\\"rightSidefaceAbnormal\\\":\\\"\\\"}\",\"faceProcessImage1\":null,\"diagnosticResults\":\" 积食（积食化热）\",\"mainPerformance\":\"[{\\\"items\\\":[],\\\"message\\\":\\\" 积食（积食化热）主要表现为不思乳食，口干，胃腹胀满，腹部灼热。或见平时好动，心烦易怒，夜卧不安，手脚心热，小便黄，大便臭秽或秘结。\\\"}]\",\"occurReason\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"1、母乳和食物聚集在体内时间长了，积食产生热性病变，从而损伤了津液。饮食不节制、损伤了脾胃，脾脏运化功能失常，体内的气运行不畅通，所以不喜欢喝母乳和吃东西，肚子胀；食物聚集在胃肠导致热性病变，消耗损伤了津液，所以口干，腹部像火烧着、烫着那样热，两手心与两脚心有发热感觉，小便比较黄，大便特别臭或大便干涩拉不下来；心神被扰动所以心里烦躁或烦闷、容易生气，晚上睡觉的时候出现不安稳和不踏实；舌头颜色红，舌苔颜色黄，舌苔质地比较腻。\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"2、食物堆积胃肠久了会化热，热会使血行加速，气血扰动则心神被扰，易出现嘴唇红、手足心热的症状。\\\"}]\",\"predisposition\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"无\\\"}]\",\"highRiskDisease\":null,\"eatingHabits\":\"[{\\\"content\\\":\\\"蒲公英与焦三仙和炒鸡内金一同熬水\\\",\\\"title\\\":\\\"宜食\\\"}]\",\"dietRehabilitation\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"蒲公英与焦三仙和炒鸡内金一同熬水\\\"}]\",\"dietaryRecuperate\":null,\"sportsHealthCare\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"蒲公英与焦三仙和炒鸡内金一同熬水\\\"}]\",\"drugHealthCare\":\"[{\\\"items\\\":[{\\\"desc\\\":\\\"【厂家】葵花药业<br> 【用法用量】口服。小儿一次0.5g，每日3次周岁内酌减。<br> 【产品成分】鸡内金、六神曲。辅料为：蔗糖、玉米淀粉。<br> 【功能主治】健脾开胃，消食化积。用于小儿因脾胃不和引起的食积胀满，饮食停滞，呕吐泄泻。<br>\\\",\\\"name\\\":\\\"小儿复方鸡内金散\\\",\\\"photo\\\":\\\"https://labelsys-images.oss-cn-hangzhou.aliyuncs.com/healthcare/葵花药业/小儿复方鸡内金散.jpg\\\"}],\\\"message\\\":\\\"\\\"},{\\\"items\\\":[{\\\"desc\\\":\\\"【厂家】广州王老吉药业股份有限公司<br> 【用法用量】开始冲服。一次3.5~7克，一日3次。<br> 【产品成分】薏苡仁、稻芽、山楂、淡竹叶、钩藤、蝉蜕、甘草。辅料为蔗糖粉。<br> 【功能主治】开胃消滞，清热定惊。用于小儿积滞化热，消化不良，不思饮食，烦躁易惊，夜寐不安，大便不畅，小便短赤。<br>\\\",\\\"name\\\":\\\"小儿七星茶颗粒\\\",\\\"photo\\\":\\\"https://labelsys-images.oss-cn-hangzhou.aliyuncs.com/healthcare/葵花药业/小儿七星茶颗粒.png\\\"}],\\\"message\\\":\\\"\\\"}]\",\"massageHealthCare\":\"[{\\\"bottom\\\":\\\"\\\",\\\"desc\\\":\\\"\\\",\\\"message\\\":\\\"1、逆运内八卦（调理因积食导致的咳嗽)\\\",\\\"photo\\\":\\\"\\\"},{\\\"bottom\\\":\\\"\\\",\\\"desc\\\":\\\"\\\",\\\"message\\\":\\\"2、以上取穴，加清天河水、清大肠。烦躁不安加清心平肝，揉曲池。\\\",\\\"photo\\\":\\\"\\\"}]\",\"tongueScore\":\"3.04\",\"tongueTips\":\"建议您一周后再次做舌诊检测。\",\"recuperates\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"1、避免食物种类过杂\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"每次饮食的种类不宜过多，四到五种即可，如若想吃多种类食物，可延长进食的时间间隔，注意营养均衡。\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"2、适量进食勿过多\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"小儿喂食应适量，吃饱就好，不是吃的越多越好，一次性吃太多的食物容易导致积食，请家长把握好分寸。\\\"}]\",\"illProbability\":\"{\\\"scope\\\":\\\"3.04\\\",\\\"tip\\\":\\\"建议您一周后再次做舌诊检测。\\\"}\",\"solarTerms\":\"寒露\",\"createTime\":\"2023-10-23 15:08:03\",\"updateTime\":\"2023-10-23 17:47:13\",\"tongueColorList\":null,\"tongueColorDescription\":null,\"faceColorList\":null,\"userShopDetailVO\":null,\"tongueColorAttrList\":null,\"tongueColorInfoList\":null,\"tongueMossAttrList\":null,\"tongueMossColorInfoList\":null,\"tongueNatureAttrList\":null,\"tongueShapAttrList\":null,\"tongueBodyAttrList\":null,\"tongueVeinAttrList\":null,\"aiFaceItemVOList\":null,\"aiFaceVOList\":null,\"aiTongueItemVOList\":[{\"name\":null,\"key\":\"舌色\",\"field\":\"淡红舌\",\"value\":\"淡红舌\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"苔色\",\"field\":\"白苔\",\"value\":\"白苔\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"点刺\",\"field\":\"未见点刺\",\"value\":\"未见点刺\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"瘀点\",\"field\":\"未见瘀点\",\"value\":\"未见瘀点\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"瘀斑\",\"field\":\"未见瘀斑\",\"value\":\"未见瘀斑\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"裂纹\",\"field\":\"未见裂纹\",\"value\":\"未见裂纹\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"齿痕\",\"field\":\"<span style=\\\"color:#F25D4C\\\">齿痕</span>\",\"value\":\"未见齿痕\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"舌形\",\"field\":\"正常\",\"value\":\"正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"厚薄\",\"field\":\"薄苔\",\"value\":\"薄苔\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"腐腻\",\"field\":\"正常\",\"value\":\"正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"苔质\",\"field\":\"正常\",\"value\":\"正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"津液\",\"field\":\"润\",\"value\":\"润\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"异常舌形\",\"field\":\"否\",\"value\":\"否\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"肝郁线\",\"field\":\"未见肝郁线\",\"value\":\"未见肝郁线\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"舌下络脉异常程度\",\"field\":\"舌下正常\",\"value\":\"舌下正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null}],\"aiTongueVOList\":[{\"name\":null,\"key\":\"舌色\",\"field\":\"淡红舌\",\"value\":\"【淡红舌】正常人舌色，舌质呈现润泽红活。</br>【病理意义】正常舌色，或者处于病情轻浅阶段。</br>\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":[],\"tongueColorAttrList\":[{\"name\":null,\"key\":\"<span style=\\\"color:#A46C7A\\\">淡白舌</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#B4586B\\\">舌质淡</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#B89EA4\\\">淡红舌</span>\",\"field\":null,\"value\":\"正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#B61617\\\">红舌</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#802924\\\">绛舌</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#7E7198\\\">青紫舌</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null}],\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"苔色\",\"field\":\"白苔\",\"value\":\"【白苔】舌苔呈现白色。</br>【病理意义】正常苔色。</br>\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":[\"舌根白苔\"],\"tongueColorAttrList\":null,\"tongueMossAttrList\":[{\"name\":null,\"key\":\"<span style=\\\"color:#EED6DC\\\">白苔</span>\",\"field\":null,\"value\":\"正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#BEAD7D\\\">淡黄苔</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#B29954\\\">黄苔</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#706136\\\">焦黄苔</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#51424B\\\">灰黑苔</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"<span style=\\\"color:#27181C\\\">焦黑苔</span>\",\"field\":null,\"value\":\"\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null}]},{\"name\":null,\"key\":\"点刺\",\"field\":\"未见点刺\",\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"瘀点\",\"field\":\"未见瘀点\",\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"瘀斑\",\"field\":\"未见瘀斑\",\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"裂纹\",\"field\":\"未见裂纹\",\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"齿痕\",\"field\":\"<span style=\\\"color:#F25D4C\\\">齿痕</span>\",\"value\":\"【齿痕】舌体边缘有牙齿压迫的痕迹。</br>【病理意义】脾虚、气虚或体内有湿。</br>\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":true,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":[\"轻度齿痕\"],\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"舌形\",\"field\":\"正常\",\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"厚薄\",\"field\":\"薄苔\",\"value\":\"【薄苔】透过舌苔能隐隐地见到舌体。 </br>【病理意义】脾胃状态良好。</br>\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":[],\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"腐腻\",\"field\":\"正常\",\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"苔质\",\"field\":\"正常\",\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"津液\",\"field\":\"润\",\"value\":\"【润】舌苔干湿适中，不滑不燥为润苔。 【病理意义】舌苔水分正常。\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"异常舌形\",\"field\":\"否\",\"value\":null,\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"舌下络脉异常程度\",\"field\":\"舌下正常\",\"value\":\"【舌下正常】正常情况下，其管径不超过2.7mm,长度不超过舌尖至舌下肉阜连线的3/5,颜色暗红，无分支和紫点。脉络无怒张、紧束、弯曲、增生，排列有序，绝大多数为单支，极少有双支出现。</br>【临床表现】舌下络脉正常。</br>\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":false,\"photo\":\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/luomai_label/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":[],\"tongueColorAttrList\":null,\"tongueMossAttrList\":null}],\"aiReportItemVOList\":null}";
	public static String str3 = "{\"pdfUrl\":null,\"openid\":null,\"id\":86,\"name\":\"测试2\",\"genderDesc\":null,\"gender\":1,\"birthday\":\"2010-07-21\",\"age\":13.3,\"reportId\":\"1166030276602761216\",\"tongueAttrs\":\"[{\\\"attrName\\\":\\\"舌色程度\\\",\\\"attrValue\\\":\\\"舌边淡\\\"},{\\\"attrName\\\":\\\"舌根苔色\\\",\\\"attrValue\\\":\\\"舌根白苔\\\"},{\\\"attrName\\\":\\\"瘀点数量\\\",\\\"attrValue\\\":\\\"0\\\"},{\\\"attrName\\\":\\\"瘀点程度\\\",\\\"attrValue\\\":\\\"全舌无瘀点\\\"},{\\\"attrName\\\":\\\"齿痕\\\",\\\"attrValue\\\":\\\"轻度齿痕\\\"}]\",\"tongueBatchNo\":\"1166030277122854912\",\"tongueStep\":3,\"tongueStepStatus\":1,\"detectionTypeDesc\":\"脾胃病\",\"tongueProcessExtraImage\":\"{}\",\"tongueProcessExtraImage1\":null,\"constitutionNames\":\"通用结论\",\"faceBatchNo\":\"1166030285750538240\",\"templateId\":1705,\"checkIndex\":null,\"checkId\":14,\"userInfoId\":8,\"step\":null,\"stepStatus\":null,\"faceStep\":2,\"faceStepStatus\":1,\"tonguePreFailMsg\":\"\",\"facePreFailMsg\":\"\",\"detectionType\":\"C08.D00\",\"detectionTypeName\":null,\"questions\":\"[{\\\"group\\\":\\\"\\\",\\\"types\\\":[{\\\"code\\\":\\\"IS0105\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"形体肌肤\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"唇红\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"霰粒肿\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"面色少华\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"形体消瘦\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"眼皮浮肿\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"面色萎黄\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"皮肤粗糙\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"嘴角溃烂\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"形体肥胖\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"鼻梁有青筋\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"眼睑发紫发红\\\"}],\\\"sort\\\":1},{\\\"code\\\":\\\"IS0106\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"头身不适\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"呃逆\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"呕吐\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腹胀\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腹痛\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"黄痰\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"肚子硬\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"脘痞胀\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"易感冒\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腿抽筋\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"倦怠乏力\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"面色晦暗\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"牙龈出血\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"腹痛欲泻\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"咽喉红肿\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"头发易打结\\\"}],\\\"sort\\\":2},{\\\"code\\\":\\\"IS0107\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"寒热\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"盗汗\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"手足心热\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"身体发热\\\"}],\\\"sort\\\":3},{\\\"code\\\":\\\"IS0108\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"饮食\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"口臭\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"口渴\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"贪吃\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"食欲不振\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"喜欢吃凉东西\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"消化系统紊乱\\\"}],\\\"sort\\\":4},{\\\"code\\\":\\\"IS0109\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"二便\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"排便不爽\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"大便干结\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"小便短黄\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"便秘或腹泻\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"大便有臭鸡蛋味\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"大便夹杂不消化食物\\\"}],\\\"sort\\\":5},{\\\"code\\\":\\\"IS0110\\\",\\\"defaultSelectMode\\\":0,\\\"name\\\":\\\"情志睡眠\\\",\\\"options\\\":[{\\\"checked\\\":false,\\\"name\\\":\\\"嗳气\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"烦躁\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"失眠\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"磨牙\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"善悲易哭\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"急躁易怒\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"睡觉露睛\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"夜眠不安\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"燥扰不宁\\\"},{\\\"checked\\\":false,\\\"name\\\":\\\"睡觉流口水\\\"}],\\\"sort\\\":6}]}]\",\"answers\":\"[{\\\"answerCode\\\":\\\"腹胀\\\",\\\"code\\\":\\\"IS0106\\\",\\\"questionIndex\\\":2},{\\\"answerCode\\\":\\\"唇红\\\",\\\"code\\\":\\\"IS0105\\\",\\\"questionIndex\\\":1},{\\\"answerCode\\\":\\\"手足心热\\\",\\\"code\\\":\\\"IS0107\\\",\\\"questionIndex\\\":3},{\\\"answerCode\\\":\\\"食欲不振\\\",\\\"code\\\":\\\"IS0108\\\",\\\"questionIndex\\\":4},{\\\"answerCode\\\":\\\"便秘或腹泻\\\",\\\"code\\\":\\\"IS0109\\\",\\\"questionIndex\\\":5},{\\\"answerCode\\\":\\\"失眠\\\",\\\"code\\\":\\\"IS0110\\\",\\\"questionIndex\\\":6}]\",\"reportLockStatus\":0,\"tongueSurfaceImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/f89cc8108b47471994f37e834eab1fcd-tmp_be0673ac3c80cf3597be30659f7b70d00b73a0489851eab3.jpg\",\"tongueBackImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/9a27ae484edf4abab461187a08bd3f27-tmp_1ee8eebbe1ec0a7394569b85a03d35c080e1950f0556bca8.jpg\",\"tongueSplitSurfaceImg\":\"https://td-platform-split.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"tongueSplitSurfaceImg1\":\"\",\"tongueSplitBackImg\":\"https://td-platform-split-back.oss-cn-hangzhou.aliyuncs.com/ai/tonguediagnosis/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\",\"tongueSplitBackImg1\":\"\",\"tongueProcessImages\":\"{\\\"ossYudianImgUrl\\\":\\\"\\\",\\\"ossSheseImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/shese/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossShexingImgUrl\\\":\\\"\\\",\\\"ossToothtraceImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/toothtrace/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossBoluoImgUrl\\\":\\\"\\\",\\\"ossLiewenImgUrl\\\":\\\"\\\",\\\"ossTaiseImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/taise/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossYubanImgUrl\\\":\\\"\\\",\\\"ossLuomaiLabelImgUrl\\\":\\\"https://td-platform-process.oss-cn-hangzhou.aliyuncs.com/ai/luomai_label/2023/10/70f7c153-df39-4fb9-9a2e-f85e7e7cb160.png\\\",\\\"ossPrickImgUrl\\\":\\\"\\\"}\",\"tongueProcessImages1\":null,\"tongueColor\":\"淡红舌\",\"tongueColorDesc\":\"【淡红舌】正常人舌色，舌质呈现润泽红活。</br>【病理意义】正常舌色，或者处于病情轻浅阶段。</br>\",\"tongueMossColor\":\"白苔\",\"tongueMossColorDesc\":\"【白苔】舌苔呈现白色。</br>【病理意义】正常苔色。</br>\",\"tongueMossNature\":\"薄苔\",\"tongueMossNatureDesc\":\"【薄苔】透过舌苔能隐隐地见到舌体。 </br>【病理意义】脾胃状态良好。</br>\",\"tongueShap\":\"齿痕\",\"tongueShapDesc\":\"【齿痕】舌体边缘有牙齿压迫的痕迹。</br>【病理意义】脾虚、气虚或体内有湿。</br>\",\"tongueBodyfluid\":\"\",\"tongueBodyfluidDesc\":\"【润】舌苔干湿适中，不滑不燥为润苔。 </br>【病理意义】舌苔水分正常。</br>\",\"tongueVein\":\"舌下正常\",\"tongueVeinDesc\":\"【舌下正常】正常情况下，其管径不超过2.7mm,长度不超过舌尖至舌下肉阜连线的3/5,颜色暗红，无分支和紫点。脉络无怒张、紧束、弯曲、增生，排列有序，绝大多数为单支，极少有双支出现。</br>【临床表现】舌下络脉正常。</br>\",\"faceFrontImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/f1a3ae630da945b5a78dc013e9d915fb-tmp_445d54aa67689e29f9a54c757abc1c69bad16f834eac7d84.jpg\",\"faceLeftImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/e1d71c630f174ab69236428e721a6aca-tmp_c0a0bf082e9e0f3c2676ecae84df531cb7426fb21848d3e1.jpg\",\"faceRightImg\":\"https://img.aikesaisi.com/user_center/images/2023-10-23/d867390967b64836bd7044d2ecace326-tmp_6ff305bf982195f64fe58701a28c19a0c6a75f70358452e3.jpg\",\"faceMianse\":\"{\\\"code\\\":\\\"m-10\\\",\\\"description\\\":\\\"【正常面色】面色红黄隐隐，明润含蓄。</br>\\\\n【病理意义】神气、胃气的正常色泽表现。</br>\\\",\\\"name\\\":\\\"面色红黄隐隐，明润含蓄\\\"}\",\"faceMianseName\":\"面色红黄隐隐，明润含蓄\",\"faceMianseDesc\":\"【正常面色】面色红黄隐隐，明润含蓄。</br>\\n【病理意义】神气、胃气的正常色泽表现。</br>\",\"faceMianseUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/face_area_label/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceMianseIsRed\":false,\"faceMainColor\":\"{\\\"code\\\":\\\"m-14\\\",\\\"description\\\":\\\"【正常色】面色红黄隐隐，明润含蓄。</br>\\\\n【病理意义】神气、胃气的正常色泽表现。</br>\\\\n\\\",\\\"name\\\":\\\"正常色\\\"}\",\"faceMainColorName\":\"正常色\",\"faceMainColorDesc\":\"【正常色】面色红黄隐隐，明润含蓄。</br>\\n【病理意义】神气、胃气的正常色泽表现。</br>\\n\",\"faceMainColorUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceMainColorIsRed\":false,\"faceGloss\":\"{\\\"code\\\":\\\"m-21\\\",\\\"description\\\":\\\"【少量】面色稍许晦暗、少泽。</br>\\\\n【病理意义】为脏腑精气稍衰，介于病轻和病重之间。</br>\\\\n\\\",\\\"name\\\":\\\"少量\\\"}\",\"faceGlossName\":\"少量\",\"faceGlossDesc\":\"【少量】面色稍许晦暗、少泽。</br>\\n【病理意义】为脏腑精气稍衰，介于病轻和病重之间。</br>\\n\",\"faceGlossUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceGlossIsRed\":true,\"faceDarkCircles\":\"{\\\"code\\\":\\\"ml-30,mr-30\\\",\\\"description\\\":\\\"【左眼正常】左眼眼圈不发黑，眼眶颜色正常。</br>\\\\n【病理意义】左眼无黑眼圈。</br>\\\\n,【右眼正常】右眼眼圈不发黑，眼眶颜色正常。</br>\\\\n【病理意义】右眼无黑眼圈。</br>\\\\n\\\",\\\"name\\\":\\\"左眼正常,右眼正常\\\"}\",\"faceDarkCirclesName\":\"左眼正常;右眼正常\",\"faceDarkCirclesDesc\":\"【左眼正常】左眼眼圈不发黑，眼眶颜色正常。</br>\\n【病理意义】左眼无黑眼圈。</br>\\n,【右眼正常】右眼眼圈不发黑，眼眶颜色正常。</br>\\n【病理意义】右眼无黑眼圈。</br>\\n\",\"faceDarkCirclesLeftUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceDarkCirclesRightUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceDarkCirclesIsRed\":false,\"faceLipColor\":\"{\\\"code\\\":\\\"m-40\\\",\\\"description\\\":\\\"【正常】唇色是明亮、润泽、含蓄的红色。</br>\\\\n【病理意义】胃气充足，气血调匀的表现。</br>\\\",\\\"name\\\":\\\"正常\\\"}\",\"faceLipColorName\":\"正常\",\"faceLipColorDesc\":\"【正常】唇色是明亮、润泽、含蓄的红色。</br>\\n【病理意义】胃气充足，气血调匀的表现。</br>\",\"faceLipColorUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/rect_lip/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceLipColorIsRed\":false,\"faceEyes\":\"{\\\"code\\\":\\\"m-51\\\",\\\"description\\\":\\\"【少神】两目乏神，动作迟缓，眼神晦暗。</br>\\\\n【病理意义】是正气不足的表现，见于虚证或轻病。</br>\\\\n\\\",\\\"name\\\":\\\"少神\\\"}\",\"faceEyesName\":\"少神\",\"faceEyesDesc\":\"【少神】两目乏神，动作迟缓，眼神晦暗。</br>\\n【病理意义】是正气不足的表现，见于虚证或轻病。</br>\\n\",\"faceEyesUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/double_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceEyesIsRed\":true,\"faceEyesColor\":\"{\\\"code\\\":\\\"ml-55,mr-55\\\",\\\"description\\\":\\\"【左眼正常】左眼黑白分明，精彩内含。</br>\\\\n【病理意义】精充、气足、神旺的表现。</br>\\\\n,【右眼正常】右眼黑白分明，精彩内含。</br>\\\\n【病理意义】精充、气足、神旺的表现。</br>\\\\n\\\",\\\"name\\\":\\\"左眼正常,右眼正常\\\"}\",\"faceEyesColorName\":\"左眼正常;右眼正常\",\"faceEyesColorDesc\":\"【左眼正常】左眼黑白分明，精彩内含。</br>\\n【病理意义】精充、气足、神旺的表现。</br>\\n,【右眼正常】右眼黑白分明，精彩内含。</br>\\n【病理意义】精充、气足、神旺的表现。</br>\\n\",\"faceEyesColorLeftUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceEyesColorRightUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceEyesColorIsRed\":false,\"faceCheekbones\":\"{\\\"code\\\":\\\"m-60\\\",\\\"description\\\":\\\"【无】面部两颧未出现红色，颜色跟正常面色一致。</br>\\\\n【病理意义】胃气充足，气血调匀的表现。</br>\\\\n\\\",\\\"name\\\":\\\"无\\\"}\",\"faceCheekbonesName\":\"无\",\"faceCheekbonesDesc\":\"【无】面部两颧未出现红色，颜色跟正常面色一致。</br>\\n【病理意义】胃气充足，气血调匀的表现。</br>\\n\",\"faceCheekbonesUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/face_area_label/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceCheekbonesIsRed\":false,\"faceNasalFold\":\"{\\\"code\\\":\\\"m-70\\\",\\\"description\\\":\\\"【无】鼻根部未出现横纹沟。</br>\\\\n【病理意义】横纹沟代表鼻褶心征。代表动脉硬化，冠动脉供血不足。</br>\\\",\\\"name\\\":\\\"无\\\"}\",\"faceNasalFoldName\":\"无\",\"faceNasalFoldDesc\":\"【无】鼻根部未出现横纹沟。</br>\\n【病理意义】横纹沟代表鼻褶心征。代表动脉硬化，冠动脉供血不足。</br>\",\"faceNasalFoldUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceNasalFoldIsRed\":false,\"faceGlabella\":\"{\\\"code\\\":\\\"m-80\\\",\\\"description\\\":\\\"【无】眉间或鼻柱之间不发青，无青色。</br>\\\\n【病理意义】眉间无青色。</br>\\\\n\\\",\\\"name\\\":\\\"无\\\"}\",\"faceGlabellaName\":\"无\",\"faceGlabellaDesc\":\"【无】眉间或鼻柱之间不发青，无青色。</br>\\n【病理意义】眉间无青色。</br>\\n\",\"faceGlabellaUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/double_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceGlabellaIsRed\":false,\"faceSkinLesions\":\"[{\\\"code\\\":\\\"m-90\\\",\\\"description\\\":\\\"【无】面部未出现色素痣、皮下颗粒物或疑似皮肤病等。</br>\\\\n【病理意义】面部正常。</br>\\\\n\\\",\\\"name\\\":\\\"无\\\",\\\"simpleDescription\\\":\\\"【无】面部未出现色素痣、皮下颗粒物或疑似皮肤病等。</br>\\\\n【病理意义】面部正常。</br>\\\\n\\\"}]\",\"faceSkinLesionsName\":\"无\",\"faceSkinLesionsDesc\":\"【无】面部未出现色素痣、皮下颗粒物或疑似皮肤病等。</br>\\n【病理意义】面部正常。</br>\\n\",\"faceSkinLesionsUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceSkinLesionsIsRed\":false,\"faceEarColor\":\"{\\\"code\\\":\\\"el-70,er-70\\\",\\\"description\\\":\\\"【左耳色正常】左耳颜色微黄而红润。</br>\\\\n【病理意义】左耳颜色正常。</br>\\\\n,【右耳色正常】右耳颜色微黄而红润。</br>\\\\n【病理意义】右耳颜色正常。</br>\\\\n\\\",\\\"name\\\":\\\"左耳色正常,右耳色正常\\\"}\",\"faceEarColorName\":\"左耳色正常;右耳色正常\",\"faceEarColorDesc\":\"【左耳色正常】左耳颜色微黄而红润。</br>\\n【病理意义】左耳颜色正常。</br>\\n,【右耳色正常】右耳颜色微黄而红润。</br>\\n【病理意义】右耳颜色正常。</br>\\n\",\"faceEarColorLeftUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceEarColorRightUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceEarColorIsRed\":false,\"faceUricularFold\":\"{\\\"code\\\":\\\"fl-70,fr-70\\\",\\\"description\\\":\\\"【左耳正常】左耳耳垂上没有褶皱。</br>\\\\n【病理意义】左耳无耳褶心征</br>\\\\n,【右耳正常】右耳耳垂上没有褶皱。</br>\\\\n【病理意义】右耳无耳褶心征</br>\\\\n\\\",\\\"name\\\":\\\"左耳正常,右耳正常\\\"}\",\"faceUricularFoldName\":\"左耳正常;右耳正常\",\"faceUricularFoldDesc\":\"【左耳正常】左耳耳垂上没有褶皱。</br>\\n【病理意义】左耳无耳褶心征</br>\\n,【右耳正常】右耳耳垂上没有褶皱。</br>\\n【病理意义】右耳无耳褶心征</br>\\n\",\"faceUricularFoldLeftUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceUricularFoldRightUrl\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"faceUricularFoldIsRed\":false,\"faceCaluateImage\":\"{\\\"doubleEye\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/double_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"faceSplit\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"leftEars\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"leftEye\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rectLip\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/rect_lip/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rightEars\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rightEye\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\"}\",\"faceCaluateImage1\":null,\"faceProcessImage\":\"{\\\"faceAbnormal\\\":\\\"\\\",\\\"faceAreaLabel\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/face_area_label/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"faceBzxz\\\":\\\"\\\",\\\"leftFaceEzxz\\\":\\\"\\\",\\\"leftSidefaceAbnormal\\\":\\\"\\\",\\\"mainMosaic\\\":\\\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/main_mosaic/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\\\",\\\"rightFaceEzxz\\\":\\\"\\\",\\\"rightSidefaceAbnormal\\\":\\\"\\\"}\",\"faceProcessImage1\":null,\"diagnosticResults\":\" 积食（积食化热）\",\"mainPerformance\":\"[{\\\"items\\\":[],\\\"message\\\":\\\" 积食（积食化热）主要表现为不思乳食，口干，胃腹胀满，腹部灼热。或见平时好动，心烦易怒，夜卧不安，手脚心热，小便黄，大便臭秽或秘结。\\\"}]\",\"occurReason\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"1、母乳和食物聚集在体内时间长了，积食产生热性病变，从而损伤了津液。饮食不节制、损伤了脾胃，脾脏运化功能失常，体内的气运行不畅通，所以不喜欢喝母乳和吃东西，肚子胀；食物聚集在胃肠导致热性病变，消耗损伤了津液，所以口干，腹部像火烧着、烫着那样热，两手心与两脚心有发热感觉，小便比较黄，大便特别臭或大便干涩拉不下来；心神被扰动所以心里烦躁或烦闷、容易生气，晚上睡觉的时候出现不安稳和不踏实；舌头颜色红，舌苔颜色黄，舌苔质地比较腻。\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"2、食物堆积胃肠久了会化热，热会使血行加速，气血扰动则心神被扰，易出现嘴唇红、手足心热的症状。\\\"}]\",\"predisposition\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"无\\\"}]\",\"highRiskDisease\":null,\"eatingHabits\":\"[{\\\"content\\\":\\\"蒲公英与焦三仙和炒鸡内金一同熬水\\\",\\\"title\\\":\\\"宜食\\\"}]\",\"dietRehabilitation\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"蒲公英与焦三仙和炒鸡内金一同熬水\\\"}]\",\"dietaryRecuperate\":null,\"sportsHealthCare\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"蒲公英与焦三仙和炒鸡内金一同熬水\\\"}]\",\"drugHealthCare\":\"[{\\\"items\\\":[{\\\"desc\\\":\\\"【厂家】葵花药业<br> 【用法用量】口服。小儿一次0.5g，每日3次周岁内酌减。<br> 【产品成分】鸡内金、六神曲。辅料为：蔗糖、玉米淀粉。<br> 【功能主治】健脾开胃，消食化积。用于小儿因脾胃不和引起的食积胀满，饮食停滞，呕吐泄泻。<br>\\\",\\\"name\\\":\\\"小儿复方鸡内金散\\\",\\\"photo\\\":\\\"https://labelsys-images.oss-cn-hangzhou.aliyuncs.com/healthcare/葵花药业/小儿复方鸡内金散.jpg\\\"}],\\\"message\\\":\\\"\\\"},{\\\"items\\\":[{\\\"desc\\\":\\\"【厂家】广州王老吉药业股份有限公司<br> 【用法用量】开始冲服。一次3.5~7克，一日3次。<br> 【产品成分】薏苡仁、稻芽、山楂、淡竹叶、钩藤、蝉蜕、甘草。辅料为蔗糖粉。<br> 【功能主治】开胃消滞，清热定惊。用于小儿积滞化热，消化不良，不思饮食，烦躁易惊，夜寐不安，大便不畅，小便短赤。<br>\\\",\\\"name\\\":\\\"小儿七星茶颗粒\\\",\\\"photo\\\":\\\"https://labelsys-images.oss-cn-hangzhou.aliyuncs.com/healthcare/葵花药业/小儿七星茶颗粒.png\\\"}],\\\"message\\\":\\\"\\\"}]\",\"massageHealthCare\":\"[{\\\"bottom\\\":\\\"\\\",\\\"desc\\\":\\\"\\\",\\\"message\\\":\\\"1、逆运内八卦（调理因积食导致的咳嗽)\\\",\\\"photo\\\":\\\"\\\"},{\\\"bottom\\\":\\\"\\\",\\\"desc\\\":\\\"\\\",\\\"message\\\":\\\"2、以上取穴，加清天河水、清大肠。烦躁不安加清心平肝，揉曲池。\\\",\\\"photo\\\":\\\"\\\"}]\",\"tongueScore\":\"3.04\",\"tongueTips\":\"建议您一周后再次做舌诊检测。\",\"recuperates\":\"[{\\\"items\\\":[],\\\"message\\\":\\\"1、避免食物种类过杂\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"每次饮食的种类不宜过多，四到五种即可，如若想吃多种类食物，可延长进食的时间间隔，注意营养均衡。\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"2、适量进食勿过多\\\"},{\\\"items\\\":[],\\\"message\\\":\\\"小儿喂食应适量，吃饱就好，不是吃的越多越好，一次性吃太多的食物容易导致积食，请家长把握好分寸。\\\"}]\",\"illProbability\":\"{\\\"scope\\\":\\\"3.04\\\",\\\"tip\\\":\\\"建议您一周后再次做舌诊检测。\\\"}\",\"solarTerms\":\"寒露\",\"createTime\":\"2023-10-23 15:08:03\",\"updateTime\":\"2023-10-23 17:47:13\",\"tongueColorList\":null,\"tongueColorDescription\":null,\"faceColorList\":null,\"userShopDetailVO\":null,\"tongueColorAttrList\":null,\"tongueColorInfoList\":null,\"tongueMossAttrList\":null,\"tongueMossColorInfoList\":null,\"tongueNatureAttrList\":null,\"tongueShapAttrList\":null,\"tongueBodyAttrList\":null,\"tongueVeinAttrList\":null,\"aiFaceItemVOList\":[{\"name\":null,\"key\":\"面色\",\"field\":\"面色红黄隐隐，明润含蓄\",\"value\":\"面色红黄隐隐，明润含蓄\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"光泽\",\"field\":\"<span style=\\\"color:#F25D4C\\\">少量</span>\",\"value\":\"正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"五色诊\",\"field\":\"正常色\",\"value\":\"正常色\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"两颧红\",\"field\":\"无\",\"value\":\"无\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"眉间/鼻柱青色\",\"field\":\"无\",\"value\":\"无\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"眼神\",\"field\":\"<span style=\\\"color:#F25D4C\\\">少神</span>\",\"value\":\"有神\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"黑眼圈\",\"field\":\"左眼正常;右眼正常\",\"value\":\"右眼正常;左眼正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"目色\",\"field\":\"左眼正常;右眼正常\",\"value\":\"右眼正常;左眼正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"鼻褶\",\"field\":\"无\",\"value\":\"无\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"唇色\",\"field\":\"正常\",\"value\":\"正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"耳色\",\"field\":\"左耳色正常;右耳色正常\",\"value\":\"右耳色正常;左耳色正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"耳褶\",\"field\":\"左耳正常;右耳正常\",\"value\":\"右耳正常;左耳正常\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"面部皮损\",\"field\":\"无\",\"value\":\"无\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":null,\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null}],\"aiFaceVOList\":[{\"name\":null,\"key\":\"面色\",\"field\":\"面色红黄隐隐，明润含蓄\",\"value\":\"【正常面色】面色红黄隐隐，明润含蓄。</br>\\n【病理意义】神气、胃气的正常色泽表现。</br>\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/face_area_label/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"五色诊\",\"field\":\"正常色\",\"value\":\"【正常色】面色红黄隐隐，明润含蓄。</br>\\n【病理意义】神气、胃气的正常色泽表现。</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"光泽\",\"field\":\"少量\",\"value\":\"【少量】面色稍许晦暗、少泽。</br>\\n【病理意义】为脏腑精气稍衰，介于病轻和病重之间。</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"眼神\",\"field\":\"少神\",\"value\":\"【少神】两目乏神，动作迟缓，眼神晦暗。</br>\\n【病理意义】是正气不足的表现，见于虚证或轻病。</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/double_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"目色\",\"field\":\"左眼正常,右眼正常\",\"value\":\"【左眼正常】左眼黑白分明，精彩内含。</br>\\n【病理意义】精充、气足、神旺的表现。</br>\\n,【右眼正常】右眼黑白分明，精彩内含。</br>\\n【病理意义】精充、气足、神旺的表现。</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"两颧红\",\"field\":\"无\",\"value\":\"【无】面部两颧未出现红色，颜色跟正常面色一致。</br>\\n【病理意义】胃气充足，气血调匀的表现。</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/process/face_area_label/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"眉间/鼻柱青色\",\"field\":\"无\",\"value\":\"【无】眉间或鼻柱之间不发青，无青色。</br>\\n【病理意义】眉间无青色。</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/double_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"鼻褶\",\"field\":\"无\",\"value\":\"【无】鼻根部未出现横纹沟。</br>\\n【病理意义】横纹沟代表鼻褶心征。代表动脉硬化，冠动脉供血不足。</br>\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"耳色\",\"field\":\"左耳色正常,右耳色正常\",\"value\":\"【左耳色正常】左耳颜色微黄而红润。</br>\\n【病理意义】左耳颜色正常。</br>\\n,【右耳色正常】右耳颜色微黄而红润。</br>\\n【病理意义】右耳颜色正常。</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"耳褶\",\"field\":\"左耳正常,右耳正常\",\"value\":\"【左耳正常】左耳耳垂上没有褶皱。</br>\\n【病理意义】左耳无耳褶心征</br>\\n,【右耳正常】右耳耳垂上没有褶皱。</br>\\n【病理意义】右耳无耳褶心征</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_ears/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"面部皮损\",\"field\":\"无\",\"value\":\"【无】面部未出现色素痣、皮下颗粒物或疑似皮肤病等。</br>\\n【病理意义】面部正常。</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/face_split/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"唇色\",\"field\":\"正常\",\"value\":\"【正常】唇色是明亮、润泽、含蓄的红色。</br>\\n【病理意义】胃气充足，气血调匀的表现。</br>\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/rect_lip/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":null,\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null},{\"name\":null,\"key\":\"黑眼圈\",\"field\":\"左眼正常,右眼正常\",\"value\":\"【左眼正常】左眼眼圈不发黑，眼眶颜色正常。</br>\\n【病理意义】左眼无黑眼圈。</br>\\n,【右眼正常】右眼眼圈不发黑，眼眶颜色正常。</br>\\n【病理意义】右眼无黑眼圈。</br>\\n\",\"faceMsg\":null,\"tongueMsg\":null,\"isRed\":null,\"photo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/left_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"photoTwo\":\"https://td-platform-face.oss-cn-hangzhou.aliyuncs.com/ai/caluate/right_eye/2023/10/7bdd03b1-9d9b-45b0-9590-8c2745feecd6.png\",\"player_url\":null,\"audio_url\":null,\"desc\":null,\"code\":null,\"tongueNatureAttrList\":null,\"tongueColorAttrList\":null,\"tongueMossAttrList\":null}],\"aiTongueItemVOList\":null,\"aiTongueVOList\":null,\"aiReportItemVOList\":null}";
	public static String str4 = "{\"aiUserRoleEnum\":\"tourist\",\"diagnostic_results\":null,\"sports_health_care\":null,\"eatingHabits\":null,\"diet_rehabilitation\":null,\"diet_rehabilitation_v2022\":null,\"predisposition\":null,\"eatingHabitsItemList\":null,\"massage_health_care\":null,\"main_performance\":[{\"message\":\" 积食（积食化热）主要表现为不思乳食，口干，胃腹胀满，腹部灼热。或见平时好动，心烦易怒，夜卧不安，手脚心热，小便黄，大便臭秽或秘结。\",\"items\":[]}],\"occur_reason\":[{\"message\":\"1、母乳和食物聚集在体内时间长了，积食产生热性病变，从而损伤了津液。饮食不节制、损伤了脾胃，脾脏运化功能失常，体内的气运行不畅通，所以不喜欢喝母乳和吃东西，肚子胀；食物聚集在胃肠导致热性病变，消耗损伤了津液，所以口干，腹部像火烧着、烫着那样热，两手心与两脚心有发热感觉，小便比较黄，大便特别臭或大便干涩拉不下来；心神被扰动所以心里烦躁或烦闷、容易生气，晚上睡觉的时候出现不安稳和不踏实；舌头颜色红，舌苔颜色黄，舌苔质地比较腻。\",\"items\":[]},{\"message\":\"2、食物堆积胃肠久了会化热，热会使血行加速，气血扰动则心神被扰，易出现嘴唇红、手足心热的症状。\",\"items\":[]}],\"illProbabilities\":null,\"drug_health_care\":null,\"recuperates\":null,\"dietaryConditioningList\":null,\"nutritionalMatchList\":null,\"highRiskDiseaseList\":[]}";
	public static String str5 = "{\"aiUserRoleEnum\":\"tourist\",\"diagnostic_results\":null,\"sports_health_care\":null,\"eatingHabits\":null,\"diet_rehabilitation\":null,\"diet_rehabilitation_v2022\":null,\"predisposition\":null,\"eatingHabitsItemList\":[{\"title\":\"宜食\",\"content\":\"蒲公英与焦三仙和炒鸡内金一同熬水\"}],\"massage_health_care\":null,\"main_performance\":null,\"occur_reason\":null,\"illProbabilities\":null,\"drug_health_care\":[{\"message\":\"\",\"items\":[{\"name\":\"小儿复方鸡内金散\",\"desc\":\"【厂家】葵花药业<br> 【用法用量】口服。小儿一次0.5g，每日3次周岁内酌减。<br> 【产品成分】鸡内金、六神曲。辅料为：蔗糖、玉米淀粉。<br> 【功能主治】健脾开胃，消食化积。用于小儿因脾胃不和引起的食积胀满，饮食停滞，呕吐泄泻。<br>\",\"player_url\":null,\"photo\":\"https://labelsys-images.oss-cn-hangzhou.aliyuncs.com/healthcare/葵花药业/小儿复方鸡内金散.jpg\",\"jumpUrl\":null,\"audio_url\":null}]},{\"message\":\"\",\"items\":[{\"name\":\"小儿七星茶颗粒\",\"desc\":\"【厂家】广州王老吉药业股份有限公司<br> 【用法用量】开始冲服。一次3.5~7克，一日3次。<br> 【产品成分】薏苡仁、稻芽、山楂、淡竹叶、钩藤、蝉蜕、甘草。辅料为蔗糖粉。<br> 【功能主治】开胃消滞，清热定惊。用于小儿积滞化热，消化不良，不思饮食，烦躁易惊，夜寐不安，大便不畅，小便短赤。<br>\",\"player_url\":null,\"photo\":\"https://labelsys-images.oss-cn-hangzhou.aliyuncs.com/healthcare/葵花药业/小儿七星茶颗粒.png\",\"jumpUrl\":null,\"audio_url\":null}]}],\"recuperates\":[{\"message\":\"1、避免食物种类过杂\",\"items\":[]},{\"message\":\"每次饮食的种类不宜过多，四到五种即可，如若想吃多种类食物，可延长进食的时间间隔，注意营养均衡。\",\"items\":[]},{\"message\":\"2、适量进食勿过多\",\"items\":[]},{\"message\":\"小儿喂食应适量，吃饱就好，不是吃的越多越好，一次性吃太多的食物容易导致积食，请家长把握好分寸。\",\"items\":[]}],\"dietaryConditioningList\":[{\"title\":\"\",\"content\":\"1、调节饮食，合理喂养，乳食宜定时定量，选择富含营养、清热的食物如苦瓜粥。\"},{\"title\":\"\",\"content\":\"2、忌暴饮暴食、过食肥甘、熏烤、生冷瓜果，偏食零食及妄加滋补。\"}],\"nutritionalMatchList\":[{\"name\":\"健脾益气\",\"notice\":\"\",\"productInfoList\":[{\"img\":\"https://img.aikesaisi.com/ai/product/pph.png\",\"message\":\"补充爱欣童脾皮虎风味饮料，每日1-3瓶；调理周期1-3个月。\",\"productName\":\"爱欣童脾皮虎风味饮料\"}]},{\"name\":\"清热祛火\",\"notice\":\"\",\"productInfoList\":[{\"img\":\"https://img.aikesaisi.com/ai/product/qqh.png\",\"message\":\"补充爱欣童顷清虎风味饮料，每日1-2瓶；调理周期5-7天。\",\"productName\":\"爱欣童顷清虎风味饮料\"}]},{\"name\":\"滋阴润燥\",\"notice\":\"\",\"productInfoList\":[{\"img\":\"https://img.aikesaisi.com/ai/product/ffh.png\",\"message\":\"补充爱欣童肺扉虎风味饮料，每日2次，每次1瓶；调理周期1-3个月。\",\"productName\":\"爱欣童肺扉虎风味饮料\"}]},{\"name\":\"均衡营养\",\"notice\":\"\",\"productInfoList\":[{\"img\":\"https://img.aikesaisi.com/ai/product/xzw.png\",\"message\":\"补充挑剔麻咪酵母富锌多种维生素补充膳食摄入不足造成的营养不均衡，每日1-3粒。\",\"productName\":\"挑剔麻咪酵母富锌多种维生素\"}]}],\"highRiskDiseaseList\":null}";

	private static final String UPLOAD_URL = "http://img.aikesaisi.com/";

	public static final String testTitleUrl1 = "https://img.aikesaisi.com/ai/user/2023-11-08/341c7a37f59d417291ae7a28f35bc570-logo.png";
	//public static final String testTitleUrl1 = "http://img.aikesaisi.com/jhbamin/images/2023-10-16/a7ab5aa59ef642e8aeca5f015ae6c3d0-title.png";
	public static final String testImgUrl1 = "http://img.aikesaisi.com/jhbamin/images/2023-10-08/8ad97167797c4f0481bdb11f647a32f7-code.jpg";
	public static final String testImgUrl2 = "http://img.aikesaisi.com/jhbamin/images/2023-10-08/e895fbdc05c347fbad116e438058abe4-shetou.jpg";
	public static final String testImgUrl3 = "http://img.aikesaisi.com/jhbamin/images/2023-10-10/61b473f845c748beac6914cd1c3bc712-bj.jpg";

	// 宋体，正常字体
	public static BaseFont bfChinese;

	static {
		try {
			bfChinese = BaseFont.createFont(PdfUtils.FontName, PdfUtils.FontEncoding, BaseFont.EMBEDDED);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Font font10 = new Font(bfChinese, 10);
	public static Font font16 = new Font(bfChinese, 16);
	public static Font font20 = new Font(bfChinese, 20);
	public static Font font30 = new Font(bfChinese, 30);
	public static Font font16Bold = new Font(bfChinese, 16, Font.BOLD);
	public static Font font20Bold = new Font(bfChinese, 20, Font.BOLD);
	public static Font font24Bold = new Font(bfChinese, 24, Font.BOLD);

	public static Color borderColor1 = new Color(79, 128, 189);
	public static Color backgroundColor1 = new Color(219, 229, 241);

	public static PdfResVo pdfResVo = new PdfResVo();


	/**
	 * 添加页眉和页脚和水印
	 */
	private static class HeaderAndFooterPageEventHelper extends PdfPageEventHelper {

		@Override
		public void onStartPage(PdfWriter writer, Document document) {
			// 数据库数据
			AiConsultFlowVO detailVo = pdfResVo.getDetailVo();

			PdfPTable table = new PdfPTable(3);
			table.setWidths(new int[]{38, 36, 36});
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setPaddingBottom(5);
			table.getDefaultCell().setBorder(PdfPCell.BOTTOM);
			table.getDefaultCell().setBorderColor(borderColor1);

			PdfPCell emptyCell = new PdfPCell(new Paragraph(""));
			emptyCell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(emptyCell);

			Paragraph title = new Paragraph("健康状态辨识报告", font16Bold);
			PdfPCell titleCell = new PdfPCell(title);
			titleCell.setPaddingBottom(10);
			titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			titleCell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(titleCell);
			table.addCell(emptyCell);

			/*table.addCell(new Paragraph("检测人：黄佳乐", font10));
			table.addCell(new Paragraph("性别：男", font10));
			table.addCell(new Paragraph("年龄：18", font10));*/
			table.addCell(new Paragraph("检测人：" + detailVo.getName(), font10));
			table.addCell(new Paragraph("性别：" + detailVo.getGenderDesc(), font10));
			table.addCell(new Paragraph("年龄：" + detailVo.getAge(), font10));

			float yOffset = -30;
			table.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
			table.writeSelectedRows(0, -1, document.left(), document.top() - yOffset, writer.getDirectContent());

			PdfPTable spacingTable = new PdfPTable(1);
			PdfPCell spacingCell = new PdfPCell();
			spacingCell.setFixedHeight(25);
			spacingCell.setBorder(PdfPCell.NO_BORDER);
			spacingTable.addCell(spacingCell);

			//document.add(table);
			document.add(spacingTable);
			//table.writeSelectedRows(0, -1, 34, 828, writer.getDirectContent());
		}

		@Override
		public void onEndPage(PdfWriter writer, Document document) {
			// 数据库数据
			AiConsultFlowVO detailVo = pdfResVo.getDetailVo();

			PdfPTable table = new PdfPTable(2);
			table.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
			table.setWidths(new int[]{50, 50});
			table.getDefaultCell().setPaddingBottom(5);
			table.getDefaultCell().setBorder(PdfPCell.TOP);

			/*Paragraph title = new Paragraph("检测时间：2023-09-15 17:15:56", font10);*/
			String createTime = "";
			if (detailVo.getCreateTime() != null) {
				createTime = DateUtil.formatDateTime(detailVo.getCreateTime());
			}
			Paragraph title = new Paragraph("检测时间：" + createTime, font10);
			PdfPCell titleCell = new PdfPCell(title);
			titleCell.setPaddingTop(4);
			titleCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			titleCell.setBorder(PdfPCell.TOP);
			titleCell.setBorderColor(borderColor1);
			table.addCell(titleCell);

			Paragraph pageNumberText = new Paragraph("第 " + document.getPageNumber() + " 页", font10);
			PdfPCell pageNumberCell = new PdfPCell(pageNumberText);
			pageNumberCell.setPaddingTop(4);
			pageNumberCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			pageNumberCell.setBorder(PdfPCell.TOP);
			pageNumberCell.setBorderColor(borderColor1);
			table.addCell(pageNumberCell);

			table.writeSelectedRows(0, -1, 36, 30, writer.getDirectContent());

			// 水印
			PdfContentByte canvas = writer.getDirectContentUnder();
			canvas.beginText();
			canvas.setFontAndSize(bfChinese, 30);
			// 设置水印文本的颜色和透明度
			Color lightGray = new Color(192, 192, 192);
			PdfGState gState = new PdfGState();
			// 设置透明度，可以根据需要调整透明度值
			gState.setFillOpacity(0.4f);
			canvas.setGState(gState);
			canvas.setColorFill(lightGray);

			// 水印文本和位置
			String watermarkText = "   安徽中医药大学云诊科技 提供技术支持   安徽中医药大学云诊科技 提供技术支持";
			// 添加水印到不同的位置, 595.0,842.0
			canvas.showTextAligned(Element.ALIGN_LEFT, watermarkText, 200, 0, 45);
			//canvas.showTextAligned(Element.ALIGN_LEFT, watermarkText, 400, 0, 45);
			//canvas.showTextAligned(Element.ALIGN_LEFT, watermarkText, 0, 0, 45);
			canvas.showTextAligned(Element.ALIGN_LEFT, watermarkText, 0, 200, 45);
			//canvas.showTextAligned(Element.ALIGN_LEFT, watermarkText, 0, 400, 45);
			canvas.showTextAligned(Element.ALIGN_LEFT, watermarkText, 0, 600, 45);
			canvas.endText();

		}
	}

	/**
	 * 第一页，标题+个人信息+二维码
	 *
	 * @param document
	 */
	private static void onePage(Document document) {
		// 数据库数据
		AiConsultFlowVO detailVo = pdfResVo.getDetailVo();
		GeneratePdfVO generatePdfVO = pdfResVo.getGeneratePdfVO();

		// 标题图片
		document.add(PdfUtils.newParagraph("\n", font16));
		document.add(PdfUtils.newImage(testTitleUrl1, Element.ALIGN_CENTER, 450, 150));

		// 标题
		document.add(PdfUtils.newParagraph("\n健康状态报告\n\n\n",
				new Font(bfChinese, 36, Font.BOLD, new Color(23, 53, 93)), Element.ALIGN_CENTER));

		// 个人信息
		PdfPTable table = new PdfPTable(2);
		// 宽度100%填充
		table.setWidthPercentage(100);
		// 设置表格无边框
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		// 创建表格的的行对象集合
		List<PdfPRow> listRow = table.getRows();
		// 将表格设置为2列，并指定列宽
		table.setWidths(new float[]{6f, 4f});

		/*PdfPCell[] cells1 = new PdfPCell[2];
		cells1[0] = PdfUtils.newCell(" 黄佳乐\n", font20, 45, PdfPCell.NO_BORDER);
		// 二维码
		cells1[1] = PdfUtils.newCell("", font20, 0, 5, Element.ALIGN_LEFT, PdfPCell.NO_BORDER);
		cells1[1].addElement(PdfUtils.newImage(testImgUrl1, 120, 120));
		// 二维码底下文案
		cells1[1].addElement(PdfUtils.newParagraph(Arrays.asList("  扫描二维码\n", "使用手机查看电子版\n"),
				new Font(bfChinese, 14, Font.BOLD, new Color(23, 53, 93)), Element.ALIGN_LEFT));

		PdfPCell[] cells2 = new PdfPCell[2];
		cells2[0] = PdfUtils.newCell(" 年龄：18\n", font20, 45, PdfPCell.NO_BORDER);
		PdfPCell[] cells3 = new PdfPCell[2];
		cells3[0] = PdfUtils.newCell(" 性别：男\n", font20, 45, PdfPCell.NO_BORDER);
		PdfPCell[] cells4 = new PdfPCell[2];
		cells4[0] = PdfUtils.newCell(" 联系电话：18616919418\n", font20, 45, PdfPCell.NO_BORDER);
		PdfPCell[] cells5 = new PdfPCell[2];
		cells5[0] = PdfUtils.newCell(" 报告时间：2023-9-15\n", font20, 45, PdfPCell.NO_BORDER);*/
		// 生成二维码图片
		File qrFile = null;
		try {
			String skipUrl = "https://jhbfat1.aikesaisi.com/healthReportHome?openid="
					+ generatePdfVO.getOpenid() + "&reportId=" + generatePdfVO.getReportId();
			int size = 300;
			String fileType = "png";
			qrFile = new File("qrcode.png");
			// 调用生成二维码
			PdfUtils.createQRCode(skipUrl, size, fileType, qrFile);

			// 填入pdf
			PdfPCell[] cells1 = new PdfPCell[2];
			cells1[0] = PdfUtils.newCell(" " + detailVo.getName() + "\n", font20, 45, PdfPCell.NO_BORDER);
			// 二维码
			cells1[1] = PdfUtils.newCell("", font20, 0, 5, Element.ALIGN_CENTER, PdfPCell.NO_BORDER);
			cells1[1].addElement(PdfUtils.newImage("qrcode.png", Element.ALIGN_CENTER, 120, 120, 100, 100));
			// 二维码底下文案
			cells1[1].addElement(PdfUtils.newParagraph(Arrays.asList("扫描二维码\n", "使用手机查看电子版\n"),
					new Font(bfChinese, 14, Font.BOLD, new Color(23, 53, 93)), Element.ALIGN_CENTER));
			listRow.add(new PdfPRow(cells1));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (qrFile != null) {
				qrFile.delete();
			}
		}

		PdfPCell[] cells2 = new PdfPCell[2];
		cells2[0] = PdfUtils.newCell(" 年龄：" + detailVo.getAge() + "\n", font20, 45, PdfPCell.NO_BORDER);
		PdfPCell[] cells3 = new PdfPCell[2];
		cells3[0] = PdfUtils.newCell(" 性别：" + detailVo.getGenderDesc() + "\n", font20, 45, PdfPCell.NO_BORDER);
		PdfPCell[] cells4 = new PdfPCell[2];
		cells4[0] = PdfUtils.newCell(" 联系电话：" + detailVo.getUserShopDetailVO().getMobile() + "\n", font20, 45, PdfPCell.NO_BORDER);
		PdfPCell[] cells5 = new PdfPCell[2];
		String createTime = "";
		if (detailVo.getCreateTime() != null) {
			createTime = DateUtil.formatDateTime(detailVo.getCreateTime());
		}
		cells5[0] = PdfUtils.newCell(" 报告时间：" + createTime + "\n", font20, 45, PdfPCell.NO_BORDER);

		listRow.add(new PdfPRow(cells2));
		listRow.add(new PdfPRow(cells3));
		listRow.add(new PdfPRow(cells4));
		listRow.add(new PdfPRow(cells5));
		document.add(table);

	}

	/**
	 * 第二页，目录+图片
	 *
	 * @param document
	 * @throws Exception
	 */
	private static void twoPage(Document document) {
		document.newPage();

		// 间距
		document.add(PdfUtils.newParagraph("\n\n", font30));

		PdfPTable table = new PdfPTable(2);
		// 宽度100%填充
		table.setWidthPercentage(100);
		// 设置表格无边框
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		// 创建表格的的行对象集合
		List<PdfPRow> listRow = table.getRows();
		// 将表格设置为2列，并指定列宽
		table.setWidths(new float[]{3f, 7f});

		PdfPCell[] cells0 = new PdfPCell[2];
		cells0[0] = PdfUtils.newCell(" 目\n\n\n 录", new Font(bfChinese, 60, Font.BOLD),
				0, 5, Element.ALIGN_LEFT, null, PdfPCell.RIGHT);
		// 设置边框宽度为3
		cells0[0].setBorderWidth(3f);
		cells0[1] = PdfUtils.newCell(" 一、健康状态\n", font30, 60, PdfPCell.NO_BORDER);
		listRow.add(new PdfPRow(cells0));

		PdfPCell[] cells1 = new PdfPCell[2];
		cells1[1] = PdfUtils.newCell(" 二、舌象辨识\n", font30, 60, PdfPCell.NO_BORDER);
		listRow.add(new PdfPRow(cells1));

		PdfPCell[] cells2 = new PdfPCell[2];
		cells2[1] = PdfUtils.newCell(" 三、舌象辨识\n", font30, 60, PdfPCell.NO_BORDER);
		listRow.add(new PdfPRow(cells2));

		PdfPCell[] cells3 = new PdfPCell[2];
		cells3[1] = PdfUtils.newCell(" 四、健康分析\n", font30, 60, PdfPCell.NO_BORDER);
		listRow.add(new PdfPRow(cells3));

		PdfPCell[] cells4 = new PdfPCell[2];
		cells4[1] = PdfUtils.newCell(" 五、调理方案\n", font30, 60, PdfPCell.NO_BORDER);
		listRow.add(new PdfPRow(cells4));

		document.add(table);

		// 图片
		document.add(PdfUtils.newImage(testImgUrl3, null, 300, 300, 200, 50));

	}

	/**
	 * 第三页，健康状态+舌象辨识
	 *
	 * @param document
	 * @throws Exception
	 */
	private static void threePage(Document document) {
		// 数据库数据
		AiConsultFlowVO detailVo = pdfResVo.getDetailVo();
		AiConsultFlowVO tongueVo = pdfResVo.getTongueVo();

		document.newPage();

		// 标题
		document.add(PdfUtils.newParagraph("一、健康状态\n\n", font24Bold));
		// 健康状态
		PdfPTable table0 = new PdfPTable(1);
		table0.setWidthPercentage(100);
		List<PdfPRow> listRow0 = table0.getRows();
		table0.setWidths(new float[]{1f});

		PdfPCell[] cells01 = new PdfPCell[1];
		//cells01[0] = PdfUtils.newCellLeft("健康状态：痰湿 (痰阻心脉证)");
		cells01[0] = PdfUtils.newCellLeft("健康状态：" + detailVo.getDiagnosticResults());
		cells01[0].setBackgroundColor(backgroundColor1);
		listRow0.add(new PdfPRow(cells01));
		PdfPCell[] cells02 = new PdfPCell[1];
		//cells02[0] = PdfUtils.newCellLeft("综合舌象、面象分析属于亚健康状态，建议您一周后再次检测");
		cells02[0] = PdfUtils.newCellLeft(detailVo.getTongueTips());
		listRow0.add(new PdfPRow(cells02));
		document.add(table0);
		document.add(PdfUtils.newParagraph("\n", font16));

		// 标题
		document.add(PdfUtils.newParagraph("二、舌象辨识\n", font24Bold));

		// 1.舌面图像采集结果
		document.add(PdfUtils.newParagraph("舌面图像采集结果\n", font20Bold, Element.ALIGN_CENTER));
		//document.add(PdfUtils.newImage(testImgUrl2, Element.ALIGN_CENTER, 100, 150));
		document.add(PdfUtils.newImage(tongueVo.getTongueSurfaceImg(), Element.ALIGN_CENTER, 100, 150));
		document.add(PdfUtils.newParagraph("舌面\n\n", font16, Element.ALIGN_CENTER));

		// 2.舌象分析结果汇总
		PdfPTable table1 = new PdfPTable(2);
		table1.setWidthPercentage(100);

		List<PdfPRow> listRow1 = table1.getRows();
		table1.setWidths(new float[]{1f, 3f});

		PdfPCell[] cells11 = new PdfPCell[2];
		cells11[0] = PdfUtils.newCell("舌象分析结果汇总", font16Bold, 2, 0, Element.ALIGN_CENTER, null, borderColor1);
		listRow1.add(new PdfPRow(cells11));

			/*Boolean flag = true;
			for (int i = 0; i < 6; i++) {
				PdfPCell[] cells12 = new PdfPCell[2];
				cells12[0] = PdfUtils.newCellCenter("舌色：" + i);
				if (flag) {
					cells12[0].setBackgroundColor(backgroundColor1);
				}
				cells12[1] = PdfUtils.newCellLeft("淡红舌");
				if (flag) {
					cells12[1].setBackgroundColor(backgroundColor1);
				}
				listRow1.add(new PdfPRow(cells12));
				flag = !flag;
			}*/
		if (CollectionUtils.isNotEmpty(tongueVo.getAiTongueItemVOList())) {
			Boolean flag = true;
			for (AiReportItemVO tongueItemVO : tongueVo.getAiTongueItemVOList()) {
				PdfPCell[] cells12 = new PdfPCell[2];
				cells12[0] = PdfUtils.newCellCenter(tongueItemVO.getKey());
				if (flag) {
					cells12[0].setBackgroundColor(backgroundColor1);
				}
				cells12[1] = PdfUtils.newCellLeft(tongueItemVO.getValue());
				if (flag) {
					cells12[1].setBackgroundColor(backgroundColor1);
				}
				listRow1.add(new PdfPRow(cells12));
				flag = !flag;
			}
		}

		document.add(table1);
		document.add(PdfUtils.newParagraph("\n", font16));

			/* 暂无
			// 3.舌象特征变化解析
			PdfPTable table2 = new PdfPTable(2);
			table2.setWidthPercentage(100);
			List<PdfPRow> listRow2 = table2.getRows();
			table2.setWidths(new float[]{1f, 1f});

			PdfPCell[] cells21 = new PdfPCell[2];
			cells21[0] = PdfUtils.newCell("结合 2023-09-15 17:12 检测,舌象特征变化解析：", font16Bold, 2, 0, Element.ALIGN_CENTER, null, borderColor1);
			listRow2.add(new PdfPRow(cells21));

			PdfPCell[] cells22 = new PdfPCell[2];
			cells22[0] = PdfUtils.newCellCenter("特征变化");
			cells22[0].setBackgroundColor(backgroundColor1);
			cells22[1] = PdfUtils.newCellCenter("病理意义");
			cells22[1].setBackgroundColor(backgroundColor1);
			listRow2.add(new PdfPRow(cells22));
			PdfPCell[] cells23 = new PdfPCell[2];
			cells23[0] = PdfUtils.newCellLeft("【厚苔 腻】变成【薄苔 腻】");
			cells23[1] = PdfUtils.newCellLeft("邪气渐退，仍有余邪");
			listRow2.add(new PdfPRow(cells23));

			document.add(table2);
			document.add(PdfUtils.newParagraph("\n", font16));*/

		// 4.中医解析
			/*PdfPTable table3 = new PdfPTable(3);
			table3.setWidthPercentage(100);
			List<PdfPRow> listRow3 = table3.getRows();
			table3.setWidths(new float[]{1f, 2f, 6f});

			PdfPCell[] cells31 = new PdfPCell[3];
			cells31[0] = PdfUtils.newCell("舌色——中医解析", font16Bold, 3, 0, Element.ALIGN_CENTER, null, borderColor1);
			listRow3.add(new PdfPRow(cells31));

			PdfPCell[] cells32 = new PdfPCell[3];
			cells32[0] = PdfUtils.newCellCenter("人工智能分析", 0, 2);
			cells32[1] = PdfUtils.newCellCenter("舌色分析");
			cells32[2] = PdfUtils.newCell("");
			cells32[2].setBorderColor(borderColor1);
			cells32[2].addElement(PdfUtils.newImage(testImgUrl2, 100, 100));
			listRow3.add(new PdfPRow(cells32));

			PdfPCell[] cells33 = new PdfPCell[3];
			cells33[1] = PdfUtils.newCellCenter("辨识结果");
			cells33[1].setBackgroundColor(backgroundColor1);
			cells33[2] = PdfUtils.newCellLeft("淡红舌");
			cells33[2].setBackgroundColor(backgroundColor1);
			listRow3.add(new PdfPRow(cells33));

			PdfPCell[] cells34 = new PdfPCell[3];
			cells34[0] = PdfUtils.newCellCenter("中医解析", 0, 2);
			cells34[1] = PdfUtils.newCellCenter("中医描述");
			cells34[2] = PdfUtils.newCellLeft("【淡红舌】正常人舌色，舌质呈现润泽红活。【淡红舌】正常人舌色，舌质呈现润泽红活。【淡红舌】正常人舌色，舌质呈现润泽红活。【淡红舌】正常人舌色，舌质呈现润泽红活。");
			listRow3.add(new PdfPRow(cells34));

			PdfPCell[] cells35 = new PdfPCell[3];
			cells35[1] = PdfUtils.newCellCenter("病理意义");
			cells35[1].setBackgroundColor(backgroundColor1);
			cells35[2] = PdfUtils.newCellLeft("【淡红舌】正常舌色，或者处于病情轻浅阶段。");
			cells35[2].setBackgroundColor(backgroundColor1);
			listRow3.add(new PdfPRow(cells35));

			document.add(table3);
			document.add(PdfUtils.newParagraph("\n", font16));
			document.add(table3);
			document.add(PdfUtils.newParagraph("\n", font16));
			document.add(table3);
			document.add(PdfUtils.newParagraph("\n", font16));*/
		if (CollectionUtils.isNotEmpty(tongueVo.getAiTongueVOList())) {
			for (AiReportItemVO tongueDetailVo : tongueVo.getAiTongueVOList()) {
				PdfPTable table3 = new PdfPTable(4);
				table3.setWidthPercentage(100);
				List<PdfPRow> listRow3 = table3.getRows();
				table3.setWidths(new float[]{1f, 2f, 3f, 3f});

				PdfPCell[] cells31 = new PdfPCell[4];
				cells31[0] = PdfUtils.newCell(tongueDetailVo.getKey() + "——中医解析", font16Bold, 4, 0, Element.ALIGN_CENTER, null, borderColor1);
				listRow3.add(new PdfPRow(cells31));

				PdfPCell[] cells32 = new PdfPCell[4];
				cells32[0] = PdfUtils.newCellCenter("人工智能分析", 0, 2);
				cells32[1] = PdfUtils.newCellCenter(tongueDetailVo.getKey() + "分析");
				if (StringUtils.isNotBlank(tongueDetailVo.getPhotoTwo())) {
					cells32[2] = PdfUtils.newCellLeft("");
					cells32[2].setBorderColor(borderColor1);
					cells32[2].addElement(PdfUtils.newParagraph("\n", font10));
					cells32[2].addElement(PdfUtils.newImage(tongueDetailVo.getPhoto(), Element.ALIGN_CENTER, 100, 100));

					cells32[3] = PdfUtils.newCellLeft("");
					cells32[3].setBorderColor(borderColor1);
					cells32[3].addElement(PdfUtils.newParagraph("\n", font10));
					cells32[3].addElement(PdfUtils.newImage(tongueDetailVo.getPhotoTwo(), Element.ALIGN_CENTER, 100, 100));
				} else {
					cells32[2] = PdfUtils.newCellLeft("", 2, 0);
					cells32[2].setBorderColor(borderColor1);
					cells32[2].addElement(PdfUtils.newParagraph("\n", font10));
					cells32[2].addElement(PdfUtils.newImage(tongueDetailVo.getPhoto(), Element.ALIGN_CENTER, 100, 100));
				}
				listRow3.add(new PdfPRow(cells32));

				PdfPCell[] cells33 = new PdfPCell[4];
				cells33[1] = PdfUtils.newCellCenter("辨识结果");
				cells33[1].setBackgroundColor(backgroundColor1);
				// "<span style=\\\"color:#B61617\\\">红舌</span>";
				String strField = "";
				if (StringUtils.isNotBlank(tongueDetailVo.getField())) {
					if (tongueDetailVo.getField().indexOf("\">") > 0 && tongueDetailVo.getField().indexOf("</") > 0
							&& tongueDetailVo.getField().indexOf("\">") < tongueDetailVo.getField().indexOf("</")) {
						strField = tongueDetailVo.getField().substring(tongueDetailVo.getField().indexOf("\">") + 2, tongueDetailVo.getField().indexOf("</"));
					} else {
						strField = tongueDetailVo.getField();
					}
				}
				cells33[2] = PdfUtils.newCellLeft(strField, 2, 0);
				cells33[2].setBackgroundColor(backgroundColor1);
				listRow3.add(new PdfPRow(cells33));

				// 详情，举例："【厚苔】透过舌苔不能见到舌体。</br>【病理意义】体内有湿，或有积食。</br>【腻苔】苔质颗粒细腻致密，融合成片，紧贴于舌面，揩之不去，刮之不易脱落。</br>【病理意义】多与湿浊，痰饮，食积相关。</br>";
				List<String> strList = new ArrayList<>();
				String str1 = "";
				String str2 = "";
				if (StringUtils.isNotBlank(tongueDetailVo.getValue())) {
					// 去除br
					String str = tongueDetailVo.getValue().replaceAll("</br>", "");
					// 分开
					String[] strs = str.split("【");
					for (String s : strs) {
						if (StringUtils.isNotBlank(s)) {
							if (s.startsWith("病理意义")) {
								str2 = str2 + "【" + s + "\n";
							} else {
								str1 = str1 + "【" + s + "\n";
							}
						}
					}
				}
				strList.add(str1.length() > 0 ? str1.substring(0, str1.length() - 1) : "");
				strList.add(str2.length() > 0 ? str2.substring(0, str2.length() - 1) : "");

				PdfPCell[] cells34 = new PdfPCell[4];
				cells34[0] = PdfUtils.newCellCenter("中医解析", 0, 2);
				cells34[1] = PdfUtils.newCellCenter("中医描述");
				cells34[2] = PdfUtils.newCellLeft(strList.get(0), 2, 0);
				listRow3.add(new PdfPRow(cells34));

				PdfPCell[] cells35 = new PdfPCell[4];
				cells35[1] = PdfUtils.newCellCenter("病理意义");
				cells35[1].setBackgroundColor(backgroundColor1);
				cells35[2] = PdfUtils.newCellLeft(strList.get(1), 2, 0);
				cells35[2].setBackgroundColor(backgroundColor1);
				listRow3.add(new PdfPRow(cells35));

				document.add(table3);
				if (tongueDetailVo != tongueVo.getAiTongueVOList().get(tongueVo.getAiTongueVOList().size() - 1)) {
					document.add(PdfUtils.newParagraph("\n", font16));
				}

			}
		}

	}

	/**
	 * 第四页，面部望诊
	 *
	 * @param document
	 * @throws Exception
	 */
	private static void fourPage(Document document) {
		// 数据库数据
		AiConsultFlowVO faceVo = pdfResVo.getFaceVo();

		document.newPage();

		// 标题
		document.add(PdfUtils.newParagraph("三、面部望诊\n", font24Bold));

		// 1.面部图像采集结果
		document.add(PdfUtils.newParagraph("面部图像采集结果\n", font20Bold, Element.ALIGN_CENTER));
		//document.add(PdfUtils.newImage(testImgUrl2, Element.ALIGN_CENTER, 100, 150));
		document.add(PdfUtils.newImage(faceVo.getFaceFrontImg(), Element.ALIGN_CENTER, 100, 150));
		document.add(PdfUtils.newParagraph("正面象\n\n", font16, Element.ALIGN_CENTER));

		// 2.面部分析结果汇总
		PdfPTable table1 = new PdfPTable(2);
		table1.setWidthPercentage(100);
		List<PdfPRow> listRow1 = table1.getRows();
		table1.setWidths(new float[]{1f, 3f});

		PdfPCell[] cells11 = new PdfPCell[2];
		cells11[0] = PdfUtils.newCell("面部分析结果汇总", font16Bold, 2, 0, Element.ALIGN_CENTER, null, borderColor1);
		listRow1.add(new PdfPRow(cells11));

			/*Boolean flag = true;
			for (int i = 0; i < 6; i++) {
				PdfPCell[] cells12 = new PdfPCell[2];
				cells12[0] = PdfUtils.newCellCenter("面色：" + i);
				if (flag) {
					cells12[0].setBackgroundColor(backgroundColor1);
				}
				cells12[1] = PdfUtils.newCellLeft("面色红黄隐隐，明润含蓄");
				if (flag) {
					cells12[1].setBackgroundColor(backgroundColor1);
				}
				listRow1.add(new PdfPRow(cells12));
				flag = !flag;
			}*/
		if (CollectionUtils.isNotEmpty(faceVo.getAiFaceItemVOList())) {
			Boolean flag = true;
			for (AiReportItemVO faceItemVo : faceVo.getAiFaceItemVOList()) {
				PdfPCell[] cells12 = new PdfPCell[2];
				cells12[0] = PdfUtils.newCellCenter(faceItemVo.getKey());
				if (flag) {
					cells12[0].setBackgroundColor(backgroundColor1);
				}
				cells12[1] = PdfUtils.newCellLeft(faceItemVo.getValue());
				if (flag) {
					cells12[1].setBackgroundColor(backgroundColor1);
				}
				listRow1.add(new PdfPRow(cells12));
				flag = !flag;
			}
		}
		document.add(table1);
		document.add(PdfUtils.newParagraph("\n", font16));

		// 4.中医解析
			/*PdfPTable table3 = new PdfPTable(3);
			table3.setWidthPercentage(100);
			List<PdfPRow> listRow3 = table3.getRows();
			table3.setWidths(new float[]{1f, 2f, 6f});

			PdfPCell[] cells31 = new PdfPCell[3];
			cells31[0] = PdfUtils.newCell("面色——中医解析", font16Bold, 3, 0, Element.ALIGN_CENTER, null, borderColor1);
			listRow3.add(new PdfPRow(cells31));

			PdfPCell[] cells32 = new PdfPCell[3];
			cells32[0] = PdfUtils.newCellCenter("人工智能分析", 0, 2);
			cells32[1] = PdfUtils.newCellCenter("面色分析");
			cells32[2] = PdfUtils.newCell("");
			cells32[2].setBorderColor(borderColor1);
			cells32[2].addElement(PdfUtils.newImage(testImgUrl2, 100, 100));
			listRow3.add(new PdfPRow(cells32));

			PdfPCell[] cells33 = new PdfPCell[3];
			cells33[1] = PdfUtils.newCellCenter("辨识结果");
			cells33[1].setBackgroundColor(backgroundColor1);
			cells33[2] = PdfUtils.newCellLeft("面色红黄隐隐，明润含蓄");
			cells33[2].setBackgroundColor(backgroundColor1);
			listRow3.add(new PdfPRow(cells33));

			PdfPCell[] cells34 = new PdfPCell[3];
			cells34[0] = PdfUtils.newCellCenter("中医解析", 0, 2);
			cells34[1] = PdfUtils.newCellCenter("中医描述");
			cells34[2] = PdfUtils.newCellLeft("【正常面色】面色红黄隐隐，明润含蓄。【正常面色】面色红黄隐隐，明润含蓄。【正常面色】面色红黄隐隐，明润含蓄。");
			listRow3.add(new PdfPRow(cells34));

			PdfPCell[] cells35 = new PdfPCell[3];
			cells35[1] = PdfUtils.newCellCenter("病理意义");
			cells35[1].setBackgroundColor(backgroundColor1);
			cells35[2] = PdfUtils.newCellLeft("【病理意义】神气、胃气的正常色泽表现。");
			cells35[2].setBackgroundColor(backgroundColor1);
			listRow3.add(new PdfPRow(cells35));

			document.add(table3);
			document.add(PdfUtils.newParagraph("\n", font16));
			document.add(table3);
			document.add(PdfUtils.newParagraph("\n", font16));
			document.add(table3);
			document.add(PdfUtils.newParagraph("\n", font16));*/
		if (CollectionUtils.isNotEmpty(faceVo.getAiFaceVOList())) {
			for (AiReportItemVO faceDetailVo : faceVo.getAiFaceVOList()) {
				PdfPTable table3 = new PdfPTable(4);
				table3.setWidthPercentage(100);
				List<PdfPRow> listRow3 = table3.getRows();
				table3.setWidths(new float[]{1f, 2f, 3f, 3f});

				PdfPCell[] cells31 = new PdfPCell[4];
				cells31[0] = PdfUtils.newCell(faceDetailVo.getKey() + "——中医解析", font16Bold, 4, 0, Element.ALIGN_CENTER, null, borderColor1);
				listRow3.add(new PdfPRow(cells31));

				PdfPCell[] cells32 = new PdfPCell[4];
				cells32[0] = PdfUtils.newCellCenter("人工智能分析", 0, 2);
				cells32[1] = PdfUtils.newCellCenter(faceDetailVo.getKey() + "分析");
				if (StringUtils.isNotBlank(faceDetailVo.getPhotoTwo())) {
					cells32[2] = PdfUtils.newCellLeft("");
					cells32[2].setBorderColor(borderColor1);
					cells32[2].addElement(PdfUtils.newParagraph("\n", font10));
					cells32[2].addElement(PdfUtils.newImage(faceDetailVo.getPhoto(), Element.ALIGN_CENTER, 100, 100));

					cells32[3] = PdfUtils.newCellLeft("");
					cells32[3].setBorderColor(borderColor1);
					cells32[3].addElement(PdfUtils.newParagraph("\n", font10));
					cells32[3].addElement(PdfUtils.newImage(faceDetailVo.getPhotoTwo(), Element.ALIGN_CENTER, 100, 100));
				} else {
					cells32[2] = PdfUtils.newCellLeft("", 2, 0);
					cells32[2].setBorderColor(borderColor1);
					cells32[2].addElement(PdfUtils.newParagraph("\n", font10));
					cells32[2].addElement(PdfUtils.newImage(faceDetailVo.getPhoto(), Element.ALIGN_CENTER, 100, 100));
				}
				listRow3.add(new PdfPRow(cells32));

				PdfPCell[] cells33 = new PdfPCell[4];
				cells33[1] = PdfUtils.newCellCenter("辨识结果");
				cells33[1].setBackgroundColor(backgroundColor1);
				cells33[2] = PdfUtils.newCellLeft(faceDetailVo.getField(), 2, 0);
				cells33[2].setBackgroundColor(backgroundColor1);
				listRow3.add(new PdfPRow(cells33));

				// 详情，举例："【厚苔】透过舌苔不能见到舌体。</br>【病理意义】体内有湿，或有积食。</br>【腻苔】苔质颗粒细腻致密，融合成片，紧贴于舌面，揩之不去，刮之不易脱落。</br>【病理意义】多与湿浊，痰饮，食积相关。</br>";
				List<String> strList = new ArrayList<>();
				String str1 = "";
				String str2 = "";
				if (StringUtils.isNotBlank(faceDetailVo.getValue())) {
					// 去除br
					String str = faceDetailVo.getValue().replaceAll("</br>", "");
					// 分开
					String[] strs = str.split("【");
					for (String s : strs) {
						if (StringUtils.isNotBlank(s)) {
							if (s.startsWith("病理意义")) {
								str2 = str2 + "【" + s + "\n";
							} else {
								str1 = str1 + "【" + s + "\n";
							}
						}
					}
				}
				strList.add(str1.length() > 0 ? str1.substring(0, str1.length() - 1) : "");
				strList.add(str2.length() > 0 ? str2.substring(0, str2.length() - 1) : "");

				PdfPCell[] cells34 = new PdfPCell[4];
				cells34[0] = PdfUtils.newCellCenter("中医解析", 0, 2);
				cells34[1] = PdfUtils.newCellCenter("中医描述");
				cells34[2] = PdfUtils.newCellLeft(strList.get(0), 2, 0);
				listRow3.add(new PdfPRow(cells34));

				PdfPCell[] cells35 = new PdfPCell[4];
				cells35[1] = PdfUtils.newCellCenter("病理意义");
				cells35[1].setBackgroundColor(backgroundColor1);
				cells35[2] = PdfUtils.newCellLeft(strList.get(1), 2, 0);
				cells35[2].setBackgroundColor(backgroundColor1);
				listRow3.add(new PdfPRow(cells35));

				document.add(table3);
				if (faceDetailVo != faceVo.getAiFaceVOList().get(faceVo.getAiFaceVOList().size() - 1)) {
					document.add(PdfUtils.newParagraph("\n", font16));
				}
			}
		}

	}

	/**
	 * 第五页，健康分析
	 *
	 * @param document
	 * @throws Exception
	 */
	private static void fivePage(Document document) {
		// 数据库数据
		AiConsultFlowVO detailVo = pdfResVo.getDetailVo();
		AiTreatPlanJsonVo healthVo = pdfResVo.getHealthVo();

		document.newPage();

		// 标题
		document.add(PdfUtils.newParagraph("四、健康分析\n", font24Bold));

		// 1.健康分析结果
		document.add(PdfUtils.newParagraph("健康分析结果\n\n", font20Bold, Element.ALIGN_CENTER));

		PdfPTable table1 = new PdfPTable(2);
		table1.setWidthPercentage(100);
		List<PdfPRow> listRow1 = table1.getRows();
		table1.setWidths(new float[]{1f, 3f});

		PdfPCell[] cells12 = new PdfPCell[2];
		cells12[0] = PdfUtils.newCell("依据您的舌象、面象及近期身体感受，AI中医辨识出您的健康状态", font16Bold, 2, 0, Element.ALIGN_CENTER, null, borderColor1);
		listRow1.add(new PdfPRow(cells12));

		PdfPCell[] cells13 = new PdfPCell[2];
		cells13[0] = PdfUtils.newCellCenter("健康状态");
		cells13[0].setBackgroundColor(backgroundColor1);
		//cells13[1] = PdfUtils.newCellLeft("气虚痰湿");
		cells13[1] = PdfUtils.newCellLeft(detailVo.getDiagnosticResults());
		cells13[1].setBackgroundColor(backgroundColor1);
		listRow1.add(new PdfPRow(cells13));

		PdfPCell[] cells14 = new PdfPCell[2];
		cells14[0] = PdfUtils.newCellCenter("风险评分");
		cells14[0].setBackgroundColor(backgroundColor1);
		//cells14[1] = PdfUtils.newCellLeft("80.29 分（风险）\n综合舌象、面象分析属于亚健康状态，建议您一周后再次检测。");
		cells14[1] = PdfUtils.newCellLeft(detailVo.getTongueScore() + "\n" + detailVo.getTongueTips());
		cells14[1].setBackgroundColor(backgroundColor1);
		listRow1.add(new PdfPRow(cells14));

		document.add(table1);

		// 2.（一）主要表现
		document.add(PdfUtils.newParagraph("（一）主要表现\n\n", font20Bold, Element.ALIGN_CENTER));

		PdfPTable table2 = new PdfPTable(1);
		table2.setWidthPercentage(100);
		List<PdfPRow> listRow2 = table2.getRows();
		table2.setWidths(new float[]{1f});

			/*PdfPCell[] cells22 = new PdfPCell[1];
			cells22[0] = PdfUtils.newCellLeft("面色微黄或虚浮或淡黄而暗，唇色淡白，毛发少光泽，多油脂。");
			listRow2.add(new PdfPRow(cells22));

			PdfPCell[] cells23 = new PdfPCell[1];
			cells23[0] = PdfUtils.newCellLeft("口黏，痰多，气短胸闷，肢体酸重，易疲倦乏力。");
			cells23[0].setBackgroundColor(backgroundColor1);
			listRow2.add(new PdfPRow(cells23));

			PdfPCell[] cells24 = new PdfPCell[1];
			cells24[0] = PdfUtils.newCellLeft("性格喜安静，语声低弱或懒言。");
			listRow2.add(new PdfPRow(cells24));*/
		if (CollectionUtils.isNotEmpty(healthVo.getMain_performance())) {
			Boolean flag2 = true;
			for (AiTreatPlanJsonVo.HealthCareExample healthCareExample : healthVo.getMain_performance()) {
				PdfPCell[] cells22 = new PdfPCell[1];
				cells22[0] = PdfUtils.newCellLeft(healthCareExample.getMessage());
				if (flag2) {
					cells22[0].setBackgroundColor(backgroundColor1);
				}
				listRow2.add(new PdfPRow(cells22));
				flag2 = !flag2;
			}
		}

		document.add(table2);

		// 3.健康风险
		document.add(PdfUtils.newParagraph("（二）风险提示\n\n", font20Bold, Element.ALIGN_CENTER));
		document.add(PdfUtils.newParagraph("1.健康风险\n\n", font20Bold, Element.ALIGN_CENTER));

		PdfPTable table3 = new PdfPTable(2);
		table3.setWidthPercentage(100);
		List<PdfPRow> listRow3 = table3.getRows();
		table3.setWidths(new float[]{1f, 1f});

			/*PdfPCell[] cells31 = new PdfPCell[2];
			cells31[0] = PdfUtils.newCellCenter("");
			cells31[0].addElement(PdfUtils.newImage(testImgUrl2, 200, 300));
			cells31[1] = PdfUtils.newCell("\n感冒\n眩晕\n流涕\n咳痰\n中风\n", font16Bold, 0, 0, Element.ALIGN_LEFT, null, borderColor1);
			listRow3.add(new PdfPRow(cells31));*/
		if (CollectionUtils.isNotEmpty(healthVo.getHighRiskDiseaseList())) {
			PdfPCell[] cells31 = new PdfPCell[2];
			cells31[0] = PdfUtils.newCellCenter("");
			cells31[0].addElement(PdfUtils.newImage(healthVo.getHighRiskDiseaseList().get(0).getImg(), 200, 300));
			//眩晕,健忘,精神不振,心悸,视力模糊
			String highStr = "";
			if (StringUtils.isNotBlank(healthVo.getHighRiskDiseaseList().get(0).getDesc())) {
				highStr = "\n" + healthVo.getHighRiskDiseaseList().get(0).getDesc().replaceAll(",", "\n") + "\n";
			}
			cells31[1] = PdfUtils.newCell(highStr, font16Bold, 0, 0, Element.ALIGN_LEFT, null, borderColor1);
			listRow3.add(new PdfPRow(cells31));
		}

		document.add(table3);

		// 4.节气风险
		if (healthVo.getIllProbabilities() != null) {
			document.add(PdfUtils.newParagraph("2、节气风险\n\n", font20Bold, Element.ALIGN_CENTER));

			PdfPTable table4 = new PdfPTable(1);
			table4.setWidthPercentage(100);
			List<PdfPRow> listRow4 = table4.getRows();
			table4.setWidths(new float[]{1f});

			PdfPCell[] cells41 = new PdfPCell[1];
			//cells41[0] = PdfUtils.newCellLeft("当前节气：白露     患病风险：中");
			cells41[0] = PdfUtils.newCellLeft("当前节气：" + healthVo.getIllProbabilities().getName()
					+ "     患病风险：" + healthVo.getIllProbabilities().getLevel());
			listRow4.add(new PdfPRow(cells41));

			PdfPCell[] cells42 = new PdfPCell[1];
			//cells42[0] = PdfUtils.newCellLeft("提示：是气候转凉的开始，昼夜温差变化大，注意避免雾天出行，预防哮喘和支气管炎等病发作");
			cells42[0] = PdfUtils.newCellLeft("提示：" + healthVo.getIllProbabilities().getTip());
			cells42[0].setBackgroundColor(backgroundColor1);
			listRow4.add(new PdfPRow(cells42));

			PdfPCell[] cells43 = new PdfPCell[1];
			//cells43[0] = PdfUtils.newCellLeft("说明：气虚不能运化水湿，聚而生痰，常见头晕乏力、口中粘腻、胸闷痰多等表现，夏季降水量增多患病风险出现上升。");
			cells43[0] = PdfUtils.newCellLeft("说明：" + healthVo.getIllProbabilities().getExplanation());
			listRow4.add(new PdfPRow(cells43));

			document.add(table4);
		}

		// 5.发生原因
		document.add(PdfUtils.newParagraph("（三）发生原因\n\n", font20Bold, Element.ALIGN_CENTER));

		PdfPTable table5 = new PdfPTable(1);
		table5.setWidthPercentage(100);
		List<PdfPRow> listRow5 = table5.getRows();
		table5.setWidths(new float[]{1f});

			/*PdfPCell[] cells51 = new PdfPCell[1];
			cells51[0] = PdfUtils.newCellLeft("多因素体气虚；或病久气虚；气虚不足以维持正常津液代谢，致使机体水湿停聚，形成气虚痰湿的证候。");
			listRow5.add(new PdfPRow(cells51));

			PdfPCell[] cells52 = new PdfPCell[1];
			cells52[0] = PdfUtils.newCellLeft("1、湿邪阻滞气机——气候潮湿，或淋雨涉水，或久居潮湿之所，湿邪侵袭人体，或喜食肥甘厚腻，损伤脾胃，致使湿浊内生，困阻于脾，致气虚痰湿共存。");
			cells52[0].setBackgroundColor(backgroundColor1);
			listRow5.add(new PdfPRow(cells52));

			PdfPCell[] cells53 = new PdfPCell[1];
			cells53[0] = PdfUtils.newCellLeft("2、先天不足或后天失养——禀赋不足，或久病伤正，或劳倦太过伤脾，气血亏乏，不能运化水湿，致气虚痰湿。");
			listRow5.add(new PdfPRow(cells53));

			PdfPCell[] cells54 = new PdfPCell[1];
			cells54[0] = PdfUtils.newCellLeft("3、久病——久病不愈，失于调养，导致气血亏乏，不能运化水湿，致气虚痰湿。");
			listRow5.add(new PdfPRow(cells54));*/
		if (CollectionUtils.isNotEmpty(healthVo.getOccur_reason())) {
			Boolean flag5 = true;
			for (AiTreatPlanJsonVo.HealthCareExample healthCareExample : healthVo.getOccur_reason()) {
				PdfPCell[] cells51 = new PdfPCell[1];
				cells51[0] = PdfUtils.newCellLeft(healthCareExample.getMessage());
				if (flag5) {
					cells51[0].setBackgroundColor(backgroundColor1);
				}
				listRow5.add(new PdfPRow(cells51));
				flag5 = !flag5;
			}
		}

		document.add(table5);

	}

	/**
	 * 第六页，调理方案+门店信息
	 *
	 * @param document
	 * @throws Exception
	 */
	private static void sixPage(Document document) {
		// 数据库数据
		AiConsultFlowVO detailVo = pdfResVo.getDetailVo();
		AiTreatPlanJsonVo conditioningVo = pdfResVo.getConditioningVo();

		document.newPage();

		// 标题
		document.add(PdfUtils.newParagraph("五、调理方案\n", font24Bold));

		// 1.（一）药物保健
		document.add(PdfUtils.newParagraph("（一）药物保健\n\n", font20Bold, Element.ALIGN_CENTER));

			/*PdfPTable table1 = new PdfPTable(3);
			table1.setWidthPercentage(100);
			List<PdfPRow> listRow1 = table1.getRows();
			table1.setWidths(new float[]{1f, 1f, 1f});

			PdfPCell[] cells11 = new PdfPCell[3];
			cells11[0] = PdfUtils.newCell("①常用补气类中药单品：西洋参、黄精、白术等；", font16Bold, 3, 0, Element.ALIGN_LEFT, null, borderColor1);
			cells11[0].setBackgroundColor(backgroundColor1);
			listRow1.add(new PdfPRow(cells11));

			PdfPCell[] cells12 = new PdfPCell[3];
			cells12[0] = PdfUtils.newCell("", font16Bold, 0, 0, Element.ALIGN_CENTER, PdfPCell.LEFT, borderColor1);
			cells12[0].addElement(PdfUtils.newImage(testImgUrl3, Element.ALIGN_CENTER, 50, 50));
			cells12[1] = PdfUtils.newCell("", font16Bold, 0, 0, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, borderColor1);
			cells12[1].addElement(PdfUtils.newImage(testImgUrl3, Element.ALIGN_CENTER, 50, 50));
			cells12[2] = PdfUtils.newCell("", font16Bold, 0, 0, Element.ALIGN_CENTER, PdfPCell.RIGHT, borderColor1);
			cells12[2].addElement(PdfUtils.newImage(testImgUrl3, Element.ALIGN_CENTER, 50, 50));
			listRow1.add(new PdfPRow(cells12));

			PdfPCell[] cells13 = new PdfPCell[3];
			cells13[0] = PdfUtils.newCell("西洋参", font16Bold, 0, 0, Element.ALIGN_CENTER, PdfPCell.LEFT, borderColor1);
			cells13[1] = PdfUtils.newCell("黄精", font16Bold, 0, 0, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, borderColor1);
			cells13[2] = PdfUtils.newCell("白术", font16Bold, 0, 0, Element.ALIGN_CENTER, PdfPCell.RIGHT, borderColor1);
			listRow1.add(new PdfPRow(cells13));

			document.add(table1);*/
		if (CollectionUtils.isNotEmpty(conditioningVo.getDrug_health_care())) {
			Integer cellNum = 1;
			if (CollectionUtils.isNotEmpty(conditioningVo.getDrug_health_care().get(0).getItems())) {
				cellNum = conditioningVo.getDrug_health_care().get(0).getItems().size();
			}

			PdfPTable table1 = new PdfPTable(cellNum);
			table1.setWidthPercentage(100);
			List<PdfPRow> listRow1 = table1.getRows();
			float[] f = new float[cellNum];
			for (int i = 0; i < cellNum; i++) {
				f[i] = 1f;
			}
			table1.setWidths(f);

			PdfPCell[] cells11 = new PdfPCell[cellNum];
			cells11[0] = PdfUtils.newCell(conditioningVo.getDrug_health_care().get(0).getMessage(), font16Bold, cellNum, 0, Element.ALIGN_LEFT, PdfPCell.BOX, borderColor1);
			cells11[0].setBackgroundColor(backgroundColor1);
			listRow1.add(new PdfPRow(cells11));

			if (CollectionUtils.isNotEmpty(conditioningVo.getDrug_health_care().get(0).getItems())) {
				PdfPCell[] cells12 = new PdfPCell[cellNum];
				for (int i = 0; i < conditioningVo.getDrug_health_care().get(0).getItems().size(); i++) {
					AiTreatPlanJsonVo.HealthCareExampleItems item = conditioningVo.getDrug_health_care().get(0).getItems().get(i);
					cells12[i] = PdfUtils.newCell("", font16Bold, 0, 0, Element.ALIGN_CENTER, PdfPCell.BOX, borderColor1);
					cells12[i].addElement(PdfUtils.newImage(item.getPhoto(), Element.ALIGN_CENTER, 50, 50));
				}
				listRow1.add(new PdfPRow(cells12));

				PdfPCell[] cells13 = new PdfPCell[cellNum];
				for (int i = 0; i < conditioningVo.getDrug_health_care().get(0).getItems().size(); i++) {
					AiTreatPlanJsonVo.HealthCareExampleItems item = conditioningVo.getDrug_health_care().get(0).getItems().get(i);
					cells13[i] = PdfUtils.newCell(item.getName(), font16Bold, 0, 0, Element.ALIGN_CENTER, PdfPCell.BOX, borderColor1);
				}
				listRow1.add(new PdfPRow(cells13));
			}
			document.add(table1);
			document.add(PdfUtils.newParagraph("", font16));
		}

			/*PdfPTable table2 = new PdfPTable(2);
			table2.setWidthPercentage(100);
			List<PdfPRow> listRow2 = table2.getRows();
			table2.setWidths(new float[]{1f, 1f});

			PdfPCell[] cells21 = new PdfPCell[2];
			cells21[0] = PdfUtils.newCell("②常用化湿类中药单品：砂仁、陈皮、豆蔻等；", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			cells21[0].setBackgroundColor(backgroundColor1);
			listRow2.add(new PdfPRow(cells21));

			PdfPCell[] cells22 = new PdfPCell[2];
			cells22[0] = PdfUtils.newCell("③常用益气化湿类复方：二陈平胃散、导痰汤、四苓散等。", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			cells22[0].setBackgroundColor(backgroundColor1);
			listRow2.add(new PdfPRow(cells22));

			for (int i = 0; i < 3; i++) {
				PdfPCell[] cells23 = new PdfPCell[2];
				cells23[0] = PdfUtils.newCellCenter("");
				cells23[0].addElement(PdfUtils.newImage(testImgUrl3, Element.ALIGN_CENTER, 150, 150));
				cells23[0].addElement(PdfUtils.newParagraph("二陈平胃散", font16, Element.ALIGN_CENTER));
				cells23[1] = PdfUtils.newCellLeft("【功效】燥湿化痰，理气和中\n" +
						"【主治】食积咳嗽，五更为甚，\n" +
						"胸脘满闷，舌苔白腻，脉濡滑者。\n" +
						"【组成】熟半夏、白茯苓、广\n" +
						"皮、甘草、熟苍术、厚朴\n" +
						"【来源】《症因脉治》");
				listRow2.add(new PdfPRow(cells23));
			}

			PdfPCell[] cells24 = new PdfPCell[2];
			cells24[0] = PdfUtils.newCell("以上方案仅供参考，需要在专业中医师的指导下根据自身情况服用", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			cells24[0].setBackgroundColor(backgroundColor1);
			listRow2.add(new PdfPRow(cells24));

			document.add(table2);*/
		if (CollectionUtils.isNotEmpty(conditioningVo.getDrug_health_care()) && conditioningVo.getDrug_health_care().size() > 1) {
			for (int i = 1; i < conditioningVo.getDrug_health_care().size(); i++) {
				AiTreatPlanJsonVo.HealthCareExample healthCareExample = conditioningVo.getDrug_health_care().get(i);
				PdfPTable table2 = new PdfPTable(2);
				table2.setWidthPercentage(100);
				List<PdfPRow> listRow2 = table2.getRows();
				table2.setWidths(new float[]{1f, 1f});

				PdfPCell[] cells22 = new PdfPCell[2];
				cells22[0] = PdfUtils.newCell(healthCareExample.getMessage(), font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
				cells22[0].setBackgroundColor(backgroundColor1);
				listRow2.add(new PdfPRow(cells22));

				if (CollectionUtils.isNotEmpty(healthCareExample.getItems())) {
					for (AiTreatPlanJsonVo.HealthCareExampleItems item : healthCareExample.getItems()) {
						PdfPCell[] cells23 = new PdfPCell[2];
						cells23[0] = PdfUtils.newCellCenter("");
						cells23[0].addElement(PdfUtils.newImage(item.getPhoto(), Element.ALIGN_CENTER, 150, 150));
						cells23[0].addElement(PdfUtils.newParagraph(item.getName(), font16, Element.ALIGN_CENTER));
						cells23[1] = PdfUtils.newCellLeft(item.getDesc());
						listRow2.add(new PdfPRow(cells23));
					}
				}

				document.add(table2);
				document.add(PdfUtils.newParagraph("", font16));
			}
		}

		// 2.（二）穴位保健
		document.add(PdfUtils.newParagraph("（二）穴位保健\n\n", font20Bold, Element.ALIGN_CENTER));

			/*PdfPTable table3 = new PdfPTable(2);
			table3.setWidthPercentage(100);
			List<PdfPRow> listRow3 = table3.getRows();
			table3.setWidths(new float[]{1f, 1f});

			PdfPCell[] cells31 = new PdfPCell[2];
			cells31[0] = PdfUtils.newCell("气虚痰湿的人群需要选择益气健脾、祛湿化痰的穴位为主，通过在这些穴位上进行点揉、艾灸、针刺，以帮助改善体质状况。根据您的健康状态辨识结果为您推荐如下方案：", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			cells31[0].setBackgroundColor(backgroundColor1);
			listRow3.add(new PdfPRow(cells31));

			PdfPCell[] cells32 = new PdfPCell[2];
			cells32[0] = PdfUtils.newCell("【取穴】气海穴、足三里穴、丰隆穴、承山穴、脾俞穴、关元穴【方法】按揉（或艾灸，或贴敷，或针刺），每穴20-30分钟，隔日一次。", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			cells32[0].setBackgroundColor(backgroundColor1);
			listRow3.add(new PdfPRow(cells32));

			for (int i = 0; i < 3; i++) {
				PdfPCell[] cells33 = new PdfPCell[2];
				cells33[0] = PdfUtils.newCell("气海穴的保健方法：", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
				cells33[0].setBackgroundColor(backgroundColor1);
				listRow3.add(new PdfPRow(cells33));

				PdfPCell[] cells34 = new PdfPCell[2];
				cells34[0] = PdfUtils.newCellCenter("");
				cells34[0].addElement(PdfUtils.newImage(testImgUrl3, Element.ALIGN_CENTER, 150, 150));
				cells34[0].addElement(PdfUtils.newParagraph("二陈平胃散", font16, Element.ALIGN_CENTER));
				cells34[1] = PdfUtils.newCellLeft("【位置】在下腹部，脐中下1.5\n" +
						"寸，前正中线上。\n" +
						"【取穴】在下腹部，肚脐中央向\n" +
						"下与关元之间的中点处。");
				listRow3.add(new PdfPRow(cells34));
			}

			PdfPCell[] cells35 = new PdfPCell[2];
			cells35[0] = PdfUtils.newCell("以上方案仅做参考，具体操作建议进一步咨询专业中医师（医疗行为需要由专业中医师进行操作）。", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			cells35[0].setBackgroundColor(backgroundColor1);
			listRow3.add(new PdfPRow(cells35));

			document.add(table3);*/
		if (CollectionUtils.isNotEmpty(conditioningVo.getMassage_health_care())) {
			for (int i = 1; i < conditioningVo.getMassage_health_care().size(); i++) {
				AiTreatPlanJsonVo.MassageHealthCareItem massageHealthCareItem = conditioningVo.getMassage_health_care().get(i);
				PdfPTable table3 = new PdfPTable(2);
				table3.setWidthPercentage(100);
				List<PdfPRow> listRow3 = table3.getRows();
				table3.setWidths(new float[]{1f, 1f});

				PdfPCell[] cells33 = new PdfPCell[2];
				cells33[0] = PdfUtils.newCell(massageHealthCareItem.getMessage(), font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
				cells33[0].setBackgroundColor(backgroundColor1);
				listRow3.add(new PdfPRow(cells33));

				if (StringUtils.isNotBlank(massageHealthCareItem.getPhoto())) {
					PdfPCell[] cells34 = new PdfPCell[2];
					cells34[0] = PdfUtils.newCellCenter("");
					cells34[0].addElement(PdfUtils.newImage(massageHealthCareItem.getPhoto(), Element.ALIGN_CENTER, 150, 150));
					cells34[0].addElement(PdfUtils.newParagraph(massageHealthCareItem.getBottom(), font16, Element.ALIGN_CENTER));
					cells34[1] = PdfUtils.newCellLeft(massageHealthCareItem.getDesc());
					listRow3.add(new PdfPRow(cells34));
				}

				document.add(table3);
				document.add(PdfUtils.newParagraph("", font16));
			}
		}

		// 3.（三）饮食调养
		document.add(PdfUtils.newParagraph("（三）饮食调养\n\n", font20Bold, Element.ALIGN_CENTER));

			/*PdfPTable table4 = new PdfPTable(2);
			table4.setWidthPercentage(100);
			List<PdfPRow> listRow4 = table4.getRows();
			table4.setWidths(new float[]{1f, 1f});

			PdfPCell[] cells41 = new PdfPCell[2];
			cells41[0] = PdfUtils.newCell("【宜食】主要遵循低盐、低脂肪饮食，宜多食白萝卜、扁豆、荠菜、紫菜、海带、芹菜、冬瓜叶、丝瓜、荸荠、荷叶、山楂、冬瓜籽、黄瓜、山药、薏苡仁、胡萝卜、赤小豆等\n【忌食】少食肥甘油腻、酸涩食品、寒凉酸味水果。忌过饱食", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			cells41[0].setBackgroundColor(backgroundColor1);
			listRow4.add(new PdfPRow(cells41));

			for (int i = 0; i < 3; i++) {
				PdfPCell[] cells33 = new PdfPCell[2];
				cells33[0] = PdfUtils.newCell("白术党参茯苓粥", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
				cells33[0].setBackgroundColor(backgroundColor1);
				listRow4.add(new PdfPRow(cells33));

				PdfPCell[] cells34 = new PdfPCell[2];
				cells34[0] = PdfUtils.newCellCenter("");
				cells34[0].addElement(PdfUtils.newImage(testImgUrl3, Element.ALIGN_CENTER, 150, 150));
				cells34[0].addElement(PdfUtils.newParagraph("二陈平胃散", font16, Element.ALIGN_CENTER));
				cells34[1] = PdfUtils.newCellLeft("【药材】薏苡仁适量，白术、党\n" +
						"参、茯苓、甘草各15克\n" +
						"【食材】红枣3颗\n" +
						"【烹饪方法】 步骤1：将红枣、\n" +
						"薏苡仁洗净，红枣去核，备用；\n" +
						"步骤2：将白术、党参、茯苓、甘\n" +
						"草洗净，加入300毫升水煮沸后，以\n" +
						"小火煎成200毫升；\n" +
						"步骤3：滤取出药汁在煮好的药汁\n" +
						"中加入薏苡仁、红枣，以大火煮开，\n" +
						"再转入小火熬煮成粥，加入适当的调\n" +
						"味料即可。\n" +
						"【功效】健脾益气、祛湿利水\n" +
						"【引自】《超简单面诊消百病全\n" +
						"书》");
				listRow4.add(new PdfPRow(cells34));
			}

			document.add(table4);*/
		if (CollectionUtils.isNotEmpty(conditioningVo.getEatingHabits())) {
			PdfPTable table4 = new PdfPTable(1);
			table4.setWidthPercentage(100);
			List<PdfPRow> listRow4 = table4.getRows();
			table4.setWidths(new float[]{1f});

			String eatStr = "";
			for (AiDietRehabilitationVo.EatingHabitsItem eatingHabit : conditioningVo.getEatingHabits()) {
				if (eatStr.length() > 0) {
					eatStr = eatStr + "\n";
				}
				eatStr = eatStr + "【" + eatingHabit.getTitle() + "】" + eatingHabit.getContent();
			}
			PdfPCell[] cells41 = new PdfPCell[2];
			cells41[0] = PdfUtils.newCell(eatStr, font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			cells41[0].setBackgroundColor(backgroundColor1);
			listRow4.add(new PdfPRow(cells41));

			document.add(table4);
		}
		if (CollectionUtils.isNotEmpty(conditioningVo.getDiet_rehabilitation())) {
			PdfPTable table4 = new PdfPTable(2);
			table4.setWidthPercentage(100);
			List<PdfPRow> listRow4 = table4.getRows();
			table4.setWidths(new float[]{1f, 1f});

			for (AiTreatPlanJsonVo.HealthCareExample healthCareExample : conditioningVo.getDiet_rehabilitation()) {
				PdfPCell[] cells33 = new PdfPCell[2];
				cells33[0] = PdfUtils.newCell(healthCareExample.getMessage(), font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
				cells33[0].setBackgroundColor(backgroundColor1);
				listRow4.add(new PdfPRow(cells33));

				for (AiTreatPlanJsonVo.HealthCareExampleItems item : healthCareExample.getItems()) {
					PdfPCell[] cells34 = new PdfPCell[2];
					cells34[0] = PdfUtils.newCellCenter("");
					cells34[0].addElement(PdfUtils.newImage(item.getPhoto(), Element.ALIGN_CENTER, 150, 150));
					cells34[0].addElement(PdfUtils.newParagraph(item.getName(), font16, Element.ALIGN_CENTER));
					cells34[1] = PdfUtils.newCellLeft(item.getDesc());
					listRow4.add(new PdfPRow(cells34));
				}
			}

			document.add(table4);
		}

		// 5.（四）运动保健
		document.add(PdfUtils.newParagraph("（四）运动保健\n\n", font20Bold, Element.ALIGN_CENTER));

			/*PdfPTable table5 = new PdfPTable(2);
			table5.setWidthPercentage(100);
			List<PdfPRow> listRow5 = table5.getRows();
			table5.setWidths(new float[]{1f, 1f});

			PdfPCell[] cells51 = new PdfPCell[2];
			cells51[0] = PdfUtils.newCell("气虚痰湿体质的人群应尽量避免在炎热和潮湿的环境中锻炼，同时避免运动量大、短时间以及快速爆发的运动。可根据自身情况循序渐进，坚持长期规律的有氧运动。如：八段锦、五禽戏、太极拳、慢跑、乒乓球、羽毛球、网球、爬山等运动。其中八段锦的“调理脾胃须单举”和五禽戏的“熊戏”可重复多做几遍。", font16, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			listRow5.add(new PdfPRow(cells51));

			PdfPCell[] cells52 = new PdfPCell[2];
			cells52[0] = PdfUtils.newCell("注意事项：", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			cells52[0].setBackgroundColor(backgroundColor1);
			listRow5.add(new PdfPRow(cells52));

			PdfPCell[] cells53 = new PdfPCell[2];
			cells53[0] = PdfUtils.newCell("若出现呼吸困难、眩晕、发力、面色发白、大汗淋漓不止、胸闷、心胸刺痛等症状时，请立即停止运动，并前往最近的医院就诊，或拨打120急救。", font16, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			listRow5.add(new PdfPRow(cells53));

			document.add(table5);*/
		if (CollectionUtils.isNotEmpty(conditioningVo.getSports_health_care())) {
			PdfPTable table5 = new PdfPTable(2);
			table5.setWidthPercentage(100);
			List<PdfPRow> listRow5 = table5.getRows();
			table5.setWidths(new float[]{1f, 1f});

			Boolean flag5 = true;
			for (AiTreatPlanJsonVo.HealthCareExample healthCareExample : conditioningVo.getSports_health_care()) {
				PdfPCell[] cells51 = new PdfPCell[2];
				cells51[0] = PdfUtils.newCell(healthCareExample.getMessage(), font16, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
				if (flag5) {
					cells51[0].setBackgroundColor(backgroundColor1);
				}
				listRow5.add(new PdfPRow(cells51));
				flag5 = !flag5;
			}
			document.add(table5);
		}

		// 6.（五）情志调养
		document.add(PdfUtils.newParagraph("（五）情志调养\n\n", font20Bold, Element.ALIGN_CENTER));

			/*PdfPTable table6 = new PdfPTable(2);
			table6.setWidthPercentage(100);
			List<PdfPRow> listRow6 = table6.getRows();
			table6.setWidths(new float[]{1f, 1f});

			PdfPCell[] cells61 = new PdfPCell[2];
			cells61[0] = PdfUtils.newCell("①精神调摄", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			listRow6.add(new PdfPRow(cells61));

			PdfPCell[] cells62 = new PdfPCell[2];
			cells62[0] = PdfUtils.newCell("日常生活中保持平和的心态，宜多参加社会活动，培养广泛的兴趣爱好，宜欣赏激进、振奋的音乐。", font16, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			cells62[0].setBackgroundColor(backgroundColor1);
			listRow6.add(new PdfPRow(cells62));

			PdfPCell[] cells63 = new PdfPCell[2];
			cells63[0] = PdfUtils.newCell("②起居调摄", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			listRow6.add(new PdfPRow(cells63));

			PdfPCell[] cells64 = new PdfPCell[2];
			cells64[0] = PdfUtils.newCell("居住环境宜干燥，不宜潮湿。穿衣面料以棉、麻、丝等透气散湿的天然纤维为佳，尽量保持宽松，有利于汗液蒸发，祛除体内湿气。起居宜规律，保证充足睡眠，劳逸相结合，根据气候变化适时增减衣物。", font16, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			cells64[0].setBackgroundColor(backgroundColor1);
			listRow6.add(new PdfPRow(cells64));

			PdfPCell[] cells65 = new PdfPCell[2];
			cells65[0] = PdfUtils.newCell("②起居调摄", font16Bold, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			listRow6.add(new PdfPRow(cells65));

			PdfPCell[] cells66 = new PdfPCell[2];
			cells66[0] = PdfUtils.newCell("居住环境宜干燥，不宜潮湿。穿衣面料以棉、麻、丝等透气散湿的天然纤维为佳，尽量保持宽松，有利于汗液蒸发，祛除体内湿气。起居宜规律，保证充足睡眠，劳逸相结合，根据气候变化适时增减衣物。", font16, 2, 0, Element.ALIGN_LEFT, null, borderColor1);
			cells66[0].setBackgroundColor(backgroundColor1);
			listRow6.add(new PdfPRow(cells66));

			document.add(table6);*/
		if (CollectionUtils.isNotEmpty(conditioningVo.getRecuperates())) {
			PdfPTable table6 = new PdfPTable(1);
			table6.setWidthPercentage(100);
			List<PdfPRow> listRow6 = table6.getRows();
			table6.setWidths(new float[]{1f});

			Boolean flag6 = true;
			for (AiTreatPlanJsonVo.HealthCareExample recuperate : conditioningVo.getRecuperates()) {
				PdfPCell[] cells61 = new PdfPCell[1];
				cells61[0] = PdfUtils.newCellLeft(recuperate.getMessage());
				if (flag6) {
					cells61[0].setBackgroundColor(backgroundColor1);
				}
				listRow6.add(new PdfPRow(cells61));
				flag6 = !flag6;
			}

			document.add(table6);
		}

		// 7.门店信息
		document.add(PdfUtils.newParagraph("\n\n门店信息\n\n", font20Bold, Element.ALIGN_CENTER));

		PdfPTable table7 = new PdfPTable(1);
		table7.setWidthPercentage(100);
		List<PdfPRow> listRow7 = table7.getRows();
		table7.setWidths(new float[]{1f});

		PdfPCell[] cells71 = new PdfPCell[1];
		//cells71[0] = PdfUtils.newCell("门店名称：桥美郡天使宝贝母婴店", font16Bold, 0, 0, Element.ALIGN_LEFT, null, borderColor1);
		cells71[0] = PdfUtils.newCell("门店名称：" + detailVo.getUserShopDetailVO().getShopName(), font16Bold, 0, 0, Element.ALIGN_LEFT, null, borderColor1);
		listRow7.add(new PdfPRow(cells71));

		PdfPCell[] cells72 = new PdfPCell[1];
		//cells72[0] = PdfUtils.newCell("门店地址：湖南省株洲市天元区湖南省天元区康", font16Bold, 0, 0, Element.ALIGN_LEFT, null, borderColor1);
		cells72[0] = PdfUtils.newCell("门店地址：" + detailVo.getUserShopDetailVO().getShopAddressDetail(), font16Bold, 0, 0, Element.ALIGN_LEFT, null, borderColor1);
		listRow7.add(new PdfPRow(cells72));

		PdfPCell[] cells73 = new PdfPCell[1];
		//cells73[0] = PdfUtils.newCell("手机号：13787338089", font16Bold, 0, 0, Element.ALIGN_LEFT, null, borderColor1);
		cells73[0] = PdfUtils.newCell("手机号：" + detailVo.getUserShopDetailVO().getMobile(), font16Bold, 0, 0, Element.ALIGN_LEFT, null, borderColor1);
		listRow7.add(new PdfPRow(cells73));

		PdfPCell[] cells74 = new PdfPCell[1];
		cells74[0] = PdfUtils.newCellLeft("");
		//cells74[0].addElement(PdfUtils.newImage(testTitleUrl1, 500, 400));
		cells74[0].addElement(PdfUtils.newImage(detailVo.getUserShopDetailVO().getShopPhoto(), 500, 400));
		listRow7.add(new PdfPRow(cells74));

		document.add(table7);

	}

	/**
	 * 数据正确性判断，和pdf是否要生成
	 *
	 * @param vo
	 * @return
	 */
	private static GeneratePdfVO queryData(GeneratePdfVO vo) {
		vo.setIsGenerate(true);
		return vo;
	}

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
	 * 生成pdf，返回url
	 *
	 * @param vo
	 * @return
	 */
	public static void main(String[] args) {
	//public BaseResponse<String> generatePdf(GeneratePdfVO vo) {

		GeneratePdfVO vo = new GeneratePdfVO();
		vo.setReportId("1166030276602761216");
		vo.setOpenid("oEpd363KwdU5Pdab4e2ojmnxRZ4o");

		// 创建文档对象
		Document document = new Document();
		File file = null;
		Boolean isSuccess = false;

		try {
			LocalDateTime begin = LocalDateTime.now();
			System.out.println("===test===begin===" + begin);

			// 数据正确性判断，和pdf是否要生成
			LocalDateTime queryData = LocalDateTime.now();
			/*vo = queryData(vo);
			if (!vo.getIsGenerate()) {
				return BaseResponse.success(vo.getPdfUrl());
			}*/
			System.out.println("===test===queryData===" + Duration.between(begin, queryData).toMillis());

			// 文件
			file = new File(vo.getReportId() + ".pdf");

			// 查询pdf数据
			LocalDateTime queryPdf = LocalDateTime.now();
			pdfResVo = queryPdfResVo(vo);
			System.out.println("===test===queryPdf===" + Duration.between(begin, queryPdf).toMillis());

			// 指定PDF文件的输出路径
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));

			// 添加页眉和页脚和水印
			LocalDateTime setPageEvent = LocalDateTime.now();
			writer.setPageEvent(new HeaderAndFooterPageEventHelper());
			System.out.println("===test===setPageEvent===" + Duration.between(queryPdf, setPageEvent).toMillis());

			// 打开文档
			document.open();

			// 第一页，标题+个人信息+二维码
			LocalDateTime end1 = LocalDateTime.now();
			onePage(document);
			System.out.println("===test===end1===" + Duration.between(setPageEvent, end1).toMillis());

			// 第二页，目录+图片
			LocalDateTime end2 = LocalDateTime.now();
			twoPage(document);
			System.out.println("===test===end2===" + Duration.between(end1, end2).toMillis());

			// 第三页，健康状态+舌象辨识
			LocalDateTime end3 = LocalDateTime.now();
			threePage(document);
			System.out.println("===test===end3===" + Duration.between(end2, end3).toMillis());

			// 第四页，面部望诊
			LocalDateTime end4 = LocalDateTime.now();
			fourPage(document);
			System.out.println("===test===end4===" + Duration.between(end3, end4).toMillis());

			// 第五页，健康分析
			LocalDateTime end5 = LocalDateTime.now();
			fivePage(document);
			System.out.println("===test===end5===" + Duration.between(end4, end5).toMillis());

			// 第六页，调理方案+门店信息
			LocalDateTime end6 = LocalDateTime.now();
			sixPage(document);
			System.out.println("===test===end6===" + Duration.between(end5, end6).toMillis());

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
					/*String uploadUrl = aliYunOssUtilsGeneral.upLoad(file, "update_center/pdf");
					System.out.println("===uploadUrl==={}", uploadUrl);
					vo.setPdfUrl(UPLOAD_URL + uploadUrl);

					// 更新数据库
					AiConsultFlowEntity entity = new AiConsultFlowEntity();
					entity.setId(vo.getId());
					entity.setPdfUrl(vo.getPdfUrl());
					System.out.println("===updateById==={}"+JSON.toJSONString(entity));
					aiConsultFlowMapper.updateById(entity);*/

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				/*if (file != null) {
					file.delete();
				}*/
			}

			System.out.println("===vo===end==={}" + JSON.toJSONString(vo));
			//return BaseResponse.success(vo.getPdfUrl());
		}
	}

}
