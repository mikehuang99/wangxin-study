package com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshChapter;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcWebFreshChapterService  extends IService<FreshChapter> {
	
	

	public Page<FreshChapterVo> queryChapters(FreshChapterQ q) ;
	

	public FreshChapterSaveVo saveChapter(FreshChapterSaveVo vo) ;

	public FreshChapterUpdateVo updateChapter(FreshChapterUpdateVo vo) ;

	
	public Result<Integer> deleteChapter(FreshChapterDeleteVo vo) ;

	
	public FreshChapterViewVo viewChapter(FreshChapterViewVo vo) ;
	
	
	
	
}
