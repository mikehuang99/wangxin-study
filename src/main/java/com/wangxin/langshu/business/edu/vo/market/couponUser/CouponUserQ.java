package com.wangxin.langshu.business.edu.vo.market.couponUser;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class CouponUserQ  implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -6700789393964302882L;
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
	
	@ApiModelProperty(value = "优惠券ID", required = false)
    private String couponId;//优惠券ID
	@ApiModelProperty(value = "绑定的用户userNo", required = false)
    private String bindUserNo;//绑定的用户userNo
	@ApiModelProperty(value = "是否已经使用，0未使用，1已经使用", required = false)
    private String isUsed;//是否已经使用，0未使用，1已经使用
	
	@ApiModelProperty(value = "优惠券有效期开始查询开始	", required = false)
    private String couponTimeBeginBegin;//优惠券有效期开始查询开始
	@ApiModelProperty(value = "优惠券有效期开始查询结束	", required = false)
    private String couponTimeBeginEnd;//优惠券有效期开始查询结束
	@ApiModelProperty(value = "优惠券有效期结束查询开始", required = false)
    private String couponTimeEndBegin;//优惠券有效期结束查询开始
	@ApiModelProperty(value = "优惠券有效期结束查询结束", required = false)
    private String couponTimeEndEnd;//优惠券有效期结束查询结束
	

	@ApiModelProperty(value = "有效期结束是否今天之前的,如果是值为TRUE", required = false)
    private String isCouponTimeEndBeforeToday;//有效期结束是否今天之前的,如果是值为TRUE
	
	@ApiModelProperty(value = "用户userNo", required = false)
    private String userNo;//用户userNo
	
	@ApiModelProperty(value = "该课程是否可用", required = false)
    private String courseId;//该课程是否可用
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
