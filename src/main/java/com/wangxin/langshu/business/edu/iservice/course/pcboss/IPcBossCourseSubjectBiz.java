package com.wangxin.langshu.business.edu.iservice.course.pcboss;

import java.util.List;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseSubjectDeleteREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseSubjectPageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseSubjectSaveREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseSubjectUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseSubjectViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseSubjectPageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseSubjectViewRESQ;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 课程分类
 *
 */

public interface IPcBossCourseSubjectBiz {

	

	/**
	 * 课程分类-分页列出
	 * 
	 * @param req
	 * @return
	 */
	public Result<Page<CourseSubjectPageRESQ>> listForPage(CourseSubjectPageREQ req) ;

	public Result<Integer> save(CourseSubjectSaveREQ req);
	public Result<Integer> delete(CourseSubjectDeleteREQ req) ;

	public Result<Integer> update(CourseSubjectUpdateREQ req) ;

	public Result<CourseSubjectViewRESQ> view(CourseSubjectViewREQ req) ;
	/**
	 * 递归展示分类
	 * 
	 * 
	 */
	//private List<CourseSubjectPageRESQ> recursionList(Long parentId) ;
}
