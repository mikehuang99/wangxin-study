package com.wangxin.langshu.business.edu.vo.market.dotConfig;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class DotConfigDeleteVo  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4152307702269901424L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	

}
