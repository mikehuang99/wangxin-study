package com.wangxin.langshu.business.edu.dao.course.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.entity.course.old.Chapter;
import com.wangxin.langshu.business.edu.entity.course.old.ChapterExample;
import com.wangxin.langshu.business.edu.entity.course.old.ChapterExample.Criteria;
import com.wangxin.langshu.business.edu.dao.course.ChapterDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.ChapterMapper;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;

@Repository
public class ChapterDaoImpl implements ChapterDao {
	@Autowired
	private ChapterMapper chapterMapper;

	@Override
	public int save(Chapter record) {
		return this.chapterMapper.insertSelective(record);
	}

	@Override
	public int deleteById(String id) {
		return this.chapterMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(Chapter record) {
		record.setCreateTime(null);
		record.setModifiedTime(null);
		return this.chapterMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Chapter getById(String id) {
		return this.chapterMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<Chapter> listForPage(int pageCurrent, int pageSize, ChapterExample example) {
		int count = this.chapterMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<Chapter>(count, totalPage, pageCurrent, pageSize, this.chapterMapper.selectByExample(example));
	}

	/**
	 * 根据章节编号、状态ID查找章节信息
	 */
	@Override
	public List<Chapter> listByCourseIdAndValidValue(String courseId, Integer validValue) {
		ChapterExample example = new ChapterExample();
		Criteria c = example.createCriteria();
		c.andCourseIdEqualTo(courseId);
		c.andValidValueEqualTo(validValue);
		example.setOrderByClause("sort asc,id desc");
		return this.chapterMapper.selectByExample(example);
	}
}