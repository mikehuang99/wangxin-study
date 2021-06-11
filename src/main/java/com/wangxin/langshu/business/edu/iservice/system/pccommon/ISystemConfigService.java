package com.wangxin.langshu.business.edu.iservice.system.pccommon;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.system.old.SystemConfig;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigDeleteVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigQ;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigSaveVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigUpdateVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigViewVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigVo;
import com.wangxin.langshu.business.util.base.Page;


public interface ISystemConfigService  extends IService<SystemConfig> {
	

	public Page<SystemConfigVo> queryEntities(SystemConfigQ q) ;

	public boolean saveEntity(SystemConfigSaveVo vo) ;

	public boolean updateEntity(SystemConfigUpdateVo vo) ;

	
	public boolean deleteEntity(SystemConfigDeleteVo vo) ;
	
	public boolean deleteEntityById(String id) ;


	
	public SystemConfigViewVo viewEntity(SystemConfigViewVo vo) ;	
	
	public SystemConfigViewVo view();
	
	
}
