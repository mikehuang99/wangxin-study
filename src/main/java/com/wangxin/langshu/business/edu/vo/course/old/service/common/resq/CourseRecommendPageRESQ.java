package com.wangxin.langshu.business.edu.vo.course.old.service.common.resq;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程推荐-分页列出
 *
 *  
 */
@Data
@Accessors(chain = true)
public class CourseRecommendPageRESQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;

	/**
	 * 状态(1:正常;0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常;0:禁用)")
	private Integer validValue;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;
	/**
	 * 分类ID
	 */
	@ApiModelProperty(value = "分类ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String subjectId;
	/**
	 * 课程ID
	 */
	@ApiModelProperty(value = "课程ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String courseId;
	/**
	 * 课程名称
	 */
	@ApiModelProperty(value = "课程名称")
	private String courseName;

}
