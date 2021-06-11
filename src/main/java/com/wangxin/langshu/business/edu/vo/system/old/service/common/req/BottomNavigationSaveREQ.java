package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * 底部导航-添加
 *
 */
@Data
@Accessors(chain = true)
public class BottomNavigationSaveREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -762133480535413632L;
	/**
	 * 父ID
	 */
	@ApiModelProperty(value = "父ID", required = false)
	private String parentId;
	/**
	 * 导航名称
	 */
	@ApiModelProperty(value = "导航名称", required = true)
	private String navigationName;

}
