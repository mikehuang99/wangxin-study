package com.wangxin.langshu.business.edu.dao.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.dao.system.WebsiteDao;
import com.wangxin.langshu.business.edu.dao.system.impl.mapper.WebsiteMapper;
import com.wangxin.langshu.business.edu.entity.system.old.Website;
import com.wangxin.langshu.business.edu.entity.system.old.WebsiteExample;
import com.wangxin.langshu.business.edu.entity.system.old.WebsiteExample.Criteria;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class WebsiteDaoImpl implements WebsiteDao {
	@Autowired
	private WebsiteMapper websiteMapper;

	@Override
	public int save(Website record) {
		record.setId(IdWorker.getId());
		return this.websiteMapper.insertSelective(record);
	}

	@Override
	public int deleteById(String id) {
		return this.websiteMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(Website record) {
		return this.websiteMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Website getById(String id) {
		return this.websiteMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<Website> listForPage(int pageCurrent, int pageSize, WebsiteExample example) {
		int count = this.websiteMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<Website>(count, totalPage, pageCurrent, pageSize, this.websiteMapper.selectByExample(example));
	}

	@Override
	public Website getWebsite() {
		WebsiteExample example = new WebsiteExample();
		List<Website> resultList = this.websiteMapper.selectByExample(example);
		if (resultList == null || resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}

	@Override
	public Website getByValidValue(Integer validValue) {
		WebsiteExample example = new WebsiteExample();
		Criteria C = example.createCriteria();
		C.andValidValueEqualTo(validValue);
		List<Website> list = this.websiteMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}