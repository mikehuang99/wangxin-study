package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单信息-列出
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemMenuListREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7268294328764732407L;
	/**
	 * 菜单名称
	 */
	@ApiModelProperty(value = "菜单名称", required = false)
	private String menuName;

}
