package com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshLesson;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcWebFreshLessonService  extends IService<FreshLesson> {
	
	

	public Page<FreshLessonVo> queryLessons(FreshLessonQ q) ;
	

	public FreshLessonSaveVo saveLesson(FreshLessonSaveVo vo) ;

	public FreshLessonUpdateVo updateLesson(FreshLessonUpdateVo vo) ;

	
	public Result<Integer> deleteLesson(FreshLessonDeleteVo vo) ;

	
	public FreshLessonViewVo viewLesson(FreshLessonViewVo vo) ;
	
	//查看已经付款的课时
	public Result<FreshLessonViewVo> viewHadPayLesson(FreshLessonViewVo vo) ;
	
	public Result<FreshLessonViewVo> queryCourseDocUrlByLessonId(FreshLessonViewVo vo) ;
	
}
