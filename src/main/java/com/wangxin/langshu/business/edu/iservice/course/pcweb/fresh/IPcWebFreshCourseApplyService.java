package com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourseApply;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplySaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcWebFreshCourseApplyService  extends IService<FreshCourseApply> {
	
	

	public Page<FreshCourseApplyVo> queryCourseApplys(FreshCourseApplyQ q) ;
	

	public FreshCourseApplySaveVo saveCourseApply(FreshCourseApplySaveVo vo) ;

	public boolean updateCourseApply(FreshCourseApplyUpdateVo vo) ;

	public Result<Integer>  updateCoursePutaway(FreshCourseUpdateVo vo) ;
	
	public boolean deleteCourseApply(FreshCourseApplyDeleteVo vo) ;

	
	public Result<FreshCourseApplyViewVo> viewCourseApply(FreshCourseApplyViewVo vo) ;
	
	
	
	
}
