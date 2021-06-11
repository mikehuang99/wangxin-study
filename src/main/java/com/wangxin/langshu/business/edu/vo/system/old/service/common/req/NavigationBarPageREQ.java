package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 头部导航
 *
 */
@Data
@Accessors(chain = true)
public class NavigationBarPageREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7411243148056620107L;
	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页", required = true)
	private int pageCurrent = 1;
	/**
	 * 每页记录数
	 */
	@ApiModelProperty(value = "每页记录数", required = true)
	private int pageSize = 20;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
	private Integer validValue;
	/**
	 * 导航标题
	 */
	@ApiModelProperty(value = "导航标题", required = false)
	private String navigationTitle;
}
