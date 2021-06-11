package com.wangxin.langshu.business.edu.vo.course.old.service.common.resq;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 专区表-分页列出
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TopicPageRESQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 专区ID
	 */
	@ApiModelProperty(value = "专区ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 状态(1:正常;0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常;0:禁用)")
	private Integer validValue;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;
	/**
	 * 专区名称
	 */
	@ApiModelProperty(value = "专区名称")
	private String topicName;

	/**
	 * 专区描述
	 */
	@ApiModelProperty(value = "专区描述")
	private String topicDesc;
	
	/**
	 * 专区平台
	 */
	@ApiModelProperty(value = "专区平台(0电脑端，1微信端, 2移动端)")
	private Integer topicLocation;
}
