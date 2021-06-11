package com.wangxin.langshu.business.edu.iservice.course.pccommon.fresh;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourse;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseVo;
import com.wangxin.langshu.business.util.base.Page;

public interface IPcCommonFreshCourseService  extends IService<FreshCourse> {
	
	

	public Page<FreshCourseVo> queryCourses(FreshCourseQ q) ;
	

	public boolean saveCourse(FreshCourseSaveVo vo) ;

	public boolean updateCourse(FreshCourseUpdateVo vo) ;

	
	public boolean deleteCourse(FreshCourseDeleteVo vo) ;

	
	public FreshCourseViewVo viewCourse(FreshCourseViewVo vo) ;
	
	
	
	
}
