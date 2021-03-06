package com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户发送短信日志
 *
 * 
 */
@Data
@Accessors(chain = true)
public class SendSmsLogVO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3231308125386653999L;
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 短信模板
	 */
	private String template;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 验证码
	 */
	private String smsCode;
	/**
	 * 是否发送成功(1发送成功，0:发送失败)
	 */
	private Integer isSuccess;

}
