package com.wangxin.langshu.business.edu.vo.course.old.service.common.req;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程分类-分页列出
 *
 *  
 */
@Data
@Accessors(chain = true)
public class CourseSubjectPageREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
	private Integer validValue;
	/**
	 * 分类类型(1课程)
	 */
	@ApiModelProperty(value = "分类类型(1:课程)", required = false)
	private Integer subjectType;
	/**
	 * 分类名称
	 */
	@ApiModelProperty(value = "分类名称", required = false)
	private String subjectName;
	/**
	 * 层级
	 */
	@ApiModelProperty(value = "层级", required = false)
	private Integer floor;
	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页", required = true)
	private Integer pageCurrent = 1;
	
	/**
	 * 父分类ID
	 */
	@ApiModelProperty(value = "父分类ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String parentId;

	/**
	 * 每页条数
	 */
	@ApiModelProperty(value = "每页条数", required = true)
	private Integer pageSize = 20;
}
