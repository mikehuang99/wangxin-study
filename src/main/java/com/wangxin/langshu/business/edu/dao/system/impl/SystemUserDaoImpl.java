package com.wangxin.langshu.business.edu.dao.system.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.dao.system.SystemUserDao;
import com.wangxin.langshu.business.edu.dao.system.impl.mapper.SystemUserMapper;
import com.wangxin.langshu.business.edu.entity.system.old.SystemUser;
import com.wangxin.langshu.business.edu.entity.system.old.SystemUserExample;
import com.wangxin.langshu.business.edu.entity.system.old.SystemUserExample.Criteria;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class SystemUserDaoImpl implements SystemUserDao {
	@Autowired
	private SystemUserMapper systemUserMapper;

	public int save(SystemUser record) {
		record.setId(IdWorker.getId());
		return this.systemUserMapper.insertSelective(record);
	}

	public int deleteById(String id) {
		return this.systemUserMapper.deleteByPrimaryKey(id);
	}

	public int updateById(SystemUser record) {
		return this.systemUserMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByExampleSelective(SystemUser record, SystemUserExample example) {
		return this.systemUserMapper.updateByExampleSelective(record, example);
	}

	public SystemUser getById(String id) {
		return this.systemUserMapper.selectByPrimaryKey(id);
	}

	public Page<SystemUser> listForPage(int pageCurrent, int pageSize, SystemUserExample example) {
		int count = this.systemUserMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<SystemUser>(count, totalPage, pageCurrent, pageSize, this.systemUserMapper.selectByExample(example));
	}

	@Override
	public SystemUser getByUserNo(String userNo) {
		SystemUserExample example = new SystemUserExample();
		Criteria c = example.createCriteria();
		c.andUserNoEqualTo(userNo);
		List<SystemUser> list = systemUserMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}
}