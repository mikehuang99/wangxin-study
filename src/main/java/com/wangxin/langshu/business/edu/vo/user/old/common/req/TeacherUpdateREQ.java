package com.wangxin.langshu.business.edu.vo.user.old.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 教师信息更新(直接生效)
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TeacherUpdateREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7602558658653248933L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private String id;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
	private Integer validValue;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序", required = false)
	private Integer sort;
	/**
	 * 教师用户编号
	 */
	@ApiModelProperty(value = "教师用户编号", required = false)
	private String teacherUserNo;
	/**
	 * 教师名称
	 */
	@ApiModelProperty(value = "教师名称", required = false)
	private String teacherName;
	/**
	 * 教师手机
	 */
	@ApiModelProperty(value = "教师手机", required = false)
	private String teacherMobile;
	/**
	 * 教师邮箱
	 */
	@ApiModelProperty(value = "教师邮箱", required = false)
	private String teacherEmail;
	/**
	 * 职位
	 */
	@ApiModelProperty(value = "职位", required = false)
	private String position;
	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像", required = false)
	private String headImgUrl;
	/**
	 * 简介
	 */
	@ApiModelProperty(value = "简介", required = false)
	private String introduce;


}
