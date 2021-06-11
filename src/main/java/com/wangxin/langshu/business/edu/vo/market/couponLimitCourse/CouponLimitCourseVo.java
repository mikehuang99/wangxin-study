package com.wangxin.langshu.business.edu.vo.market.couponLimitCourse;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class CouponLimitCourseVo  implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1963976685292393402L;
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private int	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	@ApiModelProperty(value = "优惠卡ID", required = false)
    private String couponId;//优惠卡ID	
	@ApiModelProperty(value = "课程ID", required = false)
    private String courseId;//课程ID
	
	
}
