package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 头部导航
 *
 */
@Data
@Accessors(chain = true)
public class NavigationBarViewREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6686216526186886880L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private String id;

}
