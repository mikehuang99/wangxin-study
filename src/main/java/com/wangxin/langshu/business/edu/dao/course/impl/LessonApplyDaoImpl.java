package com.wangxin.langshu.business.edu.dao.course.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.entity.course.old.LessonApply;
import com.wangxin.langshu.business.edu.entity.course.old.LessonApplyExample;
import com.wangxin.langshu.business.edu.entity.course.old.LessonApplyExample.Criteria;
import com.wangxin.langshu.business.edu.dao.course.LessonApplyDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.LessonApplyMapper;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class LessonApplyDaoImpl implements LessonApplyDao {
	@Autowired
	private LessonApplyMapper lessonApplyMapper;

	public int save(LessonApply record) {
		record.setId(IdWorker.getId());
		return this.lessonApplyMapper.insertSelective(record);
	}

	public int deleteById(String id) {
		return this.lessonApplyMapper.deleteByPrimaryKey(id);
	}

	public int updateById(LessonApply record) {
		record.setCreateTime(null);
		record.setModifiedTime(null);
		return this.lessonApplyMapper.updateByPrimaryKeySelective(record);
	}

	public LessonApply getById(String id) {
		return this.lessonApplyMapper.selectByPrimaryKey(id);
	}

	public Page<LessonApply> listForPage(int pageCurrent, int pageSize,
			LessonApplyExample example) {
		int count = this.lessonApplyMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<LessonApply>(count, totalPage, pageCurrent, pageSize,
				this.lessonApplyMapper.selectByExample(example));
	}

	@Override
	public List<LessonApply> listByCourseId(String CourseId) {
		LessonApplyExample example = new LessonApplyExample();
		Criteria c = example.createCriteria();
		c.andCourseIdEqualTo(CourseId);
		example.setOrderByClause("sort asc, id asc");
		return this.lessonApplyMapper.selectByExample(example);
	}

	@Override
	public int updateSortByLessonId(int sort, String lessonId) {
		LessonApplyExample example = new LessonApplyExample();
		Criteria c = example.createCriteria();
		c.andIdEqualTo(lessonId);
		LessonApply record = new LessonApply();
		record.setId(lessonId);
		record.setSort(sort);
		record.setCreateTime(null);
		record.setModifiedTime(null);
		return this.lessonApplyMapper.updateByExampleSelective(record, example);
	}

	@Override
	public List<LessonApply> listByIsVideoAndLessonId(String isVideo, String lessonId) {
		LessonApplyExample example = new LessonApplyExample();
		Criteria c = example.createCriteria();
		c.andIsVideoEqualTo(isVideo);
		c.andIdEqualTo(lessonId);
		example.setOrderByClause("sort asc, id asc");
		return this.lessonApplyMapper.selectByExample(example);
	}

	@Override
	public List<LessonApply> listByIsDocAndLessonId(Integer isDoc, String lessonId) {
		LessonApplyExample example = new LessonApplyExample();
		Criteria c = example.createCriteria();
		c.andIsDocEqualTo(isDoc);
		c.andIdEqualTo(lessonId);
		example.setOrderByClause("sort asc, id asc");
		return this.lessonApplyMapper.selectByExample(example);
	}

	@Override
	public LessonApply getByVideoLocalNumber(String videoLocalNumber) {
		LessonApplyExample example = new LessonApplyExample();
		Criteria c = example.createCriteria();
		c.andVideoLocalNumberEqualTo(videoLocalNumber);
		example.setOrderByClause("sort asc, id asc");
		List<LessonApply> list = this.lessonApplyMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public int deleteByCourseId(String courseId) {
		LessonApplyExample example = new LessonApplyExample();
		Criteria c = example.createCriteria();
		c.andCourseIdEqualTo(courseId);
		return this.lessonApplyMapper.deleteByExample(example);
	}

	@Override
	public List<LessonApply> listByChapterIdAndValidValue(String chapterId, Integer validValue) {
		LessonApplyExample example = new LessonApplyExample();
		Criteria c = example.createCriteria();
		c.andChapterIdEqualTo(chapterId);
		c.andValidValueEqualTo(validValue);
		example.setOrderByClause("sort asc, id asc");
		return this.lessonApplyMapper.selectByExample(example);
	}

	@Override
	public List<LessonApply> listByVideoLocalNumber(String videoLocalNumber) {
		LessonApplyExample example = new LessonApplyExample();
		Criteria c = example.createCriteria();
		c.andVideoLocalNumberEqualTo(videoLocalNumber);
		return this.lessonApplyMapper.selectByExample(example);
	}
}