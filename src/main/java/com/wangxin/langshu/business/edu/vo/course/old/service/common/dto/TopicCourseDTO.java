package com.wangxin.langshu.business.edu.vo.course.old.service.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TopicCourseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 课程主键
	 */
	@ApiModelProperty(value = "课程主键")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 教师用户编码
	 */
	@ApiModelProperty(value = "教师用户编码")
	@JsonSerialize(using = ToStringSerializer.class)
	private String teacherUserNo;
	/**
	 * 课程名称
	 */
	@ApiModelProperty(value = "课程名称")
	private String courseName;
	/**
	 * 课程封面
	 */
	@ApiModelProperty(value = "课程封面")
	private String courseLogo;
	/**
	 * 是否免费：1免费，0收费
	 */
	@ApiModelProperty(value = "是否免费：1免费，0收费")
	private Integer isFree;
	/**
	 * 原价
	 */
	@ApiModelProperty(value = "原价")
	private BigDecimal courseOriginal;
	/**
	 * 优惠价
	 */
	@ApiModelProperty(value = "优惠价")
	private BigDecimal courseDiscount;

}
