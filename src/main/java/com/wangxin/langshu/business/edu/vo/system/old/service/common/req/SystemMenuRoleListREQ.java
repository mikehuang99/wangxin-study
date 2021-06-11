package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单角色关联表-列出
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemMenuRoleListREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1577291141300670756L;
	/**
	 * 角色ID
	 */
	@ApiModelProperty(value = "角色ID", required = true)
	private String roleId;
}
