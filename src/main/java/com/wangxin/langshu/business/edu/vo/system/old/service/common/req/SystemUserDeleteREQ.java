package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 后台用户信息-删除
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemUserDeleteREQ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8906479189664597784L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private String id;

}
