package com.wangxin.langshu.business.edu.controller.course.pcboss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseGetREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CoursePageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseGetRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CoursePageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseViewRESQ;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossCourseBiz;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息
 */
@RestController
@RequestMapping(value = "/pcboss/course/course")
public class PcBossCourseController extends BaseController {

	@Autowired
	private IPcBossCourseBiz pcBossCourseBiz;

	/**
	 * 课程分页列出接口
	 * 
	 * @param coursePageREQ
	 * @return
	 */
	@ApiOperation(value = "课程分页列出接口", notes = "课程分页列出接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<CoursePageRESQ>> list(@RequestBody CoursePageREQ coursePageREQ) {
		return pcBossCourseBiz.list(coursePageREQ);
	}

	/**
	 * 课程更新接口
	 * 
	 * @param courseUpdateREQ
	 * @return
	 */
	@ApiOperation(value = "课程更新接口", notes = "课程更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody CourseUpdateREQ courseUpdateREQ) {
		return pcBossCourseBiz.update(courseUpdateREQ);
	}

	/**
	 * 课程查看接口(课程修改使用)
	 * 
	 * @param courseGetREQ
	 * @return
	 */
	@ApiOperation(value = "课程查看接口(课程修改使用)", notes = "课程查看接口(课程修改使用)")
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public Result<CourseGetRESQ> get(@RequestBody CourseGetREQ courseGetREQ) {
		return pcBossCourseBiz.get(courseGetREQ);
	}

	/**
	 * 课程查看接口
	 * 
	 * @param courseUpdateREQ
	 * @return
	 */
	@ApiOperation(value = "课程查看详情接口", notes = "课程查看详情接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<CourseViewRESQ> view(@RequestBody CourseViewREQ courseViewREQ) {
		return pcBossCourseBiz.view(courseViewREQ);
	}

}
