//package com.example.demo.elasticserach.document;
//
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import java.io.Serializable;
//import java.util.Date;
//
///**
// * @author huangjiale
// * @date 2020/4/10 13:32
// **/
//@Data
//@Document(indexName = "escity", type = "city")
//public class EsCity implements Serializable {
//
//    /**
//     * 城市编号
//     */
//    @Id
//    private Long id;
//
//    /**
//     * 省份编号
//     */
//    private Long provinceId;
//
//    /**
//     * 城市名称
//     */
//    @Field(type = FieldType.Keyword)
//    private String cityName;
//
//    /**
//     * 描述
//     */
//    @Field(analyzer = "ik_max_word", type = FieldType.Text)
//    private String description;
//
//    /**
//     * 创建时间
//     */
//    private Date createTime;
//
//}
