package com.example.demo.test.pdf;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 返回给调用者结果（1）-最终体质结果
 *
 * @author xuwenbing
 * @date 2019-02-25
 */
@Data
public class AiTongueResultVO implements Serializable {

    /**
     * oss分割图地址
     */
    private String ossSplitImgUrl;

    /**
     * oss舌下分割图地址
     */
    private String ossSplitBackImgUrl = "";

    /**
     * 过程图-剥落
     */
    private String ossBoluoImgUrl = "";

    /**
     * 过程图-裂纹
     */
    private String ossLiewenImgUrl = "";

    /**
     * 过程图-点刺
     */
    private String ossPrickImgUrl = "";

    /**
     * 过程图-瘀点
     */
    private String ossYudianImgUrl = "";

    /**
     * 过程图-舌色
     */
    private String ossSheseImgUrl = "";

    /**
     * 过程图-舌苔
     */
    private String ossTaiseImgUrl = "";

    /**
     * 过程图-舌形
     */
    private String ossShexingImgUrl = "";

    /**
     * 过程图-络脉
     */
    private String ossLuomaiLabelImgUrl = "";

    /**
     * 过程图-齿痕
     */
    private String ossToothtraceImgUrl = "";

    /**
     * 过程图-瘀斑
     */
    private String ossYubanImgUrl = "";
    //----------------------------------------------
    /**
     * 体质标识： 逗号隔开
     */
    private String constitutionCodes;

    /**
     * 体质名： 逗号隔开
     */
    private String constitutionNames;

    /**
     * 体质曲线排序
     */
    private Integer constitutionCurveSort;

    /**
     * 体质描述
     */
    private String constitutionDescribe;

    /**
     * 单一体质序号(多个逗号隔开)
     */
    private String singleConfig;

    /**
     * 单一体质名称(多个逗号隔开)
     */
    private String singleConfigName;

    /**
     * 症候名称
     */
    private String symptomName;
    //----------------------------------------------
    /**
     * 舌色名： 逗号隔开
     */
    private String colorOfTongueNames;

    /**
     * 舌色曲线排序
     */
    private Integer colorOfTongueCurveSort;

    /**
     * 舌色描述
     */
    private String colorOfTongueDescribe;
//----------------------------------------------
    /**
     * 苔色名： 逗号隔开
     */
    private String colorOfMossNames;

    /**
     * 苔色曲线排序
     */
    private Integer colorOfMossCurveSort;

    /**
     * 苔色描述
     */
    private String colorOfMossDescribe;
//----------------------------------------------
    /**
     * 舌形名： 逗号隔开
     */
    private String shapeOfTongueNames;

    /**
     * 舌形描述
     */
    private String shapeOfTongueDescribe;
    //----------------------------------------------
    /**
     * 苔质名： 逗号隔开
     */
    private String mossNames;

    /**
     * 苔质描述
     */
    private String mossDescribe;
    //----------------------------------------------
    /**
     * 津液名： 逗号隔开
     */
    private String bodyfluidNames;

    /**
     * 津液描述
     */
    private String bodyfluidDescribe;

    //----------------------------------------------
    /**
     * 络脉名： 逗号隔开
     */
    private String veinNames;

    /**
     * 络脉描述
     */
    private String veinDescribe;
    //----------------------------------------------

    /**
     * 治疗方案
     */
    private String treatPlanJson;

    //----------------------------------------------

    /**
     * 额外的过程图
     */
    private String extraProcessImgJson;

    /**
     * 舌象属性
     */
    private List<TongueAttr> tongueAttrs;

    @Data
    public static class TongueAttr {
        /**
         * 属性名称
         */
        private String attrName;

        /**
         * 属性值
         */
        private String attrValue;
    }

    /**
     * 24节气患病率
     */
    private IllProbability illProbability;

    @Data
    public static class IllProbability {
        /**
         * 属性名称
         */
        private String scope;

        /**
         * 属性值
         */
        private String tip;

        /**
         * 说明
         */
        private String explanation;

        private List<IllProbabilities> illProbabilities;
    }

    @Data
    public static class IllProbabilities {
        /**
         * 节气名称
         */
        private String name;

        /**
         * 节气患病率
         */
        private Integer probability;

        /**
         * 养生提示提示
         */
        private String tip;

        /**
         * 节气患病概率等级
         */
        private String level;

        /**
         * 说明
         */
        private String explanation;
    }
}
