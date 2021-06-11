package com.wangxin.langshu.business.edu.vo.market.coupon;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class CouponDeleteVo  implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3360883949796588876L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	

}
