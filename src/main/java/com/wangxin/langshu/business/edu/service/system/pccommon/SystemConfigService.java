package com.wangxin.langshu.business.edu.service.system.pccommon;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.system.SystemConfigDao;
import com.wangxin.langshu.business.edu.dao.system.impl.mapper.SystemConfigMapper;
import com.wangxin.langshu.business.edu.entity.system.old.SystemConfig;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.ISystemConfigService;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigDeleteVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigQ;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigSaveVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigUpdateVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigViewVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;


@Service
public class SystemConfigService  extends ServiceImpl<SystemConfigMapper, SystemConfig> implements ISystemConfigService {

	@Autowired
	public SystemConfigDao SystemConfigDao;
	
	@Autowired
    private SystemConfigMapper SystemConfigMapper;

	@Override
	public Page<SystemConfigVo> queryEntities(SystemConfigQ q) {
		return SystemConfigDao.queryEntities(q);
	}

	@Override
	public boolean saveEntity(SystemConfigSaveVo vo) {
		SystemConfig entity = new SystemConfig();
		BeanUtils.copyProperties(vo, entity);
		this.save(entity);
		return true;
	}

	@Override
	public boolean updateEntity(SystemConfigUpdateVo vo) {
		SystemConfig entity = new SystemConfig();
		BeanUtils.copyProperties(vo, entity);
		entity.setModifiedTime(new Date());
		this.updateById(entity);
		return true;
	}

	@Override
	public boolean deleteEntity(SystemConfigDeleteVo vo) {
		if(StringUtils.isNotEmpty(vo.getId())){
			this.removeById(vo.getId());
			return true;
		}
		if(vo.getIds().length<1){
    		throw new BaseException("删除ID不能为空");
		}
		for(int i=0;i<vo.getIds().length;i++){
			String id = vo.getIds()[i];
			this.removeById(id);
		}
		return true;
	}

	@Override
	public boolean deleteEntityById(String id) {
		UpdateWrapper<SystemConfig> wrapper = new UpdateWrapper<>();
		wrapper.eq("id", id);
        SystemConfigMapper.delete(wrapper);
		//this.remove(queryWrapper);
		return false;
	}
	
	@Override
	public SystemConfigViewVo viewEntity(SystemConfigViewVo vo) {
		SystemConfig entity = this.getById(vo.getId());
		BeanUtils.copyProperties(entity, vo);

		return vo;
	}

	@Override
	public SystemConfigViewVo view() {
		SystemConfigViewVo systemConfigViewVo = new SystemConfigViewVo();
		Page<SystemConfigVo> systemConfigPage = SystemConfigDao.queryEntities(null);
		List<SystemConfigVo> list  = systemConfigPage.getList();
		if(list !=null &&list.size()!=0){
			SystemConfigVo systemConfigVo =  list.get(0);
			BeanUtils.copyProperties(systemConfigVo, systemConfigViewVo);
			return systemConfigViewVo;
		}else{
			SystemConfig entity = new SystemConfig();
			String id = UUID.randomUUID().toString();
			entity.setId(id);
			entity.setValidValue(1);
			entity.setSort(1);
			this.save(entity);
			BeanUtils.copyProperties(entity, systemConfigViewVo);
			return systemConfigViewVo;
		}
	}




	


	
	
}
