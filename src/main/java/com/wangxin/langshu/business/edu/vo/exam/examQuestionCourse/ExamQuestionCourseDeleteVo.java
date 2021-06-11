package com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ExamQuestionCourseDeleteVo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 596468897788407831L;
	@ApiModelProperty(value = "课程表course的ID", required = true)
    private String	courseId;//课程表course的ID
	@ApiModelProperty(value = "章节表chapter的ID", required = true)
    private String	chapterId;//章节表chapter的ID
	@ApiModelProperty(value = "课时表lesson的ID", required = true)
    private String	lessonId;//课时表lesson的ID
	@ApiModelProperty(value = "绑定类型(1课程、2章节、3课时)", required = false)
	private String bindType;//绑定类型(1课程、2章节、3课时)
	@ApiModelProperty(value = "试题表exam_question的ID的集合", required = true)
    private String[] questionIds;//试题表exam_question的ID的集合
   
	

}
