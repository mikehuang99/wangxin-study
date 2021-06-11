package com.wangxin.langshu.business.edu.vo.system.old.service.common.resq;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class SystemMenuRoleListRESQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1577856678731679881L;
	/**
	 * 菜单角色关联表集合
	 */
	@ApiModelProperty(value = "菜单角色关联菜单ID集合")
	private List<String> list = new ArrayList<>();
}
