package com.wangxin.langshu.business.edu.dao.system;

import java.util.List;

import com.wangxin.langshu.business.edu.entity.system.old.FriendLink;
import com.wangxin.langshu.business.edu.entity.system.old.FriendLinkExample;
import com.wangxin.langshu.business.util.base.Page;

public interface FriendLinkDao {
	int save(FriendLink record);

	int deleteById(String id);

	int updateById(FriendLink record);

	FriendLink getById(String id);

	Page<FriendLink> listForPage(int pageCurrent, int pageSize, FriendLinkExample example);

	/**
	 * 根据状态查询友情链接集合
	 * 
	 * @param validValue
	 * @return
	 * 
	 */
	List<FriendLink> listByValidValue(Integer validValue);
}