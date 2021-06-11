package com.wangxin.langshu.business.edu.vo.user.old.common.bo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户基本信息
 * </p>
 *
 * 
 */
@Data
@Accessors(chain = true)
public class UserLoginPasswordBO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5627955687259616646L;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号", required = true)
	private String mobile;
	/**
	 * 登录密码
	 */
	@ApiModelProperty(value = "密码", required = true)
	private String password;
	/**
	 * clientId
	 */
	@ApiModelProperty(value = "clientId", required = true)
	private String clientId;
	
	private String ip;
	
	
	/**
	 * loginPlatform 登录平台(1PC端,2移动端,3后台管理)
	 */
	@ApiModelProperty(value = "loginPlatform登录平台(1PC端,2移动端,3后台管理)", required = true)
	private int loginPlatform;
}
