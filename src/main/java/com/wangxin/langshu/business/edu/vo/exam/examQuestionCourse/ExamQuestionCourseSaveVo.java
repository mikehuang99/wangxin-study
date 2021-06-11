package com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ExamQuestionCourseSaveVo  implements Serializable{
	
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 6109522487733139453L;
	@ApiModelProperty(value = "id", required = true)
	private String id;
	@ApiModelProperty(value = "创建时间", required = false)
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private int	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	
	@ApiModelProperty(value = "试题表exam_question的ID", required = true)
    private String questionId;//试题表exam_question的ID
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
