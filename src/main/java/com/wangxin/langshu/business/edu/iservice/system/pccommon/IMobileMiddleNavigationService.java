package com.wangxin.langshu.business.edu.iservice.system.pccommon;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.system.old.MobileMiddleNavigation;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation.MobileMiddleNavigationDeleteVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation.MobileMiddleNavigationQ;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation.MobileMiddleNavigationSaveVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation.MobileMiddleNavigationUpdateVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation.MobileMiddleNavigationViewVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation.MobileMiddleNavigationVo;
import com.wangxin.langshu.business.util.base.Page;


public interface IMobileMiddleNavigationService  extends IService<MobileMiddleNavigation> {
	
	

	public Page<MobileMiddleNavigationVo> queryMobileMiddleNavigations(MobileMiddleNavigationQ q) ;

	public boolean saveMobileMiddleNavigation(MobileMiddleNavigationSaveVo vo) ;

	public boolean updateMobileMiddleNavigation(MobileMiddleNavigationUpdateVo vo) ;

	
	public boolean deleteMobileMiddleNavigation(MobileMiddleNavigationDeleteVo vo) ;

	
	public MobileMiddleNavigationViewVo viewMobileMiddleNavigation(MobileMiddleNavigationViewVo vo) ;
	
	
	
	
}
