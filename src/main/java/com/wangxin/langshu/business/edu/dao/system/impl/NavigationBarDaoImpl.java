package com.wangxin.langshu.business.edu.dao.system.impl;

import com.wangxin.langshu.business.edu.dao.system.NavigationBarDao;
import com.wangxin.langshu.business.edu.dao.system.impl.mapper.NavigationBarMapper;
import com.wangxin.langshu.business.edu.entity.system.old.NavigationBar;
import com.wangxin.langshu.business.edu.entity.system.old.NavigationBarExample;
import com.wangxin.langshu.business.edu.entity.system.old.NavigationBarExample.Criteria;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NavigationBarDaoImpl implements NavigationBarDao {
	@Autowired
	private NavigationBarMapper navigationBarMapper;

	public int save(NavigationBar record) {
		record.setId(IdWorker.getId());
		return this.navigationBarMapper.insertSelective(record);
	}

	public int deleteById(String id) {
		return this.navigationBarMapper.deleteByPrimaryKey(id);
	}

	public int updateById(NavigationBar record) {
		return this.navigationBarMapper.updateByPrimaryKeySelective(record);
	}

	public NavigationBar getById(String id) {
		return this.navigationBarMapper.selectByPrimaryKey(id);
	}

	public Page<NavigationBar> listForPage(int pageCurrent, int pageSize, NavigationBarExample example) {
		int count = this.navigationBarMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<NavigationBar>(count, totalPage, pageCurrent, pageSize, this.navigationBarMapper.selectByExample(example));
	}

	@Override
	public NavigationBar getByNavigationUrl(String navigationUrl) {
		NavigationBarExample example = new NavigationBarExample();
		Criteria criteria = example.createCriteria();
		criteria.andNavigationUrlEqualTo(navigationUrl);
		List<NavigationBar> list = this.navigationBarMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<NavigationBar> getByValidValue(Integer validValue) {
		NavigationBarExample example = new NavigationBarExample();
		Criteria criteria = example.createCriteria();
		criteria.andValidValueEqualTo(validValue);
		example.setOrderByClause("sort desc, id desc ");
		return this.navigationBarMapper.selectByExample(example);
	}
}