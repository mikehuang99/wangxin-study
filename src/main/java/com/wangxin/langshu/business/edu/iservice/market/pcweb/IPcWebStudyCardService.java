package com.wangxin.langshu.business.edu.iservice.market.pcweb;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.market.StudyCard;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardQ;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcWebStudyCardService  extends IService<StudyCard> {
	
	

	public Result<Page<StudyCardVo>> queryStudyCards(StudyCardQ q) ;

	/**
	 * 绑定学习卡用户之前检查积分是否足够
	 * @param vo
	 * @return
	 */
	public Result<StudyCardVo> checkBeforebindStudyCardUserNo(StudyCardUpdateVo vo) ;
	
	/**
	 * 绑定学习卡用户
	 * @param vo
	 * @return
	 */
	public Result<Integer> bindStudyCardUserNo(StudyCardUpdateVo vo) ;

	
	
	
	
	
	
}
