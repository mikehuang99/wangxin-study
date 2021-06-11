package com.wangxin.langshu.business.edu.dao.system;

import java.util.List;

import com.wangxin.langshu.business.edu.entity.system.old.NavigationBar;
import com.wangxin.langshu.business.edu.entity.system.old.NavigationBarExample;
import com.wangxin.langshu.business.util.base.Page;

public interface NavigationBarDao {
	int save(NavigationBar record);

	int deleteById(String id);

	int updateById(NavigationBar record);

	NavigationBar getById(String id);

	Page<NavigationBar> listForPage(int pageCurrent, int pageSize, NavigationBarExample example);

	/**
	 * 根据导航链接查找信息
	 * 
	 * @param navigationUrl
	 * @return
	 * 
	 */
	NavigationBar getByNavigationUrl(String navigationUrl);

	/**
	 * 查找可用的头部导航
	 * 
	 * @param validValue
	 * @return
	 * 
	 */
	List<NavigationBar> getByValidValue(Integer validValue);
}