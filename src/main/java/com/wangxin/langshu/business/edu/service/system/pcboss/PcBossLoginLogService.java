package com.wangxin.langshu.business.edu.service.system.pcboss;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.system.LoginLogDao;
import com.wangxin.langshu.business.edu.dao.system.impl.mapper.LoginLogMapper;
import com.wangxin.langshu.business.edu.entity.system.LoginLog;
import com.wangxin.langshu.business.edu.iservice.system.pcboss.IPcBossLoginLogService;
import com.wangxin.langshu.business.edu.vo.system.loginLog.LoginLogDeleteVo;
import com.wangxin.langshu.business.edu.vo.system.loginLog.LoginLogQ;
import com.wangxin.langshu.business.edu.vo.system.loginLog.LoginLogSaveVo;
import com.wangxin.langshu.business.edu.vo.system.loginLog.LoginLogUpdateVo;
import com.wangxin.langshu.business.edu.vo.system.loginLog.LoginLogViewVo;
import com.wangxin.langshu.business.edu.vo.system.loginLog.LoginLogVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;


@Service
public class PcBossLoginLogService  extends ServiceImpl<LoginLogMapper, LoginLog> implements IPcBossLoginLogService {

	@Autowired
	public LoginLogDao LoginLogDao;
	
	@Autowired
    private LoginLogMapper LoginLogMapper;

	@Override
	public Result<Page<LoginLogVo>> queryEntities(LoginLogQ q) {
		return Result.success(LoginLogDao.queryEntities(q));
	}

	@Override
	public Result<Integer> saveEntity(LoginLogSaveVo vo) {
		vo.setValidValue(1);
		String id = UUID.randomUUID().toString();
		vo.setId(id);
		LoginLog entity = new LoginLog();
		BeanUtils.copyProperties(vo, entity);
		this.save(entity);
		return Result.success(1);
	}

	@Override
	public Result<Integer> updateEntity(LoginLogUpdateVo vo) {
		LoginLog entity = new LoginLog();
		BeanUtils.copyProperties(vo, entity);
		this.updateById(entity);
		return Result.success(1);
	}

	@Override
	public Result<Integer> deleteEntity(LoginLogDeleteVo vo) {
		if(StringUtils.isNotEmpty(vo.getId())){
			this.removeById(vo.getId());
			return Result.success(1);
		}
		if(vo.getIds().length<1){
    		throw new BaseException("删除ID不能为空");
		}
		for(int i=0;i<vo.getIds().length;i++){
			String id = vo.getIds()[i];
			this.removeById(id);
		}
		return Result.success(1);
	}

	
	
	@Override
	public Result<LoginLogViewVo> viewEntity(LoginLogViewVo vo) {
		if(StringUtils.isEmpty(vo.getId())){
			Result.error("缺少ID");
		}
		LoginLog entity = this.getById(vo.getId());
		BeanUtils.copyProperties(entity, vo);

		return Result.success(vo);
	}




	


	
	
}
