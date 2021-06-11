package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * 底部导航-更新
 *
 */
@Data
@Accessors(chain = true)
public class BottomNavigationUpdateREQ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8464136815870780201L;
	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID", required = true)
	private String id;
	/**
	 * 状态(1有效, 0无效)
	 */
	@ApiModelProperty(value = "状态(1有效, 0无效)", required = false)
	private Integer validValue;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序", required = false)
	private Integer sort;
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
