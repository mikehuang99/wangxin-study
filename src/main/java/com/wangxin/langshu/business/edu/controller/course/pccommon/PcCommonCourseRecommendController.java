package com.wangxin.langshu.business.edu.controller.course.pccommon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.CourseRecommendBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseRecommendListDTO;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiCourseRecommendBiz;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 课程推荐
 * 
 *
 */
@RestController
@RequestMapping(value = "/pccommon/course/course/recommend")
public class PcCommonCourseRecommendController {

	@Autowired
	private IApiCourseRecommendBiz apiCourseRecommendBiz;

	/**
	 * 课程推荐列出接口
	 * 
	 */
	@ApiOperation(value = "课程推荐列出接口", notes = "课程推荐列出接口")
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public Result<CourseRecommendListDTO> list(CourseRecommendBO courseRecommendBO) {
		return apiCourseRecommendBiz.list(courseRecommendBO);
	}

}
