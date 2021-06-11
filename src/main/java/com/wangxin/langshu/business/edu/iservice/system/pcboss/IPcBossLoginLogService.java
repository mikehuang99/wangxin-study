package com.wangxin.langshu.business.edu.iservice.system.pcboss;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.system.LoginLog;
import com.wangxin.langshu.business.edu.vo.system.loginLog.LoginLogDeleteVo;
import com.wangxin.langshu.business.edu.vo.system.loginLog.LoginLogQ;
import com.wangxin.langshu.business.edu.vo.system.loginLog.LoginLogSaveVo;
import com.wangxin.langshu.business.edu.vo.system.loginLog.LoginLogUpdateVo;
import com.wangxin.langshu.business.edu.vo.system.loginLog.LoginLogViewVo;
import com.wangxin.langshu.business.edu.vo.system.loginLog.LoginLogVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcBossLoginLogService  extends IService<LoginLog> {
	

	public Result<Page<LoginLogVo>> queryEntities(LoginLogQ q) ;

	public Result<Integer> saveEntity(LoginLogSaveVo vo) ;

	public Result<Integer> updateEntity(LoginLogUpdateVo vo) ;

	public Result<Integer> deleteEntity(LoginLogDeleteVo vo) ;
	
	public Result<LoginLogViewVo> viewEntity(LoginLogViewVo vo) ;	
	
}
