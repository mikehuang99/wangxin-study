/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.vo.user.old.common.bo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户基本信息
 * </p>
 *
 * 
 */
@Data
@Accessors(chain = true)
public class UserRegionLevelBO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7060631370738759602L;
	/**
	 * 级别
	 */
	@ApiModelProperty(value = "级别", required = true)
	private Integer level;

}
