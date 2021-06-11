package com.wangxin.langshu.business.edu.service.market.pccommon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.market.StudyCardDao;
import com.wangxin.langshu.business.edu.dao.market.impl.mapper.StudyCardMapper;
import com.wangxin.langshu.business.edu.entity.market.StudyCard;
import com.wangxin.langshu.business.edu.iservice.market.pccommon.IStudyCardLimitCourseService;
import com.wangxin.langshu.business.edu.iservice.market.pccommon.IStudyCardService;
import com.wangxin.langshu.business.edu.iservice.user.pccommon.IStudentGroupService;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardQ;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardSaveVo;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardViewVo;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardVo;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseSaveVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;


@Service
public class StudyCardService  extends ServiceImpl<StudyCardMapper, StudyCard> implements IStudyCardService {

	@Autowired
	public StudyCardDao studyCardDao;
	
	@Autowired
    private StudyCardMapper studyCardMapper;
	
	@Autowired
	private IStudentGroupService studentGroupService;
	
	@Autowired
	private IStudyCardLimitCourseService studyCardLimitCourseService;


	@Override
	public Page<StudyCardVo> queryStudyCards(StudyCardQ q) {
		return studyCardDao.queryStudyCards(q);
	}

	@Override
	@Transactional
	public boolean saveStudyCard(StudyCardSaveVo vo) {
		if(vo.getCardNoBit()<1||vo.getCardNoBit()>13){
			throw new BaseException("卡号不能超过13位");
		}
		if(vo.getCardPswBit()<1||vo.getCardPswBit()>13){
			throw new BaseException("卡密码不能超过13位");
		}
		for(int i=0;i<vo.getCardProduceNumber(); i++){
			String id = UUID.randomUUID().toString().replace("-", "");
			StudyCard studyCard = new StudyCard();
			BeanUtils.copyProperties(vo, studyCard);
			int count=100;
			String cardNo = "";
			String cardPsw = "";
			while(count<=100&&count>0){
				cardNo = getFixLenthRandom(vo.getCardNoBit());
				cardPsw = getFixLenthRandom(vo.getCardPswBit());
				if(studyCardDao.isExist(cardNo, cardPsw)){
					count--;
				}else{
					break;
				}
			}
			if(StringUtils.isEmpty(cardNo)||StringUtils.isEmpty(cardPsw)){
				throw new BaseException("卡号或者卡密码生成失败");
			}
			studyCard.setCardNo(cardNo);
			studyCard.setCardPsw(cardPsw);
			studyCard.setId(id);
			this.save(studyCard);
			
			String selectedCourseIdsString = vo.getSelectedCourseIdsString();
			if(StringUtils.isNotEmpty(selectedCourseIdsString)){
				String selectedCourseIds[] = selectedCourseIdsString.split(",");
				Set<String> set = new HashSet<>();
				for(int n=0;n<selectedCourseIds.length;n++){
					set.add(selectedCourseIds[n]);
				}
				List<String> list = new ArrayList<>();
				list.addAll(set);
				for(int n=0;n<list.size();n++){
					if(StringUtils.isNotEmpty(list.get(n))&&!"undefined".equals(list.get(n).trim())){
						StudyCardLimitCourseSaveVo studyCardLimitCourseSaveVo = new StudyCardLimitCourseSaveVo();
						studyCardLimitCourseSaveVo.setValidValue(1);
						studyCardLimitCourseSaveVo.setStudyCardId(id);
						studyCardLimitCourseSaveVo.setCourseId(list.get(n).trim());
						studyCardLimitCourseService.saveStudyCardLimitCourse(studyCardLimitCourseSaveVo);
					}
				}
			}
			
		}
		return true;
	}

	@Override
	public boolean updateStudyCard(StudyCardUpdateVo vo) {
		StudyCard StudyCard = new StudyCard();
		BeanUtils.copyProperties(vo, StudyCard);
		this.updateById(StudyCard);
		return true;
	}
	


	@Override
	public boolean deleteStudyCard(StudyCardDeleteVo vo) {
		if(vo.getIds().length<1){
    		throw new BaseException("删除ID不能为空");
		}
		for(int i=0;i<vo.getIds().length;i++){
			String id = vo.getIds()[i];
			this.removeById(id);
			
			studyCardLimitCourseService.deleteStudyCardLimitCoursesByStudyCardId(id);
		}

		return true;
	}

	@Override
	public StudyCardViewVo viewStudyCard(StudyCardViewVo viewVo) {
		StudyCard examManagement = this.getById(viewVo.getId());
		BeanUtils.copyProperties(examManagement, viewVo);
		
		viewVo.setSelectedCourseIdsString(studyCardLimitCourseService.queryAllStudyCardLimitCoursesString(viewVo.getId()));

		return viewVo;
	}

	/* 
	 * 返回长度为【strLength】的随机数字，在前面补0 ，最长13位
	 */  
	private static String getFixLenthRandom(int strLength) {  
		if(strLength>0&&strLength<=13){
		    Random rm = new Random();  
		    // 获得随机数  
		    double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);  
		    System.out.println("pross:"+pross);
		    // 将获得的获得随机数转化为字符串  
		    String fixLenthString = String.valueOf(pross); 
		    fixLenthString = fixLenthString.replace(".", "");
		    System.out.println("fixLenthString:"+fixLenthString);
		    // 返回固定的长度的随机数  
		    return fixLenthString.substring(2, strLength + 2);  
		}else{
			throw new BaseException("随机数长度不能超过13");
		}
	}


	
	
	


	
	
}
