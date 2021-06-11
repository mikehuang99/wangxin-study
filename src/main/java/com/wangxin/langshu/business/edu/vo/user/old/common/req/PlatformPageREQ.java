package com.wangxin.langshu.business.edu.vo.user.old.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 平台信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class PlatformPageREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2727904664961652232L;
	/**
	 * 客户端名称
	 */
	@ApiModelProperty(value = "客户端名称", required = false)
	private String clientName;
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
