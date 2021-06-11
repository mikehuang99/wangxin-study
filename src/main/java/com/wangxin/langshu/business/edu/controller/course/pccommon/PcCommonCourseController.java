package com.wangxin.langshu.business.edu.controller.course.pccommon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.CourseInfoPageBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.CourseInfoSearchBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.CourseVideoBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseViewBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseInfoPageDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseInfoSearchPageDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseViewDTO;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiCourseBiz;
import com.wangxin.langshu.business.edu.iservice.course.pcmobile.fresh.IPcMobileFreshCourseService;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseMobileView;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息
 *
 */
@RestController
@RequestMapping(value = "/pccommon/course/course")
public class PcCommonCourseController extends BaseController {

	@Autowired
	private IApiCourseBiz apiCourseBiz;
	
	
	@Autowired
	private IPcMobileFreshCourseService pcMobileFreshCourseService;

	/**
	 * 课程信息列表接口
	 * 
	 * @param courseInfoPageBO
	 * @return
	 */
	@ApiOperation(value = "课程列表接口", notes = "根据条件分页列出课程信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<CourseInfoPageDTO>> list(@RequestBody CourseInfoPageBO courseInfoPageBO) {
		return apiCourseBiz.list(courseInfoPageBO);
	}

	/**
	 * 课程详情接口
	 * 
	 * @param courseVideoBO
	 * @return
	 */
	/*
	@ApiOperation(value = "课程详情接口", notes = "根据课程ID获取课程信息")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<CourseViewDTO> view(@RequestBody CourseVideoBO courseVideoBO) {
		return apiCourseBiz.view(courseVideoBO);
	}*/
	
	/**
	 * 课程详情接口
	 */
	@ApiOperation(value = "课程详情接口", notes = "用户未登录获取课程详情信息接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<FreshCourseMobileView> view(@RequestBody AuthCourseViewBO authCourseViewBO) {
		Result<FreshCourseMobileView> result = pcMobileFreshCourseService.viewMobileCourse(authCourseViewBO.getCourseId(),null);
		FreshCourseMobileView freshCourseMobileView = result.getData();
		/*
		if("1085453180200448002".equals(freshCourseMobileView.getId())){
			freshCourseMobileView.setIsPay(0);
		}*/
		
		
		return 	result;

	}
	
	/**
	 * 搜索课程接口
	 * 
	 * @param courseVideoBO
	 * @return
	 */
	@ApiOperation(value = "课程搜索列表接口", notes = "根据课程名称，进行模糊搜索")
	@RequestMapping(value = "/search/list", method = RequestMethod.POST)
	public Result<Page<CourseInfoSearchPageDTO>> view(@RequestBody CourseInfoSearchBO courseInfoSearchBO) {
		return apiCourseBiz.searchList(courseInfoSearchBO);
	}
	
	
	
}
