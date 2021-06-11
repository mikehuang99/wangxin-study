package com.wangxin.langshu.business.edu.service.course.pccommon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangxin.langshu.business.edu.entity.course.old.Lesson;
import com.wangxin.langshu.business.edu.entity.course.old.LessonExample;
import com.wangxin.langshu.business.edu.dao.course.LessonDao;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.ILessonBiz;
import com.wangxin.langshu.business.util.base.Page;

@Service
public class LessonBiz implements ILessonBiz{

	@Autowired
	private LessonDao lessonDao;
	
	@Override
	public int save(Lesson record) {
		return lessonDao.save(record);
	}

	@Override
	public int deleteById(String id) {
		return lessonDao.deleteById(id);
	}

	@Override
	public int updateById(Lesson record) {
		return lessonDao.updateById(record);
	}

	@Override
	public Lesson getById(String id) {
		return lessonDao.getById(id);
	}

	@Override
	public Page<Lesson> listForPage(int pageCurrent, int pageSize, LessonExample example) {
		return lessonDao.listForPage(pageCurrent, pageSize, example);
	}

	@Override
	public List<Lesson> listByChapterId(String chapterId) {
		return lessonDao.listByChapterId(chapterId);
	}

	@Override
	public List<Lesson> listByChapterIdAndValidValue(String chapterId, Integer validValue) {
		return lessonDao.listByChapterIdAndValidValue(chapterId, validValue);
	}

	@Override
	public Lesson getByVideoLocalNumber(String videoLocalNumber) {
		return lessonDao.getByVideoLocalNumber(videoLocalNumber);
	}

	@Override
	public List<Lesson> listByVideoLocalNumber(String videoLocalNumber) {
		return lessonDao.listByVideoLocalNumber(videoLocalNumber);
	}

}
