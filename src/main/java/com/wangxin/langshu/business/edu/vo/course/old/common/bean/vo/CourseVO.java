package com.wangxin.langshu.business.edu.vo.course.old.common.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class CourseVO implements Serializable {

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
	 * 状态(1:正常，0:禁用)
	 */
	private Integer validValue;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 教师用户编码
	 */
	private String teacherUserNo;
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
	 * 一级分类ID
	 */
	private String subjectId1;
	/**
	 * 二级分类ID
	 */
	private String subjectId2;
	/**
	 * 三级分类ID
	 */
	private String subjectId3;
	/**
	 * 课程名称
	 */
	private String courseName;
	/**
	 * 课程封面
	 */
	private String courseLogo;
	/**
	 * 课程介绍ID
	 */
	private String introduceId;
	/**
	 * 是否免费：1免费，0收费
	 */
	private Integer isFree;
	/**
	 * 原价
	 */
	private BigDecimal courseOriginal;
	/**
	 * 优惠价
	 */
	private BigDecimal courseDiscount;
	/**
	 * 是否上架(1:上架，0:下架)
	 */
	private Integer isPutaway;
	/**
	 * 课程排序
	 */
	private Integer courseSort;
	/**
	 * 购买人数
	 */
	private Integer buyCount;
	/**
	 * 学习人数
	 */
	private Integer studyCount;
	/**
	 * 总课时数
	 */
	private Integer lessonTotal;
	/**
	 * 课程标签名称
	 */
	private String labelName;
	/**
	 * 章节集合
	 */
	private List<ChapterVO> chapterVOList;
	/**
	 * 课程简介
	 */
	private String introduce;

}
