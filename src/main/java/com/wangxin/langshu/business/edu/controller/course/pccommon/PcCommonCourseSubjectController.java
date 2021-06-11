package com.wangxin.langshu.business.edu.controller.course.pccommon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseSubjectListDTO;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiCourseSubjectBiz;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程分类
 *
 */
@RestController
@RequestMapping(value = "/pccommon/course/course/subject")
public class PcCommonCourseSubjectController extends BaseController {

	@Autowired
	private IApiCourseSubjectBiz apiCourseSubjectBiz;

	/**
	 * 普通课程分类列表接口
	 * 
	 */
	@ApiOperation(value = "课程分类列表接口", notes = "课程分类列表")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<CourseSubjectListDTO> list() {
		return apiCourseSubjectBiz.list();
	}

}
