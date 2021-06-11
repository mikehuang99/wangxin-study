package com.wangxin.langshu.business.edu.iservice.system.pccommon;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.system.old.CacheLog;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.cache.CacheLogQ;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.cache.CacheLogVo;
import com.wangxin.langshu.business.util.base.Page;


public interface ICacheLogService  extends IService<CacheLog> {
	

	public Page<CacheLogVo> queryEntities(CacheLogQ q) ;

	public boolean saveEntity(CacheLogVo vo) ;

	public boolean updateEntity(CacheLogVo vo) ;
	
	public boolean checkIsRunningCacheTask();
	
	public boolean executeCacheTask(String id) throws Exception;

	

	
	
}
