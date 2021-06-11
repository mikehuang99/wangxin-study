package com.wangxin.langshu.business.edu.vo.system.old.service.common.resq;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单角色关联表
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemMenuRoleRESQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7149802052859167887L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键id")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)")
	private Integer validValue;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;
	/**
	 * 菜单ID
	 */
	@ApiModelProperty(value = "菜单ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String menuId;
	/**
	 * 菜单名称
	 */
	@ApiModelProperty(value = "菜单名称")
	private String menuName;
	/**
	 * 角色ID
	 */
	@ApiModelProperty(value = "角色ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String roleId;
}
