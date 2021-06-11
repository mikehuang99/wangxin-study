package com.wangxin.langshu.business.edu.dao.user;


import com.wangxin.langshu.business.edu.entity.user.old.TeacherApply;
import com.wangxin.langshu.business.edu.entity.user.old.TeacherApplyExample;
import com.wangxin.langshu.business.util.base.Page;

public interface TeacherApplyDao {
	int save(TeacherApply record);

	int deleteById(String id);

	int updateById(TeacherApply record);

	TeacherApply getById(String id);

	Page<TeacherApply> listForPage(int pageCurrent, int pageSize, TeacherApplyExample example);

    /**
	 * 根据教师编号查询教师账户信息
	 * 
	 * @param orgNo
	 * @param teacherUserNo
	 * @return
	 * 
	 */
	TeacherApply getByTeacherUserNo(String teacherUserNo);

}