package com.wangxin.langshu.business.edu.vo.course.old.service.common.bo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 专区课程关联表
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TopicCourseBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 专区编号
	 */
	private String topicId;
}
