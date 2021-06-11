package com.wangxin.langshu.business.edu.dao.user;

import com.wangxin.langshu.business.edu.entity.user.old.Student;
import com.wangxin.langshu.business.edu.entity.user.old.StudentExample;
import com.wangxin.langshu.business.util.base.Page;

public interface StudentDao {
	int save(Student record);

	int deleteById(String id);

	int updateById(Student record);

	Student getById(String id);

	Page<Student> listForPage(int pageCurrent, int pageSize, StudentExample example);

	Student getByUserNo(String userNo);

	int updateByUserNo(Student record);

	/**
	 * 根据手机号码获取用户信息
	 * 
	 * @param mobile
	 * @return
	 * 
	 */
	Student getByMobile(String mobile);

	/**
	 * 获取用户注册量
	 * 
	 * @param date
	 * @return
	 * 
	 */
	Integer sumByCountOrders(String date);

}