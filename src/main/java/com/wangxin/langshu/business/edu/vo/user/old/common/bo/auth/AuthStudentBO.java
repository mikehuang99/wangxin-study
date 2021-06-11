package com.wangxin.langshu.business.edu.vo.user.old.common.bo.auth;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

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
public class AuthStudentBO implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -6614528892391507598L;
	/**
	 * 主键
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "主键", required = true)
	private String id;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号", required = true)
	private String userNo;
	/**
	 * 性别(1男，2女，3保密)
	 */
	@ApiModelProperty(value = "性别(1男，2女，3保密)", required = false)
	private Integer sex;
	/**
	 * 年龄
	 */
	@ApiModelProperty(value = "年龄", required = false)
	private Integer age;
	/**
	 * 昵称
	 */
	@ApiModelProperty(value = "昵称", required = false)
	private String nickname;
	/**
	 * 头像地址
	 */
	@ApiModelProperty(value = "头像地址", required = false)
	private String headImgUrl;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注", required = false)
	private String remark;
	
	/**
	 * 真实姓名
	 */
	@ApiModelProperty(value = "真实姓名", required = false)
	private String realName;
    /**
     * 身份证号码
     */
	@ApiModelProperty(value = "身份证号码", required = true)
    private String identityCardNo;//身份证号码
}
