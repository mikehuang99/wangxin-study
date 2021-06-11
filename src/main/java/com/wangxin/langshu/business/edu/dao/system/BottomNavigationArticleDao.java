package com.wangxin.langshu.business.edu.dao.system;

import com.wangxin.langshu.business.edu.entity.system.old.BottomNavigationArticle;
import com.wangxin.langshu.business.edu.entity.system.old.BottomNavigationArticleExample;
import com.wangxin.langshu.business.util.base.Page;

public interface BottomNavigationArticleDao {
	int save(BottomNavigationArticle record);

	int deleteById(String id);

	int updateById(BottomNavigationArticle record);

	BottomNavigationArticle getById(String id);

	Page<BottomNavigationArticle> listForPage(int pageCurrent, int pageSize, BottomNavigationArticleExample example);

	/**
	 * 根据导航ID查找站点导航文章
	 * 
	 * @param navigationId
	 * @return
	 * 
	 */
	BottomNavigationArticle getByNavigationId(String id);

	/**
	 * 根据导航ID和状态信息查找站点导航文章
	 * 
	 * @param navigationId
	 * @param validValue
	 * @return
	 * 
	 */
	BottomNavigationArticle getByNavigationIdAndValidValue(String navigationId, Integer validValue);
}