package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色信息-删除
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemRoleDeleteREQ implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 598823852256586061L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private String id;

}
