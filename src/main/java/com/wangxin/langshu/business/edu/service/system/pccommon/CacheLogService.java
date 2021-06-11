package com.wangxin.langshu.business.edu.service.system.pccommon;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.system.CacheLogDao;
import com.wangxin.langshu.business.edu.dao.system.impl.mapper.CacheLogMapper;
import com.wangxin.langshu.business.edu.entity.system.old.CacheLog;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamQuestionCourseService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamQuestionService;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.ICacheLogService;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.cache.CacheLogQ;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.cache.CacheLogVo;
import com.wangxin.langshu.business.util.base.Page;


@Service
public class CacheLogService  extends ServiceImpl<CacheLogMapper, CacheLog> implements ICacheLogService {

	@Autowired
	public CacheLogDao cacheLogDao;
	
	@Autowired
    private CacheLogMapper cacheLogMapper;
	
	@Autowired
	private IExamQuestionService examQuestionService;
	
	@Autowired
	private IExamQuestionCourseService examQuestionCourseService;
	

	@Override
	public Page<CacheLogVo> queryEntities(CacheLogQ q) {
		return this.cacheLogDao.queryEntities(q);
	}

	@Override
	public boolean saveEntity(CacheLogVo vo) {
		CacheLog entity = new CacheLog();
		BeanUtils.copyProperties(vo, entity);
		this.save(entity);
		return true;
	}

	@Override
	public boolean updateEntity(CacheLogVo vo) {
		CacheLog entity = new CacheLog();
		BeanUtils.copyProperties(vo, entity);
		this.updateById(entity);
		return true;
	}

	@Override
	public boolean checkIsRunningCacheTask() {
		return this.cacheLogDao.checkIsRunningCacheTask();
	}

	@Override
	public boolean executeCacheTask(String id) throws Exception{
		try{
			System.out.println("开始执行缓存任务");
			examQuestionService.cacheAllExamQuestion();
			examQuestionCourseService.cacheAllExamQuestionCourses();
			System.out.println("结束执行缓存任务");
			
			String cacheStatus = "2";
			CacheLog cacheLog = this.getById(id);
			cacheLog.setCacheStatus(cacheStatus);
			this.updateById(cacheLog);
			
			return true;
		}catch (Exception e) {
			String cacheStatus = "3";
			CacheLog cacheLog = this.getById(id);
			cacheLog.setCacheStatus(cacheStatus);
			this.updateById(cacheLog);
			throw new Exception(e);
		}
		
	}




	


	
	
}
