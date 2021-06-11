package com.wangxin.langshu.business.edu.iservice.course.pccommon;

import org.springframework.stereotype.Service;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.TopicBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.TopicDTO;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IApiTopicBiz {


	/**
	 * 专区课程分页列表接口
	 *
	 * @param topicBO
	 * 
	 */
	public Result<Page<TopicDTO>> listForPage(TopicBO topicBO);

}
