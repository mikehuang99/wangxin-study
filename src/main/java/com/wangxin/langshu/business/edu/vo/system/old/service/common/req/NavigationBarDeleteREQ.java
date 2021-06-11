package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 头部导航-删除
 */
@Data
@Accessors(chain = true)
public class NavigationBarDeleteREQ implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 9000728532916562507L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private String id;

}
