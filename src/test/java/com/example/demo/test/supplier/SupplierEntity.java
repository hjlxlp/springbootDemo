package com.example.demo.test.supplier;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author huangjiale
 * @date 2021/5/28 13:26
 **/
@Data
public class SupplierEntity {

    private Integer id;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 联系人名称
     */
    private String contactName;

    /**
     * 手机号
     */
    private String contactMobile;

    /**
     * 营业执照
     */
    private String businessLicence;

    /**
     * 经营场景照片
     */
    private String businessPhoto;

    /**
     * 合同照片
     */
    private String contractPhoto;

    /**
     * 分账到账周期
     */
    private Integer splitAccountCycle;

    /**
     * 第一次分账开始时间
     */
    private Date firstSplitBeginTime;

    /**
     * 分账开始时间
     */
    private Date splitBeginTime;

    /**
     * 分账结束时间
     */
    private Date splitEndTime;

    /**
     * 分账状态（默认0-关闭，1-开启）
     */
    private Integer splitStatus;

    /**
     * 供应商杉德宝分账过渡户账号
     */
    private String mid;

    /**
     * 供应商营业执照号码
     */
    private String businessLicenseNo;

    /**
     * 供应商对应平台接收方账号
     */
    private String platformAccno;

    @TableLogic
    private Integer deleted;
    private Date createTime;
    private Date updateTime;

}
