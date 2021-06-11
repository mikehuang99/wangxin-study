package com.wangxin.langshu.business.edu.vo.course.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程推荐-添加
 * 
 *  
 */
@Data
@Accessors(chain = true)
public class CourseRecommendSaveREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 分类ID
	 */
	@ApiModelProperty(value = "分类ID", required = true)
	private String subjectId;
	/**
	 * 课程ID
	 */
	@ApiModelProperty(value = "课程ID", required = true)
	private String courseId;

}
