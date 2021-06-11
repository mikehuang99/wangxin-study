package com.wangxin.langshu.business.edu.vo.course.old.common.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 章节信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class ChapterVO implements Serializable {

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
	 * 1
	 */
	private Integer sort;
	/**
	 * 课程ID
	 */
	private String courseId;
	/**
	 * 章节名称
	 */
	private String chapterName;
	/**
	 * 章节描述
	 */
	private String chapterDesc;
	/**
	 * 是否免费：1免费，0收费
	 */
	private Integer isFree;
	/**
	 * 原价
	 */
	private BigDecimal chapterOriginal;
	/**
	 * 优惠价
	 */
	private BigDecimal chapterDiscount;
	/**
	 * 课时集合
	 */
	private List<LessonVO> lessonVOList;
	/**
	 * 所属课程
	 */
	private String courseName;

}
