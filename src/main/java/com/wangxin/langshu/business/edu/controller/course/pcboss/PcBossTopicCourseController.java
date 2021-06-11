package com.wangxin.langshu.business.edu.controller.course.pcboss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicCourseDeleteREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicCoursePageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicCourseSaveREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicCourseUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicCourseViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.TopicCoursePageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.TopicCourseViewRESQ;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossTopicCourseBiz;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 专区课程关联表
 *
 */
@RestController
@RequestMapping(value = "/pcboss/course/topic/course")
public class PcBossTopicCourseController extends BaseController {

	@Autowired
	private IPcBossTopicCourseBiz pcBossTopicCourseBiz;

	/**
	 * 分页列出专区信息
	 * 
	 * @param topicCoursePageREQ
	 * @return
	 */
	@ApiOperation(value = "分页列出专区课程信息", notes = "分页列出专区课程信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<TopicCoursePageRESQ>> listForPage(@RequestBody TopicCoursePageREQ topicCoursePageREQ) {
		return pcBossTopicCourseBiz.listForPage(topicCoursePageREQ);
	}

	/**
	 * 添加专区课程信息
	 * 
	 * @param topicCourseSaveREQ
	 * @return
	 */
	@ApiOperation(value = "添加专区课程信息", notes = "添加专区课程信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody TopicCourseSaveREQ topicCourseSaveREQ) {
		return pcBossTopicCourseBiz.save(topicCourseSaveREQ);
	}

	/**
	 * 删除专区课程信息
	 * 
	 * @param topicCourseDeleteREQ
	 * @return
	 */
	@ApiOperation(value = "删除专区课程信息", notes = "删除专区课程信息")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody TopicCourseDeleteREQ topicCourseDeleteREQ) {
		return pcBossTopicCourseBiz.delete(topicCourseDeleteREQ);
	}

	/**
	 * 更新专区课程信息
	 * 
	 * @param topicCourseUpdateREQ
	 * @return
	 */
	@ApiOperation(value = "更新专区课程信息", notes = "更新专区课程信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody TopicCourseUpdateREQ topicCourseUpdateREQ) {
		return pcBossTopicCourseBiz.update(topicCourseUpdateREQ);
	}

	/**
	 * 查看专区课程信息
	 * 
	 * @param topicCourseViewREQ
	 * @return
	 */
	@ApiOperation(value = "查看专区课程信息", notes = "查看专区课程信息")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<TopicCourseViewRESQ> view(@RequestBody TopicCourseViewREQ topicCourseViewREQ) {
		return pcBossTopicCourseBiz.view(topicCourseViewREQ);
	}

}
