package com.wangxin.langshu.business.edu.vo.course.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 专区课程-删除
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TopicCourseDeleteREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private String id;

}
