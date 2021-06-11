package com.wangxin.langshu.business.edu.vo.user.old.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 教师信息-审核更新
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TeacherApplyUpdateREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3042639704131130714L;
	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID", required = true)
	private String id;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
	private Integer validValue;
	/**
	 * 教师名称
	 */
	@ApiModelProperty(value = "教师名称", required = false)
	private String teacherName;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序", required = false)
	private Integer sort;
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
