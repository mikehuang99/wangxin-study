package com.wangxin.langshu.business.edu.iservice.course.pcboss;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseApplyApplyStatusREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseApplyGetREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseApplyPageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseApplyUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseApplyViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseApplyGetRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseApplyPageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseApplyViewRESQ;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 课程信息-审核
 */
public interface IPcBossCourseApplyBiz {


	public Result<Page<CourseApplyPageRESQ>> list(CourseApplyPageREQ req) ;

	/**
	 * 更新
	 * 
	 * @param req
	 * @return
	 */
	
	public Result<Integer> update(CourseApplyUpdateREQ req) ;
	/**
	 * 查看(课程修改使用)
	 * 
	 * @param req
	 * @return
	 */
	public Result<CourseApplyGetRESQ> get(CourseApplyGetREQ req) ;

	/**
	 * 查看详情
	 * 
	 * @param req
	 * @return
	 */
	public Result<CourseApplyViewRESQ> view(CourseApplyViewREQ req) ;
	public Result<Integer> audit(CourseApplyApplyStatusREQ req);
	// 审核章节
	//private void chapter(CourseApply courseApply) ;
	// 课时审核
	//private void lesson(CourseApply courseApply);
}
