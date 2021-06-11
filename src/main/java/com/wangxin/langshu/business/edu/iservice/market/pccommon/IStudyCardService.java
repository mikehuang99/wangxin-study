package com.wangxin.langshu.business.edu.iservice.market.pccommon;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.market.StudyCard;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardQ;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardSaveVo;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardViewVo;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardVo;
import com.wangxin.langshu.business.util.base.Page;

public interface IStudyCardService  extends IService<StudyCard> {
	
	

	public Page<StudyCardVo> queryStudyCards(StudyCardQ q) ;

	public boolean saveStudyCard(StudyCardSaveVo vo) ;

	public boolean updateStudyCard(StudyCardUpdateVo vo) ;
	

	
	public boolean deleteStudyCard(StudyCardDeleteVo vo) ;

	
	public StudyCardViewVo viewStudyCard(StudyCardViewVo vo) ;
	
	
	
	
}
