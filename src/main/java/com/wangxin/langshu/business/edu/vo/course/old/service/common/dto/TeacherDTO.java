package com.wangxin.langshu.business.edu.vo.course.old.service.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class TeacherDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键id")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date modifiedTime;
	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态(1:正常,0:禁用)")
	private Integer validValue;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;
	/**
	 * 教师用户编号
	 */
	@ApiModelProperty(value = "教师用户编号")
	@JsonSerialize(using = ToStringSerializer.class)
	private String teacherUserNo;
	/**
	 * 教师名称
	 */
	@ApiModelProperty(value = "教师名称")
	private String teacherName;
	/**
	 * 教师手机
	 */
	@ApiModelProperty(value = "教师手机")
	private String teacherMobile;
	/**
	 * 教师邮箱
	 */
	@ApiModelProperty(value = "教师邮箱")
	private String teacherEmail;
	/**
	 * 职位
	 */
	@ApiModelProperty(value = "职位")
	private String position;
	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像")
	private String headImgUrl;
	/**
	 * 简介
	 */
	@ApiModelProperty(value = "简介")
	private String introduce;

	
	public String getId(){
		return id + "";
	}
	
	public String getTeacherUserNo(){
		return teacherUserNo + "";
	}
	
	
}
