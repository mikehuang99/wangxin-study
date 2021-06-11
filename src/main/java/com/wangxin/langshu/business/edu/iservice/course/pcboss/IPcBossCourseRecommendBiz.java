package com.wangxin.langshu.business.edu.iservice.course.pcboss;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseRecommendDeleteREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseRecommendPageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseRecommendSaveREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseRecommendUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseRecommendViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseRecommendPageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseRecommendViewRESQ;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 
 * 课程推荐
 *
 */

public interface IPcBossCourseRecommendBiz {


	public Result<Page<CourseRecommendPageRESQ>> listForPage(CourseRecommendPageREQ req);
	/**
	 * 课程推荐添加
	 * 
	 * @param req
	 * @return
	 */
	public Result<Integer> save(CourseRecommendSaveREQ req) ;

	/**
	 * 课程推荐删除
	 * 
	 * @param req
	 * @return
	 */
	public Result<Integer> delete(CourseRecommendDeleteREQ req);
	/**
	 * 课程推荐更新
	 * 
	 * @param req
	 * @return
	 */
	public Result<Integer> update(CourseRecommendUpdateREQ req) ;

	/**
	 * 课程推荐查看
	 * 
	 * @param req
	 * @return
	 */
	public Result<CourseRecommendViewRESQ> view(CourseRecommendViewREQ req) ;

}
