package com.wangxin.langshu.business.edu.controller.course.pcboss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicDeleteREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicPageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicSaveREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.TopicPageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.TopicViewRESQ;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossTopicBiz;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 专区表
 *
 */
@RestController
@RequestMapping(value = "/pcboss/course/topic")
public class PcBossTopicController extends BaseController {

	@Autowired
	private IPcBossTopicBiz pcBossTopicBiz;

	/**
	 * 分页列出专区信息
	 * 
	 * @param topicPageREQ
	 * @return
	 */
	@ApiOperation(value = "分页列出专区信息", notes = "分页列出专区信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<TopicPageRESQ>> listForPage(@RequestBody TopicPageREQ topicPageREQ) {
		return pcBossTopicBiz.listForPage(topicPageREQ);
	}

	/**
	 * 添加专区信息
	 * 
	 * @param topicSaveREQ
	 * @return
	 */
	@ApiOperation(value = "添加专区信息", notes = "添加专区信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody TopicSaveREQ topicSaveREQ) {
		return pcBossTopicBiz.save(topicSaveREQ);
	}

	/**
	 * 删除专区信息
	 * 
	 * @param topicDeleteREQ
	 * @return
	 */
	@ApiOperation(value = "删除专区信息", notes = "删除专区信息")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody TopicDeleteREQ topicDeleteREQ) {
		return pcBossTopicBiz.delete(topicDeleteREQ);
	}

	/**
	 * 更新专区信息
	 * 
	 * @param topicUpdateREQ
	 * @return
	 */
	@ApiOperation(value = "更新专区信息", notes = "更新专区信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody TopicUpdateREQ topicUpdateREQ) {
		return pcBossTopicBiz.update(topicUpdateREQ);
	}

	/**
	 * 查看专区信息
	 * 
	 * @param topicViewREQ
	 * @return
	 */
	@ApiOperation(value = "查看专区信息", notes = "查看专区信息")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<TopicViewRESQ> view(@RequestBody TopicViewREQ topicViewREQ) {
		return pcBossTopicBiz.view(topicViewREQ);
	}

}
