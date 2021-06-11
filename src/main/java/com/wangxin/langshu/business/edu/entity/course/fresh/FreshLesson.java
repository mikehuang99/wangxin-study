package com.wangxin.langshu.business.edu.entity.course.fresh;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="lesson")
public class FreshLesson extends Model<FreshLesson> implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 3829523872105340194L;
	//@TableId(type = IdType.UUID)
    private String id;
	@TableField(value ="create_time")
    private Date createTime;//创建时间	
	@TableField(value = "modified_time",update="now()")
    private Date modifiedTime;	//修改时间	
	@TableField(value = "valid_value")
    private int	validValue;//状态(1:正常，0:禁用)	
	@TableField(value = "sort")
    private int sort;//排序
	
	@TableField(value = "course_id")
    private String courseId;//课程ID
	@TableField(value = "chapter_id")
    private String chapterId;//章节ID	
	@TableField(value = "lesson_desc")
    private String lessonDesc;//课时描述
	@TableField(value = "is_free")
    private int isFree;//是否免费：1免费，0收费
	@TableField(value = "lesson_original")
    private BigDecimal lessonOriginal;//原价
	@TableField(value = "lesson_discount")
    private BigDecimal lessonDiscount;//优惠价
	@TableField(value = "buy_count")
    private int buyCount;//购买人数
	@TableField(value = "study_count")
    private int studyCount;//学习人数
	@TableField(value = "is_doc")
    private int isDoc;//是否存在文档(1存在，0否)	
	@TableField(value = "doc_name")
    private String docName;//文档名称	
	@TableField(value = "doc_url")
    private String docUrl;//文档地址
	@TableField(value = "lesson_name")
    private String lessonName;//课时名称	
	@TableField(value = "is_video")
    private String isVideo;//是否存在视频(1存在，0否)	
	@TableField(value = "video_local_number")
    private String videoLocalNumber;//视频编号
	@TableField(value = "video_name")
    private String videoName;//视频名称	
	@TableField(value = "video_length")
    private String videoLength;//时长	
	@TableField(value = "video_vid")
    private String videoVid;//视频VID
	
	@TableField(value = "lesson_type")
    private int lessonType;//课程类型，0点播，1直播，3回放
	@TableField(value = "live_broadcast_begin_time")
    private Date liveBroadcastBeginTime;//直播开始时间
	@TableField(value = "live_broadcast_plan_length")
    private int liveBroadcastPlanLength;//计划直播时长(分钟)
	@TableField(value = "live_broadcast_hls_address")
    private String liveBroadcastHlsAddress;//直播HLS播放地址
	
	@TableField(value = "live_broadcast_obs_stream_address")
    private String liveBroadcastObsStreamAddress;//直播推流地址
	@TableField(value = "live_broadcast_obs_stream_name")
    private String liveBroadcastObsStreamName;//直播OBS推流名称
	
	
	
	
}
