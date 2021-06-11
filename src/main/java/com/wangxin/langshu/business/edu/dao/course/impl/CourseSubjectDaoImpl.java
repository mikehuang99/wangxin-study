package com.wangxin.langshu.business.edu.dao.course.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.entity.course.old.CourseSubject;
import com.wangxin.langshu.business.edu.entity.course.old.CourseSubjectExample;
import com.wangxin.langshu.business.edu.entity.course.old.CourseSubjectExample.Criteria;
import com.wangxin.langshu.business.edu.dao.course.CourseSubjectDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.CourseSubjectMapper;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class CourseSubjectDaoImpl implements CourseSubjectDao {
	@Autowired
	private CourseSubjectMapper courseSubjectMapper;

	@Override
	public int save(CourseSubject record) {
		record.setId(IdWorker.getId());
		return this.courseSubjectMapper.insertSelective(record);
	}

	@Override
	public int deleteById(String id) {
		return this.courseSubjectMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(CourseSubject record) {
		return this.courseSubjectMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public CourseSubject getById(String id) {
		return this.courseSubjectMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<CourseSubject> listForPage(int pageCurrent, int pageSize, CourseSubjectExample example) {
		int count = this.courseSubjectMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<CourseSubject>(count, totalPage, pageCurrent, pageSize, this.courseSubjectMapper.selectByExample(example));
	}

	@Override
	public List<CourseSubject> listByParentId(String parentId) {
		CourseSubjectExample example = new CourseSubjectExample();
		Criteria c = example.createCriteria();
		c.andParentIdEqualTo(parentId);
		example.setOrderByClause("valid_value desc, sort desc, id desc");
		return this.courseSubjectMapper.selectByExample(example);
	}

	@Override
	public List<CourseSubject> listByFloor(Integer floor) {
		CourseSubjectExample example = new CourseSubjectExample();
		Criteria c = example.createCriteria();
		c.andFloorEqualTo(floor);
		example.setOrderByClause("id desc");
		return this.courseSubjectMapper.selectByExample(example);
	}

	@Override
	public List<CourseSubject> listByFloorAndSubjectId(Integer floor, String parentId) {
		CourseSubjectExample example = new CourseSubjectExample();
		Criteria c = example.createCriteria();
		c.andFloorEqualTo(floor);
		c.andParentIdEqualTo(parentId);
		example.setOrderByClause("id desc");
		return this.courseSubjectMapper.selectByExample(example);
	}

	@Override
	public List<CourseSubject> listBySubjectTypeAndFloorAndValidValue(Integer subjectType, Integer floor, Integer validValue) {
		CourseSubjectExample example = new CourseSubjectExample();
		Criteria c = example.createCriteria();
		c.andSubjectTypeEqualTo(subjectType);
		c.andFloorEqualTo(floor);
		c.andValidValueEqualTo(validValue);
		example.setOrderByClause(" valid_value desc, sort desc, id desc ");
		return this.courseSubjectMapper.selectByExample(example);
	}

}