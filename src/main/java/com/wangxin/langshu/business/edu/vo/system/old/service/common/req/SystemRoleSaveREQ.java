package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色信息-保存
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemRoleSaveREQ implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1266848228487937722L;
	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称", required = true)
	private String roleName;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注", required = false)
	private String remark;
}
