package com.wangxin.langshu.business.edu.dao.system;

import com.wangxin.langshu.business.edu.entity.system.old.Website;
import com.wangxin.langshu.business.edu.entity.system.old.WebsiteExample;
import com.wangxin.langshu.business.util.base.Page;

public interface WebsiteDao {
	int save(Website record);

	int deleteById(String id);

	int updateById(Website record);

	Website getById(String id);

	Page<Website> listForPage(int pageCurrent, int pageSize, WebsiteExample example);

	/**
	 * 获取站点信息
	 *
	 * 
	 */
	Website getWebsite();

	/**
	 * 根据状态信息查找站点信息
	 * 
	 * @param validValue
	 * @return
	 * 
	 */
	Website getByValidValue(Integer validValue);
}