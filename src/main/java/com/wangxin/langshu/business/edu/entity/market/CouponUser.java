package com.wangxin.langshu.business.edu.entity.market;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="coupon_user")
public class CouponUser extends Model<CouponUser> implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3101251201438740688L;
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
	@TableField(value = "coupon_id")
    private String couponId;//优惠券ID
	@TableField(value = "bind_user_no")
    private String bindUserNo;//绑定的用户userNo
	@TableField(value = "is_used")
    private int isUsed;//是否已经使用，0未使用，1已经使用
	
	
}
