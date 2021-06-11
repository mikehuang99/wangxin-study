package com.wangxin.langshu.business.edu.dao.course;

import java.util.List;

import com.wangxin.langshu.business.edu.entity.course.old.Chapter;
import com.wangxin.langshu.business.edu.entity.course.old.ChapterExample;
import com.wangxin.langshu.business.util.base.Page;

public interface ChapterDao {
    int save(Chapter record);

    int deleteById(String id);

    int updateById(Chapter record);

    Chapter getById(String id);

    Page<Chapter> listForPage(int pageCurrent, int pageSize, ChapterExample example);

	/**
	 * 根据章节编号、状态ID查找章节信息
	 */
	List<Chapter> listByCourseIdAndValidValue(String courseId, Integer validValue);
}