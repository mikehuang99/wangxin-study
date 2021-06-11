package com.wangxin.langshu.business.edu.dao.course;

import com.wangxin.langshu.business.edu.entity.course.old.CourseApply;
import com.wangxin.langshu.business.edu.entity.course.old.CourseApplyExample;
import com.wangxin.langshu.business.util.base.Page;

public interface CourseApplyDao {
	int save(CourseApply record);

	int deleteById(String id);

	int updateById(CourseApply record);

	CourseApply getById(String id);

	Page<CourseApply> listForPage(int pageCurrent, int pageSize, CourseApplyExample example);

	/**
	 * 根据课程编号更新课程审核状态
	 * 
	 * @param applyStatus
	 * @param courseId
	 * @return
	 * 
	 */
	int updateApplyStatusBycourseId(Integer applyStatus, String courseId);
}