package com.wangxin.langshu.business.edu.service.system.pccommon;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.system.NewsDao;
import com.wangxin.langshu.business.edu.dao.system.impl.mapper.NewsMapper;
import com.wangxin.langshu.business.edu.entity.system.old.News;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.INewsService;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsDeleteVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsQ;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsSaveVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsUpdateVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsViewVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;


@Service
public class NewsService  extends ServiceImpl<NewsMapper, News> implements INewsService {

	@Autowired
	public NewsDao NewsDao;
	
	@Autowired
    private NewsMapper NewsMapper;

	@Override
	public Page<NewsVo> queryEntities(NewsQ q) {
		return NewsDao.queryEntities(q);
	}

	@Override
	public boolean saveEntity(NewsSaveVo vo) {
		vo.setValidValue(1);
		News entity = new News();
		BeanUtils.copyProperties(vo, entity);
		this.save(entity);
		return true;
	}

	@Override
	public boolean updateEntity(NewsUpdateVo vo) {
		News entity = new News();
		BeanUtils.copyProperties(vo, entity);
		this.updateById(entity);
		return true;
	}

	@Override
	public boolean deleteEntity(NewsDeleteVo vo) {
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
		UpdateWrapper<News> wrapper = new UpdateWrapper<>();
		wrapper.eq("id", id);
        NewsMapper.delete(wrapper);
		//this.remove(queryWrapper);
		return false;
	}
	
	@Override
	public Result<NewsViewVo> viewEntity(NewsViewVo vo) {
		if(StringUtils.isEmpty(vo.getId())){
			Result.error("缺少ID");
		}
		News entity = this.getById(vo.getId());
		BeanUtils.copyProperties(entity, vo);

		return Result.success(vo);
	}




	


	
	
}
