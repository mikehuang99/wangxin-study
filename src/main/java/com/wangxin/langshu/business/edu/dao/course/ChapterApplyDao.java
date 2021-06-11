package com.wangxin.langshu.business.edu.dao.course;

import java.util.List;

import com.wangxin.langshu.business.edu.entity.course.old.ChapterApply;
import com.wangxin.langshu.business.edu.entity.course.old.ChapterApplyExample;
import com.wangxin.langshu.business.util.base.Page;

public interface ChapterApplyDao {
	int save(ChapterApply record);

	int deleteById(String id);

	int updateById(ChapterApply record);

	ChapterApply getById(String id);

	Page<ChapterApply> listForPage(int pageCurrent, int pageSize, ChapterApplyExample example);

	List<ChapterApply> listByCourseId(String courseId);

	/**
	 * 根据章节编号更新章节排序
	 * 
	 * @param sort
	 * @param chapterId
	 * @return
	 * 
	 */
	int updateSortByChapterId(Integer sort, String chapterId);

	/**
	 * 根据章节编号更新审核状态
	 * 
	 * @param applyStatus
	 * @param chapterId
	 * @return
	 * 
	 */
	int updateApplyStatusByChapterNo(Integer applyStatus, String chapterId);

	/**
	 * 根据课程编号删除章节信息
	 * 
	 * @param courseId
	 * @return int
	 * 
	 */
	int deleteByCourseId(String courseId);

	/**
	 * 根据课程编号查询可用的章节信息集合
	 * 
	 * @param courseId
	 * @return validValue
	 * 
	 */
	List<ChapterApply> listByCourseIdAndValidValue(String courseId, Integer validValue);
}