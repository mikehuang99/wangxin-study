package com.wangxin.langshu.business.edu.vo.market.coupon;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class CouponViewVo  implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7857614053627453384L;
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private int	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	
	@ApiModelProperty(value = "优惠券代码	", required = false)
    private String couponCode;//优惠券代码	
	@ApiModelProperty(value = "优惠券名称", required = false)
    private String couponName;//优惠券名称
	@ApiModelProperty(value = "优惠券金额", required = false)
    private BigDecimal couponAmount;//优惠券金额
	@ApiModelProperty(value = "使用课程限制，0不限制，1限制课程", required = false)
    private int isLimitCourse;//使用课程限制，0不限制，1限制课程
	@ApiModelProperty(value = "优惠券有效期开始	", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date couponTimeBegin;//优惠券有效期开始	
	@ApiModelProperty(value = "优惠券有效期结束", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date couponTimeEnd;//优惠券有效期结束
	
	@ApiModelProperty(value = "可领取优惠券的开始时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date couponAssignTimeBegin;//可领取优惠券的开始时间
	@ApiModelProperty(value = "可领取优惠券的结束时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date couponAssignTimeEnd;//可领取优惠券的结束时间
	
	@ApiModelProperty(value = "优惠券描述", required = false)
    private String couponDesc;//优惠券描述
	
	@ApiModelProperty(value = "已经选择的课程ID的集合串(以英文逗号分割)", required = false)
	private String selectedCourseIdsString;//已经选择的课程ID的集合串(以英文逗号分割)
	
	
	
}
