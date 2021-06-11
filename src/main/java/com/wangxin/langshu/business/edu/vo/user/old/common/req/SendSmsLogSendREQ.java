package com.wangxin.langshu.business.edu.vo.user.old.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户发送短信日志-发送验证码
 *
 */
@Data
@Accessors(chain = true)
public class SendSmsLogSendREQ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 457852421355059717L;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码", required = true)
	private String mobile;

}
