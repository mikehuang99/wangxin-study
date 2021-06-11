package com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FreshCourseVideoVo  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3929530643365950625L;
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private int	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	
	@ApiModelProperty(value = "课程ID", required = false)
    private String courseId;//课程ID
	@ApiModelProperty(value = "章节ID", required = false)
    private String chapterId;//章节ID
	@ApiModelProperty(value = "课时ID", required = false)
    private String lessonId;//课时ID	
	@ApiModelProperty(value = "视频名称", required = false)
    private String videoName;//视频名称
	@ApiModelProperty(value = "视频编号", required = false)
    private String videoLocalNumber;//视频编号
	@ApiModelProperty(value = "视频状态(1待上传，2上传成功，3上传失败)", required = false)
    private int videoUploadStatus;//视频状态(1待上传，2上传成功，3上传失败)
	@ApiModelProperty(value = "时长", required = false)
    private String videoLength;//时长
	@ApiModelProperty(value = "阿里云oas", required = false)
    private String videoOasUrl;//阿里云oas	
	
	
	
	@ApiModelProperty(value = "课程类型，0点播，1直播，3回放", required = false)
    private int lessonType;//课程类型，0点播，1直播，3回放
	@ApiModelProperty(value = "直播开始时间", required = false)
    private Date liveBroadcastBeginTime;//直播开始时间
	@ApiModelProperty(value = "计划直播时长(分钟)", required = false)
    private int liveBroadcastPlanLength;//计划直播时长(分钟)
	@ApiModelProperty(value = "直播HLS播放地址", required = false)
    private String liveBroadcastHlsAddress;//直播HLS播放地址
	
	@ApiModelProperty(value = "直播OBS推流地址", required = false)
    private String liveBroadcastObsStreamAddress;//直播推流地址
	@ApiModelProperty(value = "直播OBS推流名称", required = false)
    private String liveBroadcastObsStreamName;//直播OBS推流名称
	
	@ApiModelProperty(value = "直播状态，0未开始，1正在进行，2已经结束", required = false)
    private int liveBroadcastStatus;//直播状态，0未开始，1正在进行，2已经结束
}
