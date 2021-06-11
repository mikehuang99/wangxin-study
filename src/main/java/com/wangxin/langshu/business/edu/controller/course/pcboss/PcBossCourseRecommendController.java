package com.wangxin.langshu.business.edu.controller.course.pcboss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseRecommendDeleteREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseRecommendPageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseRecommendSaveREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseRecommendUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseRecommendViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseRecommendPageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseRecommendViewRESQ;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossCourseRecommendBiz;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程推荐
 *
 */
@RestController
@RequestMapping(value = "/pcboss/course/course/recommend")
public class PcBossCourseRecommendController extends BaseController {

	@Autowired
	private IPcBossCourseRecommendBiz pcBossCourseRecommendBiz;

	/**
	 * 分页列出课程推荐信息
	 * 
	 * @param courseRecommendPageREQ
	 * @return
	 */
	@ApiOperation(value = "分页列出课程推荐信息", notes = "分页列出课程推荐信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<CourseRecommendPageRESQ>> listForPage(@RequestBody CourseRecommendPageREQ courseRecommendPageREQ) {
		return pcBossCourseRecommendBiz.listForPage(courseRecommendPageREQ);
	}

	/**
	 * 添加课程推荐信息
	 * 
	 * @param courseRecommendSaveREQ
	 * @return
	 */
	@ApiOperation(value = "添加课程推荐信息", notes = "添加课程推荐信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody CourseRecommendSaveREQ courseRecommendSaveREQ) {
		return pcBossCourseRecommendBiz.save(courseRecommendSaveREQ);
	}

	/**
	 * 删除课程推荐信息
	 * 
	 * @param courseRecommendDeleteREQ
	 * @return
	 */
	@ApiOperation(value = "删除课程推荐信息", notes = "删除课程推荐信息")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody CourseRecommendDeleteREQ courseRecommendDeleteREQ) {
		return pcBossCourseRecommendBiz.delete(courseRecommendDeleteREQ);
	}

	/**
	 * 更新课程推荐信息
	 * 
	 * @param courseRecommendUpdateREQ
	 * @return
	 */
	@ApiOperation(value = "更新课程推荐信息", notes = "更新课程推荐信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody CourseRecommendUpdateREQ courseRecommendUpdateREQ) {
		return pcBossCourseRecommendBiz.update(courseRecommendUpdateREQ);
	}

	/**
	 * 查看课程推荐信息
	 * 
	 * @param courseSubjectViewREQ
	 * @return
	 */
	@ApiOperation(value = "查看课程推荐信息", notes = "查看课程推荐信息")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<CourseRecommendViewRESQ> view(@RequestBody CourseRecommendViewREQ courseRecommendViewREQ) {
		return pcBossCourseRecommendBiz.view(courseRecommendViewREQ);
	}

}
