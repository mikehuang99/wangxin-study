package com.wangxin.langshu.business.edu.vo.course.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 专区-添加
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TopicSaveREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 专区名称
	 */
	@ApiModelProperty(value = "专区名称", required = true)
	private String topicName;
	
	/**
	 * 专区平台
	 */
	@ApiModelProperty(value = "专区平台(0电脑端，1微信端, 2移动端)", required = true)
	private Integer topicLocation;
	/**
	 * 专区描述
	 */
	@ApiModelProperty(value = "专区描述", required = true)
	private String topicDesc;
}
