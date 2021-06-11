package com.wangxin.langshu.business.edu.entity.exam;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="exam_question_course")
public class ExamQuestionCourse extends Model<ExamQuestionCourse> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6902850800514167975L;
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
	@TableField(value = "question_id")
    private String questionId;//试题表exam_question的ID
	@TableField(value = "course_id")
    private String	courseId;//课程表course的ID
	@TableField(value = "chapter_id")
    private String	chapterId;//章节表chapter的ID
	@TableField(value = "lesson_id")
    private String	lessonId;//课时表lesson的ID
	@TableField(value = "bind_type")
	private String bindType;//绑定类型(1课程、2章节、3课时)
	
	
}
