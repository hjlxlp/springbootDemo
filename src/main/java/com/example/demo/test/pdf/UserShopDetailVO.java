package com.example.demo.test.pdf;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 *
 * @author fly
 *
 */
@ApiModel("店铺详情")
@Data
public class UserShopDetailVO {

  @ApiModelProperty(value = "自增id",  example = "1222")
  private Long id;

  @ApiModelProperty(value = "用户id",  example = "2")
  private Long uid;

  @ApiModelProperty(value = "是否上传合同",  example = "2")
  private Integer isContract;

  @ApiModelProperty(value = "合同照1",  example = "xxxx.png")
  private String contractPhoto1;

  @ApiModelProperty(value = "合同照2",  example = "xxxx.png")
  private String contractPhoto2;

  @ApiModelProperty(value = "合同照3",  example = "xxxx.png")
  private String contractPhoto3;

  @ApiModelProperty(value = "合同照4",  example = "xxxx.png")
  private String contractPhoto4;

  @ApiModelProperty(value = "店铺名称",  example = "母婴店")
  private String shopName;

  @ApiModelProperty(value = "店铺名称",  example = "母婴店")
  private String jobNumber;

  @ApiModelProperty(value = "联系人",   example = "母婴店")
  private String username;

  @ApiModelProperty(value = "手机号", example = "13370026711")
  private String mobile;

  @ApiModelProperty(value = "手机号", example = "13370026711")
  private List<String> mobileList;

  @ApiModelProperty(value = "uuid", example = "13370026711")
  private String uuid;

  @ApiModelProperty(value = "开户经理手机号", example = "13370026711")
  private String checkMobile;

  @ApiModelProperty(value = "维护经理手机号", example = "13370026711")
  private String managerMobile;

  @ApiModelProperty(value = "维护经理维护开始时间",  example = "xxxx.png")
  private Date managerPassTime;

  @ApiModelProperty(value = "开户时间",  example = "xxxx.png")
  private String passTime;

  @ApiModelProperty(value = "店铺类型",  example = "母婴店")
  private String shopType;

  @ApiModelProperty(value = "省",  example = "江苏省")
  private String province;

  @ApiModelProperty(value = "市",  example = "淮安市")
  private String city;

  @ApiModelProperty(value = "区县",  example = "淮阴区")
  private String district;

  //@ApiModelProperty(value = "审核进度 0 待审核 1 城市经理/办事处主任 2 区总  3 大区总 4 客服",  example = "1")
  //private UserShopDetailStatusEnum status;

  @ApiModelProperty(value = "街道/社区",  example = "某某街道")
  private String street;

  @ApiModelProperty(value = "详情地址",  example = "XX街道")
  private String addressDetail;

  @ApiModelProperty(value = "详情地址",  example = "XX街道")
  private String shopAddressDetail;

  @ApiModelProperty(value = "是否连锁 0 否 1是",  example = "1")
  private Long catena;

  @ApiModelProperty(value = "连锁数目",  example = "20")
  private Long catenaNumber;


  @ApiModelProperty(value = "身份证正面",  example = "xxxx.png")
  private String idFacePhoto;

  @ApiModelProperty(value = "身份证背面",  example = "xxxx.png")
  private String idBackPhoto;

  @ApiModelProperty(value = "营业执照",  example = "xxxx.png")
  private String licensePhoto;

  @ApiModelProperty(value = "门店照",  example = "xxxx.png")
  private String shopPhoto;

  @ApiModelProperty(value = "商品照",  example = "xxxx.png")
  private String goodsPhoto;

  @ApiModelProperty(value = "审核人uid",  example = "1")
  private Long checkUid;

  @ApiModelProperty(value = "审核人",  example = "xxxx.png")
  private String checkRealName;

  @ApiModelProperty(value = "归属区域",  example = "xxxx.png")
  private String belongArea;

  @ApiModelProperty(value = "审核人区域",  example = "xxxx.png")
  private String checkAreaLayer;

  @ApiModelProperty(value = "业务经理姓名（维护）",  example = "xxxx")
  private String managerRealName;

  @ApiModelProperty(value = "业务经理归属（维护）",  example = "xxxx")
  private String managerAreaLayer;

