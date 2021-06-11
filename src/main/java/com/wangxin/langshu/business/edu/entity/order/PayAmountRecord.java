package com.wangxin.langshu.business.edu.entity.order;

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
@TableName(value ="pay_amount_record")
public class PayAmountRecord extends Model<PayAmountRecord> implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = -7953314932302345455L;
	//@TableId(type = IdType.UUID)
	@TableField(value ="id")
    private String id;
	@TableField(value ="create_time")
    private Date createTime;//创建时间	
	@TableField(value = "modified_time",update="now()")
    private Date modifiedTime;	//修改时间	
	@TableField(value = "valid_value")
    private int	validValue;//状态(1:正常，0:禁用)	
	@TableField(value = "sort")
    private int sort;//排序
	
	@TableField(value = "order_id")
	private String orderId;//订单ID
	@TableField(value = "pay_type")
	private int payType;//支付方式
	@TableField(value = "pay_amount")
	private BigDecimal payAmount;//支付金额
	@TableField(value = "pay_type_relate_id")
	private String payTypeRelateId;//付款类型相关的ID，如学习卡就是学习卡的ID、优惠券就是优惠券的ID

}

