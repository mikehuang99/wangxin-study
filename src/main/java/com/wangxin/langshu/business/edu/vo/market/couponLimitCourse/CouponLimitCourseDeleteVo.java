package com.wangxin.langshu.business.edu.vo.market.couponLimitCourse;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class CouponLimitCourseDeleteVo  implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -6756162337095648025L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	

}
