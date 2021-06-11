package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户菜单信息-列出
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemMenuUserListREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6612388181088329279L;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号", required = false)
	private String userNo;

}
