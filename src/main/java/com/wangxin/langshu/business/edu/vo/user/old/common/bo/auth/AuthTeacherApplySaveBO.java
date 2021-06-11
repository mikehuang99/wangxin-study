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
public class AuthTeacherApplySaveBO implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -8833058830312146385L;
	/**
	 * 教师名称
	 */
	@ApiModelProperty(value = "教师名称", required = true)
	private String teacherName;
	/**
	 * 电话
	 */
	@ApiModelProperty(value = "电话", required = true)
	private String teacherMobile;
	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱", required = true)
	private String teacherEmail;
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
	/**
	 * 职位
	 */
	@ApiModelProperty(value = "职位", required = false)
	private String position;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码", required = true)
	private String userPsw;
	/**
	 * 重复密码
	 */
	@ApiModelProperty(value = "重复密码", required = true)
	private String repassword;
	/**
	 * 手机验证码
	 */
	@ApiModelProperty(value = "手机验证码", required = false)
	private String code;
	/**
	 * clientId
	 */
	@ApiModelProperty(value = "clientId", required = false)
	private String clientId;
}
