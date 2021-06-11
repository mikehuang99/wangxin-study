package com.wangxin.langshu.business.edu.vo.course.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 专区-分页列出
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TopicPageREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 位置(1电脑端，2微信端)
	 */
	@ApiModelProperty(value = "位置(1电脑端，2微信端)", required = true)
	private Integer topicLocation;
	/**
	 * 专区名称
	 */
	@ApiModelProperty(value = "专区名称", required = false)
	private String topicName;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
	private Integer validValue;
	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页", required = true)
	private Integer pageCurrent = 1;

	/**
	 * 每页条数
	 */
	@ApiModelProperty(value = "每页条数", required = true)
	private Integer pageSize = 20;
}
