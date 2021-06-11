package com.wangxin.langshu.business.edu.dao.course;

import java.util.List;

import com.wangxin.langshu.business.edu.entity.course.old.Course;
import com.wangxin.langshu.business.edu.entity.course.old.CourseExample;
import com.wangxin.langshu.business.util.base.Page;

public interface CourseDao {
    int save(Course record);

    int deleteById(String id);

    int updateById(Course record);

    Course getById(String id);

    Page<Course> listForPage(int pageCurrent, int pageSize, CourseExample example);

    /**
	 * 根据课程ID获取课程信息列表
	 * 
	 * @param courseId
	 * @return
	 */
	List<Course> listBySubjectId(String courseId);

	/**
	 * 根据课程编号和状态获取课程信息
	 * 
	 * @param courseId
	 * @param ValidValue
	 * @return
	 */
	Course getByCourseIdAndValidValue(String courseId, Integer ValidValue);

	/**
	 * 根据课程名称获取课程信息
	 * 
	 * @param courseName
	 * @return
	 */
	Course getByCourseName(String courseName);

	/**
	 * 根据二级分类ID和状态获取课程信息
	 * 
	 * @param subjectId2
	 * @param validValue
	 * @return
	 */
	List<Course> listBysubjectId2AndValidValue(String subjectId2, Integer validValue);
}