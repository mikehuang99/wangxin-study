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
@TableName(value ="study_proof_course")
public class StudyProofCourse extends Model<StudyProofCourse> implements Serializable { 

	/**
	 * 
	 */
	private static final long serialVersionUID = -6555412728386077547L;
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

	@TableField(value = "study_proof_id")
    private String studyProofId;//学习证明ID
	@TableField(value = "course_id")
    private String courseId;//课程ID
	@TableField(value = "course_name")
    private String courseName;//课程名称
	@TableField(value = "total_study_progress")
    private int totalStudyProgress;//该课程的总学习进度(0表示0%，1表示1%，2表示2%......100表示100%)	
	
	

   
}