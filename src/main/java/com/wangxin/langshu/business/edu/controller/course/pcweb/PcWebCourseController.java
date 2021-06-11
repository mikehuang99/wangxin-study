package com.wangxin.langshu.business.edu.controller.course.pcweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseSignBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseViewBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseSignDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseViewDTO;
import com.wangxin.langshu.business.edu.iservice.course.pcmobile.fresh.IPcMobileFreshCourseService;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebCourseBiz;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseMobileView;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息-审核
 *
 */
@RestController
@RequestMapping(value = "/pcweb/course/course")
public class PcWebCourseController extends BaseController {

	@Autowired
	private IPcWebCourseBiz pcWebCourseBiz;
	
	@Autowired
	private IPcMobileFreshCourseService pcMobileFreshCourseService;
	
	

	/**
	 * 课程详情接口
	 */
	/*
	@ApiOperation(value = "课程详情接口", notes = "用户登录后获取课程详情信息接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<AuthCourseViewDTO> view(@RequestBody AuthCourseViewBO authCourseViewBO) {
		return pcWebCourseBiz.view(authCourseViewBO);
	}*/
	

	/**
	 * 课程详情接口
	 */
	@ApiOperation(value = "课程详情接口", notes = "用户登录后获取课程详情信息接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<FreshCourseMobileView> view(@RequestBody AuthCourseViewBO authCourseViewBO) {
		Result<FreshCourseMobileView> result = pcMobileFreshCourseService.viewMobileCourse(authCourseViewBO.getCourseId(),authCourseViewBO.getUserNo());
		FreshCourseMobileView freshCourseMobileView = result.getData();
		/*
		if("1085453180200448002".equals(freshCourseMobileView.getId())){
			freshCourseMobileView.setIsPay(0);
		}*/
		
		
		return 	result;

	}

	
	
	/**
	 * 课时播放获取sign值接口
	 */
	@ApiOperation(value = "课时播放获取sign值接口", notes = "课时播放获取sign值接口")
	@RequestMapping(value = "/sign", method = RequestMethod.POST)
	public Result<AuthCourseSignDTO> sign(@RequestBody AuthCourseSignBO authCourseSignBO) {
		return pcWebCourseBiz.sign(authCourseSignBO);
	}

}
