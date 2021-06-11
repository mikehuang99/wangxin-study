package com.wangxin.langshu.business.edu.service.market.pccommon;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.market.DotConfigDao;
import com.wangxin.langshu.business.edu.dao.market.impl.mapper.DotConfigMapper;
import com.wangxin.langshu.business.edu.entity.market.DotConfig;
import com.wangxin.langshu.business.edu.iservice.market.pccommon.IDotConfigService;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigQ;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigSaveVo;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigViewVo;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.CacheRedis;
import com.wangxin.langshu.business.util.RedisKey;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.enums.DotGainRuleEnum;


@Service
public class DotConfigService  extends ServiceImpl<DotConfigMapper, DotConfig> implements IDotConfigService {

	@Autowired
	public DotConfigDao DotConfigDao;
	
	@Autowired
    private DotConfigMapper DotConfigMapper;
	
	@Autowired
	private CacheRedis cacheRedis;

	@Override
	public Page<DotConfigVo> queryEntities(DotConfigQ q) {
		return DotConfigDao.queryEntities(q);
	}

	@Override
	public boolean saveEntity(DotConfigSaveVo vo) {
		DotConfig entity = new DotConfig();
		BeanUtils.copyProperties(vo, entity);
		this.save(entity);
		return true;
	}

	@Override
	public boolean updateEntity(DotConfigUpdateVo vo) {
		DotConfig entity = new DotConfig();
		BeanUtils.copyProperties(vo, entity);
		entity.setModifiedTime(new Date());
		this.updateById(entity);
		cacheRedis.set(RedisKey.DOT_CONFIG, entity);
		return true;
	}

	@Override
	public boolean deleteEntity(DotConfigDeleteVo vo) {
		if(vo.getIds()==null||vo.getIds().length<1){
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
		UpdateWrapper<DotConfig> wrapper = new UpdateWrapper<>();
		wrapper.eq("id", id);
        DotConfigMapper.delete(wrapper);
		//this.remove(queryWrapper);
		return false;
	}
	
	@Override
	public DotConfigViewVo viewEntity(DotConfigViewVo vo) {
		DotConfig entity = this.getById(vo.getId());
		BeanUtils.copyProperties(entity, vo);

		return vo;
	}

	@Override
	public DotConfigViewVo view() {
		DotConfigViewVo systemConfigViewVo = new DotConfigViewVo();
		Page<DotConfigVo> systemConfigPage = DotConfigDao.queryEntities(null);
		List<DotConfigVo> list  = systemConfigPage.getList();
		if(list !=null &&list.size()!=0){
			DotConfigVo systemConfigVo =  list.get(0);
			BeanUtils.copyProperties(systemConfigVo, systemConfigViewVo);
			return systemConfigViewVo;
		}else{
			DotConfig entity = new DotConfig();
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
