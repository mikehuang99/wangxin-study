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
public class OrderMainVo  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 104524124243249957L;
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
	@ApiModelProperty(value = "用户真实姓名", required = false)
	private String realName;//用户真实姓名	
	@ApiModelProperty(value = "身份证号码", required = false)
    private String identityCardNo;//身份证号码
	
	@ApiModelProperty(value = "课程ID", required = false)
	private String courseId;//课程ID
	@ApiModelProperty(value = "课程名称", required = false)
	private String courseName;//课程名称
	@ApiModelProperty(value = "课程Logo", required = false)
	private String courseLogo;//课程Logo
	
	
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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date payTime;//支付时间	
	@ApiModelProperty(value = "订单支付最后期限	", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date lastDateForPayment;//订单支付最后期限	
	
	@ApiModelProperty(value = "PC支付的二维码qrCode", required = false)
	private String qrCode;//PC支付的二维码qrCode
	@ApiModelProperty(value = "系统备注", required = false)
	private String systemRemark;//系统备注
	@ApiModelProperty(value = "客户备注", required = false)
	private String remark;//客户备注
	
	@ApiModelProperty(value = "支付方式组合串(以,分隔，1支付宝，2微信支付，3学习卡，4优惠券)", required = false)
	private String payTypeStr;//支付方式组合串(以英文逗号分隔，1支付宝，2微信支付，3学习卡，4优惠券)
	@ApiModelProperty(value = "付款金额组合串，以英文逗号分隔", required = false)
	private String payAmountStr;//付款金额组合串，以英文逗号分隔
	
	@ApiModelProperty(value = "付款方式和金额组合串，以英文逗号分隔", required = false)
	private String payTypeAndAmountStr;//付款金额组合串，以英文逗号分隔，例如 [微信10元，优惠券20元]
	
}
