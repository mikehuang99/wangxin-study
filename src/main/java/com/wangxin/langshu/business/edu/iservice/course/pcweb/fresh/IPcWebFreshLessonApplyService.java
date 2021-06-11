package com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshLessonApply;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplySaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplySortVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcWebFreshLessonApplyService  extends IService<FreshLessonApply> {
	
	

	public Page<FreshLessonApplyVo> queryLessonApplys(FreshLessonApplyQ q) ;
	

	public FreshLessonApplySaveVo saveLessonApply(FreshLessonApplySaveVo vo) ;

	public FreshLessonApplyUpdateVo updateLessonApply(FreshLessonApplyUpdateVo vo) ;
	
	public Result<FreshLessonApplyUpdateVo>  updateLessonApplyLiveBroadcast(FreshLessonApplyUpdateVo vo) ;

	
	
	
	public Result<Integer> updateLessonApplyLessonType(FreshLessonApplyUpdateVo vo) ;
	
	
	public Result<Integer> deleteLessonApply(FreshLessonApplyDeleteVo vo) ;

	
	public FreshLessonApplyViewVo viewLessonApply(FreshLessonApplyViewVo vo) ;
	
	/**
	 * 更新课时排序接口
	 * 
	 * @param 
	 * @return
	 * 
	 */
	@Transactional
	public Result<Integer> sort(FreshLessonApplySortVo freshLessonApplySortVo) ;
	
	
}
