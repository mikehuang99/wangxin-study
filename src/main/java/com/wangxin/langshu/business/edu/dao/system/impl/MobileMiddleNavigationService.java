package com.wangxin.langshu.business.edu.dao.system.impl;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.system.MobileMiddleNavigationDao;
import com.wangxin.langshu.business.edu.dao.system.impl.mapper.MobileMiddleNavigationMapper;
import com.wangxin.langshu.business.edu.entity.system.old.MobileMiddleNavigation;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.IMobileMiddleNavigationService;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation.MobileMiddleNavigationDeleteVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation.MobileMiddleNavigationQ;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation.MobileMiddleNavigationSaveVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation.MobileMiddleNavigationUpdateVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation.MobileMiddleNavigationViewVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation.MobileMiddleNavigationVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;


@Service
public class MobileMiddleNavigationService  extends ServiceImpl<MobileMiddleNavigationMapper, MobileMiddleNavigation> implements IMobileMiddleNavigationService {

	@Autowired
	public MobileMiddleNavigationDao MobileMiddleNavigationDao;
	
	@Autowired
    private MobileMiddleNavigationMapper MobileMiddleNavigationMapper;
	
	


	@Override
	public Page<MobileMiddleNavigationVo> queryMobileMiddleNavigations(MobileMiddleNavigationQ q) {
		return MobileMiddleNavigationDao.queryMobileMiddleNavigations(q);
	}

	@Override
	public boolean saveMobileMiddleNavigation(MobileMiddleNavigationSaveVo vo) {
		String id = UUID.randomUUID().toString().replace("-", "");
		MobileMiddleNavigation mobileMiddleNavigation = new MobileMiddleNavigation();
		BeanUtils.copyProperties(vo, mobileMiddleNavigation);
		mobileMiddleNavigation.setId(id);
		mobileMiddleNavigation.setValidValue(1);
		this.save(mobileMiddleNavigation);
		return true;
	}

	@Override
	public boolean updateMobileMiddleNavigation(MobileMiddleNavigationUpdateVo vo) {
		MobileMiddleNavigation MobileMiddleNavigation = new MobileMiddleNavigation();
		BeanUtils.copyProperties(vo, MobileMiddleNavigation);
		this.updateById(MobileMiddleNavigation);
		return true;
	}

	@Override
	public boolean deleteMobileMiddleNavigation(MobileMiddleNavigationDeleteVo vo) {
		/*
		if(vo.getIds().length<1){
    		throw new BaseException("删除ID不能为空");
		}
		for(int i=0;i<vo.getIds().length;i++){
			String id = vo.getIds()[i];
			this.removeById(id);
		}*/
		if(StringUtils.isEmpty(vo.getId())){
			throw new BaseException("ID不能为空");
		}
		this.removeById(vo.getId());
		return true;
	}

	@Override
	public MobileMiddleNavigationViewVo viewMobileMiddleNavigation(MobileMiddleNavigationViewVo viewVo) {
		MobileMiddleNavigation examManagement = this.getById(viewVo.getId());
		BeanUtils.copyProperties(examManagement, viewVo);
		return viewVo;
	}

	


	
	
}
