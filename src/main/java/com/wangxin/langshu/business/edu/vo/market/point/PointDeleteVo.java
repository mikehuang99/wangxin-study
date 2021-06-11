package com.wangxin.langshu.business.edu.vo.market.point;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class PointDeleteVo  implements Serializable{
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 7484421020523654027L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	

}
