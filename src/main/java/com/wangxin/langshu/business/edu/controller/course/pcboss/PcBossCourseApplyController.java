package com.wangxin.langshu.business.edu.controller.course.pcboss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseApplyApplyStatusREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseApplyGetREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseApplyPageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseApplyUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseApplyViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseApplyGetRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseApplyPageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseApplyViewRESQ;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossCourseApplyBiz;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息-审核
 */
@RestController
@RequestMapping(value = "/pcboss/course/course/apply")
public class PcBossCourseApplyController extends BaseController {

	@Autowired
	private IPcBossCourseApplyBiz pcBossCourseApplyBiz;

	/**
	 * 课程-审核分页列出接口
	 * 
	 * @param courseApplyPageREQ
	 * @return
	 */
	@ApiOperation(value = "课程-审核分页列出接口", notes = "课程-审核分页列出接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<CourseApplyPageRESQ>> list(@RequestBody CourseApplyPageREQ courseApplyPageREQ) {
		return pcBossCourseApplyBiz.list(courseApplyPageREQ);
	}

	/**
	 * 课程-审核更新接口
	 * 
	 * @param courseApplyUpdateREQ
	 * @return
	 */
	@ApiOperation(value = "课程-审核更新接口", notes = "课程-审核更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody CourseApplyUpdateREQ courseApplyUpdateREQ) {
		return pcBossCourseApplyBiz.update(courseApplyUpdateREQ);
	}

	/**
	 * 课程-审核接口
	 * 
	 * @param courseApplyUpdateREQ
	 * @return
	 */
	@ApiOperation(value = "课程-审核接口", notes = "课程-审核接口")
	@RequestMapping(value = "/audit", method = RequestMethod.POST)
	public Result<Integer> audit(@RequestBody CourseApplyApplyStatusREQ courseApplyApplyStatusREQ) {
		return pcBossCourseApplyBiz.audit(courseApplyApplyStatusREQ);
	}

	/**
	 * 课程查看接口(课程修改使用)
	 * 
	 * @param courseGetREQ
	 * @return
	 */
	@ApiOperation(value = "课程-审核查看接口(课程修改使用)", notes = "课程-审核查看接口(课程修改使用)")
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public Result<CourseApplyGetRESQ> get(@RequestBody CourseApplyGetREQ courseApplyGetREQ) {
		return pcBossCourseApplyBiz.get(courseApplyGetREQ);
	}

	/**
	 * 课程-审核查看接口
	 * 
	 * @param courseUpdateREQ
	 * @return
	 */
	@ApiOperation(value = "课程-审核查看详情接口", notes = "课程-审核查看详情接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<CourseApplyViewRESQ> view(@RequestBody CourseApplyViewREQ courseApplyViewREQ) {
		return pcBossCourseApplyBiz.view(courseApplyViewREQ);
	}

}
