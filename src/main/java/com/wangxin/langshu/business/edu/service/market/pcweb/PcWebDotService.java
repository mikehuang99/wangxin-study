package com.wangxin.langshu.business.edu.service.market.pcweb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.market.DotDao;
import com.wangxin.langshu.business.edu.dao.market.impl.mapper.DotMapper;
import com.wangxin.langshu.business.edu.entity.market.Dot;
import com.wangxin.langshu.business.edu.iservice.market.pccommon.IDotConfigService;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebCouponService;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebDotOperateRecordService;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebDotService;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebPointService;
import com.wangxin.langshu.business.edu.vo.market.dot.DotDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.dot.DotQ;
import com.wangxin.langshu.business.edu.vo.market.dot.DotSaveVo;
import com.wangxin.langshu.business.edu.vo.market.dot.DotUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.dot.DotViewVo;
import com.wangxin.langshu.business.edu.vo.market.dot.DotVo;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigQ;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigVo;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordQ;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordSaveVo;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordVo;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordViewVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.DotGainRuleEnum;
import com.xiaoleilu.hutool.util.CollectionUtil;


@Service
public class PcWebDotService  extends ServiceImpl<DotMapper, Dot> implements IPcWebDotService {

	@Autowired
	public DotDao dotDao;
	
	@Autowired
    private DotMapper dotMapper;
	
	@Autowired
	private IPcWebCouponService pcWebCouponService;
	
	@Autowired
	private IDotConfigService dotConfigService;
	
	@Autowired
	private IPcWebDotOperateRecordService pcWebDotOperateRecordService;
	
	
	@Autowired
	private IPcWebPointService pcWebPointService;


	@Override
	public Page<DotVo> queryDots(DotQ q) {
		return dotDao.queryDots(q);
	}

	@Override
	@Transactional
	public Result<Integer> saveDot(DotSaveVo vo) {
		Dot dot = new Dot();
		BeanUtils.copyProperties(vo, dot);
		String id = UUID.randomUUID().toString();
		dot.setId(id);
		dot.setValidValue(1);
		this.save(dot);
		return Result.success(1);
	}

	@Override
	public Result<Integer> updateDot(DotUpdateVo vo) {
		Dot Dot = new Dot();
		BeanUtils.copyProperties(vo, Dot);
		this.updateById(Dot);
		
		
		return Result.success(1);
	}

	@Override
	public Result<Integer> deleteDot(DotDeleteVo vo) {
		return Result.success(1);	
	}

	@Override
	public DotViewVo viewDot(DotViewVo viewVo) {
		Dot v = this.getById(viewVo.getId());
		BeanUtils.copyProperties(v, viewVo);
		return viewVo;
	}

	@Override
	public DotViewVo viewDotByUserNo(String userNo) {
		if(StringUtils.isEmpty(userNo)){
			throw new BaseException("userNo不能为空");
		}
		DotViewVo dotViewVo = new DotViewVo();
		DotQ q= new DotQ();
		q.setBindUserNo(userNo);
		Page<DotVo> page = dotDao.queryDots(q);
		if(CollectionUtil.isEmpty(page.getList())){
			//增加初始化数据
			Dot dot = new Dot();
			String id = UUID.randomUUID().toString();
			dot.setId(id);
			dot.setBindUserNo(userNo);
			dot.setTotalDot(0);
			dot.setValidValue(1);
			this.save(dot);
			BeanUtils.copyProperties(dot, dotViewVo);
		}else{
			BeanUtils.copyProperties(page.getList().get(0), dotViewVo);
		}
		return dotViewVo;
	}
	
	@Override
	public Result<PointOperateRecordViewVo> checkDotToPoint(int dot, String userNo) {
		if(dot<=0){
	        return Result.error("兑换点券数量不能小于0");
	    }
		if(StringUtils.isEmpty(userNo)){
	        return Result.error("用户不能为空");
		}
    	DotViewVo dotViewVo = this.viewDotByUserNo(userNo);
        int totalDot = dotViewVo.getTotalDot();
        if(totalDot<dot){
        	return Result.error("总点券数量小于要兑换的点券，不能兑换");
        }
        
		DotConfigQ dotConfigQ = new DotConfigQ();
		Page<DotConfigVo> page = dotConfigService.queryEntities(dotConfigQ);
		if(CollectionUtil.isEmpty(page.getList())){
			return Result.error("系统数据初始化错误，没有点券的配置项");
		}
		
		DotConfigVo dotConfigVo = page.getList().get(0);
		int dotPoint = dotConfigVo.getDotPoint();
		int point = dot/dotPoint;
		PointOperateRecordViewVo pointOperateRecordViewVo = new PointOperateRecordViewVo();
		pointOperateRecordViewVo.setPoint(point);
		return Result.success(pointOperateRecordViewVo);
	}
	
