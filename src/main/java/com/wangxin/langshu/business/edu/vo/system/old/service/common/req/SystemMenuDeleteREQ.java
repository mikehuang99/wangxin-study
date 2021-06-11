package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单信息-删除
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemMenuDeleteREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -713985808986439442L;
	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID", required = true)
	private String id;

}
