package com.wangxin.langshu.business.edu.iservice.course.pccommon;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseSubjectListDTO;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 课程分类
 *
 *  
 */

public interface IApiCourseSubjectBiz {

	/**
	 * 获取课程分类列表
	 * 
	 * @return
	 * 
	 */
	public Result<CourseSubjectListDTO> list() ;
}
