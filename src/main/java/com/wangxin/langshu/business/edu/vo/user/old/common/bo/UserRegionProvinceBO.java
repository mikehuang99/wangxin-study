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
public class UserRegionProvinceBO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -9052779686534543923L;
	/**
	 * 省ID
	 */
	@ApiModelProperty(value = "省ID", required = true)
	private Integer provinceId;

}
