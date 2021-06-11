package com.wangxin.langshu.business.edu.dao.course.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.entity.course.old.CourseVideo;
import com.wangxin.langshu.business.edu.entity.course.old.CourseVideoExample;
import com.wangxin.langshu.business.edu.entity.course.old.CourseVideoExample.Criteria;
import com.wangxin.langshu.business.edu.dao.course.CourseVideoDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.CourseVideoMapper;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class CourseVideoDaoImpl implements CourseVideoDao {
	@Autowired
	private CourseVideoMapper courseVideoMapper;

	@Override
	public int save(CourseVideo record) {
		record.setId(IdWorker.getId());
		return this.courseVideoMapper.insertSelective(record);
	}

	@Override
	public int deleteById(String id) {
		return this.courseVideoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(CourseVideo record) {
		return this.courseVideoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public CourseVideo getById(String id) {
		return this.courseVideoMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<CourseVideo> listForPage(int pageCurrent, int pageSize, CourseVideoExample example) {
		int count = this.courseVideoMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<CourseVideo>(count, totalPage, pageCurrent, pageSize, this.courseVideoMapper.selectByExample(example));
	}

	@Override
	public CourseVideo getByVideoLocalNumberAndLessonId(String videoLocalNumber, String lessonId) {
		CourseVideoExample example = new CourseVideoExample();
		Criteria c = example.createCriteria();
		c.andVideoLocalNumberEqualTo(videoLocalNumber);
		c.andLessonIdEqualTo(lessonId);
		List<CourseVideo> list = this.courseVideoMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<CourseVideo> listByVideoLocalNumber(String videoLocalNumber) {
		CourseVideoExample example = new CourseVideoExample();
		Criteria c = example.createCriteria();
		c.andVideoLocalNumberEqualTo(videoLocalNumber);
		List<CourseVideo> list = this.courseVideoMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list;
	}

	@Override
	public List<CourseVideo> listByLessonIdAndValidValue(String lessonId, Integer validValue) {
		CourseVideoExample example = new CourseVideoExample();
		Criteria c = example.createCriteria();
		c.andLessonIdEqualTo(lessonId);
		c.andValidValueEqualTo(validValue);
		List<CourseVideo> list = this.courseVideoMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list;
	}

	@Override
	public CourseVideo getByVideoLocalNumberAndValidValue(String videoLocalNumber, Integer validValue) {
		CourseVideoExample example = new CourseVideoExample();
		Criteria c = example.createCriteria();
		c.andVideoLocalNumberEqualTo(videoLocalNumber);
		c.andValidValueEqualTo(validValue);
		List<CourseVideo> list = this.courseVideoMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<CourseVideo> listByVideoLocalNumberAndNotEqualLessonIdAndValidValue(String videoLocalNumber, String lessonId, Integer validValue) {
		CourseVideoExample example = new CourseVideoExample();
		Criteria c = example.createCriteria();
		c.andVideoLocalNumberEqualTo(videoLocalNumber);
		c.andLessonIdNotEqualTo(lessonId);
		c.andValidValueEqualTo(validValue);
		List<CourseVideo> list = this.courseVideoMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list;
	}

	@Override
	public CourseVideo getByVideoLocalNumber(String videoLocalNumber) {
		CourseVideoExample example = new CourseVideoExample();
		Criteria c = example.createCriteria();
		c.andVideoLocalNumberEqualTo(videoLocalNumber);
		List<CourseVideo> list = this.courseVideoMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<CourseVideo> listByChapterIdAndLessonIdAndValidValue(String chapterId, String lessonId, Integer validValue) {
		CourseVideoExample example = new CourseVideoExample();
		Criteria c = example.createCriteria();
		c.andChapterIdEqualTo(chapterId);
		c.andLessonIdEqualTo(lessonId);
		c.andValidValueEqualTo(validValue);
		List<CourseVideo> list = this.courseVideoMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list;
	}
}