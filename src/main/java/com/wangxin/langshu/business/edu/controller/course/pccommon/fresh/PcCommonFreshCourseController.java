package com.wangxin.langshu.business.edu.controller.course.pccommon.fresh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.iservice.course.pcmobile.fresh.IPcMobileFreshCourseService;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息
 *
 */
@RestController
@RequestMapping(value = "/pccommon/course/fresh/course")
public class PcCommonFreshCourseController extends BaseController {

	//@Autowired
	//private IApiCourseBiz apiCourseBiz;
	@Autowired
	private IPcMobileFreshCourseService pcMobileFreshCourseService;

	/**
	 * 课程信息列表接口
	 * 
	 * @param 
	 * @return
	 */
	@ApiOperation(value = "课程列表接口", notes = "根据条件分页列出课程信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<FreshCourseVo>> list(@RequestBody FreshCourseQ freshCourseQ) {
		//return apiCourseBiz.list(courseInfoPageBO);
		return Result.success(pcMobileFreshCourseService.queryCourses(freshCourseQ));
	}

	
	
	
}
