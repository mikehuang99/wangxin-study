package com.wangxin.langshu.business.edu.vo.system.old.service.common.resq;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色用户关联表
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemRoleUserListRESQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1524534264204011696L;
	/**
	 * 角色用户关联集合
	 */
	@ApiModelProperty(value = "角色用户关联集合")
	private List<SystemRoleUserRESQ> list = new ArrayList<>();
}
