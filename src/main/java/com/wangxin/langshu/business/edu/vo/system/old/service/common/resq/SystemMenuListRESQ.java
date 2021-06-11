package com.wangxin.langshu.business.edu.vo.system.old.service.common.resq;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class SystemMenuListRESQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8502788368795299255L;
	/**
	 * 菜单集合
	 */
	@ApiModelProperty(value = "菜单集合")
	private List<SystemMenuRESQ> systemMenu = new ArrayList<>();

}
