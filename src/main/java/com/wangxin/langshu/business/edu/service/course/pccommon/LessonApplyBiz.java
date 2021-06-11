package com.wangxin.langshu.business.edu.service.course.pccommon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangxin.langshu.business.edu.entity.course.old.LessonApply;
import com.wangxin.langshu.business.edu.entity.course.old.LessonApplyExample;
import com.wangxin.langshu.business.edu.dao.course.LessonApplyDao;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.ILessonApplyBiz;
import com.wangxin.langshu.business.util.base.Page;

@Service
public class LessonApplyBiz implements ILessonApplyBiz{

	@Autowired
	private LessonApplyDao lessonApplyDao;
	
	@Override
	public int save(LessonApply record) {
		return lessonApplyDao.save(record);
	}

	@Override
	public int deleteById(String id) {
		return lessonApplyDao.deleteById(id);
	}

	@Override
	public int updateById(LessonApply record) {
		return lessonApplyDao.updateById(record);
	}

	@Override
	public LessonApply getById(String id) {
		return lessonApplyDao.getById(id);
	}

	@Override
	public Page<LessonApply> listForPage(int pageCurrent, int pageSize,
			LessonApplyExample example) {
		return lessonApplyDao.listForPage(pageCurrent, pageSize, example);
	}

	@Override
	public List<LessonApply> listByCourseId(String CourseId) {
		return lessonApplyDao.listByCourseId(CourseId);
	}

	@Override
	public int updateSortByLessonId(int sort, String lessonId) {
		return lessonApplyDao.updateSortByLessonId(sort, lessonId);
	}

	@Override
	public List<LessonApply> listByIsVideoAndLessonId(String isVideo, String lessonId) {
		return lessonApplyDao.listByIsVideoAndLessonId(isVideo, lessonId);
	}

	@Override
	public List<LessonApply> listByIsDocAndLessonId(Integer isDoc, String lessonId) {
		return lessonApplyDao.listByIsDocAndLessonId(isDoc, lessonId);
	}

	@Override
	public LessonApply getByVideoLocalNumber(String videoLocalNumber) {
		return lessonApplyDao.getByVideoLocalNumber(videoLocalNumber);
	}

	@Override
	public int deleteByCourseId(String courseId) {
		return lessonApplyDao.deleteByCourseId(courseId);
	}

	@Override
	public List<LessonApply> listByChapterIdAndValidValue(String chapterId, Integer validValue) {
		return lessonApplyDao.listByChapterIdAndValidValue(chapterId, validValue);
	}

	@Override
	public List<LessonApply> listByVideoLocalNumber(String videoLocalNumber) {
		return lessonApplyDao.listByVideoLocalNumber(videoLocalNumber);
	}

}
