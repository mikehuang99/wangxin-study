package com.wangxin.langshu.business.edu.controller.course.pccommon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.TopicBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.TopicDTO;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiTopicBiz;
import com.wangxin.langshu.business.edu.iservice.course.pcmobile.fresh.IPcMobileFreshTopicService;
import com.wangxin.langshu.business.edu.vo.course.fresh.topic.FreshTopicQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.topic.FreshTopicVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 专区课程关联表
 *
 */
@RestController
@RequestMapping(value = "/pccommon/course/topic/course")
public class PcCommonTopicController extends BaseController {

	/*
	@Autowired
	private IApiTopicBiz apiTopicBiz;
	*/

	@Autowired
	private IPcMobileFreshTopicService pcMobileFreshTopicService;
	
	/**
	 * 专区课程分页列表接口
	 * 
	 * @param bo
	 * @return
	 */
	/*
	@ApiOperation(value = "专区课程列表接口", notes = "分页列出专区课程信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<TopicDTO>> listForPage(@RequestBody TopicBO topicBO) {
		return apiTopicBiz.listForPage(topicBO);
	}*/
	
	/**
	 * 专区课程分页列表接口
	 * 
	 * @param bo
	 * @return
	 */
	@ApiOperation(value = "专区课程列表接口", notes = "分页列出专区课程信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<FreshTopicVo>> topic() {
		FreshTopicQ freshTopicQ = new FreshTopicQ();
		freshTopicQ.setTopicLocation("0");//设置PC位置代码
		freshTopicQ.setValidValue("1");
		return pcMobileFreshTopicService.queryTopics(freshTopicQ);
		
	
		
	}

}
