package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 后台用户信息-更新密码
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemUserUpdatePasswordREQ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6461067771827556491L;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号", required = true)
	private String adminUserNo;
	/**
	 * 新密码
	 */
	@ApiModelProperty(value = "新密码", required = true)
	private String userPsw;
	/**
	 * 确认密码
	 */
	@ApiModelProperty(value = "确认密码", required = true)
	private String rePwd;

}
