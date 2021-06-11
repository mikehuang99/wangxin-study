package com.wangxin.langshu.business.edu.vo.user.old.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 教师信息-审核添加
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TeacherApplySaveREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6203791745415601414L;
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
	@ApiModelProperty(value = "教师手机", required = true)
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

	/**
	 * 用戶登陆密码（如果用户不存在）
	 */
	@ApiModelProperty(value = "用戶登陆密码（如果用户不存在）", required = false)
	private String userPsw;
	/**
	 * 用戶确认密码（如果用户不存在）
	 */
	@ApiModelProperty(value = "用戶确认密码（如果用户不存在）", required = false)
	private String confirmPasswd;

}
