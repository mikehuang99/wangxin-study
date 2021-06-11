package com.wangxin.langshu.business.edu.dao.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.dao.system.SystemMenuRoleDao;
import com.wangxin.langshu.business.edu.dao.system.impl.mapper.SystemMenuRoleMapper;
import com.wangxin.langshu.business.edu.entity.system.old.SystemMenuRole;
import com.wangxin.langshu.business.edu.entity.system.old.SystemMenuRoleExample;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class SystemMenuRoleDaoImpl implements SystemMenuRoleDao {
	@Autowired
	private SystemMenuRoleMapper systemMenuRoleMapper;

	public int save(SystemMenuRole record) {
		record.setId(IdWorker.getId());
		return this.systemMenuRoleMapper.insertSelective(record);
	}

	public int deleteById(String id) {
		return this.systemMenuRoleMapper.deleteByPrimaryKey(id);
	}

	public int updateById(SystemMenuRole record) {
		return this.systemMenuRoleMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByExampleSelective(SystemMenuRole record, SystemMenuRoleExample example) {
		return this.systemMenuRoleMapper.updateByExampleSelective(record, example);
	}

	public SystemMenuRole getById(String id) {
		return this.systemMenuRoleMapper.selectByPrimaryKey(id);
	}

	public Page<SystemMenuRole> listForPage(int pageCurrent, int pageSize, SystemMenuRoleExample example) {
		int count = this.systemMenuRoleMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<SystemMenuRole>(count, totalPage, pageCurrent, pageSize, this.systemMenuRoleMapper.selectByExample(example));
	}

	@Override
	public List<SystemMenuRole> listByRoleId(String roleId) {
		SystemMenuRoleExample example = new SystemMenuRoleExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		return this.systemMenuRoleMapper.selectByExample(example);
	}

	@Override
	public int deleteByRoleId(String roleId) {
		SystemMenuRoleExample example = new SystemMenuRoleExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		return this.systemMenuRoleMapper.deleteByExample(example);
	}
}