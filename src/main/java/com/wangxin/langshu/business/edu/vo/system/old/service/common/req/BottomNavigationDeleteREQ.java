package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * 底部导航-删除
 *
 */
@Data
@Accessors(chain = true)
public class BottomNavigationDeleteREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2002971275830749045L;
	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID", required = true)
	private String id;

}
