package com.wangxin.langshu.business.edu.dao.course.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.dao.course.AdvertDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.AdvertMapper;
import com.wangxin.langshu.business.edu.entity.course.old.Advert;
import com.wangxin.langshu.business.edu.entity.course.old.AdvertExample;
import com.wangxin.langshu.business.edu.entity.course.old.AdvertExample.Criteria;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class AdvertDaoImpl implements AdvertDao {
	@Autowired
	private AdvertMapper advertMapper;

	@Override
	public int save(Advert record) {
		record.setId(IdWorker.getId());
		return this.advertMapper.insertSelective(record);
	}

	@Override
	public int deleteById(String id) {
		return this.advertMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(Advert record) {
		return this.advertMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Advert getById(String id) {
		return this.advertMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<Advert> listForPage(int pageCurrent, int pageSize, AdvertExample example) {
		int count = this.advertMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<Advert>(count, totalPage, pageCurrent, pageSize, this.advertMapper.selectByExample(example));
	}

	@Override
	public List<Advert> listByPlatShowAndValidValueAndBeginTimeAndEndTime(Integer platShow, Integer validValue, Date beginTime, Date endTime) {
		AdvertExample example = new AdvertExample();
		Criteria c = example.createCriteria();
		c.andPlatShowEqualTo(platShow);
		c.andValidValueEqualTo(validValue);
	
		//Date date = new Date();
		//c.andBeginTimeGreaterThan(date);
		//c.andEndTimeLessThan(date);
		
		return this.advertMapper.selectByExample(example);
	}


}