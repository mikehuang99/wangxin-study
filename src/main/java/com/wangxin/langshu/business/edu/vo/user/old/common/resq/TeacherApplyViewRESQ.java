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
 * 教师信息-审核分页信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TeacherApplyViewRESQ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5483397397681967457L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
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
	 * 审核状态(0:待审核,1:审核通过,2:审核不通过)
	 */
	@ApiModelProperty(value = "审核状态(0:待审核,1:审核通过,2:审核不通过)")
	private Integer applyStatus;
	/**
	 * 审核意见
	 */
	@ApiModelProperty(value = "审核意见")
	private String applyResultDesc;
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
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱")
	private String teacherEmail;
	/**
	 * 教师简介
	 */
	@ApiModelProperty(value = "教师简介")
	private String introduce;



}