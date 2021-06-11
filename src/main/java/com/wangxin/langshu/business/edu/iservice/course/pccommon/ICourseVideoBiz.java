package com.wangxin.langshu.business.edu.iservice.course.pccommon;

import java.util.List;

import com.wangxin.langshu.business.edu.entity.course.old.CourseVideo;
import com.wangxin.langshu.business.edu.entity.course.old.CourseVideoExample;
import com.wangxin.langshu.business.util.base.Page;

public interface ICourseVideoBiz {

	int save(CourseVideo record);

	int deleteById(String id);

	int updateById(CourseVideo record);

	CourseVideo getById(String id);

	Page<CourseVideo> listForPage(int pageCurrent, int pageSize, CourseVideoExample example);

	/**
	 * 根据视频编号、课时ID查询课程视频信息
	 * 
	 * @param videoLocalNumber
	 * @param lessonId
	 * @return
	 * 
	 */
	CourseVideo getByVideoLocalNumberAndLessonId(String videoLocalNumber, String lessonId);

	/**
	 * 根据视频编号查询课程视频信息集合
	 * 
	 * @param videoLocalNumber
	 * @return
	 * 
	 */
	List<CourseVideo> listByVideoLocalNumber(String videoLocalNumber);

	/**
	 * 根据课时ID查询可用的课程视频信息集合
	 * 
	 * @param lessonId
	 * @param validValue
	 * @return
	 * 
	 */
	List<CourseVideo> listByLessonIdAndValidValue(String lessonId, Integer validValue);

	/**
	 * 根据视频编号查询可用的课程视频信息
	 * 
	 * @param videoLocalNumber
	 * @param validValue
	 * @return
	 * 
	 */
	CourseVideo getByVideoLocalNumberAndValidValue(String videoLocalNumber, Integer validValue);

	/**
	 * 根据视频编号、课时ID查询可用的课程视频信息集合
	 * 
	 * @param videoLocalNumber
	 * @param lessonId
	 * @param validValue
	 * @return
	 * 
	 */
	List<CourseVideo> listByVideoLocalNumberAndNotEqualLessonIdAndValidValue(String videoLocalNumber, String lessonId, Integer validValue);

	/**
	 * 根据视频编号课程视频信息
	 * 
	 * @param videoLocalNumber
	 * @return
	 * 
	 */
	CourseVideo getByVideoLocalNumber(String videoLocalNumber);

	/**
	 * 根据章节ID、课时ID查询可用的课程视频信息集合
	 * 
	 * @param chapterId
	 * @param lessonId
	 * @param validValue
	 * @return
	 * 
	 */
	List<CourseVideo> listByChapterIdAndLessonIdAndValidValue(String chapterId, String lessonId, Integer validValue);
	
}
