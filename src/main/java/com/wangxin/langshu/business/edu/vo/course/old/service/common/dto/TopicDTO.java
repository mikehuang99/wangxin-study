package com.wangxin.langshu.business.edu.vo.course.old.service.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 专区表
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TopicDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 专区编号
	 */
	@ApiModelProperty(value = "专区编号")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;

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
	 * 课程信息
	 */
	@ApiModelProperty(value = "课程信息")
	private List<TopicCourseDTO> topicCourseList = new ArrayList<>();
	
	/**
	 * 专区平台
	 */
	@ApiModelProperty(value = "专区平台(0电脑端，1微信端, 2移动端)")
	private String topicLocation;

}
