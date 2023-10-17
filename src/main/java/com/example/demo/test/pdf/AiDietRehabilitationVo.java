package com.example.demo.test.pdf;

import lombok.Data;

import java.util.List;

/**
 * 健康指导方案-饮食调养VO
 *
 * @author liangyh
 */
@Data
public class AiDietRehabilitationVo {
    /**
     * 膳食调理
     */
    private List<DietaryRecuperateItem> dietaryRecuperate;

    /**
     * 饮食习惯
     */
    private List<EatingHabitsItem> eatingHabits;

    @Data
    public static class DietaryRecuperateItem {
        /**
         * 引自
         */
        private String reference;

        /**
         * 图片
         */
        private String img;

        /**
         * 烹饪方法
         */
        private List<String> cookMethods;

        /**
         * 名称
         */
        private String name;

        /**
         * 食材
         */
        private String food;

        /**
         * 功效
         */
        private String efficacy;

        /**
         * 药材
         */
        private String herbs;
    }

    @Data
    public static class EatingHabitsItem {
        /**
         * 名称：如宜食、忌食
         */
        private String title;

        /**
         * 内容
         */
        private String content;
    }
}
