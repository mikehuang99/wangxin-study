package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 头部导航-更新
 */
@Data
@Accessors(chain = true)
public class NavigationBarUpdateREQ implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -2119133091665221753L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private String id;
	/**
	 * 状态(1有效, 0无效)
	 */
	@ApiModelProperty(value = "状态(1有效, 0无效)", required = false)
	private Integer validValue;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序", required = false)
	private Integer sort;
	/**
	 * 导航标题
	 */
	@ApiModelProperty(value = "导航标题", required = false)
	private String navigationTitle;
	/**
	 * 跳转方式
	 */
	@ApiModelProperty(value = "跳转方式", required = false)
	private String target;

}
