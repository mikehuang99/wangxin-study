package com.wangxin.langshu.business.edu.iservice.course.pcboss.fresh;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourseApply;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyAuditVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplySaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcBossFreshCourseApplyService  extends IService<FreshCourseApply> {
	
	

	public Page<FreshCourseApplyVo> queryCourseApplys(FreshCourseApplyQ q) ;
	

	public FreshCourseApplySaveVo saveCourseApply(FreshCourseApplySaveVo vo) ;

	public boolean updateCourseApply(FreshCourseApplyUpdateVo vo) ;

	
	public Result<Integer> deleteCourseApply(FreshCourseApplyDeleteVo vo) ;

	
	public FreshCourseApplyViewVo viewCourseApply(FreshCourseApplyViewVo vo) ;
	
	
	public Result<Integer> audit(FreshCourseApplyAuditVo vo);
	

	
}
