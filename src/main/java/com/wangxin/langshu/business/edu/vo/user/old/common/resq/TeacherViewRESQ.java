package com.wangxin.langshu.business.edu.vo.user.old.common.resq;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class TeacherViewRESQ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4417123859179245419L;
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
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
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
