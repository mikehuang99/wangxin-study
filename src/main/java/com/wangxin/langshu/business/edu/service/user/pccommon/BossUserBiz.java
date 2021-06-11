package com.wangxin.langshu.business.edu.service.user.pccommon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangxin.langshu.business.edu.dao.user.UserDao;
import com.wangxin.langshu.business.edu.entity.user.old.User;
import com.wangxin.langshu.business.edu.entity.user.old.UserExample;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.qo.UserQO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.UserVO;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.BeanUtil;

/**
 * 用户基本信息
 *
 *  
 */
@Component
public class BossUserBiz  {

	@Autowired
	private UserDao dao;

	public Page<UserVO> listForPage(UserQO qo) {
		UserExample example = new UserExample();
		example.setOrderByClause(" id desc ");
		Page<User> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, UserVO.class);
	}

	public int save(UserQO qo) {
		User record = BeanUtil.copyProperties(qo, User.class);
		return dao.save(record);
	}

	public int deleteById(String id) {
		return dao.deleteById(id);
	}

	public UserVO getById(String id) {
		User record = dao.getById(id);
		return BeanUtil.copyProperties(record, UserVO.class);
	}

	public int updateById(UserQO qo) {
		User record = BeanUtil.copyProperties(qo, User.class);
		return dao.updateById(record);
	}

	public UserVO getByMobile(String mobile) {
		User record = dao.getByMobile(mobile);
		return BeanUtil.copyProperties(record, UserVO.class);
	}

	public UserVO getByUserNo(String userNo) {
		User record = dao.getByUserNo(userNo);
		return BeanUtil.copyProperties(record, UserVO.class);
	}

}
