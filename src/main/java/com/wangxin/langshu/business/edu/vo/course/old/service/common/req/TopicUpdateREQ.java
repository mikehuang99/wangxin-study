package com.wangxin.langshu.business.edu.vo.course.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 专区-更新
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TopicUpdateREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private String id;
	/**
	 * 状态(1:正常;0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常;0:禁用)", required = false)
	private Integer validValue;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序", required = false)
	private Integer sort;
	/**
	 * 专区名称
	 */
	@ApiModelProperty(value = "专区名称", required = true)
	private String topicName;
	/**
	 * 专区描述
	 */
	@ApiModelProperty(value = "专区描述", required = false)
	private String topicDesc;
	
	/**
	 * 专区平台
	 */
	@ApiModelProperty(value = "专区平台(0电脑端，1微信端, 2移动端)")
	private Integer topicLocation;

}
