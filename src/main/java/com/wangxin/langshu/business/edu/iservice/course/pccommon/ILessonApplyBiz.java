package com.wangxin.langshu.business.edu.iservice.course.pccommon;

import java.util.List;

import com.wangxin.langshu.business.edu.entity.course.old.LessonApply;
import com.wangxin.langshu.business.edu.entity.course.old.LessonApplyExample;
import com.wangxin.langshu.business.util.base.Page;

public interface ILessonApplyBiz {

	int save(LessonApply record);

	int deleteById(String id);

	int updateById(LessonApply record);

	LessonApply getById(String id);

	Page<LessonApply> listForPage(int pageCurrent, int pageSize, LessonApplyExample example);

	/**
	 * 根据课程ID查询课时信息
	 *
	 * @param CourseId
	 */
	List<LessonApply> listByCourseId(String CourseId);

	/**
	 * 根据章节ID修改课时排序
	 *
	 * @param sort
	 * @param lessonId
	 */
	int updateSortByLessonId(int sort, String lessonId);

	/**
	 * 根据课时ID、是否存在视频(1存在，0否)查询课时审核信息
	 *
	 * @param isVideo
	 * @param lessonId
	 */
	List<LessonApply> listByIsVideoAndLessonId(String isVideo, String lessonId);

	/**
	 * 根据课时ID、是否存在文档(1存在，0否)查询课时审核信息
	 *
	 * @param isDoc
	 * @param lessonId
	 */
	List<LessonApply> listByIsDocAndLessonId(Integer isDoc, String lessonId);

	/**
	 * 根据视频编号查询课时审核信息
	 *
	 * @param videoLocalNumber
	 * 
	 */
	LessonApply getByVideoLocalNumber(String videoLocalNumber);

	/**
	 * 根据课程编号删除课时信息
	 * 
	 * @param courseId
	 * @return int
	 * 
	 */
	int deleteByCourseId(String courseId);

	/**
	 * 根据章节ID查询可用的课时信息集合
	 *
	 * @param chapterId
	 * @return validValue
	 * 
	 */
	List<LessonApply> listByChapterIdAndValidValue(String chapterId, Integer validValue);

	/**
	 * 根据视频编号获取课时信息
	 * 
	 * @param videoLocalNumber
	 * 
	 */
	List<LessonApply> listByVideoLocalNumber(String videoLocalNumber);
}
