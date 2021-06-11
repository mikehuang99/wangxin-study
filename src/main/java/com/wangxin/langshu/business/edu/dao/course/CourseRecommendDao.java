package com.wangxin.langshu.business.edu.dao.course;

import java.util.List;

import com.wangxin.langshu.business.edu.entity.course.old.CourseRecommend;
import com.wangxin.langshu.business.edu.entity.course.old.CourseRecommendExample;
import com.wangxin.langshu.business.util.base.Page;

public interface CourseRecommendDao {
	int save(CourseRecommend record);

	int deleteById(String id);

	int updateById(CourseRecommend record);

	CourseRecommend getById(String id);

	Page<CourseRecommend> listForPage(int pageCurrent, int pageSize, CourseRecommendExample example);

	/**
	 * 根据分类ID、状态获取推荐课程信息
	 * 
	 * @param subjectId
	 * @param ValidValue
	 * 
	 */
	List<CourseRecommend> listBySubjectIdAndValidValue(String subjectId, Integer ValidValue);

	/**
	 * 根据分类ID、课程ID获取推荐课程信息
	 * 
	 * @param subjectId
	 * @param courseId
	 * @return
	 */
	CourseRecommend getBySubjectIdAndCourseId(String subjectId, String courseId);

}