package com.wangxin.langshu.business.edu.iservice.course.pccommon;

import org.springframework.stereotype.Service;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.CourseRecommendBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseRecommendListDTO;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 
 * 课程推荐
 * 
 * 
 *
 */
public interface IApiCourseRecommendBiz {


	/**
	 * 根据分类ID获取推荐课程信息
	 * 
	 * @param courseRecommendBO
	 * 
	 */
	public Result<CourseRecommendListDTO> list(CourseRecommendBO bo) ;

}
