package com.wangxin.langshu.business.edu.dao.course;

import java.util.List;

import com.wangxin.langshu.business.edu.entity.course.old.TopicCourse;
import com.wangxin.langshu.business.edu.entity.course.old.TopicCourseExample;
import com.wangxin.langshu.business.util.base.Page;

public interface TopicCourseDao {
	int save(TopicCourse record);

	int deleteById(String id);

	int updateById(TopicCourse record);

	TopicCourse getById(String id);

	Page<TopicCourse> listForPage(int pageCurrent, int pageSize, TopicCourseExample example);

	/**
	 * 根据专区编号获取可用的专区课程信息集合
	 * 
	 * @param topicId
	 * @param validValue
	 * @return
	 * 
	 */
	List<TopicCourse> listByTopicIdAndValidValue(String topicId, Integer validValue);

	/**
	 * 根据专区编号,获取可以专区课程信息
	 * 
	 * @param topicId
	 * @return
	 */
	List<TopicCourse> listByTopicId(String topicId);

	TopicCourse getTopicIdAndCourseId(String courseId, String topicId);

}