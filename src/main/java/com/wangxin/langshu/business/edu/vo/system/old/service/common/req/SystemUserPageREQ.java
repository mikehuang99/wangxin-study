package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 后台用户信息-分页
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemUserPageREQ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4623839404675502012L;
	/**
	 * 手机
	 */
	@ApiModelProperty(value = "手机", required = false)
	private String mobile;
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
