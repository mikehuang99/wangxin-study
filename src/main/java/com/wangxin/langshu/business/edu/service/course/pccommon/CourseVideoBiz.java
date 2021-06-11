package com.wangxin.langshu.business.edu.service.course.pccommon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wangxin.langshu.business.edu.entity.course.old.CourseVideo;
import com.wangxin.langshu.business.edu.entity.course.old.CourseVideoExample;
import com.wangxin.langshu.business.edu.dao.course.CourseVideoDao;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.ICourseVideoBiz;
import com.wangxin.langshu.business.util.base.Page;

@Service
public class CourseVideoBiz implements ICourseVideoBiz{


	@Autowired
	private CourseVideoDao courseVideoDao;
	
	@Override
	public int save(CourseVideo record) {
		return courseVideoDao.save(record);
	}

	@Override
	public int deleteById(String id) {
		return courseVideoDao.deleteById(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateById(CourseVideo record) {
		return courseVideoDao.updateById(record);
	}

	@Override
	public CourseVideo getById(String id) {
		return courseVideoDao.getById(id);
	}

	@Override
	public Page<CourseVideo> listForPage(int pageCurrent, int pageSize, CourseVideoExample example) {
		return courseVideoDao.listForPage(pageCurrent, pageSize, example);
	}

	@Override
	public CourseVideo getByVideoLocalNumberAndLessonId(String videoLocalNumber, String lessonId) {
		return courseVideoDao.getByVideoLocalNumberAndLessonId(videoLocalNumber, lessonId);
	}

	@Override
	public List<CourseVideo> listByVideoLocalNumber(String videoLocalNumber) {
		return courseVideoDao.listByVideoLocalNumber(videoLocalNumber);
	}

	@Override
	public List<CourseVideo> listByLessonIdAndValidValue(String lessonId, Integer validValue) {
		return courseVideoDao.listByLessonIdAndValidValue(lessonId, validValue);
	}

	@Override
	public CourseVideo getByVideoLocalNumberAndValidValue(String videoLocalNumber, Integer validValue) {
		return courseVideoDao.getByVideoLocalNumberAndValidValue(videoLocalNumber, validValue);
	}

	@Override
	public List<CourseVideo> listByVideoLocalNumberAndNotEqualLessonIdAndValidValue(String videoLocalNumber, String lessonId,
			Integer validValue) {
		return courseVideoDao.listByVideoLocalNumberAndNotEqualLessonIdAndValidValue(videoLocalNumber, lessonId, validValue);
	}

	@Override
	public CourseVideo getByVideoLocalNumber(String videoLocalNumber) {
		return courseVideoDao.getByVideoLocalNumber(videoLocalNumber);
	}

	@Override
	public List<CourseVideo> listByChapterIdAndLessonIdAndValidValue(String chapterId, String lessonId, Integer validValue) {
		return courseVideoDao.listByChapterIdAndLessonIdAndValidValue(chapterId, lessonId, validValue);
	}

}
