package com.wangxin.langshu.business.edu.iservice.course.pcboss;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseGetREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CoursePageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseGetRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CoursePageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseViewRESQ;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 课程信息
 */
public interface IPcBossCourseBiz {

	

	/**
	 * 分页列出
	 * 
	 * @param req
	 * @return
	 */
	public Result<Page<CoursePageRESQ>> list(CoursePageREQ req) ;

	
	public Result<Integer> update(CourseUpdateREQ req) ;

	/**
	 * 查看(课程修改使用)
	 * 
	 * @param req
	 * @return
	 */
	public Result<CourseGetRESQ> get(CourseGetREQ req) ;

	/**
	 * 查看详情
	 * 
	 * @param req
	 * @return
	 */
	public Result<CourseViewRESQ> view(CourseViewREQ req) ;

}
