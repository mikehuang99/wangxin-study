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
public class UserRegisterBO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7578825331541244978L;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机", required = true)
	private String mobile;
	/**
	 * 登录密码
	 */
	@ApiModelProperty(value = "密码", required = true)
	private String password;
	/**
	 * 重复密码
	 */
	@ApiModelProperty(value = "重复密码", required = true)
	private String repassword;
	
	/**
	 * 真实姓名
	 */
	@ApiModelProperty(value = "真实姓名", required = true)
	private String realName;
    /**
     * 身份证号码
     */
	@ApiModelProperty(value = "身份证号码", required = true)
    private String identityCardNo;//身份证号码
	/**
	 * clientId
	 */
	@ApiModelProperty(value = "clientId", required = true)
	private String clientId;
	/**
	 * 手机验证码
	 */
	@ApiModelProperty(value = "手机验证码", required = true)
	private String code;
	
	private String ip;

}
