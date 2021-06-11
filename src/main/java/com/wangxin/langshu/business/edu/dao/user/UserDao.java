package com.wangxin.langshu.business.edu.dao.user;

import com.wangxin.langshu.business.edu.entity.user.old.User;
import com.wangxin.langshu.business.edu.entity.user.old.UserExample;
import com.wangxin.langshu.business.util.base.Page;

public interface UserDao {
	int save(User record);

	int deleteById(String id);

	int updateById(User record);

	User getById(String id);

	Page<User> listForPage(int pageCurrent, int pageSize, UserExample example);

	User getByMobile(String mobile);

	User getByUserNo(String userNo);

	
}