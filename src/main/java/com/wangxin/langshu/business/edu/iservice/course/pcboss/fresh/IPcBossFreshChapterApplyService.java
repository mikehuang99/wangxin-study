package com.wangxin.langshu.business.edu.iservice.course.pcboss.fresh;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshChapterApply;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplySaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplySortVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcBossFreshChapterApplyService  extends IService<FreshChapterApply> {
	
	

	public Page<FreshChapterApplyVo> queryChapterApplys(FreshChapterApplyQ q) ;
	

	public FreshChapterApplySaveVo saveChapterApply(FreshChapterApplySaveVo vo) ;

	public FreshChapterApplyUpdateVo updateChapterApply(FreshChapterApplyUpdateVo vo) ;

	
	public Result<Integer> deleteChapterApply(FreshChapterApplyDeleteVo vo) ;

	
	public FreshChapterApplyViewVo viewChapterApply(FreshChapterApplyViewVo vo) ;
	
	
	
}
