package com.wangxin.langshu.business.edu.iservice.course.pcboss;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicDeleteREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicPageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicSaveREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.TopicPageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.TopicViewRESQ;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 
 * 专区
 *
 */

public interface IPcBossTopicBiz {

	

	/**
	 * 分页列出
	 * 
	 * @param req
	 * @return
	 */
	public Result<Page<TopicPageRESQ>> listForPage(TopicPageREQ req) ;
	public Result<Integer> save(TopicSaveREQ req) ;
	public Result<Integer> delete(TopicDeleteREQ req) ;

	public Result<Integer> update(TopicUpdateREQ req);

	public Result<TopicViewRESQ> view(TopicViewREQ req) ;
}
