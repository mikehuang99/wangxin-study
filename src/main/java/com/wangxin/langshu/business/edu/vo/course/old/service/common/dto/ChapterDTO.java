package com.wangxin.langshu.business.edu.vo.course.old.service.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 章节信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class ChapterDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 章节ID
	 */
	@ApiModelProperty(value = "章节ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)")
	private Integer validValue;
	/**
	 * 课程ID
	 */
	@ApiModelProperty(value = "课程ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String courseId;
	/**
	 * 章节名称
	 */
	@ApiModelProperty(value = "章节名称")
	private String chapterName;
	/**
	 * 章节描述
	 */
	@ApiModelProperty(value = "章节描述")
	private String chapterDesc;
	/**
	 * 是否免费：1免费，0收费
	 */
	@ApiModelProperty(value = "是否免费：1免费，0收费")
	private Integer isFree;
	/**
	 * 原价
	 */
	@ApiModelProperty(value = "原价")
	private BigDecimal chapterOriginal;
	/**
	 * 优惠价
	 */
	@ApiModelProperty(value = "优惠价")
	private BigDecimal chapterDiscount;
	/**
	 * 课时信息
	 */
	@ApiModelProperty(value = "课时信息")
	private List<LessonDTO> lessonList;
	
	/**
	 *关联试题数量
	 */
	@ApiModelProperty(value = "章节关联试题数量")
	private Integer examQuestionChapterCount;
	
	
	public String getId(){
		return id + "";
	}
	
	public String getCourseId(){
		return courseId + "";
	}
}
