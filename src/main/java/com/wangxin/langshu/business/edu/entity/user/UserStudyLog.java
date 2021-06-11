package com.wangxin.langshu.business.edu.entity.user;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="user_study_log")
public class UserStudyLog extends Model<UserStudyLog> implements Serializable { 



	/**
	 * 
	 */
	private static final long serialVersionUID = 3078140639526132833L;
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
	
	@TableField(value = "study_user_no")
    private String studyUserNo;//学习的用户编号
	@TableField(value = "study_progress")
    private int studyProgress;//学习进度(0表示0%，1表示1%，2表示2%......100表示100%)
	@TableField(value = "study_progess_details")
    private String studyProgessDetails;//学习进度详细，以英文逗号分开，如值为1,2,3,6,60,65,80   表示学过了1%,2%,3%,6%,60%,65%,80%


}