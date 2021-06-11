package com.wangxin.langshu.business.edu.dao.course;

import java.util.List;

import com.wangxin.langshu.business.edu.entity.course.old.CourseSubject;
import com.wangxin.langshu.business.edu.entity.course.old.CourseSubjectExample;
import com.wangxin.langshu.business.util.base.Page;

public interface CourseSubjectDao {
	int save(CourseSubject record);

	int deleteById(String id);

	int updateById(CourseSubject record);

	CourseSubject getById(String id);

	Page<CourseSubject> listForPage(int pageCurrent, int pageSize, CourseSubjectExample example);

	/**
	 * 根据父类编号查找课程分类信息
	 * 
	 * @param parentId
	 * 
	 */
	List<CourseSubject> listByParentId(String parentId);

	/**
	 * 根据层级列出分类信息
	 * 
	 * @param floor
	 * 
	 */
	List<CourseSubject> listByFloor(Integer floor);

	/**
	 * 根据层级、父类ID列出分类信息
	 * 
	 * @param floor
	 * @param parentId
	 * 
	 */
	List<CourseSubject> listByFloorAndSubjectId(Integer floor, String parentId);

	/**
	 * 根据分类类型、层级查询可用状态的课程分类集合
	 * 
	 * @param subjectType
	 * @param floor
	 * @param validValue
	 * 
	 */
	List<CourseSubject> listBySubjectTypeAndFloorAndValidValue(Integer subjectType, Integer floor, Integer validValue);
}