package com.wangxin.langshu.business.edu.iservice.course.pcboss;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicCourseDeleteREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicCoursePageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicCourseSaveREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicCourseUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicCourseViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.TopicCoursePageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.TopicCourseViewRESQ;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 
 * 专区课程关联表
 *
 */

public interface IPcBossTopicCourseBiz {
	

	/**
	 * 分页列出
	 * 
	 * @param topicCoursePageREQ
	 * @return
	 */
	public Result<Page<TopicCoursePageRESQ>> listForPage(TopicCoursePageREQ req);

	/**
	 * 专区课程保存
	 * 
	 * @param req
	 * @return
	 */
	public Result<Integer> save(TopicCourseSaveREQ req) ;

	/**
	 * 专区课程删除
	 * 
	 * @param topicCourseDeleteREQ
	 * @return
	 */
	public Result<Integer> delete(TopicCourseDeleteREQ topicCourseDeleteREQ) ;

	/**
	 * 专区课程更新
	 * 
	 * @param topicCourseUpdateREQ
	 * @return
	 */
	public Result<Integer> update(TopicCourseUpdateREQ topicCourseUpdateREQ) ;

	/**
	 * 专区课程查看
	 * 
	 * @param topicCourseViewREQ
	 * @return
	 */
	public Result<TopicCourseViewRESQ> view(TopicCourseViewREQ topicCourseViewREQ) ;
}
