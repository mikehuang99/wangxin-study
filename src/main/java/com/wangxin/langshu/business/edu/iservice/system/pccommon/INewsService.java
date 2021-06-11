package com.wangxin.langshu.business.edu.iservice.system.pccommon;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.system.old.News;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsDeleteVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsQ;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsSaveVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsUpdateVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsViewVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;


public interface INewsService  extends IService<News> {
	

	public Page<NewsVo> queryEntities(NewsQ q) ;

	public boolean saveEntity(NewsSaveVo vo) ;

	public boolean updateEntity(NewsUpdateVo vo) ;

	
	public boolean deleteEntity(NewsDeleteVo vo) ;
	
	public boolean deleteEntityById(String id) ;


	
	public Result<NewsViewVo> viewEntity(NewsViewVo vo) ;	
	
	
}
