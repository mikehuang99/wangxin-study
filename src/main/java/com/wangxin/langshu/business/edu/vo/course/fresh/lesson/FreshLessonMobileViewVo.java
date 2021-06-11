package com.wangxin.langshu.business.edu.vo.course.fresh.lesson;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FreshLessonMobileViewVo  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1452918240215929930L;
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
	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
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
	/*
	@ApiModelProperty(value = "阿里云oas")
	private String videoOasUrl;
	*/
	

	
	@ApiModelProperty(value = "课程类型，0点播，1直播，3回放", required = false)
    private int lessonType;//课程类型，0点播，1直播，3回放
	@ApiModelProperty(value = "直播开始时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date liveBroadcastBeginTime;//直播开始时间
	@ApiModelProperty(value = "计划直播时长(分钟)", required = false)
    private int liveBroadcastPlanLength;//计划直播时长(分钟)
	@ApiModelProperty(value = "直播HLS播放地址", required = false)
    private String liveBroadcastHlsAddress;//直播HLS播放地址
	
	@ApiModelProperty(value = "直播OBS推流地址", required = false)
    private String liveBroadcastObsStreamAddress;//直播推流地址
	@ApiModelProperty(value = "直播OBS推流名称", required = false)
    private String liveBroadcastObsStreamName;//直播OBS推流名称
	
	
	/**
	 * 时长
	 */
	@ApiModelProperty(value = "时长")
	private String videoLengthReal;
	
	@ApiModelProperty(value = "视频上传状态", required = false)
    private String videoUploadStatus;//视频上传状态

	
	/**
	 *关联试题数量
	 */
	@ApiModelProperty(value = "课时关联试题数量")
	private Integer examQuestionLessonCount;
	
	/**
	 *课时关联试题数量(用户已经完成的试题数)
	 */
	@ApiModelProperty(value = "课时关联试题数量(用户已经完成的试题数)")
	private Integer examQuestionLessonCountHadFinished;
	
}
