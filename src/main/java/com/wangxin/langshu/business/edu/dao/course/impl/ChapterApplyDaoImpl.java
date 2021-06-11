package com.wangxin.langshu.business.edu.dao.course.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.entity.course.old.ChapterApply;
import com.wangxin.langshu.business.edu.entity.course.old.ChapterApplyExample;
import com.wangxin.langshu.business.edu.entity.course.old.ChapterApplyExample.Criteria;
import com.wangxin.langshu.business.edu.dao.course.ChapterApplyDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.ChapterApplyMapper;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class ChapterApplyDaoImpl implements ChapterApplyDao {
	@Autowired
	private ChapterApplyMapper chapterApplyMapper;

	public int save(ChapterApply record) {
		record.setId(IdWorker.getId());
		return this.chapterApplyMapper.insertSelective(record);
	}

	public int deleteById(String id) {
		return this.chapterApplyMapper.deleteByPrimaryKey(id);
	}

	public int updateById(ChapterApply record) {
		record.setCreateTime(null);
		record.setModifiedTime(null);
		return this.chapterApplyMapper.updateByPrimaryKeySelective(record);
	}

	public ChapterApply getById(String id) {
		return this.chapterApplyMapper.selectByPrimaryKey(id);
	}

	public Page<ChapterApply> listForPage(int pageCurrent, int pageSize, ChapterApplyExample example) {
		int count = this.chapterApplyMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<ChapterApply>(count, totalPage, pageCurrent, pageSize, this.chapterApplyMapper.selectByExample(example));
	}

	@Override
	public List<ChapterApply> listByCourseId(String courseId) {
		ChapterApplyExample example = new ChapterApplyExample();
		Criteria c = example.createCriteria();
		c.andCourseIdEqualTo(courseId);
		example.setOrderByClause("sort asc, id asc");
		return this.chapterApplyMapper.selectByExample(example);
	}

	@Override
	public int updateSortByChapterId(Integer sort, String chapterId) {
		ChapterApplyExample example = new ChapterApplyExample();
		Criteria c = example.createCriteria();
		c.andIdEqualTo(chapterId);
		ChapterApply record = new ChapterApply();
		record.setId(chapterId);
		record.setSort(sort);
		record.setCreateTime(null);
		record.setModifiedTime(null);
		return this.chapterApplyMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateApplyStatusByChapterNo(Integer applyStatus, String chapterId) {
		ChapterApplyExample example = new ChapterApplyExample();
		Criteria c = example.createCriteria();
		c.andIdEqualTo(chapterId);
		ChapterApply record = new ChapterApply();
		record.setId(chapterId);
		record.setApplyStatus(applyStatus);
		record.setCreateTime(null);
		record.setModifiedTime(null);
		return this.chapterApplyMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int deleteByCourseId(String courseId) {
		ChapterApplyExample example = new ChapterApplyExample();
		Criteria c = example.createCriteria();
		c.andCourseIdEqualTo(courseId);
		return this.chapterApplyMapper.deleteByExample(example);
	}

	@Override
	public List<ChapterApply> listByCourseIdAndValidValue(String courseId, Integer validValue) {
		ChapterApplyExample example = new ChapterApplyExample();
		Criteria c = example.createCriteria();
		c.andCourseIdEqualTo(courseId);
		c.andValidValueEqualTo(validValue);
		example.setOrderByClause("sort asc, id asc");
		return this.chapterApplyMapper.selectByExample(example);
	}

}