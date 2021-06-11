package com.wangxin.langshu.business.edu.vo.order.payAmountRecord;

import java.io.Serializable;
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
public class PayAmountRecordQ  implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -9025522978949034701L;
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
    private String	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	
	@ApiModelProperty(value = "订单ID", required = false)
	private String orderId;//订单ID
	@ApiModelProperty(value = "支付方式", required = false)
	private String payType;//支付方式
	@ApiModelProperty(value = "支付金额", required = false)
	private String payAmount;//支付金额
	@ApiModelProperty(value = "付款类型相关的ID，如学习卡就是学习卡的ID、优惠券就是优惠券的ID", required = false)
	private String payTypeRelateId;//付款类型相关的ID，如学习卡就是学习卡的ID、优惠券就是优惠券的ID
	
	
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