  @ApiModelProperty(value = "业务经理uid（维护）",  example = "xxxx")
  private Long managerUid;

  @ApiModelProperty(value = "业务经理归属区域，如果有维护经理取维护经理的，否则取开户经理的")
  private String managerOrCheckAreaLayer;

  @ApiModelProperty(value = "业务经理归属区域，如果有维护经理取维护经理的，否则取开户经理的")
  private String managerOrCheckName;

  @ApiModelProperty(value = "上一任归属业务经理区域（维护）",  example = "xxxx")
  private String hisManagerAreaLayer;

  @ApiModelProperty(value = "上一任归属业务经理（维护）",  example = "xxxx")
  private String hisManagerRealName;

  @ApiModelProperty(value = "上一任归属业务经理uid（维护）",  example = "xxxx")
  private Long hisManagerUid;

  @ApiModelProperty(value = "城市经理名称",  example = "xxxx.png")
  private String cityName;

  @ApiModelProperty(value = "城市经理审核时间",  example = "xxxx.png")
  private String cityUpdateTime;

  @ApiModelProperty(value = "城市经理审核状态",  example = "xxxx.png")
  private Integer cityStatus;

  @ApiModelProperty(value = "申请状态",  example = "xxxx.png")
  private Integer applyStatus;

  @ApiModelProperty(value = "区总名称",  example = "xxxx.png")
  private String areaName;

  @ApiModelProperty(value = "区总审核时间",  example = "xxxx.png")
  private String areaUpdateTime;

  @ApiModelProperty(value = "区总审核状态",  example = "xxxx.png")
  private Integer areaStatus;

  @ApiModelProperty(value = "总部名称",  example = "xxxx.png")
  private String headName;

  @ApiModelProperty(value = "总部审核时间",  example = "xxxx.png")
  private String headUpdateTime;

  @ApiModelProperty(value = "总部审核状态",  example = "xxxx.png")
  private Integer headStatus;

  @ApiModelProperty(value = "登录时间",  example = "xxxx.png")
  private String loginTime;

  @ApiModelProperty(value = "用户状态",  example = "xxxx.png")
  private Integer userStatus;

  @ApiModelProperty(value = "创建时间",  example = "2020-10-10 10:10:10")
  private String createTime;

  @ApiModelProperty(value = "更新时间",  example = "2020-10-10 10:10:10")
  private String updateTime;

  @ApiModelProperty(value = "身份证与营业执照法人是否一致")
  private Integer isSameCardCorporate;

  @ApiModelProperty(value = "归属办对应区域编码",  example = "412")
  private String officeCode;

  //@ApiModelProperty(value = "用户收货地址")
  //private List<UserAddressVO> userAddressVOList;

  @ApiModelProperty(value = "店铺ID",  example = "母婴店")
  private String shopId;

  //@ApiModelProperty(value = "用户品牌推荐值")
  //private List<UserHomePageElementVo> homePageElementList;

  @ApiModelProperty(value = "是否默认排序")
  private Integer isDefaultSort;

  @ApiModelProperty(value = "窜货记录")
  private String fleeRecord;

  @ApiModelProperty(value = "是否有窜货记录")
  private Integer isFleeRecord;

  @ApiModelProperty(value = "省公司")
  private String area;

  @ApiModelProperty(value = "办事处名称")
  private String officeName;

  @ApiModelProperty(value = "大区名称")
  private String bname;

  //@ApiModelProperty(value = "钉钉用户详情")
  //private UserDingQueryVO userDingQueryVO;

  @ApiModelProperty(value = "大区名称")
  private String ShopBname;

  @ApiModelProperty(value = "用户ID")
  private List<Long> userIdList;

  @ApiModelProperty(value = "商户风险等级")
  private Integer riskLevel;

  @ApiModelProperty(value = "开户审批备注")
  private String remark;

  @ApiModelProperty(value = "风险备注")
  private String riskRemark;

  @ApiModelProperty(value = "账号冻结/挂失状态")
  private Integer freezeStatus;

  //@ApiModelProperty(value = "风险用户信息")
  //private RiskUserDto riskUserDto;

}
