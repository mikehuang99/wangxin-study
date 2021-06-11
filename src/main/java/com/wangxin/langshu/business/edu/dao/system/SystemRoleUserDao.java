package com.wangxin.langshu.business.edu.dao.system;

import java.util.List;

import com.wangxin.langshu.business.edu.entity.system.old.SystemRoleUser;
import com.wangxin.langshu.business.edu.entity.system.old.SystemRoleUserExample;
import com.wangxin.langshu.business.util.base.Page;

public interface SystemRoleUserDao {
	int save(SystemRoleUser record);

	int deleteById(String id);

	int updateById(SystemRoleUser record);

	int updateByExampleSelective(SystemRoleUser record, SystemRoleUserExample example);

	SystemRoleUser getById(String id);

	Page<SystemRoleUser> listForPage(int pageCurrent, int pageSize, SystemRoleUserExample example);

	/**
	 * 删除用户所有角色
	 * 
	 * @param userId
	 * @return
	 */
	int deleteByUserId(String userId);

	/**
	 * 列出用户所有角色
	 * 
	 * @param userId
	 * @return
	 */
	List<SystemRoleUser> listByUserId(String userId);
}