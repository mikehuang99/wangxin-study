package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单角色关联表-添加
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemMenuRoleSaveREQ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 88672881191427045L;
	/**
	 * 菜单ID集合
	 */
	private List<String> menuId;
	/**
	 * 角色ID
	 */
	private String roleId;
}