	@Override
	public Result<Integer> dotToPoint(int dot,String userNo) {
		if(dot<=0){
	        return Result.error("兑换点券数量不能小于0");
	    }
		if(StringUtils.isEmpty(userNo)){
	        return Result.error("用户不能为空");
		}
    	DotViewVo dotViewVo = this.viewDotByUserNo(userNo);
        int totalDot = dotViewVo.getTotalDot();
        if(totalDot<dot){
        	return Result.error("总点券数量小于要兑换的点券，不能兑换");
        }
        
		DotConfigQ dotConfigQ = new DotConfigQ();
		Page<DotConfigVo> page = dotConfigService.queryEntities(dotConfigQ);
		if(CollectionUtil.isEmpty(page.getList())){
			return Result.error("系统数据初始化错误，没有点券的配置项");
		}
		
		DotConfigVo dotConfigVo = page.getList().get(0);
		int dotPoint = dotConfigVo.getDotPoint();
		int point = dot/dotPoint;
		this.reduceDot(dot, 5, userNo);
		pcWebPointService.addPoint(point, userNo);
		return Result.success(1);
	}

	@Override
	public Result<Integer> dotGainRule(DotGainRuleEnum dotGainRuleEnum,String userNo) {
		DotConfigQ dotConfigQ = new DotConfigQ();
		Page<DotConfigVo> page = dotConfigService.queryEntities(dotConfigQ);
		if(CollectionUtil.isEmpty(page.getList())){
			return Result.error("系统数据初始化错误，没有点券的配置项");
		}
		DotConfigVo dotConfigVo = page.getList().get(0);
		int dot = 0;
		DotOperateRecordQ dotOperateRecordQ = new DotOperateRecordQ();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(); 
        String dateStr = format.format(date);
        
		if(DotGainRuleEnum.REG.getCode()==dotGainRuleEnum.getCode()){
			//注册
			dot = dotConfigVo.getRegDot();		
			return Result.success(1);
		}
		if(DotGainRuleEnum.LOGIN.getCode()==dotGainRuleEnum.getCode()){
			//登陆
			dotOperateRecordQ.setOperateDate(dateStr);
			dotOperateRecordQ.setOperateType("0");
			dotOperateRecordQ.setDotChangeReson("2");
			dotOperateRecordQ.setPageCurrent(-1);
			dotOperateRecordQ.setPageSize(-1);
			Page<DotOperateRecordVo> dotOperateRecordVoPage = pcWebDotOperateRecordService.queryDotOperateRecords(dotOperateRecordQ);
			List<DotOperateRecordVo> dotOperateRecordVoList = dotOperateRecordVoPage.getList();
			int allDotToday = 0;
			if(CollectionUtil.isNotEmpty(dotOperateRecordVoList)){
				for(DotOperateRecordVo dotOperateRecordVo: dotOperateRecordVoList){
					allDotToday = allDotToday + dotOperateRecordVo.getDot();
				}
			}
			dot = dotConfigVo.getLoginDot();
			int loginMaxDotPerDay = dotConfigVo.getLoginMaxDotPerDay();
			if((allDotToday+dot) > loginMaxDotPerDay){
				return Result.error("已到达今日最大登陆所获卡券，明天再继续了");
			}else{
				this.addDot(dot, 2, userNo);
			}
		}
		if(DotGainRuleEnum.SHARE.getCode()==dotGainRuleEnum.getCode()){
			//分享
			dotOperateRecordQ.setOperateDate(dateStr);
			dotOperateRecordQ.setOperateType("0");
			dotOperateRecordQ.setDotChangeReson("3");
			dotOperateRecordQ.setPageCurrent(-1);
			dotOperateRecordQ.setPageSize(-1);
			Page<DotOperateRecordVo> dotOperateRecordVoPage = pcWebDotOperateRecordService.queryDotOperateRecords(dotOperateRecordQ);
			List<DotOperateRecordVo> dotOperateRecordVoList = dotOperateRecordVoPage.getList();
			int allDotToday = 0;
			if(CollectionUtil.isNotEmpty(dotOperateRecordVoList)){
				for(DotOperateRecordVo dotOperateRecordVo: dotOperateRecordVoList){
					allDotToday = allDotToday + dotOperateRecordVo.getDot();
				}
			}
			dot = dotConfigVo.getShareDot();
			int shareMaxDotPerDay = dotConfigVo.getShareMaxDotPerDay();
			if((allDotToday+dot) > shareMaxDotPerDay){
				return Result.error("已到达今日最大分享所获卡券，明天再继续了");
			}else{
				this.addDot(dot, 3, userNo);
			}
		}
		if(DotGainRuleEnum.SHARE_REG.getCode()==dotGainRuleEnum.getCode()){
			//分享后注册
			dotOperateRecordQ.setOperateDate(dateStr);
			dotOperateRecordQ.setOperateType("0");
			dotOperateRecordQ.setDotChangeReson("4");
			dotOperateRecordQ.setPageCurrent(-1);
			dotOperateRecordQ.setPageSize(-1);
			Page<DotOperateRecordVo> dotOperateRecordVoPage = pcWebDotOperateRecordService.queryDotOperateRecords(dotOperateRecordQ);
			List<DotOperateRecordVo> dotOperateRecordVoList = dotOperateRecordVoPage.getList();
			int allDotToday = 0;
			if(CollectionUtil.isNotEmpty(dotOperateRecordVoList)){
				for(DotOperateRecordVo dotOperateRecordVo: dotOperateRecordVoList){
					allDotToday = allDotToday + dotOperateRecordVo.getDot();
				}
			}
			dot = dotConfigVo.getShareRegDot();
			int shareRegMaxDotPerDay = dotConfigVo.getShareRegMaxDotPerDay();
			if((allDotToday+dot) > shareRegMaxDotPerDay){
				return Result.error("已到达今日最大分享后被注册所获卡券，明天再继续了");
			}else{
				this.addDot(dot, 4, userNo);
			}
		}
		return Result.success(1);
	}

