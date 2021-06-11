package com.wangxin.langshu.business.edu.dao.course.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.entity.course.old.Lesson;
import com.wangxin.langshu.business.edu.entity.course.old.LessonExample;
import com.wangxin.langshu.business.edu.entity.course.old.LessonExample.Criteria;
import com.wangxin.langshu.business.edu.dao.course.LessonDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.LessonMapper;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;

@Repository
public class LessonDaoImpl implements LessonDao {
	@Autowired
	private LessonMapper lessonMapper;

	@Override
	public int save(Lesson record) {
		return this.lessonMapper.insertSelective(record);
	}

	@Override
	public int deleteById(String id) {
		return this.lessonMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(Lesson record) {
		record.setCreateTime(null);
		record.setModifiedTime(null);
		return this.lessonMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Lesson getById(String id) {
		return this.lessonMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<Lesson> listForPage(int pageCurrent, int pageSize, LessonExample example) {
		int count = this.lessonMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<Lesson>(count, totalPage, pageCurrent, pageSize,
				this.lessonMapper.selectByExample(example));
	}

	@Override
	public List<Lesson> listByChapterId(String chapterId) {
		LessonExample example = new LessonExample();
		example.createCriteria().andChapterIdEqualTo(chapterId);
		return this.lessonMapper.selectByExample(example);
	}

	@Override
	public List<Lesson> listByChapterIdAndValidValue(String chapterId, Integer validValue) {
		LessonExample example = new LessonExample();
		Criteria c = example.createCriteria();
		c.andChapterIdEqualTo(chapterId);
		c.andValidValueEqualTo(validValue);
		example.setOrderByClause(" sort asc ,id asc");
		return this.lessonMapper.selectByExample(example);
	}

	@Override
	public Lesson getByVideoLocalNumber(String videoLocalNumber) {
		LessonExample example = new LessonExample();
		Criteria c = example.createCriteria();
		c.andVideoLocalNumberEqualTo(videoLocalNumber);
		example.setOrderByClause(" sort asc ,id asc");
		List<Lesson> list = this.lessonMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<Lesson> listByVideoLocalNumber(String videoLocalNumber) {
		LessonExample example = new LessonExample();
		Criteria c = example.createCriteria();
		c.andVideoLocalNumberEqualTo(videoLocalNumber);
		return this.lessonMapper.selectByExample(example);
	}
}