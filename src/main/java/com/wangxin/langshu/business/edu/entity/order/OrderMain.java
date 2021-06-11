package com.wangxin.langshu.business.edu.entity.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="order_main")
public class OrderMain extends Model<OrderMain> implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 7169065234781622647L;
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
	
	@TableField(value = "order_user_no")
	private String orderUserNo;//用户编号	
	
	@TableField(value = "order_no")
	private String orderNo;//订单号

	@TableField(value = "course_id")
	private String courseId;//课程ID
	@TableField(value = "payable_amount")
	private BigDecimal payableAmount;//应付金额
	@TableField(value = "discount_amount")
	private BigDecimal discountAmount;//折扣金额
	@TableField(value = "payable_actual_amount")
	private BigDecimal payableActualAmount;//折扣后应付金额(包括学习卡、优惠券的金额)
	@TableField(value = "pay_amount")
	private BigDecimal payAmount;//实付金额(不包括学习卡、优惠券的金额)
	@TableField(value = "pay_status")
	private int payStatus;//支付状态，0未支付，1完成支付，2过期订单，3取消订单
	@TableField(value = "pay_time")
	private Date payTime;//订单时间
	@TableField(value = "last_date_for_payment")
	private Date lastDateForPayment;//订单支付最后期限	
	@TableField(value = "qr_code")
	private String qrCode;//PC支付的二维码qrCode
	@TableField(value = "system_remark")
	private String systemRemark;//系统备注
	@TableField(value = "remark")
	private String mark;//客户备注
	
	@TableField(value = "alipay_html_form")
	private String alipayHtmlForm;//支付宝移动端返回的跳转html表单内容
	
	

}

