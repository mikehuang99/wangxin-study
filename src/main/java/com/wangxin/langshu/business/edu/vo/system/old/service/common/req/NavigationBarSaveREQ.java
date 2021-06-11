package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 头部导航-保存
 *
 */
@Data
@Accessors(chain = true)
public class NavigationBarSaveREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 233735311650511997L;
	/**
	 * 导航标题
	 */
	@ApiModelProperty(value = "导航标题", required = true)
	private String navigationTitle;
	/**
	 * 跳转方式
	 */
	@ApiModelProperty(value = "跳转方式", required = true)
	private String target;
	/**
	 * 导航url
	 */
	@ApiModelProperty(value = "导航url", required = true)
	private String navigationUrl;

}
