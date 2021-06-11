package com.wangxin.langshu.business.edu.vo.market.coupon;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class CouponQ  implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1808979599418828481L;
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private String	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	
	@ApiModelProperty(value = "优惠券代码	", required = false)
    private String couponCode;//优惠券代码	
	@ApiModelProperty(value = "优惠券名称", required = false)
    private String couponName;//优惠券名称
	@ApiModelProperty(value = "优惠券金额", required = false)
    private String couponAmount;//优惠券金额
	@ApiModelProperty(value = "使用课程限制，0不限制，1限制课程", required = false)
    private String isLimitCourse;//使用课程限制，0不限制，1限制课程
	@ApiModelProperty(value = "优惠券有效期开始查询开始	", required = false)
    private String couponTimeBeginBegin;//优惠券有效期开始查询开始
	@ApiModelProperty(value = "优惠券有效期开始查询结束	", required = false)
    private String couponTimeBeginEnd;//优惠券有效期开始查询结束
	@ApiModelProperty(value = "优惠券有效期结束查询开始", required = false)
    private String couponTimeEndBegin;//优惠券有效期结束查询开始
	@ApiModelProperty(value = "优惠券有效期结束查询结束", required = false)
    private String couponTimeEndEnd;//优惠券有效期结束查询结束
	
	@ApiModelProperty(value = "可领取优惠券的开始时间开始	", required = false)
    private String couponAssignTimeBeginBegin;//优惠券有效期开始查询开始
	@ApiModelProperty(value = "可领取优惠券的开始时间结束	", required = false)
    private String couponAssignTimeBeginEnd;//优惠券有效期开始查询结束
	@ApiModelProperty(value = "可领取优惠券的结束时间开始", required = false)
    private String couponAssignTimeEndBegin;//优惠券有效期结束查询开始
	@ApiModelProperty(value = "可领取优惠券的结束时间结束", required = false)
    private String couponAssignTimeEndEnd;//优惠券有效期结束查询结束
	
	
	@ApiModelProperty(value = "优惠券描述", required = false)
    private String couponDesc;//优惠券描述
	
	@ApiModelProperty(value = "用户userNo", required = false)
    private String userNo;//用户userNo
	
	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页", required = true)
	private int pageCurrent = 1;
	/**
	 * 每页记录数
	 */
	@ApiModelProperty(value = "每页记录数", required = true)
	private int pageSize = 20;
	
}
