package com.wangxin.langshu.business.edu.vo.order.order;

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
public class OrderMainSaveVo  implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1304734341155736051L;
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
	
	@ApiModelProperty(value = "用户编号", required = false)
	private String orderUserNo;//用户编号
	@ApiModelProperty(value = "订单号", required = false)
	private String orderNo;//订单号
	@ApiModelProperty(value = "课程ID", required = false)
	private String courseId;//课程ID
	@ApiModelProperty(value = "应付金额", required = false)
	private BigDecimal payableAmount;//应付金额
	@ApiModelProperty(value = "折扣金额", required = false)
	private BigDecimal discountAmount;//折扣金额
	@ApiModelProperty(value = "折扣后应付金额(包括学习卡、优惠券的金额)", required = false)
	private BigDecimal payableActualAmount;//折扣后应付金额(包括学习卡、优惠券的金额)
	@ApiModelProperty(value = "实付金额(不包括学习卡、优惠券的金额)", required = false)
	private BigDecimal payAmount;//实付金额(不包括学习卡、优惠券的金额)
	@ApiModelProperty(value = "支付状态，0未支付，1完成支付，2过期订单，3取消订单", required = false)
	private int payStatus;//支付状态，0未支付，1完成支付，2过期订单，3取消订单
	@ApiModelProperty(value = "支付时间	", required = false)
	private Date payTime;//支付时间	
	@ApiModelProperty(value = "订单支付最后期限	", required = false)
	private Date lastDateForPayment;//订单支付最后期限	
	
	@ApiModelProperty(value = "PC支付的二维码qrCode", required = false)
	private String qrCode;//PC支付的二维码qrCode
	@ApiModelProperty(value = "系统备注", required = false)
	private String systemRemark;//系统备注
	@ApiModelProperty(value = "客户备注", required = false)
	private String remark;//客户备注
	
	//以下是页面传来的参数，数据库没有的
	@ApiModelProperty(value = "支付方式，0未确定,1支付宝,2微信", required = false)
	private int payType;//支付方式，0未确定,1支付宝,2微信
	@ApiModelProperty(value = "卡ID，当cardType为1的时候代表的是学习卡的ID，当cardType为2的时候代表是优惠券的ID", required = false)
	private String cardId;//卡ID，当cardType为1的时候代表的是学习卡的ID，当cardType为2的时候代表是优惠券的ID
	@ApiModelProperty(value = "卡类型，当cardType为1的时候代表的是学习卡，当cardType为2的时候代表是优惠券", required = false)
	private int cardType;//卡类型，当cardType为1的时候代表的是学习卡，当cardType为2的时候代表是优惠券
	@ApiModelProperty(value = "需要实际支付的金额，就是使用支付宝或者微信支付的金额", required = false)
	private BigDecimal shouldPay;//需要实际支付的金额，就是使用支付宝或者微信支付的金额
	
	@ApiModelProperty(value = "页面传来的用户编号", required = false)
	private String userNo;//页面传来的用户编号
	
	
	@ApiModelProperty(value = "平台，PC端为pcweb，移动端为mobile", required = false)
	private String platform;//平台，PC端为pcweb，移动端为mobile
	@ApiModelProperty(value = "支付宝移动端返回的跳转html表单内容", required = false)
	private String alipayHtmlForm;
	

	
	
}
