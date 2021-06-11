package com.wangxin.langshu.business.edu.controller.course.pcweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.vo.CourseCcpListVo;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.vo.CourseCcpQ;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiCourseApplyCcpBiz;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiCourseCcpBiz;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程分类
 *
 */
@RestController
@RequestMapping(value = "/pcweb/course/ccp/audit")
public class PcWebCourseCcpController extends BaseController {

	@Autowired
	private IApiCourseCcpBiz apiCourseCcpBiz;
	
	@Autowired
	private IApiCourseApplyCcpBiz apiCourseApplyCcpBiz;

	/**
	 * 普通课程分类列表接口
	 * 
	 */
	@ApiOperation(value = "课程分类列表接口", notes = "课程分类列表")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<CourseCcpListVo> list(@RequestBody CourseCcpQ courseCcpQ) {		
		return Result.success(apiCourseCcpBiz.list(courseCcpQ));
	}
	
	@ApiOperation(value = "课程分类列表接口(未审核的)", notes = "课程分类列表")
	@RequestMapping(value = "/auditlist", method = RequestMethod.POST)
	public Result<CourseCcpListVo> auditlist(@RequestBody CourseCcpQ courseCcpQ) {		
		return Result.success(apiCourseApplyCcpBiz.list(courseCcpQ));
	}

}
