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
public class UserAuthBO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4870871473663292325L;
	/**
	 * uuid
	 */
	@ApiModelProperty(value = "uuid", required = true)
	private String uuid;

}
