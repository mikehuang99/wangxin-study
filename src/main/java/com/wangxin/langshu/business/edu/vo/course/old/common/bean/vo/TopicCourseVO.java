package com.wangxin.langshu.business.edu.vo.course.old.common.bean.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 专区课程关联表
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TopicCourseVO implements Serializable {

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
	 * 专区编号
	 */
	private String topicId;
	/**
	 * 课程ID
	 */
	private String courseId;
	/**
	 * 课程名称
	 */
	private String courseName;
	/**
     * 位置(0电脑端，1微信端)
     */
    private Integer topicLocation;
	/**
	 * 一级分类名
	 */
	private String subjectName1;
	/**
	 * 二级分类名
	 */
	private String subjectName2;
	/**
	 * 三级分类名
	 */
	private String subjectName3;
	/**
	 * 当前页
	 */
	private int pageCurrent;
	/**
	 * 每页记录数
	 */
	private int pageSize;
	/**
	 * 章节集合
	 */
	private List<ChapterVO> chapterList;
}
