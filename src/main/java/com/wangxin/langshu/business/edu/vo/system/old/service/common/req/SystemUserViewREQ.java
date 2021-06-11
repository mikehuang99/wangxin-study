package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 后台用户信息-查看
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemUserViewREQ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3030077381911684449L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private String id;
}
