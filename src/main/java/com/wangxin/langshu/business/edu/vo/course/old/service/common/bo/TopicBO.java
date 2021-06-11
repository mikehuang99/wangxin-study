package com.wangxin.langshu.business.edu.vo.course.old.service.common.bo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 专区
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TopicBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 专区编号
	 */
	@ApiModelProperty(value = "专区编号")
	private String id;

	/**
	 * 位置(1电脑端，2微信端)
	 */
	@ApiModelProperty(value = "位置(1电脑端，2微信端)", required = true)
	private Integer topicLocation;

	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页")
	private Integer pageCurrent = 1;

	/**
	 * 每页条数
	 */
	@ApiModelProperty(value = "每页条数")
	private Integer pageSize = 20;
}
