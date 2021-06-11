package com.wangxin.langshu.business.edu.vo.course.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 专区课程-分页列出
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TopicCoursePageREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 专区ID
	 */
	@ApiModelProperty(value = "专区ID", required = false)
	private String topicId;
	/**
	 * 课程名称
	 */
	@ApiModelProperty(value = "课程名称", required = false)
	private String courseName;
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
