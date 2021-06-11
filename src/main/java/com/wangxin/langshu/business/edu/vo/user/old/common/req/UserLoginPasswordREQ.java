/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.vo.user.old.common.req;

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
public class UserLoginPasswordREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1076267181943561287L;
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
	
	/**
	 *登录平台
	 */
	@ApiModelProperty(value = "登录平台", required = true)
	private int loginPlatform;
	
	
	private String ip;

}
