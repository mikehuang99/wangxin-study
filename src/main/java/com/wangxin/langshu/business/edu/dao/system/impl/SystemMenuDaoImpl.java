package com.wangxin.langshu.business.edu.dao.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.dao.system.SystemMenuDao;
import com.wangxin.langshu.business.edu.dao.system.impl.mapper.SystemMenuMapper;
import com.wangxin.langshu.business.edu.entity.system.old.SystemMenu;
import com.wangxin.langshu.business.edu.entity.system.old.SystemMenuExample;
import com.wangxin.langshu.business.edu.entity.system.old.SystemMenuExample.Criteria;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class SystemMenuDaoImpl implements SystemMenuDao {
	@Autowired
	private SystemMenuMapper systemMenuMapper;

	public int save(SystemMenu record) {
		record.setId(IdWorker.getId());
		return this.systemMenuMapper.insertSelective(record);
	}

	public int deleteById(String id) {
		return this.systemMenuMapper.deleteByPrimaryKey(id);
	}

	public int updateById(SystemMenu record) {
		return this.systemMenuMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByExampleSelective(SystemMenu record, SystemMenuExample example) {
		return this.systemMenuMapper.updateByExampleSelective(record, example);
	}

	public SystemMenu getById(String id) {
		return this.systemMenuMapper.selectByPrimaryKey(id);
	}

	public Page<SystemMenu> listForPage(int pageCurrent, int pageSize, SystemMenuExample example) {
		int count = this.systemMenuMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<SystemMenu>(count, totalPage, pageCurrent, pageSize, this.systemMenuMapper.selectByExample(example));
	}

	@Override
	public List<SystemMenu> listByParentId(String parentId) {
		SystemMenuExample example = new SystemMenuExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		example.setOrderByClause(" sort desc, id desc");
		return this.systemMenuMapper.selectByExample(example);
	}

	@Override
	public List<SystemMenu> listByParentIdAndNotMenuType(String parentId, Integer menuType) {
		SystemMenuExample example = new SystemMenuExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		if (menuType != null) {
			criteria.andMenuTypeNotEqualTo(menuType);
		}
		example.setOrderByClause(" sort desc, id desc");
		return this.systemMenuMapper.selectByExample(example);
	}

	@Override
	public List<SystemMenu> listAll() {
		SystemMenuExample example = new SystemMenuExample();
		example.setOrderByClause(" sort desc, id desc");
		return this.systemMenuMapper.selectByExample(example);
	}

	@Override
	public List<SystemMenu> listByMenuName(String menuName) {
		SystemMenuExample example = new SystemMenuExample();
		example.createCriteria().andMenuNameLike(menuName);
		example.setOrderByClause(" sort desc, id desc");
		return this.systemMenuMapper.selectByExample(example);
	}
}