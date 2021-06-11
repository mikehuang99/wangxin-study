package com.wangxin.langshu.business.edu.vo.user.old.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户基本信息
 *
 * 
 */
@Data
@Accessors(chain = true)
public class UserUpdateBO implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -1628462219304048638L;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码")
	private String mobile;
	/**
	 * 新登录密码
	 */
	@ApiModelProperty(value = "新登录密码")
	private String newPassword;
	/**
	 * 确认密码
	 */
	@ApiModelProperty(value = "确认密码")
	private String confirmPassword;
	/**
	 * clientId
	 */
	@ApiModelProperty(value = "clientId")
	private String clientId;
	/**
	 * 验证码
	 */
	@ApiModelProperty(value = "验证码")
	private String code;
}
