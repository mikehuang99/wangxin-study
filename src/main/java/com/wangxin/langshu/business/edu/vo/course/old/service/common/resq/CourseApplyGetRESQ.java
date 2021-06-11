package com.wangxin.langshu.business.edu.vo.course.old.service.common.resq;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程信息-审核-查看
 */
@Data
@Accessors(chain = true)
public class CourseApplyGetRESQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "课程ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)")
	private Integer validValue;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;
	/**
	 * 教师用户编码
	 */
	@ApiModelProperty(value = "教师用户编码")
	@JsonSerialize(using = ToStringSerializer.class)
	private String teacherUserNo;
	/**
	 * 教师名称
	 */
	@ApiModelProperty(value = "教师名称")
	private String teacherName;

	/**
	 * 一级分类名
	 */
	@ApiModelProperty(value = "一级分类名")
	private String subjectName1;
	/**
	 * 二级分类名
	 */
	@ApiModelProperty(value = "二级分类名")
	private String subjectName2;
	/**
	 * 三级分类名
	 */
	@ApiModelProperty(value = "三级分类名")
	private String subjectName3;
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
	 * 课程介绍ID
	 */
	@ApiModelProperty(value = "课程介绍ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String introduceId;
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
	/**
	 * 是否上架(1:上架，0:下架)
	 */
	@ApiModelProperty(value = "是否上架(1:上架，0:下架)")
	private Integer isPutaway;
	/**
	 * 课程介绍
	 */
	@ApiModelProperty(value = "课程介绍")
	private String introduce;
}
