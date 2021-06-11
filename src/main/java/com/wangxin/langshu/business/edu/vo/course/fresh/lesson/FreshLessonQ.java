package com.wangxin.langshu.business.edu.vo.course.fresh.lesson;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class FreshLessonQ  implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 3566359576562224956L;
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
    private String	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	
	
	@ApiModelProperty(value = "课程ID", required = false)
    private String courseId;//课程ID
	@ApiModelProperty(value = "章节ID", required = false)
    private String chapterId;//章节ID	
	@ApiModelProperty(value = "课时ID", required = false)
    private String lessonId;//课时ID	
	
	@ApiModelProperty(value = "课时描述", required = false)
    private String lessonDesc;//课时描述
	@ApiModelProperty(value = "是否免费：1免费，0收费", required = false)
    private String isFree;//是否免费：1免费，0收费
	@ApiModelProperty(value = "原价", required = false)
    private String lessonOriginal;//原价
	@ApiModelProperty(value = "优惠价", required = false)
    private String lessonDiscount;//优惠价
	@ApiModelProperty(value = "购买人数", required = false)
    private String buyCount;//购买人数
	@ApiModelProperty(value = "学习人数", required = false)
    private String studyCount;//学习人数
	@ApiModelProperty(value = "是否存在文档(1存在，0否)", required = false)
    private String isDoc;//是否存在文档(1存在，0否)	
	@ApiModelProperty(value = "文档名称", required = false)
    private String docName;//文档名称	
	@ApiModelProperty(value = "文档地址", required = false)
    private String docUrl;//文档地址
	@ApiModelProperty(value = "课时名称", required = false)
    private String lessonName;//课时名称	
	@ApiModelProperty(value = "是否存在视频(1存在，0否)", required = false)
    private String isVideo;//是否存在视频(1存在，0否)	
	@ApiModelProperty(value = "视频编号", required = false)
    private String videoLocalNumber;//视频编号
	@ApiModelProperty(value = "视频名称", required = false)
    private String videoName;//视频名称	
	@ApiModelProperty(value = "时长", required = false)
    private String videoLength;//时长	
	@ApiModelProperty(value = "视频VID", required = false)
    private String videoVid;//视频VID
	
	@ApiModelProperty(value = "课程类型，0点播，1直播，3回放", required = false)
    private String lessonType;//课程类型，0点播，1直播，3回放
	@ApiModelProperty(value = "直播开始时间", required = false)
    private String liveBroadcastBeginTime;//直播开始时间
	@ApiModelProperty(value = "计划直播时长(分钟)", required = false)
    private String liveBroadcastPlanLength;//计划直播时长(分钟)
	@ApiModelProperty(value = "直播HLS播放地址", required = false)
    private String liveBroadcastHlsAddress;//直播HLS播放地址
	
	@ApiModelProperty(value = "直播OBS推流地址", required = false)
    private String liveBroadcastObsStreamAddress;//直播推流地址
	@ApiModelProperty(value = "直播OBS推流名称", required = false)
    private String liveBroadcastObsStreamName;//直播OBS推流名称
	
	@ApiModelProperty(value = "审核状态(0:待审核,1:审核通过,2:审核不通过)", required = false)
    private String applyStatus;//审核状态(0:待审核,1:审核通过,2:审核不通过)
	@ApiModelProperty(value = "审核意见", required = false)
    private String applyResultDesc;//审核意见
	
	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页", required = true)
	private int pageCurrent = 1;
	/**
	 * 每页记录数
	 */
	@ApiModelProperty(value = "每页记录数", required = true)
	private int pageSize = 20;
}
