package com.wangxin.langshu.business.edu.vo.course.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 专区课程-添加
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TopicCourseSaveREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 专区ID
	 */
	@ApiModelProperty(value = "专区ID", required = true)
	private String topicId;
	/**
	 * 课程ID
	 */
	@ApiModelProperty(value = "课程ID", required = true)
	private String courseId;
}
