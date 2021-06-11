package com.wangxin.langshu.business.edu.entity.market;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="coupon")
public class Coupon extends Model<Coupon> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5714734692331680618L;
	//@TableId(type = IdType.UUID)
    private String id;
	@TableField(value ="create_time")
    private Date createTime;//创建时间	
	@TableField(value = "modified_time",update="now()")
    private Date modifiedTime;	//修改时间	
	@TableField(value = "valid_value")
    private int	validValue;//状态(1:正常，0:禁用)	
	@TableField(value = "sort")
    private int sort;//排序
	@TableField(value = "coupon_code")
    private String couponCode;//优惠券代码	
	@TableField(value = "coupon_name")
    private String couponName;//优惠券名称
	@TableField(value = "coupon_amount")
    private BigDecimal couponAmount;//优惠券金额
	@TableField(value = "is_limit_course")
    private int isLimitCourse;//使用课程限制，0不限制，1限制课程
	@TableField(value = "coupon_time_begin")
    private Date couponTimeBegin;//优惠券有效期开始	
	@TableField(value = "coupon_time_end")
    private Date couponTimeEnd;//优惠券有效期结束
	@TableField(value = "coupon_assign_time_begin")
    private Date couponAssignTimeBegin;//可领取优惠券开始时间
	@TableField(value = "coupon_assign_time_end")
    private Date couponAssignTimeEnd;//可领取优惠券结束时间
	@TableField(value = "coupon_desc")
    private String couponDesc;//优惠券描述
	
	
}
