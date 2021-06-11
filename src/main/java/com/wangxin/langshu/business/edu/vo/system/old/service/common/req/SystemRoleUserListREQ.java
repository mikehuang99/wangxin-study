package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色用户关联表-列出
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemRoleUserListREQ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7484289313485702058L;
	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户ID", required = true)
	private String userId;
}
