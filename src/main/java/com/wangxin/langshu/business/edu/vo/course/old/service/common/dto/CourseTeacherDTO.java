/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.vo.course.old.service.common.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 教师信息
 *
 * 
 */
@Data
@Accessors(chain = true)
public class CourseTeacherDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 教师用户编号
	 */
	@ApiModelProperty(value = "教师编号", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private String teacherUserNo;
	/**
	 * 教师名称
	 */
	@ApiModelProperty(value = "教师名称", required = true)
	private String teacherName;
	/**
	 * 职位
	 */
	@ApiModelProperty(value = "教师职位", required = true)
	private String position;
	/**
	 * 头像
	 */
	@ApiModelProperty(value = "教师头像", required = true)
	private String imgLogo;
	/**
	 * 简介
	 */
	@ApiModelProperty(value = "教师介绍", required = true)
	private String introduce;

}
