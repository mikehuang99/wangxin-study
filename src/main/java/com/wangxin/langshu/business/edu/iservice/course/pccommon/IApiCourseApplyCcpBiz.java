package com.wangxin.langshu.business.edu.iservice.course.pccommon;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.vo.CourseCcpListVo;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.vo.CourseCcpQ;

/**
 * 课程分类
 *
 *  
 */

public interface IApiCourseApplyCcpBiz {

	/**
	 * 获取课程分类列表
	 * 
	 * @return
	 */
	public CourseCcpListVo list(CourseCcpQ courseCcpQ) ;
}
