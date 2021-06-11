package com.wangxin.langshu.business.edu.vo.user.old.common.resq;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 平台信息-列出
 *
 *  
 */
@Data
@Accessors(chain = true)
public class PlatformViewRESQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5428211450420933944L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)")
	private Integer validValue;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;
	/**
	 * 客户端ID
	 */
	@ApiModelProperty(value = "客户端ID")
	private String clientId;
	/**
	 * 客户端密匙
	 */
	@ApiModelProperty(value = "客户端密匙")
	private String clientSecret;
	/**
	 * 客户端名称
	 */
	@ApiModelProperty(value = "客户端名称")
	private String clientName;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;
}
