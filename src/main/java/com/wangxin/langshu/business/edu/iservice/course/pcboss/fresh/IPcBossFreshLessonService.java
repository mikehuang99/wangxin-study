package com.wangxin.langshu.business.edu.iservice.course.pcboss.fresh;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshLesson;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcBossFreshLessonService  extends IService<FreshLesson> {
	
	

	public Page<FreshLessonVo> queryLessons(FreshLessonQ q) ;
	

	public FreshLessonSaveVo saveLesson(FreshLessonSaveVo vo) ;

	public FreshLessonUpdateVo updateLesson(FreshLessonUpdateVo vo) ;

	
	public Result<Integer> deleteLesson(FreshLessonDeleteVo vo) ;

	
	public FreshLessonViewVo viewLesson(FreshLessonViewVo vo) ;
	
	
	
	
}