	@Override
	public Result<Integer> addDot(int dot,int dotChangeReson,String userNo) {
    	DotViewVo dotViewVo = this.viewDotByUserNo(userNo);
        int totalDot = dotViewVo.getTotalDot();
        
		Dot p = this.getById(dotViewVo);
        int newTotalDot = totalDot+dot;
        p.setTotalDot(newTotalDot);
        this.updateById(p);

        DotOperateRecordSaveVo dotOperateRecordSaveVo = new DotOperateRecordSaveVo();
        dotOperateRecordSaveVo.setOperateType(0);//0增加
        dotOperateRecordSaveVo.setDotChangeReson(dotChangeReson);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(); 
        String dateStr = format.format(date);
        dotOperateRecordSaveVo.setOperateDate(dateStr);
        dotOperateRecordSaveVo.setUserNoOfDot(p.getBindUserNo());
        dotOperateRecordSaveVo.setDot(dot);
        dotOperateRecordSaveVo.setTotalDot(newTotalDot);
        dotOperateRecordSaveVo.setValidValue(1);
        pcWebDotOperateRecordService.saveDotOperateRecord(dotOperateRecordSaveVo);
        
		return Result.success(1);
	}

	@Override
	public Result<Integer> reduceDot(int dot,int dotChangeReson,String userNo) {
    	DotViewVo dotViewVo = this.viewDotByUserNo(userNo);
        int totalDot = dotViewVo.getTotalDot();
		if(totalDot<dot){
			return Result.error("点券不够，扣点券失败");
		}
        int newTotalDot = totalDot-dot;
        Dot p = this.getById(dotViewVo.getId());
        p.setTotalDot(newTotalDot);
        this.updateById(p);

        DotOperateRecordSaveVo dotOperateRecordSaveVo = new DotOperateRecordSaveVo();
        dotOperateRecordSaveVo.setOperateType(1);//1减少
        dotOperateRecordSaveVo.setDotChangeReson(dotChangeReson);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(); 
        dotOperateRecordSaveVo.setOperateDate(format.format(date));
        dotOperateRecordSaveVo.setUserNoOfDot(p.getBindUserNo());
        dotOperateRecordSaveVo.setDot(dot);
        dotOperateRecordSaveVo.setTotalDot(newTotalDot);
        dotOperateRecordSaveVo.setValidValue(1);
        pcWebDotOperateRecordService.saveDotOperateRecord(dotOperateRecordSaveVo);
        
		return Result.success(1);
	}

	


	
	
}
