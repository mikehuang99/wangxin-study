/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.vo.user.old.common.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserLoginDTO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1004320549415727304L;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号")
	@JsonSerialize(using = ToStringSerializer.class)
	private String userNo;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号")
	private String mobile;
	/**
	 * token，设置有效期为1天
	 */
	@ApiModelProperty(value = "token，有效期为1天")
	private String token;
	
	/**
	 * 真实姓名
	 */
	@ApiModelProperty(value = "真实姓名")
	private String  realName;
    /**
     * 身份证号码
     */
	@ApiModelProperty(value = "身份证号码")
    private String identityCardNo;//身份证号码
	
	
}
