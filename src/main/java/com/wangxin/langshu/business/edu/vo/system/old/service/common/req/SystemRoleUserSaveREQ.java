package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色用户关联表-添加
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemRoleUserSaveREQ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8233040221073370252L;

	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户ID", required = true)
	private String userId;

	/**
	 * 角色ID集合
	 */
	@ApiModelProperty(value = "角色ID集合", required = true)
	private List<String> roleId;
}
