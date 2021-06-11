package com.wangxin.langshu.business.edu.dao.system;

import java.util.List;

import com.wangxin.langshu.business.edu.entity.system.old.SystemMenu;
import com.wangxin.langshu.business.edu.entity.system.old.SystemMenuExample;
import com.wangxin.langshu.business.util.base.Page;

public interface SystemMenuDao {
	int save(SystemMenu record);

	int deleteById(String id);

	int updateById(SystemMenu record);

	int updateByExampleSelective(SystemMenu record, SystemMenuExample example);

	SystemMenu getById(String id);

	Page<SystemMenu> listForPage(int pageCurrent, int pageSize, SystemMenuExample example);

	/**
	 * 根据父ID获取菜单
	 * 
	 * @param parentId
	 * @return
	 */
	List<SystemMenu> listByParentId(String parentId);

	/**
	 * 根据父ID、如果菜单类型为空获取所有菜单，包含按钮,则获取所有菜单，不包含按钮
	 * 
	 * @param parentId
	 * @param menuType
	 * @return
	 */
	List<SystemMenu> listByParentIdAndNotMenuType(String parentId, Integer menuType);

	/**
	 * 列出所有菜单
	 * 
	 * @return
	 */
	List<SystemMenu> listAll();

	/**
	 * 根据菜单名称列出菜单信息(模糊查询)
	 * 
	 * @param menuName
	 * @return
	 */
	List<SystemMenu> listByMenuName(String menuName);
}