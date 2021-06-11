package com.wangxin.langshu.business.edu.dao.system;

import java.util.List;

import com.wangxin.langshu.business.edu.entity.system.old.SystemMenuRole;
import com.wangxin.langshu.business.edu.entity.system.old.SystemMenuRoleExample;
import com.wangxin.langshu.business.util.base.Page;

public interface SystemMenuRoleDao {
	int save(SystemMenuRole record);

	int deleteById(String id);

	int updateById(SystemMenuRole record);

	int updateByExampleSelective(SystemMenuRole record, SystemMenuRoleExample example);

	SystemMenuRole getById(String id);

	Page<SystemMenuRole> listForPage(int pageCurrent, int pageSize, SystemMenuRoleExample example);

	/**
	 * 根据角色ID列出角色下所有的菜单
	 * 
	 * @param roleId
	 * @return
	 */
	List<SystemMenuRole> listByRoleId(String roleId);

	/**
	 * 删除角色下的菜单
	 * 
	 * @param roleId
	 */
	int deleteByRoleId(String roleId);
}