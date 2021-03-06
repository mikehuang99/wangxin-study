package com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程信息-审核
 *
 *  
 */
@Data
@Accessors(chain = true)
public class AuthCourseApplyViewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "课程ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 一级分类ID
	 */
	@ApiModelProperty(value = "一级分类ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String subjectId1;
	/**
	 * 二级分类ID
	 */
	@ApiModelProperty(value = "二级分类ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String subjectId2;
	/**
	 * 三级分类ID
	 */
	@ApiModelProperty(value = "三级分类ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String subjectId3;
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
	 * 课程介绍
	 */
	@ApiModelProperty(value = "课程介绍")
	private String introduce;
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
	/**
	 * 是否免费(1:免费, 0:收费)
	 */
	@ApiModelProperty(value = "是否免费(1:免费, 0:收费)")
	private Integer isFree;

}
