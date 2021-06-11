package com.wangxin.langshu.business.edu.iservice.course.pcboss.fresh;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourse;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseMobileView;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcBossFreshCourseService  extends IService<FreshCourse> {
	
	

	public Page<FreshCourseVo> queryCourses(FreshCourseQ q) ;
	

	public FreshCourseSaveVo saveCourse(FreshCourseSaveVo vo) ;

	public Result<Integer>  updateCourse(FreshCourseUpdateVo vo) ;

	public Result<Integer>  updateCoursePutaway(FreshCourseUpdateVo vo) ;
	
	public Result<Integer>  updateCourseValidValue(FreshCourseUpdateVo vo) ;
	
	public boolean deleteCourse(FreshCourseDeleteVo vo) ;

	
	public FreshCourseViewVo viewCourse(FreshCourseViewVo vo) ;
	

	
	
	
}
