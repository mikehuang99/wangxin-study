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
@TableName(value ="teacher_student_group")
public class TeacherStudentGroup extends Model<TeacherStudentGroup> implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -9123437046573660000L;
	//@TableId(type = IdType.UUID)
	@TableField(value ="id")
    private String id;
	@TableField(value ="create_time")
    private Date createTime;//创建时间	
	@TableField(value = "modified_time",update="now()")
    private Date modifiedTime;	//修改时间	
	@TableField(value = "valid_value")
    private int	validValue;//状态(1:正常，0:禁用)	
	@TableField(value = "sort")
    private int sort;//排序
	@TableField(value = "teacher_id")
	private String teacherId;//教师ID
	@TableField(value = "student_group_id")
	private String studentGroupId;//学生群组ID
	
}
