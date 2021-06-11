package com.wangxin.langshu.business.edu.vo.user.old.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户教育信息更新
 * 
 */
@Data
@Accessors(chain = true)
public class StudentUpdateREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7543882635529151216L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private String id;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
	private Integer validValue;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码", required = false)
	private String mobile;
	/**
	 * 性别
	 */
	@ApiModelProperty(value = "性别", required = false)
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
	 * 头像
	 */
	@ApiModelProperty(value = "头像", required = false)
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
