package com.wangxin.langshu.business.edu.vo.user.old.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 教师信息-审核
 *
 *  
 */
@Data
@Accessors(chain = true)
public class AuthTeacherApplyBO implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -5594261757093142670L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "教师主键", required = true)
	private String id;
	/**
	 * 教师用户编号
	 */
	@ApiModelProperty(value = "教师用户编号", required = true)
	private String teacherUserNo;
	/**
	 * 教师名称
	 */
	@ApiModelProperty(value = "教师名称", required = false)
	private String teacherName;
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
