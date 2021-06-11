package com.wangxin.langshu.business.edu.vo.user.old.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 平台信息-查看
 *
 *  
 */
@Data
@Accessors(chain = true)
public class PlatformViewREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -461376155167165192L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private String id;
}
