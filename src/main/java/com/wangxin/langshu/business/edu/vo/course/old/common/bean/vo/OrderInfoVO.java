package com.wangxin.langshu.business.edu.vo.course.old.common.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单信息表
 *
 *  
 */
@Data
@Accessors(chain = true)
public class OrderInfoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date modifiedTime;
	/**
	 * 教师用户编号
	 */
	private String teacherUserNo;
	/**
	 * 教师名称
	 */
	private String teacherName;
	/**
	 * 用户编号
	 */
	private String userNo;
	/**
	 * 用户电话
	 */
	private String mobile;
	/**
	 * 用户注册时间
	 */
	private Date registerTime;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 课程ID
	 */
	private String courseId;
	/**
	 * 课程名称
	 */
	private String courseName;
	/**
	 * 应付金额
	 */
	private BigDecimal pricePayable;
	/**
	 * 优惠金额
	 */
	private BigDecimal priceDiscount;
	/**
	 * 实付金额
	 */
	private BigDecimal pricePay;
	/**
	 * 平台收入
	 */
	private BigDecimal platformProfit;

	/**
	 * 交易类型：1线上支付，2线下支付
	 */
	private Integer tradeType;
	/**
	 * 支付方式：1微信支付，2支付宝支付，3积分支付，4手工录单
	 */
	private Integer payType;
	/**
	 * 购买渠道：1web，2pp，3微信，4手工绑定
	 */
	private Integer channelType;
	/**
	 * 订单状态：1待支付，2成功支付，3支付失败，4已关闭，5已退款，6订单解绑
	 */
	private Integer orderStatus;
	/**
	 * 是否显示给教师(1是，0否)
	 */
	private Integer isShowTeacher;
	/**
	 * 是否显示给用户看(1是，0否)
	 */
	private Integer isShowUser;
	/**
	 * 客户备注
	 */
	private String remarkCus;
	/**
	 * 后台备注
	 */
	private String remark;
	/**
	 * 支付时间
	 */
	private Date payTime;

	/**
	 * 流水号
	 */
	private String serialNumber;
}
