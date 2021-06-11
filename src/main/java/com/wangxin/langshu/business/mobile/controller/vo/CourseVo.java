package com.wangxin.langshu.business.mobile.controller.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class CourseVo implements Serializable{
			
	/**
	 * 
	 */
	private static final long serialVersionUID = -4346638907923969010L;

	private int offset;
	
	private int limit;
	
	private String type ;
	
	private String sort ;
	
	private String courseId;
	
	private String selectScreenStr;
}
