package com.wangxin.langshu.business.edu.dao.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.dao.user.UserDao;
import com.wangxin.langshu.business.edu.dao.user.impl.mapper.UserMapper;
import com.wangxin.langshu.business.edu.entity.user.old.User;
import com.wangxin.langshu.business.edu.entity.user.old.UserExample;
import com.wangxin.langshu.business.edu.vo.user.old.common.AbstractBaseJdbc;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class UserDaoImpl extends AbstractBaseJdbc implements UserDao {
	@Autowired
	private UserMapper userMapper;

	@Override
	public int save(User record) {
		record.setId(IdWorker.getId());
		return this.userMapper.insertSelective(record);
	}

	@Override
	public int deleteById(String id) {
		return this.userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(User record) {
		return this.userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public User getById(String id) {
		return this.userMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<User> listForPage(int pageCurrent, int pageSize, UserExample example) {
		int count = this.userMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<User>(count, totalPage, pageCurrent, pageSize, this.userMapper.selectByExample(example));
	}

	@Override
	public User getByMobile(String mobile) {
		UserExample example = new UserExample();
		example.createCriteria().andMobileEqualTo(mobile);
		List<User> list = this.userMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public User getByUserNo(String userNo) {
		UserExample example = new UserExample();
		example.createCriteria().andUserNoEqualTo(userNo);
		List<User> list = this.userMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}


}