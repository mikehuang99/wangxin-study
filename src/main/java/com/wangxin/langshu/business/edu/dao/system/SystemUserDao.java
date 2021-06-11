package com.wangxin.langshu.business.edu.dao.system;

import com.wangxin.langshu.business.edu.entity.system.old.SystemUser;
import com.wangxin.langshu.business.edu.entity.system.old.SystemUserExample;
import com.wangxin.langshu.business.util.base.Page;

public interface SystemUserDao {
	int save(SystemUser record);

	int deleteById(String id);

	int updateById(SystemUser record);

	int updateByExampleSelective(SystemUser record, SystemUserExample example);

	SystemUser getById(String id);

	Page<SystemUser> listForPage(int pageCurrent, int pageSize, SystemUserExample example);

	/**
	 * 根据用户编号获取管理员信息
	 * 
	 * @param userNo
	 * @return
	 */
	SystemUser getByUserNo(String userNo);
}