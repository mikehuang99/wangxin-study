package com.wangxin.langshu.business.edu.service.market.pcweb;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.market.PointDao;
import com.wangxin.langshu.business.edu.dao.market.StudyCardDao;
import com.wangxin.langshu.business.edu.dao.market.impl.mapper.StudyCardMapper;
import com.wangxin.langshu.business.edu.entity.market.Point;
import com.wangxin.langshu.business.edu.entity.market.StudyCard;
import com.wangxin.langshu.business.edu.iservice.market.pccommon.IStudyCardLimitCourseService;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebPointOperateRecordService;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebPointService;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebStudyCardService;
import com.wangxin.langshu.business.edu.iservice.user.pccommon.IStudentGroupService;
import com.wangxin.langshu.business.edu.vo.market.point.PointQ;
import com.wangxin.langshu.business.edu.vo.market.point.PointViewVo;
import com.wangxin.langshu.business.edu.vo.market.point.PointVo;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordSaveVo;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardQ;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.xiaoleilu.hutool.util.CollectionUtil;


@Service
public class PcWebStudyCardService  extends ServiceImpl<StudyCardMapper, StudyCard> implements IPcWebStudyCardService {

	@Autowired
	public StudyCardDao studyCardDao;
	
	@Autowired
    private StudyCardMapper studyCardMapper;
	

	
	@Autowired
	private IStudentGroupService studentGroupService;
	
	@Autowired
	private IStudyCardLimitCourseService studyCardLimitCourseService;
	
	
	@Autowired
	private PointDao pointDao;
	
	@Autowired
	private IPcWebPointService pcWebPointService;
	
	@Autowired
	private IPcWebPointOperateRecordService pcWebPointOperateRecordService;


	@Override
	public Result<Page<StudyCardVo>> queryStudyCards(StudyCardQ q) {
		if(StringUtils.isEmpty(q.getUserNo())){
			return Result.error("缺少userNo,必须在登录的情况下查询");
		}
		q.setBindUserNo(q.getUserNo());
		q.setIsStudyCardTimeEndBeforeToday("TRUE");
		q.setPageCurrent(-1);
		q.setPageSize(-1);
		q.setValidValue("1");
		return Result.success(studyCardDao.queryPcWebStudyCards(q));
	}

	@Override
	public Result<StudyCardVo> checkBeforebindStudyCardUserNo(StudyCardUpdateVo vo) {
		vo.setCardNo(vo.getCardNo().trim());
		vo.setCardPsw(vo.getCardPsw().trim());
		
		StudyCardQ studyCardQ = new StudyCardQ();
		studyCardQ.setCardNo(vo.getCardNo());
		studyCardQ.setCardPsw(vo.getCardPsw());
		Page<StudyCardVo> page = studyCardDao.queryPcWebStudyCards(studyCardQ);
		if(CollectionUtil.isEmpty(page.getList())){
			 return Result.error("学习卡卡号密码不正确，系统找不到该学习卡");
		}
		if(page.getList().size()>1){
			return Result.error("系统数据异常");
		}
		StudyCardVo studyCardVo = page.getList().get(0);
		return Result.success(studyCardVo);
	}


	@Override
	public Result<Integer> bindStudyCardUserNo(StudyCardUpdateVo vo) {
		vo.setCardNo(vo.getCardNo().trim());
		vo.setCardPsw(vo.getCardPsw().trim());
		
		StudyCardQ studyCardQ = new StudyCardQ();
		studyCardQ.setCardNo(vo.getCardNo());
		studyCardQ.setCardPsw(vo.getCardPsw());
		Page<StudyCardVo> page = studyCardDao.queryPcWebStudyCards(studyCardQ);
		if(CollectionUtil.isEmpty(page.getList())){
			 return Result.error("学习卡卡号密码不正确，系统找不到该学习卡");
		}
		if(page.getList().size()>1){
			return Result.error("系统数据异常");
		}
		StudyCardVo studyCardVo = page.getList().get(0);
		if(0==studyCardVo.getValidValue()){
			return Result.error("该学习卡目前为停用状态");
		}
		if(!(0==studyCardVo.getCardStatus())){
			return Result.error("该学习卡已经被绑定");
		}

        int valuePoint = studyCardVo.getValuePoint();
        
        if(valuePoint>0){
	        Result<Integer> reducePointResult = pcWebPointService.reducePoint(valuePoint,vo.getUserNo());
	        if(reducePointResult.getCode().intValue() == ResultEnum.ERROR.getCode().intValue()){
	        	return reducePointResult;
	        }
        }
        
        StudyCard studyCard = this.getById(studyCardVo.getId());
        studyCard.setBindUserNo(vo.getUserNo());
        Date date = new Date();
        studyCard.setBindUserTime(date);
        studyCard.setCardStatus(1);
        studyCard.setModifiedTime(date);
        
        if(studyCard.getDaysFromBindUserTime()>0){
        	int days = studyCard.getDaysFromBindUserTime();
    		long time = date.getTime();
            Date studyCardTimeBegin = studyCard.getStudyCardTimeBegin();
            Date studyCardTimeEnd = studyCard.getStudyCardTimeEnd();
            if(time>studyCardTimeBegin.getTime()){//当天大于有效期开始日期，重置开始日期为今天
            	studyCard.setStudyCardTimeBegin(date);
            }
            long endTime = studyCard.getStudyCardTimeBegin().getTime()+days*24*60*60*1000L;
            if(endTime<studyCardTimeEnd.getTime()){//用激活后有效天数计算出来的结束日期如果小于有效期结束日期，重置结束日期为激活后有效天数计算出来的结束日期
            	studyCard.setStudyCardTimeEnd(new Date(endTime));
            }
        }
        
        this.updateById(studyCard);
        
		return Result.success(1);
	}


	
	


	
	
	


	
	
}
