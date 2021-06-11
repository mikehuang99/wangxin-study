package com.wangxin.langshu.business.edu.iservice.course.pccommon;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.CourseInfoPageBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.CourseInfoSearchBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.CourseVideoBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseInfoPageDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseInfoSearchPageDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseViewDTO;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 课程信息
 *
 *  
 */

public interface IApiCourseBiz {

	/**
	 * 课程详情接口
	 * 
	 * @param courseView
	 * @return
	 */
	public Result<CourseViewDTO> view(CourseVideoBO courseVideoBO) ;
	/**
	 * 课程信息列表接口
	 * 
	 * @param courseInfoPageBO
	 * @return
	 * 
	 */
	public Result<Page<CourseInfoPageDTO>> list(CourseInfoPageBO courseInfoPageBO);

	/**
	 * 课程搜索列表接口
	 * 
	 * @param courseInfoSearchBO
	 * 
	 */
	public Result<Page<CourseInfoSearchPageDTO>> searchList(CourseInfoSearchBO bo);
	
}
