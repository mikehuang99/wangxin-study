package com.wangxin.langshu.business.edu.dao.system;

import java.util.List;

import com.wangxin.langshu.business.edu.entity.system.old.BottomNavigation;
import com.wangxin.langshu.business.edu.entity.system.old.BottomNavigationExample;
import com.wangxin.langshu.business.util.base.Page;

public interface BottomNavigationDao {
	int save(BottomNavigation record);

	int deleteById(String id);

	int updateById(BottomNavigation record);

	BottomNavigation getById(String id);

	Page<BottomNavigation> listForPage(int pageCurrent, int pageSize, BottomNavigationExample example);

	/**
	 * 根据父类ID查找站点导航信息
	 * 
	 * @param parentId
	 * @return
	 * 
	 */
	List<BottomNavigation> listByParentId(String id);

	/**
	 * 根据状态和父ID信息查找站点导航信息
	 * 
	 * @param validValue
	 * @param parentId
	 * 
	 */
	List<BottomNavigation> listByValidValueAndParentId(Integer validValue, String parentId);
}