package com.wangxin.langshu.business.edu.vo.user.old.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户教育信息
 *
 * 
 */
@Data
@Accessors(chain = true)
public class AuthStudentViewBO implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -9218290339589784745L;


	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号")
	private String userNo;
	
	
	/**
	 * 平台
	 */
	@ApiModelProperty(value = "平台")
	private String platform;
	
}
