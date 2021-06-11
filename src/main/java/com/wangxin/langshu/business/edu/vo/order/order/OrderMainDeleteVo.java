package com.wangxin.langshu.business.edu.vo.order.order;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class OrderMainDeleteVo  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8294122285172998309L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	

}
