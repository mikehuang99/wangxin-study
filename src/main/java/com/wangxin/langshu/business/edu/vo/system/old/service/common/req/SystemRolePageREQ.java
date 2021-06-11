package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色信息-分页列出
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemRolePageREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6653824441545485255L;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
	private Integer validValue;
	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称", required = false)
	private String roleName;

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
}
