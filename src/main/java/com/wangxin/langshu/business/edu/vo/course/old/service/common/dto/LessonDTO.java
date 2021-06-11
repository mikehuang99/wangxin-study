package com.wangxin.langshu.business.edu.vo.course.old.service.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课时信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class LessonDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 课时ID
	 */
	@ApiModelProperty(value = "课时ID")
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
	 * 章节ID
	 */
	@ApiModelProperty(value = "章节ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String chapterId;
	/**
	 * 课时名称
	 */
	@ApiModelProperty(value = "课时名称")
	private String lessonName;
	/**
	 * 课时描述
	 */
	@ApiModelProperty(value = "课时描述")
	private String lessonDesc;
	/**
	 * 是否免费：1免费，0收费
	 */
	@ApiModelProperty(value = "是否免费：1免费，0收费")
	private Integer isFree;
	/**
	 * 原价
	 */
	@ApiModelProperty(value = "原价")
	private BigDecimal lessonOriginal;
	/**
	 * 优惠价
	 */
	@ApiModelProperty(value = "优惠价")
	private BigDecimal lessonDiscount;
	/**
	 * 购买人数
	 */
	@ApiModelProperty(value = "购买人数")
	private Integer buyCount;
	/**
	 * 学习人数
	 */
	@ApiModelProperty(value = "学习人数")
	private Integer studyCount;
	/**
	 * 是否存在文档(1存在，0否)
	 */
	@ApiModelProperty(value = "是否存在文档(1存在，0否)")
	private Integer isDoc;
	/**
	 * 文档名称
	 */
	@ApiModelProperty(value = "文档名称")
	private String docName;
	/**
	 * 文档地址
	 */
	@ApiModelProperty(value = "文档地址")
	private String docUrl;
	/**
	 * 是否存在视频(1存在，0否)
	 */
	@ApiModelProperty(value = "是否存在视频(1存在，0否)")
	private String isVideo;
	/**
	 * 视频编号
	 */
	@ApiModelProperty(value = "视频编号")
	@JsonSerialize(using = ToStringSerializer.class)
	private String videoLocalNumber;
	/**
	 * 视频名称
	 */
	@ApiModelProperty(value = "视频名称")
	private String videoName;
	/**
	 * 时长
	 */
	@ApiModelProperty(value = "时长")
	private String videoLength;

	/**
	 * 阿里云oas
	 */
	@ApiModelProperty(value = "阿里云oas")
	private String videoOasUrl;
	
	/**
	 *关联试题数量
	 */
	@ApiModelProperty(value = "课时关联试题数量")
	private Integer examQuestionLessonCount;
	
	public String getId(){
		return id + "";
	}
	
	public String getCourseId(){
		return courseId + "";
	}
	
	public String getChapterId(){
		return chapterId + "";
	}
	
	public String getVideoLocalNumber(){
		return videoLocalNumber + "";
	}
}
