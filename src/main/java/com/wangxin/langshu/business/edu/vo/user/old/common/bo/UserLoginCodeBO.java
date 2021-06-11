/**
 *  广州旺鑫计算机科技有限公司
 */
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
public class UserLoginCodeBO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5959547030080767389L;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机", required = true)
	private String mobile;
	/**
	 * 手机验证码
	 */
	@ApiModelProperty(value = "手机验证码", required = true)
	private String code;
	/**
	 * clientId
	 */
	@ApiModelProperty(value = "clientId", required = true)
	private String clientId;
	
	private String ip;
	
	/**
	 * loginPlatform 登录平台(1PC端,2移动端,3后台管理)
	 */
	@ApiModelProperty(value = "loginPlatform 登录平台(登录平台(1PC端,2移动端,3后台管理))", required = true)
	private Integer loginPlatform;

}
