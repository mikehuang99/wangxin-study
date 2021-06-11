package com.wangxin.langshu.business.edu.dao.user;

import java.util.List;

import com.wangxin.langshu.business.edu.entity.user.old.Teacher;
import com.wangxin.langshu.business.edu.entity.user.old.TeacherExample;
import com.wangxin.langshu.business.util.base.Page;

public interface TeacherDao {
	int save(Teacher record);

	int deleteById(String id);

	int updateById(Teacher record);

	Teacher getById(String id);

	Page<Teacher> listForPage(int pageCurrent, int pageSize, TeacherExample example);

	/**
	 * 根据教师编号查找教师信息
	 * 
	 * @param teacherUserNo
	 * @return
	 * 
	 */
	Teacher getByTeacherUserNo(String teacherUserNo);

	/**
	 * 根据教师名称查找教师信息
	 * 
	 * @param TeacherName
	 * @return
	 * 
	 */
	List<Teacher> getByTeacherName(String TeacherName);

	List<Teacher> listByValidValue(Integer validValue);

	/**
	 * 根据教师编号、状态查找教师信息
	 * 
	 * @param teacherUserNo
	 * @param validValue
	 * @return
	 * 
	 */
	Teacher getByTeacherUserNoAndValidValue(String teacherUserNo, Integer validValue);
}