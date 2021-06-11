package com.wangxin.langshu.business.mobile.controller.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ExamQuestionCourseQuery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4722067960153657637L;
	
	//用户编号
	private String userNo;
	
	//课程ID、章节ID、课时ID，决定是由type决定
	private String ccpId;
	
	//当type为1的时候是课程，为2的时候是章节，为3的时候是课时
	private String type;
	
	
	private String questionId;//习题ID
	
	
	private String noteContent;//笔记内容
	
	
}
