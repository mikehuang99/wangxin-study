package com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class AuthCourseViewBO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 用户NO
	 */
	@ApiModelProperty(value = "用户NO", required = true)
	private String userNo;
	/**
	 * 课程ID
	 */
	@ApiModelProperty(value = "课程ID", required = true)
	private String courseId;

}