package com.wangxin.langshu.business.edu.vo.user.old.common.resq;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StudentPageRESQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1208314149277338630L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private Date modifiedTime;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)")
	private Integer validValue;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号")
	@JsonSerialize(using = ToStringSerializer.class)
	private String userNo;
	/**
	 * 用户类型
	 */
	@ApiModelProperty(value = "用户类型")
	private Integer userType;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码")
	private String mobile;
	/**
	 * 性别
	 */
	@ApiModelProperty(value = "性别")
	private Integer sex;
	/**
	 * 年龄
	 */
	@ApiModelProperty(value = "年龄")
	private Integer age;
	/**
	 * 昵称
	 */
	@ApiModelProperty(value = "昵称")
	private String nickname;
	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像")
	private String headImgUrl;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;

	/**
	 * 真实姓名
	 */
	@ApiModelProperty(value = "真实姓名")
	private String realName;
    /**
     * 身份证号码
     */
	@ApiModelProperty(value = "身份证号码")
    private String identityCardNo;//身份证号码
}
