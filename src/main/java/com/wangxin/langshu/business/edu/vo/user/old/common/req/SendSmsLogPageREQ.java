package com.wangxin.langshu.business.edu.vo.user.old.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户发送短信日志-分页
 *
 */
@Data
@Accessors(chain = true)
public class SendSmsLogPageREQ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4020844562457300844L;

	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码", required = false)
	private String mobile;

	/**
	 * 开始时间
	 */
	@ApiModelProperty(value = "开始时间", required = false)
	private String beginCreateTime;
	/**
	 * 结束时间
	 */
	@ApiModelProperty(value = "结束时间", required = false)
	private String endCreateTime;

	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页", required = true)
	private int pageCurrent = 1;
	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页", required = true)
	private int pageSize = 20;
}
