package com.wangxin.langshu.business.edu.dao.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.dao.system.SystemRoleUserDao;
import com.wangxin.langshu.business.edu.dao.system.impl.mapper.SystemRoleUserMapper;
import com.wangxin.langshu.business.edu.entity.system.old.SystemRoleUser;
import com.wangxin.langshu.business.edu.entity.system.old.SystemRoleUserExample;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class SystemRoleUserDaoImpl implements SystemRoleUserDao {
	@Autowired
	private SystemRoleUserMapper systemRoleUserMapper;

	public int save(SystemRoleUser record) {
		record.setId(IdWorker.getId());
		return this.systemRoleUserMapper.insertSelective(record);
	}

	public int deleteById(String id) {
		return this.systemRoleUserMapper.deleteByPrimaryKey(id);
	}

	public int updateById(SystemRoleUser record) {
		return this.systemRoleUserMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByExampleSelective(SystemRoleUser record, SystemRoleUserExample example) {
		return this.systemRoleUserMapper.updateByExampleSelective(record, example);
	}

	public SystemRoleUser getById(String id) {
		return this.systemRoleUserMapper.selectByPrimaryKey(id);
	}

	public Page<SystemRoleUser> listForPage(int pageCurrent, int pageSize, SystemRoleUserExample example) {
		int count = this.systemRoleUserMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<SystemRoleUser>(count, totalPage, pageCurrent, pageSize, this.systemRoleUserMapper.selectByExample(example));
	}

	@Override
	public int deleteByUserId(String userId) {
		SystemRoleUserExample example = new SystemRoleUserExample();
		example.createCriteria().andUserIdEqualTo(userId);
		return this.systemRoleUserMapper.deleteByExample(example);
	}

	@Override
	public List<SystemRoleUser> listByUserId(String userId) {
		SystemRoleUserExample example = new SystemRoleUserExample();
		example.createCriteria().andUserIdEqualTo(userId);
		return this.systemRoleUserMapper.selectByExample(example);
	}
}