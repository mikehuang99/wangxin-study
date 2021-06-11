package com.wangxin.langshu.business.edu.vo.user.old.common.dto;

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
public class TeacherViewDTO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -845661829942097285L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 教师编号
	 */
	@ApiModelProperty(value = "教师编号")
	@JsonSerialize(using = ToStringSerializer.class)
	private String teacherUserNo;
	/**
	 * 教师名称
	 */
	@ApiModelProperty(value = "教师名称")
	private String teacherName;
	/**
	 * 电话
	 */
	@ApiModelProperty(value = "电话")
	private String teacherMobile;
	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像")
	private String headImgUrl;
	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱")
	private String teacherEmail;
	/**
	 * 职位
	 */
	@ApiModelProperty(value = "职位")
	private String position;
	/**
	 * 简介
	 */
	@ApiModelProperty(value = "简介")
	private String introduce;
}
