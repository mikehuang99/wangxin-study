package com.wangxin.langshu.business.edu.vo.course.old.service.common.bo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 广告信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class AdvertBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 位置(0电脑端，1微信端)
	 */
	@ApiModelProperty(value = "位置(0电脑端，1微信端，2移动网页端)")
	private Integer platShow;

}
