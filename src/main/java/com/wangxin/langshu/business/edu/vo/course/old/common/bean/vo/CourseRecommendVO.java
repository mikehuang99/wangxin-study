package com.wangxin.langshu.business.edu.vo.course.old.common.bean.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程推荐
 * 
 *  
 */
@Data
@Accessors(chain = true)
public class CourseRecommendVO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date modifiedTime;
	/**
	 * 状态(1:正常;0:禁用)
	 */
	private Integer validValue;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 分类ID
	 */
	private String subjectId;
	/**
	 * 课程ID
	 */
	private String courseId;
	/**
	 * 课程名称
	 */
	private String courseName;

}