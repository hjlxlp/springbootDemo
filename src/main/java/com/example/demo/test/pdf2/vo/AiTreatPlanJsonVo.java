package com.example.demo.test.pdf2.vo;

import lombok.Data;

import java.util.List;

/**
 * 健康指导方案VO
 *
 * @author liangyh
 */
@Data
public class AiTreatPlanJsonVo {

    /**
     * 角色
     */
    private AiUserRoleEnum aiUserRoleEnum;
    /**
     * 诊断结果
     */
    private String diagnostic_results;


    /**
     * 回答内容
     */
    private List<String> answerList;
    /**
     * 运动保健
     */
    private List<HealthCareExample> sports_health_care;

    @Data
    public static class HealthCareExample {
        /**
         * 描述
         */
        private String message;

        /**
         * 例子
         */
        private List<HealthCareExampleItems> items;
    }

    @Data
    public static class HealthCareExampleItems {
        /**
         * 例子名称
         */
        private String name;

        /**
         * 例子描述
         */
        private String desc;

        /**
         * 例子视频地址
         */
        private String player_url;

        /**
         * 例子图片地址
         */
        private String photo;

        /**
         * 例子跳转地址
         */
        private String jumpUrl;

        /**
         * 例子音频地址
         */
        private String audio_url;
    }

    /**
     * 饮食习惯
     */
    private List<AiDietRehabilitationVo.EatingHabitsItem> eatingHabits;

    /**
     * 饮食调养
     */
    private List<HealthCareExample> diet_rehabilitation;

    /**
     * 饮食调养
     */
    private AiDietRehabilitationVo diet_rehabilitation_v2022;

    /**
     * 易患病症
     */
    private List<HealthCareExample> predisposition;

    /**
     * 易患病症
     */
    private List<AiDietRehabilitationVo.EatingHabitsItem> eatingHabitsItemList;

    /**
     * 艾灸按摩
     */
    private List<MassageHealthCareItem> massage_health_care;

    @Data
    public static class MassageHealthCareItem {
        /**
         * 名称
         */
        private String message;

        /**
         * 穴位
         */
        private String bottom;

        /**
         * 位置及描述
         */
        private String desc;

        /**
         * 图片
         */
        private String photo;
    }

    /**
     * 主要表现
     */
    private List<HealthCareExample> main_performance;

    /**
     * 发生原因
     */
    private List<HealthCareExample> occur_reason;

    /**
     * 发生原因
     */
    private AiTongueResultVO.IllProbabilities illProbabilities;

    /**
     * 药物保健
     */
    private List<HealthCareExample> drug_health_care;

    /**
     * 药物保健
     */
    private List<HealthCareExample> drug_health_care_new;

    /**
     * 情志起居调养
     */
    private List<HealthCareExample> recuperates;

    /**
     * 膳食调理
     */
    private List<DietaryConditioningItem> dietaryConditioningList;

    @Data
    public static class DietaryConditioningItem {
        /**
         * 标题
         */
        private String title;
        /**
         * 内容
         */
        private String content;
    }

    /**
     * 营养搭配
     */
    private List<NutritionalMatchItem> nutritionalMatchList;

    @Data
    public static class NutritionalMatchItem {
        /**
         * 1：必选 0：非必选
         */
        private String selectModel;
        /**
         * 名称
         */
        private String name;
        /**
         * 注意   可选（或必选）
         */
        private String notice;
        /**
         * 产品名称
         */
        private List<productInfoItem> productInfoList;

    }

    @Data
    public static class productInfoItem {
        /**
         * 图片
         */
        private String img;
        /**
         * 信息
         */
        private String message;
        /**
         * 产品名称
         */
        private String productName;
    }

    /**
     * 高风险提示
     */
    private List<highRiskDiseaseItem> highRiskDiseaseList;

    @Data
    public static class highRiskDiseaseItem {
        /**
         * 信息
         */
        private String message;
        /**
         * 描述
         */
        private String desc;
        /**
         * 图片
         */
        private String img;
    }

}
