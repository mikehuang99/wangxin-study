package com.wangxin.langshu.business.edu.vo.user.old.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 平台信息-添加
 *
 *  
 */
@Data
@Accessors(chain = true)
public class PlatformSaveREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4042227563887054630L;
	/**
	 * 客户端名称
	 */
	@ApiModelProperty(value = "客户端名称", required = true)
	private String clientName;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注", required = false)
	private String remark;
}
