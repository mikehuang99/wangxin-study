package com.wangxin.langshu.business.edu.vo.course.old.common.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单汇总
 * 
 * 
 */
@Data
@Accessors(chain = true)
public class OrderReportVO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 教师用户编号
	 */
	private String teacherUserNo;
	/**
	 * 教师名称
	 */
	private String teacherName;
	/**
	 * 课程名称
	 */
	private String courseName;
	/**
	 * 课程编号
	 */
	private String courseId;
	/**
	 * 课程销售数量
	 */
	private Integer courseCount;
	/**
	 * 销售总金额
	 */
	private BigDecimal countPayprice;
}
