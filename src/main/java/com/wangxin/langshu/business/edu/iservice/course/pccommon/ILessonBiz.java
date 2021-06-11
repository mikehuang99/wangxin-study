package com.wangxin.langshu.business.edu.iservice.course.pccommon;

import java.util.List;

import com.wangxin.langshu.business.edu.entity.course.old.Lesson;
import com.wangxin.langshu.business.edu.entity.course.old.LessonExample;
import com.wangxin.langshu.business.util.base.Page;

public interface ILessonBiz {

	int save(Lesson record);

	int deleteById(String id);

	int updateById(Lesson record);

	Lesson getById(String id);

	Page<Lesson> listForPage(int pageCurrent, int pageSize, LessonExample example);

	List<Lesson> listByChapterId(String chapterId);

	/**
	 * 根据章节编号和状态查找可用的课时信息集合
	 * 
	 * 
	 * @param chapterId
	 * @return
	 */
	List<Lesson> listByChapterIdAndValidValue(String chapterId, Integer validValue);

	/**
	 * 根据课时编号查找课时信息集合
	 * 
	 * @param videoLocalNumber
	 * @return
	 * 
	 */
	Lesson getByVideoLocalNumber(String videoLocalNumber);

	/**
	 * 根据视频编号查找课时信息
	 * 
	 * @param videoLocalNumber
	 * @return
	 */
	List<Lesson> listByVideoLocalNumber(String videoLocalNumber);

}
