package com.wangxin.langshu.business.edu.vo.market.dot;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class DotDeleteVo  implements Serializable{
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -5776707808214216028L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	

}
