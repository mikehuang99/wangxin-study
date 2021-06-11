package com.wangxin.langshu.business.edu.controller.course.pcboss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseSubjectDeleteREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseSubjectPageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseSubjectSaveREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseSubjectUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseSubjectViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseSubjectPageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseSubjectViewRESQ;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossCourseSubjectBiz;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程分类
 *
 */
@RestController
@RequestMapping(value = "/pcboss/course/course/subject")
public class PcBossCourseSubjectController extends BaseController {

	@Autowired
	private IPcBossCourseSubjectBiz pcBossCourseSubjectBiz;

	/**
	 * 分页列出课程分类信息
	 * 
	 * @param courseSubjectPageREQ
	 * @return
	 */
	@ApiOperation(value = "分页列出课程分类信息", notes = "分页列出课程分类信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<CourseSubjectPageRESQ>> listForPage(@RequestBody CourseSubjectPageREQ courseSubjectPageREQ) {
		return pcBossCourseSubjectBiz.listForPage(courseSubjectPageREQ);
	}

	/**
	 * 添加课程分类信息
	 * 
	 * @param courseSubjectSaveREQ
	 * @return
	 */
	@ApiOperation(value = "添加课程分类信息", notes = "添加课程分类信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody CourseSubjectSaveREQ courseSubjectSaveREQ) {
		return pcBossCourseSubjectBiz.save(courseSubjectSaveREQ);
	}

	/**
	 * 删除课程分类信息
	 * 
	 * @param courseSubjectDeleteREQ
	 * @return
	 */
	@ApiOperation(value = "删除课程分类信息", notes = "删除课程分类信息")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody CourseSubjectDeleteREQ courseSubjectDeleteREQ) {
		return pcBossCourseSubjectBiz.delete(courseSubjectDeleteREQ);
	}

	/**
	 * 更新课程分类信息
	 * 
	 * @param courseSubjectUpdateREQ
	 * @return
	 */
	@ApiOperation(value = "更新课程分类信息", notes = "更新课程分类信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody CourseSubjectUpdateREQ courseSubjectUpdateREQ) {
		return pcBossCourseSubjectBiz.update(courseSubjectUpdateREQ);
	}

	/**
	 * 查看课程分类信息
	 * 
	 * @param courseSubjectViewREQ
	 * @return
	 */
	@ApiOperation(value = "查看课程分类信息", notes = "查看课程分类信息")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<CourseSubjectViewRESQ> view(@RequestBody CourseSubjectViewREQ courseSubjectViewREQ) {
		return pcBossCourseSubjectBiz.view(courseSubjectViewREQ);
	}

}
