package com.wangxin.langshu.business.edu.iservice.course.pccommon;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.vo.CourseCcpListVo;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.vo.CourseCcpQ;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 课程分类
 *
 *  
 */

public interface IApiCourseCcpBiz {

	/**
	 * 获取课程分类列表
	 * 
	 * @return
	 */
	public CourseCcpListVo list(CourseCcpQ courseCcpQ) ;
}
