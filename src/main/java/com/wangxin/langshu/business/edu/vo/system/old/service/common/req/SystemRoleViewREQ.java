package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色信息-查看
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemRoleViewREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2936044200357300125L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private String id;
}
