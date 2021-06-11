package com.wangxin.langshu.business.edu.entity.course.fresh;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="course_video")
public class FreshCourseVideo extends Model<FreshCourseVideo> implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2850786660244883336L;
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
	@TableField(value = "lesson_id")
    private String lessonId;//课时ID	
	@TableField(value = "video_name")
    private String videoName;//视频名称
	@TableField(value = "video_local_number")
    private String videoLocalNumber;//视频编号
	@TableField(value = "video_upload_status")
    private String videoUploadStatus;//视频状态(1待上传，2上传成功，3上传失败)
	@TableField(value = "video_length")
    private String videoLength;//时长
	@TableField(value = "video_oas_url")
    private String videoOasUrl;//阿里云oas	
	
}
