package com.wangxin.langshu.business.edu.vo.market.couponUser;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class CouponUserDeleteVo  implements Serializable{
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1878246838998184450L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	

}
